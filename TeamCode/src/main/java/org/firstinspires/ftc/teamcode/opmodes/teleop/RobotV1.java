package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.TankDrive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Impulse;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@TeleOp(name="RobotV1")
public class RobotV1 extends OpMode {
    TankDrive drive = new TankDrive();
    Intake intake = new Intake();
    Shooter shooter = new Shooter();
    Impulse impulse = new Impulse();

    @Override
    public void init() {
        drive.init(hardwareMap);
        intake.init(hardwareMap);
        shooter.init(hardwareMap);
        impulse.init(hardwareMap);
    }

    @Override
    public void loop() {
        drive.setTankDrivePower(
                gamepad2.left_stick_y,
                gamepad2.right_stick_x
        );

        intake.update(gamepad1);
        shooter.update(gamepad1);
        impulse.update(gamepad1);

        if (gamepad1.b) this.stopAll();

        telemetry.addData("Intake", intake.isActive());
        telemetry.addData("Shooter", shooter.isActive());
        telemetry.addData("Impulse", impulse.isActive());
        telemetry.addData("Impulse Pos", impulse.getPosition());
        telemetry.update();
    }

    private void stopAll() {
        intake.stop();
        shooter.stop();
        impulse.stop();
    }
}