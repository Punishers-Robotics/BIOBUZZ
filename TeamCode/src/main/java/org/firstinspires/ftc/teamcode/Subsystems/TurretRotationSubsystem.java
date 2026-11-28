package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;

import org.firstinspires.ftc.teamcode.Common.MyRobot;

public class TurretRotationSubsystem extends SubsystemBase {
    public static final double DEFAULT_P = 0.06;
    public static final double DEFAULT_D = 0;

    private static final double MOTOR_ENCODER_TICKS_PER_OUTPUT_REVOLUTION = 145.1;
    private static final double MOTOR_GEAR_TEETH = 7.0;
    private static final double TURRET_GEAR_TEETH = 15.0;
    private static final double TICKS_PER_ROTATION =
            MOTOR_ENCODER_TICKS_PER_OUTPUT_REVOLUTION * TURRET_GEAR_TEETH / MOTOR_GEAR_TEETH;
    private static final double TICKS_PER_DEGREE = TICKS_PER_ROTATION / 360.0;

    private final DcMotorEx turretRotationMotor;
    private final MyRobot robot;
    private PIDController pidController;

    public TurretRotationSubsystem(MyRobot robot) {
        this.robot = robot;

        turretRotationMotor = robot.hardwareMap().get(DcMotorEx.class, "turretRotationMotor");
        turretRotationMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turretRotationMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        turretRotationMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        pidController = new PIDController(DEFAULT_P, 0, DEFAULT_D);
    }

    public void setPidCoefficients(double p, double i, double d) {
        pidController = new PIDController(p, i, d);
    }

    public void setTurretRotationMotor(double power) {
        turretRotationMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        turretRotationMotor.setPower(power);
    }

    public int getTurretsPosition() {
        return turretRotationMotor.getCurrentPosition();
    }

    @Override
    public void periodic() {
        setTurretToDesiredAngle();
    }

    public void setTurretToDesiredAngle() {
        double desiredTurretAngleDeg = robot.useFixedTurret
                ? 0
                : robot.followerSubsystem.getAngleToGoal();
        desiredTurretAngleDeg = clamp(desiredTurretAngleDeg, -150, 150);

        double turretAngleDeg = normalize(getTurretsPosition() / TICKS_PER_DEGREE);
        double error = shortestAngleError(turretAngleDeg, desiredTurretAngleDeg);
        double outputPower = clamp(pidController.calculate(0, error), -1, 1);
        setTurretRotationMotor(outputPower);

//        robot.telemetry().addData("Turret Angle", turretAngleDeg);
//        robot.telemetry().addData("Target Angle", desiredTurretAngleDeg);
//        robot.telemetry().addData("Error", error);
//        robot.telemetry().update();
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private double normalize(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }

    private double shortestAngleError(double current, double target) {
        double error = target - current;
        while (error > 180) error -= 360;
        while (error < -180) error += 360;
        return error;
    }
}
