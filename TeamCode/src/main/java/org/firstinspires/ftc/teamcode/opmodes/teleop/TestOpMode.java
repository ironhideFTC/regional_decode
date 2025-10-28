package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.TankDrive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.ShooterServo;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@TeleOp(name="TestOpMode", group ="Test")
public class TestOpMode extends OpMode {
    TankDrive tankDrive = new TankDrive();
    Intake intake = new Intake();
    Shooter shooter = new Shooter();
    ShooterServo shooterServo = new ShooterServo();

    double yAxis, xAxis;
    double lastServoTime = 0;
    boolean servoAtZero = true;

    enum mode {
        INTAKE,
        SHOOTER
    }
    mode modeState = mode.INTAKE;

    @Override
    public void init() {
        tankDrive.init(hardwareMap);
        intake.init(hardwareMap);
        shooter.init(hardwareMap);
        shooterServo.init(hardwareMap);

        intake.stopIntake();
        shooter.stopShooter();
        shooterServo.setShooterServoPosition(0);
    }

    @Override
    public void loop() {
        yAxis = gamepad1.left_stick_y;
        xAxis = gamepad1.right_stick_x;

        tankDrive.setTankDrivePower(yAxis, xAxis);

        if(gamepad1.right_bumper) {
            modeState = mode.INTAKE;
        } else if(gamepad1.left_bumper) {
            modeState = mode.SHOOTER;
        }

        switch (modeState) {
            case INTAKE:
                intake.setIntakePower(1);
                shooter.stopShooter();
                shooterServo.setShooterServoPosition(0);

                break;

            case SHOOTER:
                intake.stopIntake();
                shooter.setShooterPower(1);

                double currentTime = getRuntime();

                if (servoAtZero && currentTime - lastServoTime >= 2.0) {
                    shooterServo.setShooterServoPosition(1);
                    servoAtZero = false;
                    lastServoTime = currentTime;
                } else if (!servoAtZero && currentTime - lastServoTime >= 0.5) {
                    shooterServo.setShooterServoPosition(0);
                    servoAtZero = true;
                    lastServoTime = currentTime;
                }

                break;
        }

        telemetry.addData("Drive Power", tankDrive.getTankDrivePower());
        telemetry.addData("Intake Power", intake.getIntakePower());
        telemetry.addData("Shooter Power", shooter.getShooterPower());
        telemetry.addData("Servo Pos", shooterServo.getShooterServoPosition());
        telemetry.addData("Actual Mode", modeState);
        telemetry.update();
    }
}