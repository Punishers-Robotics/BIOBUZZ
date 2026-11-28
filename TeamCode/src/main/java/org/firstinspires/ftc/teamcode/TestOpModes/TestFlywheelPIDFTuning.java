package org.firstinspires.ftc.teamcode.TestOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Test Flywheel PIDF Tuning")
public class TestFlywheelPIDFTuning extends OpMode {

    private DcMotorEx flyWheelMotor;

    private static final double HIGH_VELOCITY = 1500;
    private static final double LOW_VELOCITY = 900;
    private static final double[] STEP_SIZES = {10.0, 1.0, 0.1, 0.01, 0.001, 0.0001};

    private double targetVelocity = -HIGH_VELOCITY;
    private int stepIndex;
    private double p = 40.7;
    private double f = 12.7170;

    @Override
    public void init() {
        flyWheelMotor = hardwareMap.get(DcMotorEx.class, "flyWheelMotor");
        flyWheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flyWheelMotor.setVelocityPIDFCoefficients(p, 0, 0, f);
    }

    @Override
    public void loop() {
        if (gamepad1.yWasPressed()) {
            targetVelocity = targetVelocity == -HIGH_VELOCITY
                    ? -LOW_VELOCITY
                    : -HIGH_VELOCITY;
        }

        if (gamepad1.bWasPressed()) {
            stepIndex = (stepIndex + 1) % STEP_SIZES.length;
        }

        if (gamepad1.dpadLeftWasPressed()) {
            f -= STEP_SIZES[stepIndex];
        }

        if (gamepad1.dpadRightWasPressed()) {
            f += STEP_SIZES[stepIndex];
        }

        if (gamepad1.dpadUpWasPressed()) {
            p += STEP_SIZES[stepIndex];
        }

        if (gamepad1.dpadDownWasPressed()) {
            p -= STEP_SIZES[stepIndex];
        }

        flyWheelMotor.setVelocityPIDFCoefficients(p, 0, 0, f);
        flyWheelMotor.setVelocity(targetVelocity);

        double currentVelocity = flyWheelMotor.getVelocity();
        telemetry.addData("Target velocity", targetVelocity);
        telemetry.addData("Current velocity", "%.2f", currentVelocity);
        telemetry.addData("Error", "%.2f", targetVelocity - currentVelocity);
        telemetry.addData("Tuning P", "%.4f (D-Pad U/D)", p);
        telemetry.addData("Tuning F", "%.4f (D-Pad L/R)", f);
        telemetry.addData("Step size", "%.4f (B Button)", STEP_SIZES[stepIndex]);
    }

    @Override
    public void stop() {
        flyWheelMotor.setPower(0);
    }
}
