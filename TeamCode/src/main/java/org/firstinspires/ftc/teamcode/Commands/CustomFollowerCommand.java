package org.firstinspires.ftc.teamcode.Commands;

import com.pedropathing.paths.Path;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Common.MyRobot;

public class CustomFollowerCommand extends CommandBase {
    MyRobot robot;
    Path path;
    public CustomFollowerCommand(MyRobot robot, Path path){
        this.robot = robot;
        this.path = path;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            robot.followerSubsystem.follower.stop();
        }
    }


    @Override
    public void initialize() {
        robot.followerSubsystem.follower.follow(path);
    }

    @Override
    public boolean isFinished() {
        return !robot.followerSubsystem.follower.isBusy();
    }
}
