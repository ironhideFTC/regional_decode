package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDConfig {
    public double integralSum = 0;
    public double lastError = 0;
    public ElapsedTime timer = new ElapsedTime();

    public PIDConfig() {
        timer.reset();
    }
}