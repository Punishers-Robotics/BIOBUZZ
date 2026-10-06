package org.firstinspires.ftc.teamcode.Common;

import android.service.dreams.DreamService;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.FlowerServo;
import org.firstinspires.ftc.teamcode.Subsystems.FollowerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TransferSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.FlyWheelSubsystem;

public class MyRobot extends Robot {

    private final OpMode opMode;
    public final TransferSubsystem transferSubsystem;

    public final DriveSubsystem driveSubsystem;
    public final IntakeSubsystem intakeSubsystem;
    public final FlyWheelSubsystem flyWheelSubsystem;

    public final FlowerServo flowerServoSubsystem;

    public LimelightSubsystem limelightSubsystem;
    public FollowerSubsystem followerSubsystem;

    private double driverAimAdjustment = 0;
    private double driverAimAdjustmentLimelight = 0;


    public MyRobot(OpMode opMode) {
        this.opMode = opMode;

        CommandScheduler scheduler = CommandScheduler.getInstance();
        scheduler.reset();

        flowerServoSubsystem = new FlowerServo(this);
        driveSubsystem = new DriveSubsystem(this);
        intakeSubsystem =  new IntakeSubsystem(this);
        transferSubsystem = new TransferSubsystem(this);
        flyWheelSubsystem = new FlyWheelSubsystem(this);
        limelightSubsystem = new LimelightSubsystem(this);
        followerSubsystem = new FollowerSubsystem(this);
        scheduler.registerSubsystem(flowerServoSubsystem);
        scheduler.registerSubsystem(intakeSubsystem);
        scheduler.registerSubsystem(flyWheelSubsystem);
        scheduler.registerSubsystem(intakeSubsystem);
        scheduler.registerSubsystem(driveSubsystem);
        scheduler.registerSubsystem(limelightSubsystem);
        scheduler.registerSubsystem(followerSubsystem);
    }

    public double getDriverAimAdjustment(){
        return this.driverAimAdjustment;
    }

    public void setDriverAimAdjustment(double aimAdjustment){
        this.driverAimAdjustment = aimAdjustment;
    }

    public double getDriverAimAdjustmentLimelight(){
        return this.driverAimAdjustmentLimelight;
    }

    public void setDriverAimAdjustmentLimelight(double aimAdjustment){
        this.driverAimAdjustmentLimelight += aimAdjustment;
    }

    public Telemetry telemetry() {
        return opMode.telemetry;
    }

    public HardwareMap hardwareMap() {
        return opMode.hardwareMap;
    }

    public Gamepad gamepad1() {
        return opMode.gamepad1;
    }

    public Gamepad gamepad2() {
        return opMode.gamepad2;
    }
}
