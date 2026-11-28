package org.firstinspires.ftc.teamcode.TestOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.Common.MyRobot;
import org.firstinspires.ftc.teamcode.Subsystems.TurretRotationSubsystem;

@TeleOp(name = "Test Turret PID Tuning")
public class TestTurretPIDTuning extends OpMode {

    private static final double P_STEP = 0.001;
    private static final double D_STEP = 0.00001;

    private MyRobot robot;
    private TurretRotationSubsystem turretRotationSubsystem;
    private double p = TurretRotationSubsystem.DEFAULT_P;
    private double d = TurretRotationSubsystem.DEFAULT_D;

    @Override
    public void init() {
        robot = new MyRobot(this);
        robot.followerSubsystem.follower.setStartingPose(new Pose(81, 9, Math.toRadians(90)));
        turretRotationSubsystem = robot.turretRotationSubsystem;
    }

    @Override
    public void loop() {
        boolean gainsChanged = false;
        if (gamepad1.dpadUpWasPressed()) {
            p += P_STEP;
            gainsChanged = true;
        }
        if (gamepad1.dpadDownWasPressed()) {
            p = Math.max(0, p - P_STEP);
            gainsChanged = true;
        }
        if (gamepad1.dpadRightWasPressed()) {
            d += D_STEP;
            gainsChanged = true;
        }
        if (gamepad1.dpadLeftWasPressed()) {
            d = Math.max(0, d - D_STEP);
            gainsChanged = true;
        }
        if (gainsChanged) {
            turretRotationSubsystem.setPidCoefficients(p, 0, d);
        }

        robot.followerSubsystem.follower.update();
        turretRotationSubsystem.setTurretToDesiredAngle();
        telemetry.addData("P (D-pad up/down)", "%.5f", p);
        telemetry.addData("D (D-pad right/left)", "%.5f", d);
        telemetry.addData("Fixed I", 0);
    }

    @Override
    public void stop() {
        turretRotationSubsystem.setTurretRotationMotor(0);
    }
}
