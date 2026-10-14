package org.firstinspires.ftc.teamcode.Common;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierPoint;
import org.firstinspires.ftc.teamcode.Commands.CustomFollowerCommand;

import java.util.ArrayList;
import java.util.List;

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
    public CustomFollowerCommand createFollowCurveCommand(Pose targetPose, BezierPoint... controlPoints) {
        return createFollowCurveCommandWithPower(targetPose, 1, controlPoints);
    }

    public CustomFollowerCommand createFollowCurveCommandWithPower(
            Pose targetPose,
            double maxPower,
            BezierPoint... controlPoints
    ) {
        Pose startPose = lastTargetPose;
        lastTargetPose = targetPose;

        // Build Pose control point list: start + user points + end
        List<Pose> poses = new ArrayList<>();

        // Start pose
        poses.add(new Pose(startPose.getX(), startPose.getY(), startPose.getHeading()));

        // Middle control points (convert BezierPoint → Pose)
        for (BezierPoint cp : controlPoints) {
            poses.add(new Pose(cp.getPose(0).getX(), cp.getPose(0).getY()));
        }

        // End pose
        poses.add(new Pose(targetPose.getX(), targetPose.getY(), targetPose.getHeading()));

        // Build the curve using the correct constructor
        BezierCurve curve = new BezierCurve(poses);


        // Build the path and return the command
        return new CustomFollowerCommand(
                robot,
                robot.followerSubsystem.follower.pathBuilder()
                        .addPath(curve)
                        .setLinearHeadingInterpolation(

                                startPose.getHeading(),
                                targetPose.getHeading()
                        )
                        .setGlobalDeceleration(0.2)
                        .build(),

                maxPower
        );
    }




    public CustomFollowerCommand createFollowPathCommandWithPower(Pose targetPose, double maxPower){
        Pose startPose = lastTargetPose;
        lastTargetPose = targetPose;



        return new CustomFollowerCommand(
                robot,
                robot.followerSubsystem.follower.pathBuilder()
                        .addPath(new BezierLine(startPose, targetPose))
                        .setLinearHeadingInterpolation(startPose.getHeading(), targetPose.getHeading())
                        .setGlobalDeceleration(0.2)
                        .build(),
                maxPower);

    }
}
