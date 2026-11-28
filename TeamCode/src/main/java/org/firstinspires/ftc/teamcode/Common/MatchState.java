package org.firstinspires.ftc.teamcode.Common;

import com.pedropathing.geometry.Pose;

public class MatchState {

    private static MatchState instance;

    private Alliance alliance;
    private AutoStartPosition autoStartPosition;
    private Pose startingPose;

    private MatchState() {
        alliance = Alliance.Blue;
        autoStartPosition = AutoStartPosition.FarFromGoal;
    }

    public static MatchState getInstance() {
        if (instance == null) {
            instance = new MatchState();
            instance.updateStartingPose();
        }
        return instance;
    }

    public void initializeForMatchWithGamepad(MyRobot robot) {
        if (robot.gamepad1().dpadLeftWasReleased()) {
            alliance = Alliance.Blue;
        }
        if (robot.gamepad1().dpadRightWasReleased()) {
            alliance = Alliance.Red;
        }
        if (robot.gamepad1().dpadUpWasReleased()) {
            autoStartPosition = AutoStartPosition.NearToGoal;
        }
        if (robot.gamepad1().dpadDownWasReleased()) {
            autoStartPosition = AutoStartPosition.FarFromGoal;
        }

        updateStartingPose();
        robot.telemetry().addData("Alliance", alliance);
        robot.telemetry().addData("Auto start", autoStartPosition);
        robot.telemetry().addData("Starting pose", startingPose);
        robot.telemetry().update();
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public AutoStartPosition getAutoStartPosition() {
        return autoStartPosition;
    }

    public Pose getStartingPose() {
        return startingPose;
    }

    public void setStartingPose(Pose startingPose) {
        this.startingPose = startingPose;
    }

    private void updateStartingPose() {
        startingPose = autoStartPosition == AutoStartPosition.NearToGoal
                ? FieldPose.startNearGoal()
                : FieldPose.startFarFromGoal();
    }
}
