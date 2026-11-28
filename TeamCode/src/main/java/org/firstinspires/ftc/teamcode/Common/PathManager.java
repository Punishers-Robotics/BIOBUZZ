package org.firstinspires.ftc.teamcode.Common;

import com.pedropathing.api.Paths;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import org.firstinspires.ftc.teamcode.Commands.CustomFollowerCommand;

public class PathManager {

    private Pose lastTargetPose;

    MyRobot robot;

    public PathManager(MyRobot robot, Pose startPose){

        this.robot = robot;
        this.lastTargetPose = startPose;
    }
    public CustomFollowerCommand createFollowPathCommand(Pose targetPose) {
        return createFollowPathCommandWithPower(targetPose, 1);
    }
    public CustomFollowerCommand createFollowCurveCommand(Pose targetPose, Pose... controlPoints) {
        return createFollowCurveCommandWithPower(targetPose, 1, controlPoints);
    }

    public CustomFollowerCommand createFollowCurveCommandWithPower(
            Pose targetPose,
            double speedScale,
            Pose... controlPoints
    ) {
        Pose startPose = lastTargetPose;
        Pose[] poses = new Pose[controlPoints.length + 2];
        poses[0] = startPose;
        System.arraycopy(controlPoints, 0, poses, 1, controlPoints.length);
        poses[poses.length - 1] = targetPose;
        Path path = withSpeedScale(Paths.curve(poses).linear(startPose, targetPose), speedScale);
        lastTargetPose = targetPose;
        return new CustomFollowerCommand(robot, path);
    }

    public CustomFollowerCommand createFollowPathCommandWithPower(Pose targetPose, double speedScale) {
        Pose startPose = lastTargetPose;
        Path path = withSpeedScale(Paths.line(startPose, targetPose).linear(startPose, targetPose), speedScale);
        lastTargetPose = targetPose;
        return new CustomFollowerCommand(robot, path);
    }

    private Path withSpeedScale(Path path, double speedScale) {
        if (!(speedScale > 0) || speedScale > 1) {
            throw new IllegalArgumentException("speedScale must be greater than 0 and at most 1");
        }
        return path.with(PedroPathingConstants.foresightConstants.maxPathSpeed.at(speedScale));
    }
}
