package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Common.Alliance;
import org.firstinspires.ftc.teamcode.Common.MatchState;
import org.firstinspires.ftc.teamcode.Common.MyRobot;
import org.firstinspires.ftc.teamcode.Subsystems.FollowerSubsystem;

public class ManualDriveFollowerCommand extends CommandBase {

    private final MyRobot robot;
    private final FollowerSubsystem followerSubsystem;

    public ManualDriveFollowerCommand(MyRobot robot) {
        this.robot = robot;
        followerSubsystem = robot.followerSubsystem;
        addRequirements(followerSubsystem);
    }

    @Override
    public void execute() {
        Gamepad gamepad1 = robot.gamepad1();
        double rotation = gamepad1.right_stick_x;

        if (gamepad1.left_bumper) {
            rotation *= 0.5;
        }

        double allianceFactor = MatchState.getInstance().getAlliance() == Alliance.Red ? -1 : 1;
        followerSubsystem.follower.setTeleOpDrive(
                -gamepad1.left_stick_y * allianceFactor,
                -gamepad1.left_stick_x * allianceFactor,
                rotation,
                false
        );
    }

    @Override
    public void end(boolean interrupted) {
        followerSubsystem.follower.setTeleOpDrive(0, 0, 0, false);
        followerSubsystem.follower.update();
    }
}
