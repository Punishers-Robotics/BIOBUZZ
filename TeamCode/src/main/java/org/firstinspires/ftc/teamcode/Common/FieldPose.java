package org.firstinspires.ftc.teamcode.Common;

import com.pedropathing.geometry.Pose;
import com.pedropathing.math.MathFunctions;

public class FieldPose {
    // Pedro's own Pose.mirror(fieldLength) defaults to this value for the x-axis mirror
    // (it represents the field's length/width in Pedro coordinates, since the FTC field is
    // square). We reuse it for our own y-axis mirror below for the same reason.
    private static final double FIELD_LENGTH = 141.5;
    private static final double FIELD_WIDTH = 141.5;

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
            // This season's field requires mirroring across both axes (equivalent to a
            // 180-degree rotation about the field center), not just the x-axis mirror that
            // Pose.mirror() provides.
            resultingPose = mirrorY(pose.mirror(FIELD_LENGTH), FIELD_WIDTH);
        }

        return resultingPose;
    }

    // Mirrors across a horizontal line at the given y-coordinate, flipping y and the heading.
    // This is the y-axis counterpart to Pose.mirror(fieldLength), which only mirrors the x-axis.
    private static Pose mirrorY(Pose pose, double fieldWidth){
        return new Pose(pose.getX(), fieldWidth - pose.getY(),
                MathFunctions.normalizeAngle(-pose.getHeading()));
    }
}
