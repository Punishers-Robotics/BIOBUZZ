package org.firstinspires.ftc.teamcode.Common;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.utils.Angle;

public class FieldPose {
    MyRobot robot;

    public FieldPose(MyRobot robot){
        this.robot = robot;
    }

    private static final double FIELD_LENGTH = 141.5;
    private static final double FIELD_WIDTH = 141.5;
    public static final PoseFactory BluePoses = PoseFactory.degrees();
    public static final PoseFactory RedPoses =
            BluePoses.mirrorX(FIELD_LENGTH / 2.0)
            .mirrorY(FIELD_WIDTH / 2.0);

    // Field poses are defined in terms of the Blue Alliance and are mirrored automatically
    // for Red via getPoseForAlliance().
    public static Pose centerOfGoal(){
        return getPoseForAlliance(new Pose(9, 134));
    }

    // TODO: add this season's field poses here, following the pattern above, e.g.:
    // public static Pose scoreNearGoal(){
    //     return getPoseForAlliance(new Pose(62, 88.5, Math.toRadians(130)));
    // }

    private static Pose getPoseForAlliance(Pose pose){
        PoseFactory poseFactory =
            MatchState.getInstance().getAlliance() == Alliance.Red ? RedPoses : BluePoses;

        return poseFactory.of(pose.x(), pose.y(), pose.heading());
    }

    // Mirrors across a horizontal line at the given y-coordinate, flipping y and the heading.
    // This is the y-axis counterpart to Pose.mirror(fieldLength), which only mirrors the x-axis.
    private static Pose mirrorY(Pose pose){
        final double FIELD_WIDTH = 141.5;
        return new Pose(
                pose.x(),
                FIELD_WIDTH - pose.y(),
                Angle.normalize(-pose.heading()));
    }
}
