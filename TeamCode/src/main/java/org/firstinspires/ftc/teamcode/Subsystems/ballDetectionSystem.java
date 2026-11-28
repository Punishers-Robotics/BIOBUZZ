package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Common.MyRobot;

public class ballDetectionSystem extends SubsystemBase {
    MyRobot robot;
    private ElapsedTime artifactDetectionTimer;
    private boolean wasArtifactPreviouslyDetected = false;
    Rev2mDistanceSensor distanceSensor;
    public ballDetectionSystem(MyRobot robot){
        this.robot=robot;
        //distanceSensor=robot.hardwareMap().get(Rev2mDistanceSensor.class, "distanceSensor");
    }
    private void getDetectedArtifact(){

        if (this.isArtifactCurrentlyDetected()){

            if (wasArtifactPreviouslyDetected){

                if (artifactDetectionTimer.seconds() > 0.35){
                    // A ball has been sitting in the slot for more than 0.35 seconds, so we are full
                    robot.isTransferFull = true;
                }
                else {
                    robot.isTransferFull = false;
                }
            }
            else{
                // Start the "detection" timer each time we switch from not finding an artifact
                // to finding an artifact.
                artifactDetectionTimer = new ElapsedTime();
                robot.isTransferFull = false;
            }

            wasArtifactPreviouslyDetected = true;
        }
        else {
            // No artifact is present so reset everything
            artifactDetectionTimer = null;
            wasArtifactPreviouslyDetected = false;
            robot.isTransferFull = false;
        }
    }

    private boolean isArtifactCurrentlyDetected()
    {
        // Checking two sensors to ensure we account for holes in the artifacts
        boolean isPresent = false;
        double distance1ToArtifact = distanceSensor.getDistance(DistanceUnit.INCH);
        //double distance2ToArtifact = distanceSensor2.getDistance(DistanceUnit.INCH);

        robot.telemetry().addData("Distance to artifact 1: ", distance1ToArtifact);
        //       robot.telemetry().addData("Distance to artifact 2: ", distance2ToArtifact);
        //      robot.telemetry().addData("Read count: ", readCount++);

        double INCHES_TO_DETECT_ARTIFACT = 5;
        if (distance1ToArtifact < INCHES_TO_DETECT_ARTIFACT){ //|| distance2ToArtifact < INCHES_TO_DETECT_ARTIFACT){
            isPresent = true;
        }

        return isPresent;
    }
}

