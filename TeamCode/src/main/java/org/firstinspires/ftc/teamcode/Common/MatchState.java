package org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MatchState {

    private static MatchState instance;

    private Alliance alliance;

    private MatchState() {
        alliance = Alliance.Blue;
    }

    public static MatchState getInstance() {
        if (instance == null) {
            instance = new MatchState();
        }
        return instance;
    }

    public void initializeForMatchWithGamepad(MyRobot robot) {
        initializeForMatchWithGamepad(robot.gamepad1(), robot.telemetry());
        robot.telemetry().update();
    }

    public void initializeForMatchWithGamepad(Gamepad gamepad, Telemetry telemetry) {
        if (gamepad.dpadLeftWasReleased()) {
            alliance = Alliance.Blue;
        }
        if (gamepad.dpadRightWasReleased()) {
            alliance = Alliance.Red;
        }

        telemetry.addData("Alliance: ", alliance);
    }

    public Alliance getAlliance() {
        return alliance;
    }
}
