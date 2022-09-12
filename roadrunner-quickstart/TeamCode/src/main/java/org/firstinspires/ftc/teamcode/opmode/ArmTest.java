package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Arm;
import org.firstinspires.ftc.teamcode.hardware.Grabber;

@TeleOp
public class ArmTest extends LinearOpMode {
    Arm grab = new Arm();
    @Override
    public void runOpMode(){
        grab.init(hardwareMap);
        waitForStart();

        while(opModeIsActive()) {

            if(gamepad1.a){
                grab.intake();
            }
            else if (gamepad1.b){
                grab.outtake();
            }

            else{
                grab.stop();
            }

            grab.run();
        }

    }
}
