package org.firstinspires.ftc.teamcode.OpModes;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.Commands.PedroManualDriveCommand;
import org.firstinspires.ftc.teamcode.Subsystems.PedroDriveSubsystem;

@TeleOp(name = "Pedro Drive TeleOp", group = "Pedro")
public class PedroDriveTeleOp extends OpMode {
    private PedroDriveSubsystem drive;

    @Override
    public void init() {
        CommandScheduler.getInstance().reset();
        drive = new PedroDriveSubsystem(hardwareMap, telemetry);
        CommandScheduler.getInstance().registerSubsystem(drive);
        CommandScheduler.getInstance().setDefaultCommand(
                drive, new PedroManualDriveCommand(drive, gamepad1));
        telemetry.addLine("Robot-centric: left stick drive/strafe, right stick turn");
        telemetry.addLine("Wheel power capped at 30%; no mechanism hardware required");
        telemetry.update();
    }

    @Override
    public void start() {
        drive.follower.setPose(Pose.zero());
    }

    @Override
    public void loop() {
        CommandScheduler.getInstance().run();
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
