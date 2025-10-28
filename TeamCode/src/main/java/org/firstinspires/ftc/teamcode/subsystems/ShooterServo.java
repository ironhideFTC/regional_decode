package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ShooterServo {
    private Servo servo;

    public void init(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "servo");
    }

    public void setShooterServoPosition(double servoPosition) {
        servo.setPosition(servoPosition);
    }

    public double getShooterServoPosition() {
        return servo.getPosition();
    }
}