package org.firstinspires.ftc.teamcode.Common;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

public class FieldPose {
    private static final PoseFactory RED_ALLIANCE_POSES = PoseFactory.radians().mirrorX(70.75);

    MyRobot robot;

    public FieldPose(MyRobot robot){
        this.robot = robot;
    }

    // Field poses are defined for Blue and mirrored automatically for Red.
    public Pose getGoalPose(){
        if (robot.followerSubsystem.follower.pose().y() > 70.5){
            return getPoseForAlliance(new Pose(82
                    , 98));
        }
        else {
            return getPoseForAlliance(new Pose(79, 56));
        }
        }


    public static Pose startNearGoal() {
        return getPoseForAlliance(new Pose(23.66, 120.6, Math.toRadians(140.95)));
    }

    public static Pose startFarFromGoal() {
        return getPoseForAlliance(new Pose(53, 7.5, Math.toRadians(90)));
    }

    public static Pose scoreNearGoal() {
        return getPoseForAlliance(new Pose(62, 88.5, Math.toRadians(130)));
    }

    public static Pose scoreNearGoalFar() {
        return getPoseForAlliance(new Pose(61, 75, Math.toRadians(205)));
    }

    public static Pose scoreEndOfAutoNear() {
        return getPoseForAlliance(new Pose(64, 100, Math.toRadians(160)));
    }

    public static Pose scoreFarFromGoal() {
        return getPoseForAlliance(new Pose(55.8, 16.5, Math.toRadians(180)));
    }

    public static Pose ppgIntake() {
        return getPoseForAlliance(new Pose(23.5, 85, Math.toRadians(180)));
    }

    public static Pose pgpPickup() {
        return getPoseForAlliance(new Pose(56, 60, Math.toRadians(180)));
    }

    public static Pose pgpIntake() {
        return getPoseForAlliance(new Pose(16, 60, Math.toRadians(180)));
    }

    public static Pose gppPickup() {
        return getPoseForAlliance(new Pose(50, 35.5, Math.toRadians(180)));
    }

    public static Pose gppIntake() {
        return getPoseForAlliance(new Pose(16, 36, Math.toRadians(180)));
    }

    public static Pose emptyClassifierPose() {
        return getPoseForAlliance(new Pose(23, 60, Math.toRadians(148.7)));
    }

    public static Pose emptyClassifier() {
        return getPoseForAlliance(new Pose(8.5, 60.5, Math.toRadians(146)));
    }


    private static Pose getPoseForAlliance(Pose pose){
        Pose resultingPose = pose;

        if (MatchState.getInstance().getAlliance() == Alliance.Red){
            resultingPose = RED_ALLIANCE_POSES.of(pose.x(), pose.y(), pose.heading());
        }

        return resultingPose;
    }
}
