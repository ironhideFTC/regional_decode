package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private DcMotor motor;
    private boolean active = false;

    public void init(HardwareMap hw) {
        motor = hw.get(DcMotor.class, "intake");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.stop();
    }

    public void toggle() {
        active = !active;
        if (active) this.start();
        else this.stop();
    }

    public void start() { motor.setPower(1); }
    public void stop()  { motor.setPower(0); }

    public boolean isActive() { return active; }

    public void update(Gamepad gp) {
        if (gp.right_bumper && !lastRb) this.toggle();
        lastRb = gp.right_bumper;
    }

    private boolean lastRb = false;
}