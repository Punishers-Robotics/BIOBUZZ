package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Common.MyRobot;

public class FlyWheelSubsystem extends SubsystemBase {
    DcMotorEx flyWheelMotor;
    public FlyWheelSubsystem(MyRobot robot){
        flyWheelMotor = robot.hardwareMap().get(DcMotorEx.class,"flyWheelMotor");
    }
    public void setFlywheelMotor(){
        flyWheelMotor.setPower(-0.67);
    }

    public void periodic(){
        setFlywheelMotor();
    }
}
