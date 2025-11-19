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
                        .lineToY(-54)
                        .turn(Math.toRadians(-27.5))
                        .stopAndAdd(new shooterImpulse(shooter,  1, impulse, 9))
                        .turn(Math.toRadians(117.5))
                        .lineToX(62)
                        .waitSeconds(3)
                        .lineToX(10)
                        .turn(Math.toRadians(-113.5))
                        .stopAndAdd(new shooterImpulse(shooter,  1, impulse, 9))
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
}