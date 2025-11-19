package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Impulse {
    private Servo servo;
    private boolean toggleState = false;
    private boolean lastA = false;
    private boolean active = false;
    private boolean atZero = true;
    private double lastTime = 0;

    public void init(HardwareMap hw) {
        servo = hw.get(Servo.class, "impulse");
        servo.setPosition(0.3);
    }

    public void update(Gamepad gp) {
        if (gp.a && !lastA) {
            toggleState = !toggleState;
            servo.setPosition(toggleState ? 0.3 : 0);
        }
        lastA = gp.a;
    }

    public void startAuto() {
        active = true;
        lastTime = 0;
        atZero = true;
        servo.setPosition(0);
    }

    public void stop() {
        active = false;
        servo.setPosition(0.3);
    }

    public boolean isActive() {
        return active;
    }

    public void updateAuto(double now) {
        if (!active) return;
        runCycle(now);
    }

    private void runCycle(double now) {
        if (atZero && now - lastTime >= 2.5) {
            servo.setPosition(0.3);
            atZero = false;
            lastTime = now;
        }
        else if (!atZero && now - lastTime >= 0.5) {
            servo.setPosition(0);
            atZero = true;
            lastTime = now;
        }
    }

    public double getPosition() {
        return servo.getPosition();
    }
}