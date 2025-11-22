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
public class Launch1Red extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        TankDrive drive = new TankDrive(hardwareMap, new Pose2d(12, -60, Math.toRadians(90)));
        Intake intake = new Intake();
        Shooter shooter = new Shooter();
        Impulse impulse = new Impulse();

        intake.init(hardwareMap);
        shooter.init(hardwareMap);
        impulse.init(hardwareMap);

        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(12, -60, Math.toRadians(90)))
                        .lineToY(-56)
                        .turn(Math.toRadians(15))
                        .stopAndAdd(new shooterImpulse(shooter,  intake, 1, impulse, 10))
                        .turn(Math.toRadians(32))
                        .stopAndAdd(new intake(intake, 1))
                        .lineToX(62)
                        .lineToX(0)
                        .stopAndAdd(new intakeStop(intake))
                        .turn(Math.toRadians(30))
                        .stopAndAdd(new shooterImpulse(shooter,  intake, 1, impulse, 7.5))
                        /*.turn(Math.toRadians(113.5))
                        .lineToX(62)
                        .waitSeconds(3)
                        .lineToX(10)
                        .turn(Math.toRadians(-113.5))
                        .stopAndAdd(new shooterImpulse(shooter,  1, impulse, 7.5))
                         */

                        .build()
        );
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

    public static class shooterImpulse implements Action {
        Shooter shooter;
        Intake intake;
        Impulse impulse;
        double power;
        double duration;
        double startTime = -1;
        boolean impulseStarted = false;

        public shooterImpulse(Shooter shooter, Intake intake, double power, Impulse impulse, double duration) {
            this.intake = intake;
            this.shooter = shooter;
            this.impulse = impulse;
            this.power = power;
            this.duration = duration;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            double now = System.currentTimeMillis() / 1000.0;

            if (startTime < 0) {
                startTime = now;
                intake.start();
                shooter.start(power);
            }

            if (now - startTime >= 2) {

                if (!impulseStarted) {
                    impulse.startAuto();
                    impulseStarted = true;
                }

                impulse.updateAuto(now);
            }

            if (now - startTime >= duration) {
                intake.stop();
                shooter.stop();
                impulse.stop();
                return false;
            }

            return true;
        }
    }
}