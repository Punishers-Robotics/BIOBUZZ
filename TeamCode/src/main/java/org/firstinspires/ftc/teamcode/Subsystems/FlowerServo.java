package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Common.MyRobot;

public class FlowerServo extends SubsystemBase {

    Servo flowerServo;
    public FlowerServo(MyRobot robot){
        flowerServo = robot.hardwareMap().get(Servo.class,"flowerServo");
    }
    public void setFlowerServoOpen(){
        flowerServo.setPosition(0.595);
    }
    public void setFlowerServoClose(){
        flowerServo.setPosition(0.2);
    }
}
