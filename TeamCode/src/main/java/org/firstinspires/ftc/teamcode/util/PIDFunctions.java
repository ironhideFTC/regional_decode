package org.firstinspires.ftc.teamcode.util;

public class PIDFunctions {

    public static double PIDControl(double reference, double state, PIDConfig config) {
        double error = angleWrap(reference - state);

        double dt = config.timer.seconds();
        config.timer.reset();

        double derivative = (error - config.lastError) / dt;
        config.integralSum += error * dt;
        config.lastError = error;

        return (error * PIDConstants.Kp) + (config.integralSum * PIDConstants.Ki) + (derivative * PIDConstants.Kd);
    }

    public static double angleWrap(double radians){
        while(radians > Math.PI) radians -= 2 * Math.PI;
        while(radians < -Math.PI) radians += 2 * Math.PI;
        return radians;
    }
}