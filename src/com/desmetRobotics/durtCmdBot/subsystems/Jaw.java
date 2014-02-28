package com.desmetRobotics.durtCmdBot.subsystems;

import com.desmetRobotics.utils.subsystems.Subsystem1178;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author John
 */
public class Jaw extends Subsystem1178{
    public static final boolean JAW_OPEN = false;
    public static final boolean JAW_CLOSED = true;

    Solenoid openMuscle;
    Solenoid closeMuscle;
    private boolean jawState;

    public Jaw(int myOpenSolenoid, int myCloseSolenoid){
        super("Jaw");
        this.openMuscle = new Solenoid(myOpenSolenoid);
        this.closeMuscle = new Solenoid(myCloseSolenoid);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void setJawState(boolean daState){
       jawState = daState;
       update();
    }

    public void update(){
        if(jawState == JAW_OPEN){
            openMuscle.set(true);
            closeMuscle.set(false);
        }else if(jawState == JAW_CLOSED){
            openMuscle.set(false);
            closeMuscle.set(true);
        }else{
            openMuscle.set(false);
            closeMuscle.set(false);
        }
    }

    public void setDefaultCommand(Command command){
        if(getDefaultCommand() != null){
            super.getDefaultCommand().cancel();
        }
        super.setDefaultCommand(command);
    }
}