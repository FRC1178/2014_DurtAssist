/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.desmetRobotics.durtCmdBot.commands;

import com.desmetRobotics.durtCmdBot.Components;
import com.desmetRobotics.durtCmdBot.subsystems.Jaw;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author kfrank
 */
public class JawCloseCommand extends Command{
    Jaw jaw;
    public JawCloseCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        jaw = Components.getInstance().jaw;
        requires(jaw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        jaw.setJawState(Jaw.JAW_CLOSED);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
