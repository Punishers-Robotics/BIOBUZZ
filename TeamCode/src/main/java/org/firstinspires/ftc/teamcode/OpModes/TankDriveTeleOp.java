package org.firstinspires.ftc.teamcode.OpModes;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.button.Trigger;

import org.firstinspires.ftc.teamcode.Commands.ManualDriveFollowerCommand;
import org.firstinspires.ftc.teamcode.Commands.SetFlowerServoClose;
import org.firstinspires.ftc.teamcode.Commands.SetFlowerServoOpen;
import org.firstinspires.ftc.teamcode.Commands.ShootCommand;
import org.firstinspires.ftc.teamcode.Common.MatchState;
import org.firstinspires.ftc.teamcode.Common.MyRobot;
import org.firstinspires.ftc.teamcode.Subsystems.FollowerSubsystem;

@TeleOp(name = "Tank Drive TeleOp")
public class TankDriveTeleOp extends OpMode {

    private MyRobot robot;

    @Override
    public void init() {
        robot = new MyRobot(this);

        robot.followerSubsystem.follower.setStartingPose(new Pose(81,9,Math.toRadians(90)));

        robot.followerSubsystem.follower.startTeleopDrive();

        CommandScheduler.getInstance().setDefaultCommand(
                robot.followerSubsystem,
                new ManualDriveFollowerCommand(robot)
        );

        new Trigger(() -> gamepad1.right_bumper)
                .whileActiveOnce(new ShootCommand(robot));

        new Trigger(() -> gamepad1.dpad_down)
                .whileActiveOnce(new SetFlowerServoOpen(robot));

        new Trigger(() -> gamepad1.dpad_up)
                .whileActiveOnce(new SetFlowerServoClose(robot));

    }

    @Override
    public void loop() {
        CommandScheduler.getInstance().run();
        robot.telemetry().addData("Heading reading", robot.followerSubsystem.follower.getPose().getHeading());
        robot.telemetry().addData("X reading", robot.followerSubsystem.follower.getPose().getX());
        robot.telemetry().addData("Y reading", robot.followerSubsystem.follower.getPose().getY());
        robot.telemetry().update();
    }

    @Override
    public void stop() {
        CommandScheduler.getInstance().reset();
    }
}
