package org.firstinspires.ftc.teamcode.Common;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class PedroPathingConstants {
    public static final MecanumConfig driveConstants = new MecanumConfig(c -> {
        c.frontLeftName.set("frontLeft");
        c.frontRightName.set("frontRight");
        c.backLeftName.set("rearLeft");
        c.backRightName.set("rearRight");
        c.frontLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.frontRightDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.REVERSE);
        c.manualBrakeMode.set(true);
    });

    public static final PinpointConfig localizerConstants = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.xPodOffset.set(7.0);
        c.yPodOffset.set(-4.25);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
    });

    // Pedro 3 replaces the old PID configuration; these example coefficients must be re-tuned on the robot.
    public static final ForesightConfig foresightConstants = new ForesightConfig(c -> {
        Controller primaryForward = Controller.proportional(0.3);
        Controller secondaryForward = Controller.proportional(0.1);
        Controller primaryStrafe = Controller.proportional(0.3);
        Controller secondaryStrafe = Controller.proportional(0.1);

        c.forwardTranslational.set(Controller.piecewise(secondaryForward).put(2.5, primaryForward));
        c.strafeTranslational.set(Controller.piecewise(secondaryStrafe).put(2.5, primaryStrafe));
        c.coast.set(Controller.proportionalFeedforward(0.010978350889324107));
        c.brake.set(Controller.proportionalFeedforward(0.008731598255925491));
        c.headingFeedback.set(Controller.proportional(5.258721785960744));
        c.headingBrakeCoefficients.set(Vector2D.cartesian(0.05642143125655298, 0.0063829525363003695));
        c.linearBrakeCoefficients.set(Matrix.diag(0.10605894992901523, 0.08719146175596092));
        c.quadraticBrakeCoefficients.set(Matrix.diag(0.0014663966976606565, 0.0013837064502458813));
        c.maxAchievableForwardVelocity.set(90.0);
        c.maxAchievableStrafeVelocity.set(73.0);
        c.naturalForwardDeceleration.set(32.0);
        c.naturalStrafeDeceleration.set(53.0);
    });

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new Follower(
                new PinpointLocalizer(hardwareMap, localizerConstants),
                new Mecanum(hardwareMap, driveConstants),
                new Foresight(foresightConstants)
        );
    }
}
