package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Common.MyRobot;

public class IntakeSubsystem extends SubsystemBase {
    DcMotorEx intakeMotor;
    public IntakeSubsystem(MyRobot robot){
        intakeMotor = robot.hardwareMap().get(DcMotorEx.class,"intakeMotor");
    }
    public void setIntakeMotor(){
        intakeMotor.setPower(-0.45);
    }

    public void periodic(){
        setIntakeMotor();
    }
}
