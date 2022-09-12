package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.Claw;
import org.firstinspires.ftc.teamcode.hardware.Grabber;
import org.firstinspires.ftc.teamcode.hardware.Lift;

@TeleOp
@Config
public class LiftTune extends LinearOpMode {
    public static double testingPos = 10;
    Lift lift = new Lift();
    DcMotor motor;
    @Override
    public void runOpMode(){
        lift.init(hardwareMap);

        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("Current Position",lift.getCurrentPosition());
            telemetry.addData("Target Position",lift.getTargetPosition());
            if(gamepad1.a){
                lift.setTargetPosition(testingPos);
            }
            else if (gamepad1.b){
                lift.setTargetPosition(0);
            }
            telemetry.addData("Gamepad a", gamepad1.a);
            telemetry.addData("Gamepad B", gamepad1.b);

            telemetry.update();
            lift.update();
        }

    }
}
