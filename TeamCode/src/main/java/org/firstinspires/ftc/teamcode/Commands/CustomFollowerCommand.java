package org.firstinspires.ftc.teamcode.Commands;

import com.pedropathing.paths.PathChain;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Common.MyRobot;

public class CustomFollowerCommand extends CommandBase {
    MyRobot robot;
    PathChain pathChain;
    double power;
    public CustomFollowerCommand(MyRobot robot, PathChain pathChain, double power){
        this.robot = robot;
        this.pathChain = pathChain;
        this.power = power;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            robot.followerSubsystem.follower.breakFollowing();
        }
    }


    @Override
    public void initialize() {
        robot.followerSubsystem.follower.followPath(pathChain, power, true);
    }

    @Override
    public boolean isFinished() {
        return !robot.followerSubsystem.follower.isBusy();
    }
}
