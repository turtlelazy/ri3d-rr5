package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.checkerframework.checker.units.qual.Speed;

@Config
public class Arm extends Mechanism{
    DcMotor left;
    DcMotor right;
    public static double idlePower = 0.05;
    public static double power = 0.3;

    boolean forwards = true;
    @Override
    public void init(HardwareMap hwMap) {
        left = hwMap.dcMotor.get("leftSwing");
        right = hwMap.dcMotor.get("rightSwing");
        right.setDirection(DcMotorSimple.Direction.REVERSE);

//        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }
    public enum SpeedState{
        IDLE,
        IN,
        OUT
    }

    SpeedState currentSpeed = SpeedState.IDLE;

    public void run(){
        if(currentSpeed == SpeedState.IDLE){
            if(forwards) setPower(idlePower);
            else setPower(-idlePower);
        }

        else if (currentSpeed == SpeedState.IN){
            setPower(power);
        }

        else if (currentSpeed == SpeedState.OUT){
            setPower(-power);
        }

    }

    public void stop(){
        currentSpeed = SpeedState.IDLE;
    }

    public void outtake(){
        currentSpeed = SpeedState.OUT;
        forwards = false;
    }

    public void intake(){
        currentSpeed = SpeedState.IN;
        forwards = true;
    }

    private void setPower(double power){
        left.setPower(power);
        right.setPower(power);
    }

}
