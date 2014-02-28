package com.desmetRobotics.durtCmdBot.commands;

import com.desmetRobotics.durtCmdBot.Components;
import edu.wpi.first.wpilibj.command.Command;
import com.desmetRobotics.durtCmdBot.subsystems.Drivetrain;
/**
 *
 * @author John
 */
public class ChangeDriveTypeCommand extends Command {
    Drivetrain driveTrain;
    public ChangeDriveTypeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        driveTrain = Components.getInstance().drivetrain;
        requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        driveTrain.changeDriveType();
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