package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Common.MyRobot;
import org.firstinspires.ftc.teamcode.Subsystems.FlowerServo;

public class SetFlowerServoOpen extends CommandBase {

    FlowerServo flowerServoSubstem;
    public SetFlowerServoOpen(MyRobot robot){
        flowerServoSubstem = robot.flowerServoSubsystem;
    }
    public void execute(){
        flowerServoSubstem.setFlowerServoOpen();
    }
    @Override
    public void end(boolean interrupted) {
        flowerServoSubstem.setFlowerServoOpen();
    }
}
