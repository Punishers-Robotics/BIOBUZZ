package org.firstinspires.ftc.teamcode.Common;

import com.pedropathing.geometry.Pose;

public class FieldPose {
    MyRobot robot;

    public FieldPose(MyRobot robot){
        this.robot = robot;
    }

    // Field poses are defined in terms of the Blue Alliance and are mirrored automatically
    // for Red via getPoseForAlliance(). This season's poses still need to be defined below;
    // centerOfGoal() is kept as a working example of the pattern to follow.
    public static Pose centerOfGoal(){
        return getPoseForAlliance(new Pose(9, 134));
    }

    // TODO: add this season's field poses here, following the pattern above, e.g.:
    // public static Pose scoreNearGoal(){
    //     return getPoseForAlliance(new Pose(62, 88.5, Math.toRadians(130)));
    // }

    private static Pose getPoseForAlliance(Pose pose){
        Pose resultingPose = pose;

        if (MatchState.getInstance().getAlliance() == Alliance.Red){
            resultingPose = pose.mirror();
        }

        return resultingPose;
    }
}
