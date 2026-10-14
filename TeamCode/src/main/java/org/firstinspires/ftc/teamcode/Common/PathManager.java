package org.firstinspires.ftc.teamcode.Common;

import com.pedropathing.api.Paths;
import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.Commands.CustomFollowerCommand;
import org.firstinspires.ftc.teamcode.Subsystems.PedroDriveSubsystem;

public class PathManager {
    private Pose lastTargetPose;
    private final PedroDriveSubsystem drive;

    public PathManager(MyRobot robot, Pose startPose) {
        this(robot.followerSubsystem, startPose);
    }

    public PathManager(PedroDriveSubsystem drive, Pose startPose) {
        this.drive = drive;
        lastTargetPose = startPose;
    }

    public CustomFollowerCommand createFollowPathCommand(Pose targetPose) {
        return createFollowPathCommandWithPower(targetPose, 1);
    }

    public CustomFollowerCommand createFollowCurveCommand(Pose targetPose, Pose... controlPoints) {
        return createFollowCurveCommandWithPower(targetPose, 1, controlPoints);
    }

    public CustomFollowerCommand createFollowCurveCommandWithPower(
            Pose targetPose, double maxPower, Pose... controlPoints) {
        Pose startPose = lastTargetPose;
        lastTargetPose = targetPose;

        Pose[] poses = new Pose[controlPoints.length + 2];
        poses[0] = startPose;
        System.arraycopy(controlPoints, 0, poses, 1, controlPoints.length);
        poses[poses.length - 1] = targetPose;

        return new CustomFollowerCommand(drive,
                Paths.curve(poses).linear(startPose, targetPose), maxPower);
    }

    public CustomFollowerCommand createFollowPathCommandWithPower(Pose targetPose, double maxPower) {
        Pose startPose = lastTargetPose;
        lastTargetPose = targetPose;
        return new CustomFollowerCommand(drive,
                Paths.line(startPose, targetPose).linear(startPose, targetPose), maxPower);
    }
}
