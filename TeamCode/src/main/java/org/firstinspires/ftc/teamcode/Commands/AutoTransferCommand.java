package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Common.MyRobot;

public class AutoTransferCommand extends CommandBase {
    private final MyRobot robot;

    public AutoTransferCommand(MyRobot robot) {
        this.robot = robot;
        addRequirements(robot.transferSubsystem);
    }

    @Override
    public void execute() {
        if (!robot.isTransferFull || robot.isShooting) {
            robot.transferSubsystem.setTransferMotor();
        } else {
            robot.transferSubsystem.stopTransferMotor();
        }
    }

    @Override
    public void end(boolean interrupted) {
        robot.transferSubsystem.stopTransferMotor();
    }
}
