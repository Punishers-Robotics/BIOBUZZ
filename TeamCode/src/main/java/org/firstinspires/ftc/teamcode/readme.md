## TeamCode Module

### Pedro 3 drive testing and autonomous

The project uses Pedro Pathing `revhub:3.0.1` and AutoTune `tuning:1.0.1`.
Both Pedro test OpModes use SolversLib's command scheduler and a drive-only
`PedroDriveSubsystem`. Its `periodic()` updates the follower once per scheduler
loop. Commands require this subsystem, so manual driving and path following
cannot control it simultaneously. STOP cancels commands, immediately zeros the
drivetrain, and resets the scheduler.

`AutoExcellence - Path Only` is a minimal port of the first near-goal drive
segment from [OnWord-Robotics/punishers](https://github.com/OnWord-Robotics/punishers).
It follows a straight Pedro path from `(23.66, 120.6, 140.95 degrees)` to
`(62, 88.5, 130 degrees)` using a scheduled `CustomFollowerCommand`.
Positions are in inches. It does not shoot or initialize `MyRobot` or any
mechanism subsystems. The rest of the competition routine and the far-goal start
are omitted. `PathManager` also supports curves with intermediate `Pose` control
points using Pedro 3's `Paths.curve()` API.

Required Robot Controller hardware names:

- Four mecanum motors: `frontLeft`, `frontRight`, `rearLeft`, `rearRight`.
- goBILDA Pinpoint: `pinpoint`, with two odometry pods.

Before driving, verify `Common/PedroPathingConstants.java` against the actual robot.
The inherited settings assume left motors reversed, right motors forward, 4-bar
pods, a forward pod Y offset (`xPodOffset`) of 2.25 inches, and a strafe pod X
offset (`yPodOffset`) of -5 inches. These are not measurements of BIOBUZZ.

### AutoTune before autonomous

Pedro 2's controller gains do not map directly to Pedro 3's Foresight algorithm.
`FORESIGHT_TUNED` is deliberately `false`: autonomous/path commands refuse to run
until the new values are measured. Manual TeleOp does not require Foresight tuning.
The partial `foresightConfig` only supplies inherited natural decelerations needed
to construct a follower for manual driving; it cannot run paths or hold position.

Deploy the app and connect a browser to the Robot Controller's Wi-Fi. Open
`http://192.168.43.1:10158` (use the RC's actual IP if different). AutoTune registers
its temporary `Pedro Tuning` utility OpMode when a procedure runs; there is no
separate static tuning OpMode to select.

Run the registered procedures in this order:

1. **Mecanum Tuner:** verify motor names/directions and paste the generated
   `drivetrainConfig` into `PedroPathingConstants`.
2. **Pinpoint Tuner:** measure pod directions/offsets/resolution and paste the
   generated `localizerConfig` into the same file.
3. Rebuild/deploy those hardware settings, then run **Foresight Tuner**. Copy its
   Java-tab `foresightConfig` into the same file, add the generated code's imports
   (`Controller`, `Matrix`, `Vector2D`), and set `FORESIGHT_TUNED` to `true`.
   Rebuild/deploy again before selecting the autonomous.

**AutoTune uses uncapped drivetrain power, including full-power measurements.**
Raise the robot for motor-direction tests, and provide ample clear floor space
for Foresight's driving/strafing/spinning tests and coast-down distance. The 30%
cap described below applies only to the two Pedro test OpModes, not the tuners.
Driver Station STOP aborts Foresight measurements rather than returning partial
results, and the drivetrain is stopped on completion, cancellation, or error.

### Pedro Drive TeleOp

Select `Pedro Drive TeleOp`, INIT, then START. Gamepad 1 left stick controls
forward/backward and strafing; right stick X controls turning. The default
`PedroManualDriveCommand` sends robot-centric controls to `follower.manual()`.
This OpMode needs only the four drive motors and Pinpoint, not the tank-drive
motors or mechanisms. It resets the reported pose to `(0, 0, 0)` at START for
relative localization testing; this is not an absolute field position or an
autonomous-to-TeleOp pose handoff.

The existing `Tank Drive TeleOp` is retained separately.

### Running the autonomous

Select the OpMode and press INIT. Release gamepad 1 D-pad left for Blue or right
for Red, and confirm the displayed alliance/start/target before pressing START.
Red uses BIOBUZZ's existing mirror across both field axes, not Punishers' original
x-only mirror. Place the robot at the selected start pose and heading; initialization
does not determine its physical location.

Clear the entire path before starting. Both Pedro test OpModes are capped at
30% wheel power (`TEST_MAX_POWER` in `PedroPathingConstants`), including combined
translation/turn inputs and autonomous corrections. Pedro 3's path-speed
constraint is also set to 30% for this segment; path speed is not a motor-power
limit. The follower reports its pose and path status and stops driving on
completion, STOP, interruption, or a 15-second timeout.
`Finished` means Pedro ended the path; confirm the actual end position on the robot.
Edit the two poses in `FieldPose` to experiment with the path and adjust power only
after localization and tuning are verified.

Build with `.\gradlew.bat :TeamCode:assembleDebug` using Android Studio's JDK and
an installed Android SDK.

### Imported tuning procedures

The three procedures under `pedro/procedures` come from the official
[Pedro-Pathing/Quickstart](https://github.com/Pedro-Pathing/Quickstart/tree/41158ec1ec7314a7cef1d6c9b9f21b380a6cb8e9/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/pedro/procedures)
at commit `41158ec1ec7314a7cef1d6c9b9f21b380a6cb8e9`. Their BSD license is retained
in that directory. Local adaptations add reliable stop/finally handling for the
motor-driving procedures and use Android-compatible list construction. Other
localizers, drivetrain types, and competition mechanisms are not imported.

Welcome!

This module, TeamCode, is the place where you will write/paste the code for your team's
robot controller App. This module is currently empty (a clean slate) but the
process for adding OpModes is straightforward.

## Creating your own OpModes

The easiest way to create your own OpMode is to copy a Sample OpMode and make it your own.

Sample opmodes exist in the FtcRobotController module.
To locate these samples, find the FtcRobotController module in the "Project/Android" tab.

Expand the following tree elements:
 FtcRobotController/java/org.firstinspires.ftc.robotcontroller/external/samples

### Naming of Samples

To gain a better understanding of how the samples are organized, and how to interpret the
naming system, it will help to understand the conventions that were used during their creation.

These conventions are described (in detail) in the sample_conventions.md file in this folder.

To summarize: A range of different samples classes will reside in the java/external/samples.
The class names will follow a naming convention which indicates the purpose of each class.
The prefix of the name will be one of the following:

Basic:  	This is a minimally functional OpMode used to illustrate the skeleton/structure
            of a particular style of OpMode.  These are bare bones examples.

Sensor:    	This is a Sample OpMode that shows how to use a specific sensor.
            It is not intended to drive a functioning robot, it is simply showing the minimal code
            required to read and display the sensor values.

Robot:	    This is a Sample OpMode that assumes a simple two-motor (differential) drive base.
            It may be used to provide a common baseline driving OpMode, or
            to demonstrate how a particular sensor or concept can be used to navigate.

Concept:	This is a sample OpMode that illustrates performing a specific function or concept.
            These may be complex, but their operation should be explained clearly in the comments,
            or the comments should reference an external doc, guide or tutorial.
            Each OpMode should try to only demonstrate a single concept so they are easy to
            locate based on their name.  These OpModes may not produce a drivable robot.

After the prefix, other conventions will apply:

* Sensor class names are constructed as:    Sensor - Company - Type
* Robot class names are constructed as:     Robot - Mode - Action - OpModetype
* Concept class names are constructed as:   Concept - Topic - OpModetype

Once you are familiar with the range of samples available, you can choose one to be the
basis for your own robot.  In all cases, the desired sample(s) needs to be copied into
your TeamCode module to be used.

This is done inside Android Studio directly, using the following steps:

 1) Locate the desired sample class in the Project/Android tree.

 2) Right click on the sample class and select "Copy"

 3) Expand the  TeamCode/java folder

 4) Right click on the org.firstinspires.ftc.teamcode folder and select "Paste"

 5) You will be prompted for a class name for the copy.
    Choose something meaningful based on the purpose of this class.
    Start with a capital letter, and remember that there may be more similar classes later.

Once your copy has been created, you should prepare it for use on your robot.
This is done by adjusting the OpMode's name, and enabling it to be displayed on the
Driver Station's OpMode list.

Each OpMode sample class begins with several lines of code like the ones shown below:

```
 @TeleOp(name="Template: Linear OpMode", group="Linear Opmode")
 @Disabled
```

The name that will appear on the driver station's "opmode list" is defined by the code:
 ``name="Template: Linear OpMode"``
You can change what appears between the quotes to better describe your opmode.
The "group=" portion of the code can be used to help organize your list of OpModes.

As shown, the current OpMode will NOT appear on the driver station's OpMode list because of the
  ``@Disabled`` annotation which has been included.
This line can simply be deleted , or commented out, to make the OpMode visible.



## ADVANCED Multi-Team App management:  Cloning the TeamCode Module

In some situations, you have multiple teams in your club and you want them to all share
a common code organization, with each being able to *see* the others code but each having
their own team module with their own code that they maintain themselves.

In this situation, you might wish to clone the TeamCode module, once for each of these teams.
Each of the clones would then appear along side each other in the Android Studio module list,
together with the FtcRobotController module (and the original TeamCode module).

Selective Team phones can then be programmed by selecting the desired Module from the pulldown list
prior to clicking to the green Run arrow.

Warning:  This is not for the inexperienced Software developer.
You will need to be comfortable with File manipulations and managing Android Studio Modules.
These changes are performed OUTSIDE of Android Studios, so close Android Studios before you do this.
 
Also.. Make a full project backup before you start this :)

To clone TeamCode, do the following:

Note: Some names start with "Team" and others start with "team".  This is intentional.

1)  Using your operating system file management tools, copy the whole "TeamCode"
    folder to a sibling folder with a corresponding new name, eg: "Team0417".

2)  In the new Team0417 folder, delete the TeamCode.iml file.

3)  the new Team0417 folder, rename the "src/main/java/org/firstinspires/ftc/teamcode" folder
    to a matching name with a lowercase 'team' eg:  "team0417".

4)  In the new Team0417/src/main folder, edit the "AndroidManifest.xml" file, change the line that contains
         package="org.firstinspires.ftc.teamcode"
    to be
         package="org.firstinspires.ftc.team0417"

5)  Add:    include ':Team0417' to the "/settings.gradle" file.
    
6)  Open up Android Studios and clean out any old files by using the menu to "Build/Clean Project""