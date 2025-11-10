package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.TankDrive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Impulse;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@TeleOp(name="Test2OpMode", group ="Test")
public class Test2OpMode extends OpMode {
    TankDrive tankDrive = new TankDrive();
    Intake intake = new Intake();
    Shooter shooter = new Shooter();
    Impulse impulse = new Impulse();

    double yAxis, xAxis;
    double lastServoTime = 0;
    boolean servoAtZero = true;

    boolean lastRB = false;
    boolean lastLB = false;
    boolean lastA = false;
    boolean lastB = true;

    boolean intakeActive = false;
    boolean shooterActive = false;
    boolean impulseActive = false;

    @Override
    public void init() {
        tankDrive.init(hardwareMap);
        intake.init(hardwareMap);
        shooter.init(hardwareMap);
        impulse.init(hardwareMap);

        tankDrive.setTankDriveDirection(true);
        intake.stopIntake();
        shooter.stopShooter();
        impulse.setServoPosition(0);
    }

    @Override
    public void loop() {
        yAxis = gamepad2.left_stick_y;
        xAxis = gamepad2.right_stick_x;

        tankDrive.setTankDrivePower(yAxis, xAxis);

        this.setIntake();
        this.setShooter();
        this.setImpulse();
        this.stopAll();

        telemetry.addData("Intake", intakeActive ? "ON" : "OFF");
        telemetry.addData("Shooter", shooterActive ? "ON" : "OFF");
        telemetry.addData("Servo Auto", impulseActive ? "ON" : "OFF");
        telemetry.addData("Servo Pos", impulse.getServoPosition());
        telemetry.update();
    }

    public void setIntake() {
        if(gamepad1.left_bumper && !lastLB) {
            intakeActive = !intakeActive;
            if (intakeActive) intake.setIntakePower(1);
            else intake.stopIntake();
        }
        lastLB = gamepad1.left_bumper;
    }

    public void setShooter() {
        if(gamepad1.right_bumper && !lastRB) {
            shooterActive = !shooterActive;
            if (shooterActive) shooter.setShooterPower(1);
            else shooter.stopShooter();
        }
        lastRB = gamepad1.right_bumper;
    }

    public void setImpulse() {
        if(gamepad1.a && !lastA) {
            impulseActive = !impulseActive;
        }
        lastA = gamepad1.a;

        if (impulseActive) {
            double currentTime = getRuntime();

            if (servoAtZero && currentTime - lastServoTime >= 2.0) {
                impulse.setServoPosition(1);
                servoAtZero = false;
                lastServoTime = currentTime;
            } else if (!servoAtZero && currentTime - lastServoTime >= 0.5) {
                impulse.setServoPosition(0);
                servoAtZero = true;
                lastServoTime = currentTime;
            }
        } else {
            impulse.setServoPosition(0);
        }
    }

    public void stopAll() {
        if(gamepad1.b && !lastB) {
            intakeActive = false;
            shooterActive = false;
            impulseActive = false;

            intake.stopIntake();
            shooter.stopShooter();
            impulse.setServoPosition(0);
        }
        lastB = gamepad1.b;
    }
}