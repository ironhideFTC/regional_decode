package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TankDrive {
    private DcMotor rightMotor, leftMotor;

    public void init(HardwareMap hardwareMap) {
        rightMotor = hardwareMap.get(DcMotor.class, "right");
        leftMotor = hardwareMap.get(DcMotor.class, "left");

        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setTankDrivePower(double yAxis, double xAxis) {
        double rightPower = yAxis + xAxis;
        double leftPower = yAxis - xAxis;
        double largest = Math.max(Math.abs(rightPower), Math.abs(leftPower));
        
        if(largest > 1.0) {
            rightPower /= largest;
            leftPower /= largest;
        }

        rightMotor.setPower(rightPower);
        leftMotor.setPower(leftPower);
    }

    public void setTankDrivePID(double output){
        rightMotor.setPower(output);
        leftMotor.setPower(-output);
    }

    public void setTankDriveDirection(boolean direction) {
        if(direction) {
            rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
            rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        }
    }
}