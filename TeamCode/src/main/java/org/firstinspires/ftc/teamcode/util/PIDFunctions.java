package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDFunctions {
    double integralSum = 0;
    double Kp = PIDConstants.Kp;
    double Ki = PIDConstants.Ki;
    double Kd = PIDConstants.Kd;

    ElapsedTime timer = new ElapsedTime();

    double lastError = 0;

    public PIDFunctions() {
        timer.reset();
    }

    public double PIDControl(double reference, double state) {
        double error = PIDAngleWrap(reference - state);
        double deltaTime = timer.seconds();

        integralSum += error * deltaTime;

        double derivative = (error - lastError) / deltaTime;

        double output = (error * Kp) + (derivative * Kd) + (integralSum * Ki);

        if (output > 1) {
            output = 1;
        } else if (output < -1) {
            output = -1;
        }
        return output;
    }

    public double PIDAngleWrap(double radians){
        while(radians > Math.PI){
            radians -= 2 * Math.PI;
        }
        while(radians < -Math.PI){
            radians += 2 * Math.PI;
        }
        return radians;
    }
}