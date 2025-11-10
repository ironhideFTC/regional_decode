package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Impulse {
    private Servo servo;

    public void init(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "servo");
    }

    public void setServoPosition(double servoPosition) {
        servo.setPosition(servoPosition);
    }

    public double getServoPosition() {
        return servo.getPosition();
    }
}