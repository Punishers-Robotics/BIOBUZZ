package org.firstinspires.ftc.teamcode.Common;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class PedroPathingConstants {
    public static final double TEST_MAX_POWER = 0.3;
    public static final boolean FORESIGHT_TUNED = false;

    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("frontLeft");
        c.backLeftName.set("rearLeft");
        c.frontRightName.set("frontRight");
        c.backRightName.set("rearRight");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.manualBrakeMode.set(true);
    });

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.xPodOffset.set(2.25);
        c.yPodOffset.set(-5.0);
        c.offsetUnits.set(DistanceUnit.INCH);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
    });

    // Replace this configuration with AutoTune's generated ForesightConfig.
    // These inherited decelerations only allow construction for manual driving.
    public static ForesightConfig foresightConfig = new ForesightConfig(c -> {
        c.naturalForwardDeceleration.set(32.0);
        c.naturalStrafeDeceleration.set(53.0);
    });

    public static void requireForesightTuning() {
        if (!FORESIGHT_TUNED) {
            throw new IllegalStateException(
                    "Run Pedro AutoTune, paste its ForesightConfig into PedroPathingConstants, "
                            + "and set FORESIGHT_TUNED to true before following paths.");
        }
    }

    public static Follower createFollower(HardwareMap hardwareMap) {
        return createFollower(hardwareMap, 1);
    }

    public static Follower createFollower(HardwareMap hardwareMap, double maxPower) {
        Mecanum drivetrain = createDrivetrain(hardwareMap, maxPower);
        return new Follower(
                new PinpointLocalizer(hardwareMap, localizerConfig),
                drivetrain,
                new Foresight(foresightConfig));
    }

    public static Mecanum createDrivetrain(HardwareMap hardwareMap, double maxPower) {
        if (!Double.isFinite(maxPower) || maxPower <= 0 || maxPower > 1) {
            throw new IllegalArgumentException("maxPower must be in (0, 1]");
        }

        return new Mecanum(hardwareMap, drivetrainConfig) {
            @Override
            public void drive(DrivePowers powers, boolean manual) {
                double peak = 0;
                for (double power : computeWheelPowersUnnormalized(powers)) {
                    peak = Math.max(peak, Math.abs(power));
                }
                // Cap wheel power, including heading/position corrections, not just path speed.
                double scale = peak > maxPower ? maxPower / peak : 1;
                super.drive(new DrivePowers(
                        powers.forward() * scale,
                        powers.strafe() * scale,
                        powers.turn() * scale), manual);
            }
        };
    }
}
