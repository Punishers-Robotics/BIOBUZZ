package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.button.Trigger;

import org.firstinspires.ftc.teamcode.Commands.ManualDriveCommand;
import org.firstinspires.ftc.teamcode.Commands.SetFlowerServoClose;
import org.firstinspires.ftc.teamcode.Commands.SetFlowerServoOpen;
import org.firstinspires.ftc.teamcode.Commands.ShootCommand;
import org.firstinspires.ftc.teamcode.Common.MyRobot;
import org.firstinspires.ftc.teamcode.Subsystems.TransferSubsystem;

@TeleOp(name = "Tank Drive TeleOp")
public class TankDriveTeleOp extends OpMode {

    private MyRobot robot;

    @Override
    public void init() {
        robot = new MyRobot(this);

        CommandScheduler.getInstance().setDefaultCommand(
                robot.driveSubsystem,
                new ManualDriveCommand(robot)
        );

        new Trigger(() -> gamepad1.right_bumper)
                .whileActiveOnce(new ShootCommand(robot));

        new Trigger(() -> gamepad1.a)
                .whileActiveOnce(new SetFlowerServoOpen(robot));

        new Trigger(() -> gamepad1.b)
                .whileActiveOnce(new SetFlowerServoClose(robot));
    }

    @Override
    public void loop() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void stop() {
        robot.driveSubsystem.stop();
        CommandScheduler.getInstance().reset();
    }
}
