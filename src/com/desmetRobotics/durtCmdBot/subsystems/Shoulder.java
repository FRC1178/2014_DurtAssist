package com.desmetRobotics.durtCmdBot.subsystems;

import com.desmetRobotics.utils.subsystems.Subsystem1178;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author John
 */
public class Shoulder extends Subsystem1178 {
    public static final boolean SHOULDER_UP = false;
    public static final boolean SHOULDER_DOWN = true;

    Solenoid downMuscle;
    Solenoid upMuscle;
    
    private boolean shoulderState;

    public Shoulder(int myDownSolenoid, int myUpSolenoid){
        super("Shoulder");
        this.downMuscle = new Solenoid(myDownSolenoid);
        this.upMuscle = new Solenoid(myUpSolenoid);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void setShoulderState(boolean daState){
       shoulderState = daState;
       update();
    }

    public void update(){
        if(shoulderState == SHOULDER_UP){
            upMuscle.set(true);
            downMuscle.set(false);
        }else if(shoulderState == SHOULDER_DOWN){
            upMuscle.set(false);
            downMuscle.set(true);
        }   else{
            upMuscle.set(false);
            downMuscle.set(false);
            }            
         
    }

    public void setDefaultCommand(Command command){
        if(getDefaultCommand() != null){
            super.getDefaultCommand().cancel();
        }
        super.setDefaultCommand(command);
    }
}