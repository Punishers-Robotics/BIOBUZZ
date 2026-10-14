package org.firstinspires.ftc.teamcode.pedro.procedures;

import com.pedropathing.drivetrain.Drivetrain;
import com.pedropathing.tuning.autotune.TuningOpMode;

abstract class SafeDriveTuningOpMode<Result> extends TuningOpMode<Result> {
    private Drivetrain drivetrain;

    protected SafeDriveTuningOpMode(String name, String description, boolean canStop) {
        super(name, description, canStop);
    }

    protected final Drivetrain registerDrivetrain(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        return drivetrain;
    }

    @Override
    protected final Result runTuningOpMode() throws InterruptedException {
        try {
            Result result = runDriveTuningOpMode();
            if (isStopRequested() || Thread.currentThread().isInterrupted()) {
                throw new InterruptedException("Tuning cancelled before the measurement completed");
            }
            return result;
        } finally {
            if (drivetrain != null) {
                drivetrain.stop();
            }
        }
    }

    protected abstract Result runDriveTuningOpMode() throws InterruptedException;
}
