package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Claw extends Mechanism{
    //0.3 for claws

    public static double leftClamp = 0.0;
    public static double leftUnclamp = 1.0;

    public static double rightClamp = 0.0;
    public static double rightUnclamp = 1.0;

    Servo left;
    Servo right;

    public enum State {
        CLAMP,
        IDLE,
    }

    State currentState = State.IDLE;

    @Override
    public void init(HardwareMap hwMap) {
        left = hwMap.servo.get("left");
        right = hwMap.servo.get("right");
    }

    public void run(){
        if(currentState == State.IDLE){
            unClamp();
        }
        else if(currentState == State.CLAMP){
            clamp();
        }
    }
    
    public void setClamped(){
        currentState = State.CLAMP;
    }
    public void setUnclamped(){
        currentState = State.IDLE;
    }
    
    private void unClamp(){
        left.setPosition(leftUnclamp);
        right.setPosition(rightUnclamp);
    }
    private void clamp(){
        left.setPosition(leftClamp);
        right.setPosition(rightClamp);
    }


}
