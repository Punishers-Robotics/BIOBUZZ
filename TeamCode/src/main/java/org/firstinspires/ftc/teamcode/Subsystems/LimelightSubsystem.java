package org.firstinspires.ftc.teamcode.Subsystems;

import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Common.MyRobot;

/**
 * Placeholder for Limelight-based vision tracking. FollowerSubsystem references this
 * subsystem for future vision-assisted aiming; flesh out hardware access and periodic()
 * once the Limelight is wired up for this season.
 */
public class LimelightSubsystem extends SubsystemBase {
    MyRobot robot;

    public LimelightSubsystem(MyRobot robot) {
        this.robot = robot;
    }

    @Override
    public void periodic() {
        // TODO: implement Limelight vision tracking for this season.
    }
}
