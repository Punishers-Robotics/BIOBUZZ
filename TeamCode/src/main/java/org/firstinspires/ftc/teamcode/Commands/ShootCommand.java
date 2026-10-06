package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Common.MyRobot;
import org.firstinspires.ftc.teamcode.Subsystems.TransferSubsystem;

public class ShootCommand extends CommandBase {
    MyRobot robot;

    TransferSubsystem transferSubsystem;
    public ShootCommand(MyRobot robot){
        this.robot = robot;
        transferSubsystem = robot.transferSubsystem;
        addRequirements(transferSubsystem);
    }

    @Override
    public void execute() {
        transferSubsystem.setTransferMotor();
        new WaitCommand(3000);
        transferSubsystem.stopTransferMotor();
    }
}
