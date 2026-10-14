package org.firstinspires.ftc.teamcode.Subsystems;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.PedroPathingConstants;

public class PedroDriveSubsystem extends SubsystemBase {
    public final Follower follower;
    private final Telemetry telemetry;

    public PedroDriveSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this(hardwareMap, telemetry, PedroPathingConstants.TEST_MAX_POWER);
    }

    protected PedroDriveSubsystem(HardwareMap hardwareMap, Telemetry telemetry, double maxPower) {
        this(PedroPathingConstants.createFollower(hardwareMap, maxPower), telemetry);
    }

    public PedroDriveSubsystem(Follower follower, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.follower = follower;
    }

    public void stop() {
        follower.stop();
        // Follower.stop() changes mode; stop the hardware immediately without another update.
        follower.drivetrain.stop();
    }

    @Override
    public void periodic() {
        follower.update();
        Pose pose = follower.pose();
        telemetry.addData("Pedro mode", follower.mode());
        telemetry.addData("X (in)", pose.x());
        telemetry.addData("Y (in)", pose.y());
        telemetry.addData("Heading (deg)", Math.toDegrees(pose.heading()));
    }
}
