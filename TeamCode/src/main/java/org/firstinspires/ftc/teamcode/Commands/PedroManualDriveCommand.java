package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.PedroDriveSubsystem;

public class PedroManualDriveCommand extends CommandBase {
    private final PedroDriveSubsystem drive;
    private final Gamepad gamepad;

    public PedroManualDriveCommand(PedroDriveSubsystem drive, Gamepad gamepad) {
        this.drive = drive;
        this.gamepad = gamepad;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.follower.manual(
                -gamepad.left_stick_y,
                gamepad.left_stick_x,
                gamepad.right_stick_x);
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }
}
