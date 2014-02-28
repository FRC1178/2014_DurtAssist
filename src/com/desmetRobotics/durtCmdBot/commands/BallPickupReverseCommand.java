/*THIS COMMAND DOES NOTHING
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.desmetRobotics.durtCmdBot.commands;

import com.desmetRobotics.durtCmdBot.Components;
import com.desmetRobotics.durtCmdBot.subsystems.BallPickup;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author kfrank
 */
public class BallPickupReverseCommand extends Command{
    BallPickup Ballpickup;
    public BallPickupReverseCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        Ballpickup = Components.getInstance().ballPickup;
        requires(Ballpickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Ballpickup.setConveyorSpeed(BallPickup.ROLLER_SHOOT_OUT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //ballPickup.setConveyorSpeed(BallPickup.ROLLER_SHOOT_OUT);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
