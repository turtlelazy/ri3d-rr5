package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Grabber extends Mechanism{
    CRServo left;
    CRServo right;

    public enum SpeedState{
        IDLE,
        IN,
        OUT
    }

    SpeedState currentSpeed = SpeedState.IDLE;

    @Override
    public void init(HardwareMap hwMap) {
        left = hwMap.crservo.get("left");
        right = hwMap.crservo.get("right");
    }

    public void run(){
        if(currentSpeed == SpeedState.IDLE){
            setPower(0);
        }

        else if (currentSpeed == SpeedState.IN){
            setPower(1);
        }

        else if (currentSpeed == SpeedState.OUT){
            setPower(-1);
        }

    }

    public void stop(){
        currentSpeed = SpeedState.IDLE;
    }

    public void outtake(){
        currentSpeed = SpeedState.OUT;
    }

    public void intake(){
        currentSpeed = SpeedState.IN;
    }

    private void setPower(double power){
        left.setPower(power);
        right.setPower(power);
    }

}
