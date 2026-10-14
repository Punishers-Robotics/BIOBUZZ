package org.firstinspires.ftc.teamcode.Subsystems;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.math.Velocity;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Common.FieldPose;
import org.firstinspires.ftc.teamcode.Common.MyRobot;
import org.firstinspires.ftc.teamcode.Common.PedroPathingConstants;


public class FollowerSubsystem extends SubsystemBase {
    MyRobot robot;
    public Follower follower;

    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;
    DcMotorEx turretFlyWheel;
    DcMotorEx turretFlyWheel2;

    LimelightSubsystem limelightSubsystem;
    FollowerSubsystem followerSubsystem;
    public FollowerSubsystem(MyRobot robot){
        this.robot = robot;
        this.followerSubsystem = robot.followerSubsystem;
        this.limelightSubsystem = robot.limelightSubsystem;
        follower = PedroPathingConstants.createFollower(robot.hardwareMap());
        frontLeft = robot.hardwareMap().get(DcMotorEx.class, "frontLeft");
        frontRight = robot.hardwareMap().get(DcMotorEx.class, "frontRight");
        backLeft =  robot.hardwareMap().get(DcMotorEx.class, "rearLeft");
        backRight = robot.hardwareMap().get(DcMotorEx.class, "rearRight");
        turretFlyWheel = robot.hardwareMap().get(DcMotorEx.class, "flywheelMotor");
        turretFlyWheel2 = robot.hardwareMap().get(DcMotorEx.class, "turretFlyWheel2");
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

            double angularVelocity = follower.velocity().omega;

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
        follower.update();

        Pose currentPose = follower.pose();
//        robot.telemetry().addData("frontLeft draw :",frontLeft.getCurrent(CurrentUnit.AMPS));
//        robot.telemetry().addData("frontRight draw :",frontRight.getCurrent(CurrentUnit.AMPS));
//        robot.telemetry().addData("rearLeft draw :", backLeft.getCurrent(CurrentUnit.AMPS));
//        robot.telemetry().addData("rearRight draw :", backRight.getCurrent(CurrentUnit.AMPS));
       // robot.telemetry().addData("Flywheel draw :",turretFlyWheel.getCurrent(CurrentUnit.AMPS));
        //robot.telemetry().addData("Flywheel2 draw :",turretFlyWheel2.getCurrent(CurrentUnit.AMPS));

        robot.telemetry().addData("Current degrees: ", getAngleToGoal());

        robot.telemetry().addData("Current X: ", currentPose.x());
        robot.telemetry().addData("Current Y: ", currentPose.y());
        robot.telemetry().addData("Current Heading: ", currentPose.heading());
        robot.telemetry().update();
    }
}
