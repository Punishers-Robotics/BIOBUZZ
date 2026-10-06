package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Common.MyRobot;

public class TransferSubsystem extends SubsystemBase {

    DcMotorEx transferMotor;
    public TransferSubsystem(MyRobot robot){
        transferMotor = robot.hardwareMap().get(DcMotorEx.class,"transferMotor");
    }
    public void setTransferMotor(){
        transferMotor.setPower(-1);
    }
    public void stopTransferMotor(){
        transferMotor.setPower(0);
    }
}
