package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

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
        robot.isShooting = true;
        transferSubsystem.setTransferMotor();
    }

    @Override
    public void end(boolean interrupted) {
        robot.isShooting = false;
        transferSubsystem.stopTransferMotor();
    }
}
