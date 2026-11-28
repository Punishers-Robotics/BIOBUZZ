package org.firstinspires.ftc.teamcode.Commands;

import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Common.MatchState;
import org.firstinspires.ftc.teamcode.Common.MyRobot;

public class ResetRobotForTeleOpCommand extends CommandBase {
    private final MyRobot robot;

    public ResetRobotForTeleOpCommand(MyRobot robot) {
        this.robot = robot;
        addRequirements(robot.followerSubsystem);
    }

    @Override
    public void execute() {
        robot.useFixedTurret = true;
        robot.followerSubsystem.follower.setTeleOpDrive(0, 0, 0);
        robot.followerSubsystem.follower.update();

        Pose finalPose = robot.followerSubsystem.follower.getPose().copy();
        MatchState.getInstance().setStartingPose(finalPose);
        robot.telemetry().addData("Ending pose", finalPose);
    }
}
