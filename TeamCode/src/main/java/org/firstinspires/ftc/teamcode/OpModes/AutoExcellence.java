package org.firstinspires.ftc.teamcode.OpModes;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.ParallelDeadlineGroup;
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.AutoTransferCommand;
import org.firstinspires.ftc.teamcode.Commands.ResetRobotForTeleOpCommand;
import org.firstinspires.ftc.teamcode.Commands.ShootCommand;
import org.firstinspires.ftc.teamcode.Common.AutoStartPosition;
import org.firstinspires.ftc.teamcode.Common.FieldPose;
import org.firstinspires.ftc.teamcode.Common.MatchState;
import org.firstinspires.ftc.teamcode.Common.MyRobot;
import org.firstinspires.ftc.teamcode.Common.PathManager;

@Autonomous(name = "Auto Excellence")
public class AutoExcellence extends LinearOpMode {

    private static final long AUTO_DURATION_MS = 31000;
    private static final long SHOOT_DURATION_MS = 400;

    private MyRobot robot;
    private Follower follower;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new MyRobot(this);
        MatchState matchState = MatchState.getInstance();

        while (!isStarted() && !isStopRequested()) {
            matchState.initializeForMatchWithGamepad(robot);
            idle();
        }
        if (isStopRequested()) {
            return;
        }

        follower = robot.followerSubsystem.follower;
        follower.setPose(matchState.getStartingPose());
        PathManager pathManager = new PathManager(robot, matchState.getStartingPose());

        waitForStart();
        if (isStopRequested()) {
            return;
        }

        CommandScheduler scheduler = CommandScheduler.getInstance();
        scheduler.setDefaultCommand(robot.transferSubsystem, new AutoTransferCommand(robot));
        scheduler.schedule(new ParallelDeadlineGroup(
                new WaitCommand(AUTO_DURATION_MS),
                new SequentialCommandGroup(
                        new ParallelDeadlineGroup(
                                pathManager.createFollowPathCommandWithPower(getInitialScorePose(), 0.7),
                                new SequentialCommandGroup(new WaitCommand(700), timedShoot())
                        ),
                        pathManager.createFollowPathCommand(FieldPose.pgpPickup()),
                        new ParallelRaceGroup(
                                pathManager.createFollowPathCommand(FieldPose.pgpIntake()),
                                new WaitCommand(600)
                        ),
                        pathManager.createFollowPathCommand(FieldPose.scoreNearGoalFar()),
                        new WaitCommand(150),
                        timedShoot(),
                        new SequentialCommandGroup(
                                new ParallelRaceGroup(
                                        pathManager.createFollowPathCommand(FieldPose.emptyClassifierPose()),
                                        new WaitCommand(1100)
                                ),
                                pathManager.createFollowPathCommandWithPower(FieldPose.emptyClassifier(), 0.9)
                                        .withTimeout(230),
                                new WaitCommand(1200)
                        ),
                        pathManager.createFollowPathCommandWithPower(FieldPose.scoreNearGoalFar(), 1),
                        timedShoot(),
                        new SequentialCommandGroup(
                                new ParallelRaceGroup(
                                        pathManager.createFollowPathCommand(FieldPose.emptyClassifierPose()),
                                        new WaitCommand(1100)
                                ),
                                pathManager.createFollowPathCommandWithPower(FieldPose.emptyClassifier(), 0.9)
                                        .withTimeout(230),
                                new WaitCommand(1200)
                        ),
                        pathManager.createFollowPathCommandWithPower(FieldPose.scoreNearGoalFar(), 1),
                        timedShoot(),
                        pathManager.createFollowPathCommand(FieldPose.ppgIntake()),
                        new SequentialCommandGroup(
                                pathManager.createFollowPathCommand(FieldPose.scoreNearGoalFar()),
                                new WaitCommand(350),
                                timedShoot()
                        ),
                        new SequentialCommandGroup(
                                new ParallelRaceGroup(
                                        pathManager.createFollowPathCommand(FieldPose.emptyClassifierPose()),
                                        new WaitCommand(1100)
                                ),
                                pathManager.createFollowPathCommandWithPower(FieldPose.emptyClassifier(), 0.9)
                                        .withTimeout(230),
                                new WaitCommand(1200)
                        ),
                        new SequentialCommandGroup(
                                pathManager.createFollowPathCommand(FieldPose.scoreNearGoalFar()),
                                timedShoot()
                        ),
                        pathManager.createFollowPathCommand(FieldPose.gppPickup()),
                        pathManager.createFollowPathCommand(FieldPose.gppIntake()),
                        pathManager.createFollowPathCommandWithPower(FieldPose.scoreNearGoalFar(), 1),
                        timedShoot(),
                        new SequentialCommandGroup(
                                new ParallelRaceGroup(
                                        pathManager.createFollowPathCommand(FieldPose.emptyClassifierPose()),
                                        new WaitCommand(1100)
                                ),
                                pathManager.createFollowPathCommandWithPower(FieldPose.emptyClassifier(), 0.9)
                                        .withTimeout(230),
                                new WaitCommand(1200)
                        ),
                        new ParallelDeadlineGroup(
                                pathManager.createFollowPathCommandWithPower(FieldPose.scoreEndOfAutoNear(), 1),
                                new SequentialCommandGroup(
                                        new WaitCommand(820),
                                        timedShoot(),
                                        new ResetRobotForTeleOpCommand(robot)
                                )
                        )
                )
        ));

        while (opModeIsActive()) {
            scheduler.run();
            telemetry.addData("Pose", follower.pose());
            telemetry.addData("Initial score pose", getInitialScorePose());
            telemetry.update();
        }
        scheduler.reset();
    }

    private Command timedShoot() {
        return new ShootCommand(robot).withTimeout(SHOOT_DURATION_MS);
    }

    private Pose getInitialScorePose() {
        return MatchState.getInstance().getAutoStartPosition() == AutoStartPosition.NearToGoal
                ? FieldPose.scoreNearGoal()
                : FieldPose.scoreFarFromGoal();
    }
}
