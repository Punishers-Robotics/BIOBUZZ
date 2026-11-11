package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Common.MyRobot;

public class HoodAngleSubsystem extends SubsystemBase {
    MyRobot robot;
    double rawHoodAngle;

    Servo hoodServo;
    public HoodAngleSubsystem(MyRobot robot){
        hoodServo = robot.hardwareMap().get(Servo.class, "hoodServo");
        this.robot = robot;
    }
    public double getHoodAngle(){
        double distanceToGoal=robot.followerSubsystem.getDistanceToGoal();
        return rawHoodAngle = -0.00436763*(distanceToGoal) + 0.928264;
    }
    public void setHoodServo(){
        hoodServo.setPosition(getHoodAngle());
    }
}
