package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TankDrive {
    private DcMotor right, left;

    public void init(HardwareMap hw) {
        right = hw.get(DcMotor.class, "right");
        left = hw.get(DcMotor.class, "left");

        right.setDirection(DcMotorSimple.Direction.FORWARD);
        left.setDirection(DcMotorSimple.Direction.REVERSE);

        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setTankDrivePower(double yAxis, double xAxis) {
        double rightPower = yAxis + xAxis;
        double leftPower  = yAxis - xAxis;

        double largest = Math.max(Math.abs(rightPower), Math.abs(leftPower));
        if (largest > 1) {
            rightPower /= largest;
            leftPower  /= largest;
        }

        right.setPower(rightPower);
        left.setPower(leftPower);
    }
}