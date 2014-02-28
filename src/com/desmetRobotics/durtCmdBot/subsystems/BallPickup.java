package com.desmetRobotics.durtCmdBot.subsystems;

import com.desmetRobotics.utils.subsystems.Subsystem1178;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author John
 */
public class BallPickup extends Subsystem1178{
    public static final double ROLLER_SHOOT_IN = -1;
    public static final double ROLLER_SHOOT_OUT = 1;
    //public static final double ROLLER_COLLECT_IN = -0.5;
    //public static final double ROLLER_COLLECT_OUT = 0.5;
    public static final double ROLLER_STOP = 0;

    Victor TopWheel;
    double velocity;

    public BallPickup(int myRoller){
        super("BallPickup");
        this.TopWheel = new Victor(myRoller);
    }

    /**
     * Sets the speed of the roller inside the robot. The values can
     * range from -1.0 to 1.0 and represent the speed of the roller.
     * A positive value will set the roller to bring balls into the robot
     * at the fastest possible speed. A negative value will remove balls
     * from the robot.
     * @param velocity The new speed of the roller as defined above.
     */
    public void setConveyorSpeed(double velocity){
        this.velocity = velocity;
        update();
    }

    public double getConveyorVelocity() {
        return velocity;
    }

    public void update(){
        TopWheel.set(velocity);
    }


    public void setDefaultCommand(Command command){
        if(getDefaultCommand() != null){
            super.getDefaultCommand().cancel();
        }
        super.setDefaultCommand(command);
    }
}