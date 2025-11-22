package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Impulse {

    private Servo servo1;
    private Servo servo2;

    private boolean toggleState = false;
    private boolean lastA = false;
    private boolean lastX = false;

    private boolean active = false;
    private int step = 0;
    private double lastTime = 0;

    private static final double STEP_DELAY = 1;

    public void init(HardwareMap hw) {
        servo1 = hw.get(Servo.class, "impulse1");
        servo2 = hw.get(Servo.class, "impulse2");

        servo1.setPosition(1);
        servo2.setPosition(0.35);
    }

    public void update(Gamepad gp) {
        if (gp.a && !lastA) {
            toggleState = !toggleState;
            servo2.setPosition(toggleState ? 0.35 : 0);
        }
        lastA = gp.a;

        if (gp.x && !lastX) {
            toggleState = !toggleState;
            servo1.setPosition(toggleState ? 1 : 0);
        }
        lastX= gp.x;
    }

    public void startAuto() {
        active = true;
        step = 0;
        lastTime = 0;

        servo1.setPosition(1);
        servo2.setPosition(0.35);
    }

    public void stop() {
        active = false;
        servo1.setPosition(1);
        servo2.setPosition(0.35);
    }

    public void updateAuto(double now) {
        if (!active) return;

        if (now - lastTime < STEP_DELAY) return;
        lastTime = now;

        switch (step) {
            case 0:
                servo2.setPosition(0);
                step = 1;
                break;

            case 1:
                servo1.setPosition(0);
                step = 2;
                break;

            case 2:
                servo2.setPosition(0.35);
                step = 3;
                break;

            case 3:
                servo1.setPosition(1);
                step = 0;
                break;
        }
    }

    public double getPosition1() { return servo1.getPosition(); }
    public double getPosition2() { return servo2.getPosition(); }
}