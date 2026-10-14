package org.firstinspires.ftc.teamcode.Commands;

import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Common.MyRobot;
import org.firstinspires.ftc.teamcode.Common.PedroPathingConstants;
import org.firstinspires.ftc.teamcode.Subsystems.PedroDriveSubsystem;

public class CustomFollowerCommand extends CommandBase {
    private static final double PATH_TIMEOUT_SECONDS = 15;
    private final PedroDriveSubsystem drive;
    private final Path path;
    private final ElapsedTime timer = new ElapsedTime();
    private String status = "Ready";

    public CustomFollowerCommand(MyRobot robot, Path path, double maxPower) {
        this(robot.followerSubsystem, path, maxPower);
    }

    public CustomFollowerCommand(PedroDriveSubsystem drive, Path path, double maxPower) {
        if (!Double.isFinite(maxPower) || maxPower <= 0 || maxPower > 1) {
            throw new IllegalArgumentException("maxPower must be in (0, 1]");
        }
        this.drive = drive;
        // Pedro 3 constrains path speed separately from the drivetrain's wheel-power cap.
        this.path = path.with(PedroPathingConstants.foresightConfig.maxPathSpeed.at(maxPower));
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        PedroPathingConstants.requireForesightTuning();
        timer.reset();
        status = "Following";
        drive.follower.holdEnd.set(false);
        drive.follower.follow(path);
    }

    @Override
    public boolean isFinished() {
        if (!drive.follower.following()) {
            status = "Finished";
            return true;
        }
        if (timer.seconds() >= PATH_TIMEOUT_SECONDS) {
            status = "Timed out - check localization and tuning";
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
        if (interrupted) {
            status = "Interrupted";
        }
    }

    public String getStatus() {
        return status;
    }
}
