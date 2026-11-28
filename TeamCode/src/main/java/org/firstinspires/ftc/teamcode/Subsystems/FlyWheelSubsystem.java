package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Common.MyRobot;

public class FlyWheelSubsystem extends SubsystemBase {
    private final DcMotorEx flyWheelMotor;
    private final MyRobot robot;

    public FlyWheelSubsystem(MyRobot robot) {
        this.robot = robot;
        flyWheelMotor = robot.hardwareMap().get(DcMotorEx.class, "flyWheelMotor");
        flyWheelMotor.setVelocityPIDFCoefficients(114.7,0,0,18.717);
    }

    public double getFlywheelSpeed() {
        double distanceToGoal = robot.followerSubsystem.getDistanceToGoal();
        double calculatedSpeed =
                0.015022 * Math.pow(distanceToGoal, 3)
                        - 2.29936 * Math.pow(distanceToGoal, 2)
                        + 113.28757 * distanceToGoal
                        - 994.32745;
        return Math.max(0, calculatedSpeed);
    }

    public void setFlywheelMotor() {
        flyWheelMotor.setVelocity(-getFlywheelSpeed());
    }

    @Override
    public void periodic() {
        robot.telemetry().addData("Flywheel velocity", flyWheelMotor.getVelocity());
        robot.telemetry().addData("Flywheel target velocity", -getFlywheelSpeed());
        setFlywheelMotor();
    }
}
