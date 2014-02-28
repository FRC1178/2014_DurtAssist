package com.desmetRobotics.durtCmdBot.commands;

import com.desmetRobotics.durtCmdBot.Components;
import com.desmetRobotics.durtCmdBot.subsystems.Shoulder;
import edu.wpi.first.wpilibj.command.Command;


public class SetShoulderCommand extends Command {
    private Shoulder shoulder;
    private boolean shoulderState;

    public SetShoulderCommand(boolean shoulderState) {
        super("SetShoulderCommand");
        this.shoulder = Components.getInstance().shoulder;
        this.shoulderState = shoulderState;
        requires(shoulder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shoulder.setShoulderState(shoulderState);
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