param(
    [string]$TypstPath = 'typst'
)

$ErrorActionPreference = 'Stop'
$version = & $TypstPath --version
if ($LASTEXITCODE -ne 0) {
    throw "Unable to read the Typst version (exit $LASTEXITCODE)."
}
if ($version -notmatch '^typst 0\.15\.1(?:\s|$)') {
    throw "Expected Typst 0.15.1, got: $version. See the portfolio README for setup."
}

$outputDirectory = Join-Path $PSScriptRoot 'build'
$inputFile = Join-Path $PSScriptRoot 'main.typ'
$outputFile = Join-Path $outputDirectory 'portfolio.pdf'
New-Item -ItemType Directory -Force -Path $outputDirectory | Out-Null

& $TypstPath compile --root $PSScriptRoot --ignore-system-fonts $inputFile $outputFile
if ($LASTEXITCODE -ne 0) {
    throw "Portfolio build failed (exit $LASTEXITCODE). Any previous PDF is stale."
}

Write-Output "Built: $outputFile"
