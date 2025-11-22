package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Shooter {
    private DcMotorEx motor;
    private boolean active = false;

    public void init(HardwareMap hw) {
        motor = hw.get(DcMotorEx.class, "shooter");
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.stop();
    }

    public void toggleHigh() {
        active = !active;
        if (active) motor.setPower(1);
        else this.stop();
    }

    public void toggleLow() {
        active = !active;
        if (active) motor.setPower(0.9);
        else this.stop();
    }

    public void start(double power) { motor.setPower(power); }
    public void stop() { motor.setPower(0); }
    public double getPower() { return motor.getPower(); }
    public boolean isActive() { return active; }

    public void update(Gamepad gp) {
        if (gp.left_bumper && !lastLB) this.toggleHigh();
        if (gp.dpad_down && !lastDown) this.toggleLow();

        lastLB = gp.left_bumper;
        lastDown = gp.dpad_down;
    }

    private boolean lastLB = false;
    private boolean lastDown = false;
}