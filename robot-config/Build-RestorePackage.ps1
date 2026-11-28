[CmdletBinding()]
param(
    [string]$BackupPath,
    [string]$OutputPath
)

$ErrorActionPreference = 'Stop'
Add-Type -AssemblyName System.IO.Compression.FileSystem

if (-not $BackupPath) {
    $BackupPath = Join-Path $PSScriptRoot 'backups\11-28-2026-10.37.40.813.revbkp.zip'
}
if (-not $OutputPath) {
    $OutputPath = Join-Path $PSScriptRoot 'current.revbkp.zip'
}
$backupFile = Get-Item -LiteralPath $BackupPath
$configurationFile = Get-Item -LiteralPath (Join-Path $PSScriptRoot 'active\driving.xml')
$outputFile = $ExecutionContext.SessionState.Path.GetUnresolvedProviderPathFromPSPath($OutputPath)
if (-not $outputFile.EndsWith('.revbkp.zip', [StringComparison]::OrdinalIgnoreCase)) {
    throw 'OutputPath must end with .revbkp.zip.'
}
if ($outputFile -eq $backupFile.FullName) {
    throw 'OutputPath must not overwrite the source backup.'
}

$configurationBytes = [System.IO.File]::ReadAllBytes($configurationFile.FullName)
$configurationStream = New-Object System.IO.MemoryStream(,$configurationBytes)
try {
    $configuration = New-Object System.Xml.XmlDocument
    $configuration.XmlResolver = $null
    $configuration.Load($configurationStream)
    if ($configuration.DocumentElement.Name -cne 'Robot' -or
        $configuration.DocumentElement.GetAttribute('type') -cne 'FirstInspires-FTC') {
        throw 'active\driving.xml must be an FTC Robot configuration.'
    }
} finally {
    $configurationStream.Dispose()
}

$source = [System.IO.Compression.ZipFile]::OpenRead($backupFile.FullName)
$temporaryFile = Join-Path ([System.IO.Path]::GetDirectoryName($outputFile)) (
    '.restore-' + [Guid]::NewGuid().ToString('N') + '.zip'
)
try {
    $duplicateEntries = @($source.Entries | Group-Object FullName | Where-Object Count -gt 1)
    if ($duplicateEntries.Count -gt 0) {
        throw 'The source backup contains duplicate archive entries.'
    }
    $manifests = @($source.Entries | Where-Object FullName -clike '*Manifest.json')
    if ($manifests.Count -ne 1 -or $null -eq $source.GetEntry('driving.xml')) {
        throw 'The source backup must contain one REV manifest and driving.xml at the archive root.'
    }

    $reader = New-Object System.IO.StreamReader($manifests[0].Open())
    try {
        $manifest = $reader.ReadToEnd() | ConvertFrom-Json
    } finally {
        $reader.Dispose()
    }
    $devicePath = '/sdcard/FIRST/driving.xml'
    if (@($manifest.backedUpFiles | Where-Object { $_ -ceq $devicePath }).Count -ne 1) {
        throw 'The REV manifest must list /sdcard/FIRST/driving.xml exactly once.'
    }
    $modifiedTimes = @($manifest.modifiedTimes | Where-Object { $_.fileName -ceq $devicePath })
    if ($modifiedTimes.Count -ne 1) {
        throw 'The REV manifest must contain one modification time for driving.xml.'
    }
    $modifiedTimes[0].time = $configurationFile.LastWriteTimeUtc.ToString(
        "ddd MMM dd HH:mm:ss 'UTC' yyyy", [Globalization.CultureInfo]::InvariantCulture
    ) + "`n"
    $manifestBytes = [System.Text.Encoding]::UTF8.GetBytes(
        ($manifest | ConvertTo-Json -Depth 100 -Compress)
    )

    $destination = [System.IO.Compression.ZipFile]::Open(
        $temporaryFile, [System.IO.Compression.ZipArchiveMode]::Create
    )
    try {
        foreach ($entry in $source.Entries) {
            $newEntry = $destination.CreateEntry($entry.FullName)
            $newEntry.LastWriteTime = $entry.LastWriteTime
            $outputStream = $newEntry.Open()
            try {
                if ($entry.FullName -ceq 'driving.xml') {
                    $outputStream.Write($configurationBytes, 0, $configurationBytes.Length)
                } elseif ($entry.FullName -ceq $manifests[0].FullName) {
                    $outputStream.Write($manifestBytes, 0, $manifestBytes.Length)
                } else {
                    $inputStream = $entry.Open()
                    try {
                        $inputStream.CopyTo($outputStream)
                    } finally {
                        $inputStream.Dispose()
                    }
                }
            } finally {
                $outputStream.Dispose()
            }
        }
    } finally {
        $destination.Dispose()
    }

    # Publish only after the complete archive has been written successfully.
    if ([System.IO.File]::Exists($outputFile)) {
        [System.IO.File]::Replace($temporaryFile, $outputFile, [System.Management.Automation.Language.NullString]::Value)
    } else {
        [System.IO.File]::Move($temporaryFile, $outputFile)
    }
    Write-Output "Created restore package: $outputFile"
} finally {
    $source.Dispose()
    if ([System.IO.File]::Exists($temporaryFile)) {
        [System.IO.File]::Delete($temporaryFile)
    }
}
