package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Common.MyRobot;
import org.firstinspires.ftc.teamcode.Subsystems.FlowerServo;

public class SetFlowerServoClose extends CommandBase {

    FlowerServo flowerServoSubstem;
    public SetFlowerServoClose(MyRobot robot){
        flowerServoSubstem = robot.flowerServoSubsystem;
    }
    public void execute(){
        flowerServoSubstem.setFlowerServoOpen();
    }
    @Override
    public void end(boolean interrupted) {
        flowerServoSubstem.setFlowerServoClose();
    }
}
