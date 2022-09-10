package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Claw;
import org.firstinspires.ftc.teamcode.hardware.Grabber;

@TeleOp
public class ClawTest extends LinearOpMode {
    Claw claw = new Claw();
    @Override
    public void runOpMode(){
        claw.init(hardwareMap);
        waitForStart();

        while(opModeIsActive()) {

            if(gamepad1.a){
            }
            else if (gamepad1.b){
            }

            else{
            }

            claw.run();
        }

    }
}
