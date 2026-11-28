# Robot configuration backups

## Contents

- [active/driving.xml](active/driving.xml): the current robot configuration and
  editable source of truth for device names, types, and port mappings.
- [current.revbkp.zip](current.revbkp.zip): the generated package to restore
  through REV Hardware Client 2. Rebuild it whenever the active XML changes.
- [Build-RestorePackage.ps1](Build-RestorePackage.ps1): packages the active XML
  using an existing REV backup as a template.
- [backups/](backups/): dated REV Hardware Client `.revbkp.zip` backups, kept
  unchanged for use with Restore Files.

The initial backup is `11-28-2026-10.37.40.813.revbkp.zip`. It contains
`driving.xml`, `punishers25-26.xml`, `Test.xml`, and the REV backup manifest.
`driving.xml` was selected as the current configuration.

## Updating mappings

1. Edit `active/driving.xml` to match the robot's wiring and the device names
   expected by the robot code.
2. From the repository root, run:

   ```powershell
   powershell.exe -NoProfile -ExecutionPolicy Bypass -File .\robot-config\Build-RestorePackage.ps1
   ```

3. Connect the Control Hub to REV Hardware Client 2 and use Restore Files to
   select `robot-config\current.revbkp.zip`. In the Driver Station's Configure
   Robot screen, select and activate the `driving` configuration.
4. Verify the devices and test the mappings safely before operating the robot.
5. Make a fresh backup with REV Hardware Client and copy the resulting dated
   `.revbkp.zip` into `backups/`.
6. Commit and push the updated XML, generated current ZIP, and new backup to GitHub.

If you change mappings on the hub instead, copy the updated `driving.xml`
back into `active/` and rebuild the current ZIP before committing. Repository
edits do not automatically update the hub or any existing backup ZIP.

## How packaging works

The script validates `active/driving.xml`, replaces the ZIP's `driving.xml`,
and updates its modification time in the REV manifest. It preserves the other
files and manifest fields from the template. The source backup is never modified;
an existing current ZIP is replaced only after packaging succeeds.

By default, the template is the initial backup listed above. To use a newer
backup, pass its path explicitly:

```powershell
powershell.exe -NoProfile -ExecutionPolicy Bypass -File .\robot-config\Build-RestorePackage.ps1 -BackupPath ".\robot-config\backups\<new-backup>.revbkp.zip"
```

Choose a template containing `driving.xml` at the archive root and its matching
REV manifest. The script does not automatically pick the newest backup.
`-OutputPath` optionally selects a different `.revbkp.zip` destination in an
existing directory.

Restoring the package also restores the template's other files, including
`Test.xml` and `punishers25-26.xml` in the initial backup. These are not updated
from `active/`.

Local package validation does not prove REV Hardware Client 2 accepts the ZIP.
For the first generated package, confirm Restore Files succeeds, then make a
fresh hub backup and verify its `driving.xml` matches the intended mappings.

## Restoring after a hub replacement

1. Connect the replacement Control Hub to REV Hardware Client.
2. Rebuild `current.revbkp.zip` using the command above, then use Restore Files
   to select it. To deliberately restore a historical snapshot instead,
   select the desired `.revbkp.zip` from `backups/`.
3. Select and activate the `driving` configuration in Configure Robot.
4. Check the detected hubs, module addresses, and other hardware identifiers;
   reassociate replacement hardware as needed. Verify the physical wiring
   and test all mappings safely.

This initial backup contains configuration files only; it is not a backup
of the installed robot application, hub firmware, or all hub settings.
Redeploy the robot application and configure the replacement hub as needed.

Keep an additional backup copy outside the hub and this local checkout.
See the [REV backup and restore documentation](https://docs.revrobotics.com/rev-hardware-client/duo/control-hub/restore).
