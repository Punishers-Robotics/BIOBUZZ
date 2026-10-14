package org.firstinspires.ftc.teamcode.OpModes;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.Commands.CustomFollowerCommand;
import org.firstinspires.ftc.teamcode.Common.FieldPose;
import org.firstinspires.ftc.teamcode.Common.MatchState;
import org.firstinspires.ftc.teamcode.Common.PathManager;
import org.firstinspires.ftc.teamcode.Common.PedroPathingConstants;
import org.firstinspires.ftc.teamcode.Subsystems.PedroDriveSubsystem;

@Autonomous(name = "AutoExcellence - Path Only", group = "Pedro")
public class AutoExcellence extends OpMode {
    private PedroDriveSubsystem drive;
    private CustomFollowerCommand pathCommand;

    @Override
    public void init() {
        PedroPathingConstants.requireForesightTuning();
        CommandScheduler.getInstance().reset();
        drive = new PedroDriveSubsystem(hardwareMap, telemetry);
        CommandScheduler.getInstance().registerSubsystem(drive);
        displaySelection();
    }

    @Override
    public void init_loop() {
        displaySelection();
    }

    private void displaySelection() {
        MatchState.getInstance().initializeForMatchWithGamepad(gamepad1, telemetry);
        telemetry.addLine("D-pad left: Blue; D-pad right: Red (release to select)");
        telemetry.addData("Start", FieldPose.startNearGoal());
        telemetry.addData("Target", FieldPose.scoreNearGoal());
        telemetry.update();
    }

    @Override
    public void start() {
        Pose startPose = FieldPose.startNearGoal();
        drive.follower.setPose(startPose);
        PathManager paths = new PathManager(drive, startPose);
        pathCommand = paths.createFollowPathCommandWithPower(
                FieldPose.scoreNearGoal(), PedroPathingConstants.TEST_MAX_POWER);
        CommandScheduler.getInstance().schedule(pathCommand);
    }

    @Override
    public void loop() {
        CommandScheduler.getInstance().run();
        telemetry.addData("Path", pathCommand.getStatus());
        telemetry.addData("Alliance", MatchState.getInstance().getAlliance());
        telemetry.update();
    }

    @Override
    public void stop() {
        CommandScheduler.getInstance().cancelAll();
        if (drive != null) {
            drive.stop();
        }
        CommandScheduler.getInstance().reset();
    }
}
