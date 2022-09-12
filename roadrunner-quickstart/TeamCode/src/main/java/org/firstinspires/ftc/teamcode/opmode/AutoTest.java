package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.ConeVision;

/**
 * Created by Mahim on 9/11/22.
 */
@Autonomous(name="AutoTest")
public class AutoTest extends LinearOpMode {
    ConeVision cv = new ConeVision();

    @Override
    public void runOpMode(){
        cv.init(hardwareMap);

        while (!isStarted()){
            telemetry.addData("Avg", cv.pipeline.getPosition());
            telemetry.update();
        }

        while (opModeIsActive()){
            telemetry.addData("Avg", cv.pipeline.getPosition());
            telemetry.update();
        }
        cv.stop();
    }

}

