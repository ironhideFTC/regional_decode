package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.drive.TankDrive;
import org.firstinspires.ftc.teamcode.util.PIDConfig;
import org.firstinspires.ftc.teamcode.util.PIDFunctions;

@TeleOp(name = "PIDAngleOpMode")
public class PIDAngleOpMode extends LinearOpMode {

    TankDrive tankDrive = new TankDrive();
    PIDConfig pidConfig = new PIDConfig();

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        tankDrive.init(hardwareMap);

        IMU imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.DOWN,
                        RevHubOrientationOnRobot.UsbFacingDirection.RIGHT
                )
        );
        imu.initialize(parameters);
        imu.resetYaw();

        double referenceAngle = Math.toRadians(90);
        waitForStart();

        while(opModeIsActive()){
            double currentAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            double power = PIDFunctions.PIDControl(referenceAngle, currentAngle, pidConfig);
            tankDrive.setTankDrivePID(power);
        }
    }
}