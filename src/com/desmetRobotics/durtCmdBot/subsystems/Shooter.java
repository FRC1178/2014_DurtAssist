package com.desmetRobotics.durtCmdBot.subsystems;

import com.desmetRobotics.utils.subsystems.Subsystem1178;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author John
 */

public class Shooter extends Subsystem1178{
    public static final boolean SHOOTER_ON = false;
    //public static final boolean SHOOTER_OFF = true;

    Solenoid shooterOn;
    //Solenoid shooterOff;
    private boolean shooterState;


    public Shooter(int myOnSolenoid){
        super("Shooter");
        this.shooterOn = new Solenoid(myOnSolenoid);
        //this.shooterOff = new Solenoid(myOffSolenoid);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void setShooterState(boolean daState){
       shooterState = daState;
       update();
    }


    public void update(){
       if (shooterState == SHOOTER_ON){
           shooterOn.set(true);
           //shooterOff.set(false);
       }else{
           shooterOn.set(false);
       }
       
    }

    public void setDefaultCommand(Command command){
        if(getDefaultCommand() != null){
            super.getDefaultCommand().cancel();
        }
        super.setDefaultCommand(command);
    }
}