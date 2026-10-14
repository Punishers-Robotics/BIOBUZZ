package org.firstinspires.ftc.teamcode.Subsystems;

import com.pedropathing.math.Pose;
import com.pedropathing.math.Velocity;

import org.firstinspires.ftc.teamcode.Common.FieldPose;
import org.firstinspires.ftc.teamcode.Common.MyRobot;


public class FollowerSubsystem extends PedroDriveSubsystem {
    MyRobot robot;
    public FollowerSubsystem(MyRobot robot){
        super(robot.hardwareMap(), robot.telemetry(), 1);
        this.robot = robot;
        robot.telemetry().addData("follower: ", follower);
        robot.telemetry().update();
        // DO NOT set the starting pose here since it must only be set once in the OpMode to
        // avoid flaky Pedro Pathing behavior
    }

    public Pose getPredictedPoseForShot() {
        Pose pose = follower.pose();
        Velocity velocity = follower.velocity();

        double dx = FieldPose.centerOfGoal().x() - pose.x();
        double dy = FieldPose.centerOfGoal().y() - pose.y();
        double distance = Math.hypot(dx, dy);

        if (distance < 20) {
            return pose;
        }
        else {
            double timeOfFlight = getTimeOfFlight();

            double vx = velocity.vx;
            double vy = velocity.vy;

            double angularVelocity = velocity.omega;

            double dx_robot = vx * timeOfFlight;
            double dy_robot = vy * timeOfFlight;
            double headingVelocityVector = angularVelocity * 0.135;
            //sets new predicted pose
            return new Pose(pose.x() + dx_robot, pose.y() + dy_robot,
                    headingVelocityVector + pose.heading()
            );
        }
    }
    public double getDistanceToGoal() {
        Pose predicted = getPredictedPoseForShot();
        double dxPrediction = FieldPose.centerOfGoal().x() - predicted.x();
        double dyPrediction = FieldPose.centerOfGoal().y() - predicted.y();
        return Math.hypot(dxPrediction, dyPrediction);
    }


    public double getAngleToGoal() {
        //Uses the new predicted pose for calculation
        Pose predicted = getPredictedPoseForShot();

        double dxPrediction = FieldPose.centerOfGoal().x() - predicted.x();
        double dyPrediction = FieldPose.centerOfGoal().y() - predicted.y();
                double robotHeadingRad = predicted.heading();

        //Finds angle to goal
        double fieldAngleRad = Math.atan2(dyPrediction, dxPrediction);

        //Driver aim adjustment
        fieldAngleRad += robot.getDriverAimAdjustment();

        double fieldAngleDeg = Math.toDegrees(fieldAngleRad);
        double robotHeadingDeg = Math.toDegrees(robotHeadingRad);

        double turretTargetAngle = fieldAngleDeg - robotHeadingDeg;

        turretTargetAngle -= robot.getDriverAimAdjustmentLimelight();

        return normalize(turretTargetAngle);
    }

    private double getTimeOfFlight() {
        Pose pose = follower.pose();
        double dx = FieldPose.centerOfGoal().x() - pose.x();
        double dy = FieldPose.centerOfGoal().y() - pose.y();
        double distance = Math.hypot(dx, dy);
        return (0.00115231 * distance + 0.400983) + 0.195;
    }

    private double normalize(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }

    @Override
    public void periodic() {
        super.periodic();
        robot.telemetry().addData("Current degrees: ", getAngleToGoal());
        robot.telemetry().update();
    }
}
