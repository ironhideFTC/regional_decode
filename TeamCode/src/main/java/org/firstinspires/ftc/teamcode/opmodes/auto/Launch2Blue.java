package org.firstinspires.ftc.teamcode.opmodes.auto;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.TankDrive;
import org.firstinspires.ftc.teamcode.subsystems.Impulse;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@Autonomous
public class Launch2Blue extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        TankDrive drive = new TankDrive(hardwareMap, new Pose2d(-50, 60, Math.toRadians(135)));
        Intake intake = new Intake();
        Shooter shooter = new Shooter();
        Impulse impulse = new Impulse();

        intake.init(hardwareMap);
        shooter.init(hardwareMap);
        impulse.init(hardwareMap);

        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(-50, 50, Math.toRadians(135)))
                        .lineToY(12)
                        .stopAndAdd(new Launch2Red.shooterImpulse(shooter, 0.9, impulse, 7.5))
                        .turn(Math.toRadians(-135))
                        .stopAndAdd(new Launch2Red.intake(intake, 1))
                        .lineToX(-48)
                        .lineToX(-12)
                        .turn(Math.toRadians(135))
                        .stopAndAdd(new Launch2Red.intakeStop(intake))
                        .stopAndAdd(new Launch2Red.shooterImpulse(shooter, 0.9, impulse, 7.5))
                        .turn(Math.toRadians(-45))
                        .lineToY(-12)
                        .turn(Math.toRadians(-90))
                        .stopAndAdd(new Launch2Red.intake(intake, 1))
                        .lineToX(-48)
                        .lineToX(-12)
                        .turn(Math.toRadians(90))
                        .stopAndAdd(new Launch2Red.intakeStop(intake))
                        .lineToY(12)
                        .turn(Math.toRadians(45))
                        .stopAndAdd(new Launch2Red.shooterImpulse(shooter, 0.25, impulse, 9))

                        .build()
        );
    }

    public static class shooterImpulse implements Action {
        Shooter shooter;
        double power;
        Impulse impulse;
        double duration;
        double startTime = -1;

        public shooterImpulse(Shooter shooter, double power, Impulse impulse, double duration) {
            this.shooter = shooter;
            this.impulse = impulse;
            this.power = power;
            this.duration = duration;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            double now = System.currentTimeMillis() / 1000.0;

            if (startTime < 0) {
                startTime = now;
                shooter.start(power);
                impulse.startAuto();
            }

            impulse.updateAuto(now);

            if (now - startTime >= duration) {
                shooter.stop();
                impulse.stop();
                return false;
            }

            return true;
        }
    }

    public static class intake implements Action {
        Intake intake;
        double power;

        public intake(Intake intake, double power) {
            this.intake = intake;
            this.power = power;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            intake.start();
            return false;
        }
    }

    public static class intakeStop implements Action {
        Intake intake;

        public intakeStop(Intake intake) {
            this.intake = intake;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            intake.stop();
            return false;
        }
    }
}