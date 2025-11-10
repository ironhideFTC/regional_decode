package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.TankDrive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Impulse;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@TeleOp(name="TestOpMode", group ="Test")
public class TestOpMode extends OpMode {
    TankDrive tankDrive = new TankDrive();
    Intake intake = new Intake();
    Shooter shooter = new Shooter();
    Impulse impulse = new Impulse();

    double yAxis, xAxis;
    double lastServoTime = 0;
    boolean servoAtZero = true;

    enum mode {
        INTAKE,
        STOP_ALL,
        SHOOTER
    }
    mode modeState = mode.STOP_ALL;

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

        this.setMode();

        switch (modeState) {
            case INTAKE:
                this.intakeMode();
                break;
            case STOP_ALL:
                this.stopAll();
                break;
            case SHOOTER:
                this.shooterMode();
                break;
        }

        telemetry.addData("Intake Power", intake.getIntakePower());
        telemetry.addData("Shooter Power", shooter.getShooterPower());
        telemetry.addData("Servo Pos", impulse.getServoPosition());
        telemetry.addData("Actual Mode", modeState);
        telemetry.update();
    }

    public void setMode() {
        if(gamepad1.right_bumper) {
            modeState = mode.INTAKE;
        } else if(gamepad1.a) {
            modeState = mode.STOP_ALL;
        } else if(gamepad1.left_bumper) {
            modeState = mode.SHOOTER;
        }
    }

    public void intakeMode() {
        tankDrive.setTankDriveDirection(true);
        intake.setIntakePower(1);
        shooter.stopShooter();
        impulse.setServoPosition(0);
    }

    public void stopAll() {
        tankDrive.setTankDriveDirection(true);
        intake.stopIntake();
        shooter.stopShooter();
        impulse.setServoPosition(0);
    }

    public void shooterMode() {
        tankDrive.setTankDriveDirection(false);
        intake.stopIntake();
        shooter.setShooterPower(1);
        this.setImpulse();
    }

    public void setImpulse() {
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
    }
}