package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.Arm;
import org.firstinspires.ftc.teamcode.hardware.Claw;
import org.firstinspires.ftc.teamcode.hardware.Grabber;
import org.firstinspires.ftc.teamcode.hardware.Lift;

@TeleOp
public class Main extends LinearOpMode {
    @Override
    public void runOpMode(){
        Lift lift = new Lift();
        Grabber grab = new Grabber();
        Arm arm = new Arm();
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        lift.init(hardwareMap);
        grab.init(hardwareMap);
        arm.init(hardwareMap);


        waitForStart();

        while(opModeIsActive()) {

            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y * 0.3,
                            -gamepad1.left_stick_x * 0.3,
                            -gamepad1.right_stick_x * 0.3
                    )
            );


            if(gamepad1.right_bumper){
                lift.setTargetPosition(19);
            }
            else if(gamepad1.left_bumper){
                lift.setTargetPosition(0);
            }

            if(gamepad1.right_trigger > 0.1){
                arm.intake();
            }
            else if(gamepad1.left_trigger > 0.1){
                arm.outtake();
            }
            else{
                arm.stop();
            }

            if(gamepad1.a){
                grab.intake();
            }
            else if(gamepad1.b){
                grab.outtake();
            }
            else{
                grab.stop();
            }

            drive.update();
            lift.update();
            grab.run();
            arm.run();
        }

    }
}
