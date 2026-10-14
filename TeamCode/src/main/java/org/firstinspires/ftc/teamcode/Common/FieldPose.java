package org.firstinspires.ftc.teamcode.Common;

import com.pedropathing.math.Pose;

public class FieldPose {
    private static final double FIELD_LENGTH = 141.5;
    private static final double FIELD_WIDTH = 141.5;

    MyRobot robot;

    public FieldPose(MyRobot robot){
        this.robot = robot;
    }

    // Field poses are defined in terms of the Blue Alliance and are mirrored automatically
    // for Red via getPoseForAlliance().
    public static Pose startNearGoal(){
        return getPoseForAlliance(new Pose(23.66, 120.6, Math.toRadians(140.95)));
    }

    public static Pose scoreNearGoal(){
        return getPoseForAlliance(new Pose(62, 88.5, Math.toRadians(130)));
    }

    public static Pose centerOfGoal(){
        return getPoseForAlliance(new Pose(9, 134));
    }

    private static Pose getPoseForAlliance(Pose pose){
        Pose resultingPose = pose;

        if (MatchState.getInstance().getAlliance() == Alliance.Red){
            // A 180-degree rotation about the field center mirrors both field axes.
            resultingPose = new Pose(FIELD_LENGTH - pose.x(), FIELD_WIDTH - pose.y(),
                    pose.heading() + Math.PI);
        }

        return resultingPose;
    }

}
