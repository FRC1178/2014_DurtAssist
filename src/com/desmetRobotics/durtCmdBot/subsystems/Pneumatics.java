package com.desmetRobotics.durtCmdBot.subsystems;

import com.desmetRobotics.utils.subsystems.Subsystem1178;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author John
 */
public class Pneumatics extends Subsystem1178{
    public static final boolean COMPRESSOR_ON = true;
    public static final boolean COMPRESSOR_OFF = false;

    Compressor compressor;
    boolean compressorState;


    public Pneumatics(int pressureSwitchChannel, int compressorChannel){
        super("Pneumatics");
        this.compressor = new Compressor(pressureSwitchChannel, compressorChannel);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void setPneumaticsState(boolean daState){
       compressorState = daState;
       update();
    }

    public void update(){   

        
        if (compressorState == COMPRESSOR_ON && compressor.getPressureSwitchValue() == false){
            compressor.start();
        } else
        {
            compressor.stop();
        }
    }


    public void setDefaultCommand(Command command){
        if(getDefaultCommand() != null){
            super.getDefaultCommand().cancel();
        }
        super.setDefaultCommand(command);
    }
}