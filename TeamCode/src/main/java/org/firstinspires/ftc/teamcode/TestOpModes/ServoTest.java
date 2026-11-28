package org.firstinspires.ftc.teamcode.TestOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ServoTest extends OpMode {

    Servo servo1;

    double servoPosition = 0;
    @Override
    public void init() {
        servo1 = hardwareMap.get(Servo.class, "flowerServo");
    }

    @Override
    public void loop() {
        if(gamepad1.dpadUpWasPressed()){
            servoPosition += 0.1;
        }
        else if(gamepad1.dpadDownWasPressed()){
            servoPosition -= 0.1;
        }
        servo1.setPosition(servoPosition);
        telemetry.addData("Servo position", servoPosition);
        telemetry.update();
    }
}
