package com.desmetRobotics.utils.subsystems;

import com.desmetRobotics.utils.commands.MaintainStateCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author John
 */
public abstract class Subsystem1178 extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    /**
     * Constructs a new Subsystem1178 with no given name.
     */
    public Subsystem1178(){
        super();
    }

    /**
     * Constructs a new Subsystem1178 with the given {@code name}.
     * @param name The name of the new subsystem.
     */
    public Subsystem1178(String name){
        super(name);
    }

    /**
     * Sets the default command of the subsystem to be a MaintainStateCommand.
     */
    protected void initDefaultCommand() {
        setDefaultCommand(new MaintainStateCommand(this));
    }

    /**
     * Updates all subcomponents of this subsystem. Calling this method
     * ensures that the watchdog timers for the subcomponents will not
     * time out.
     */
    public abstract void update();
}