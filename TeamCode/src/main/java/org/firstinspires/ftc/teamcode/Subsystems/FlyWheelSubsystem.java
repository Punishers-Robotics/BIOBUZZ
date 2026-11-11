package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Common.MyRobot;

public class FlyWheelSubsystem extends SubsystemBase {
    DcMotorEx flyWheelMotor;
    MyRobot robot;
    FollowerSubsystem followerSubsystem;
    private double flywheelIncrement = 0;
    double rawFlywheelSpeed;
    PIDFCoefficients pidfCoefficients;
    public FlyWheelSubsystem(MyRobot robot){
        flyWheelMotor = robot.hardwareMap().get(DcMotorEx.class,"flyWheelMotor");
        this.followerSubsystem=new FollowerSubsystem(robot);
        this.robot = robot;
        new PIDFCoefficients(0,0,0,0);

    }
    public void getFlywheelSpeed(){
        double distanceToGoal = followerSubsystem.getDistanceToGoal();
        rawFlywheelSpeed =
            0.000156599 * (Math.pow(distanceToGoal, 3) -
            0.0145812 * Math.pow(distanceToGoal, 2) +
            6.39732 * distanceToGoal + 1139.12912);
    }
    public void setFlywheelMotor() {
        if (robot.gamepad1().dpadUpWasPressed()) {
            flywheelIncrement += 0.1;
        } else if (robot.gamepad1().dpadDownWasPressed()) {
            flywheelIncrement -= 0.1;
        }
        flyWheelMotor.setPower(-flywheelIncrement);
    }

    public void periodic(){
        setFlywheelMotor();
    }
}
