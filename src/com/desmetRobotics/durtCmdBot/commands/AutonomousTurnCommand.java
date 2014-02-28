/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.desmetRobotics.durtCmdBot.commands;

import com.desmetRobotics.durtCmdBot.Components;
import com.desmetRobotics.durtCmdBot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author kfrank
 */
public class AutonomousTurnCommand extends Command{
    private Drivetrain driveTrain;
    private int state;
    
    private double startTime;
    
    public static int TURN_CLOCKWISE = 1;
    public static int TURN_COUNTERCW = -1;
    
    private double turnTime = 2; //seconds
    private double turnSpeed = 0.5;    
    private int direction = TURN_CLOCKWISE;
    
    private static final int BOOTING = 0;
    private static final int TIMED_TURN = 1;
    private static final int FINISHED = 2;
    
    
    //double myseconds;
    public AutonomousTurnCommand(double turntime) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.turnTime = turntime;
        driveTrain = Components.getInstance().drivetrain;
        requires(driveTrain);
        //myseconds = seconds;
        
    }
    
    public AutonomousTurnCommand(int direction, double speed, double turntime){
        this.turnTime = turntime;
        this.direction = direction;
        this.turnSpeed = speed;
        driveTrain = Components.getInstance().drivetrain;
        requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //this.setTimeout(myseconds);
        this.setInterruptible(false);
        state = BOOTING;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        switch(state)
        {
            //initializing, grabs start time
            case BOOTING:
                startTime = Timer.getFPGATimestamp();
                state = TIMED_TURN;
                break;
            
            case TIMED_TURN:
                if (Timer.getFPGATimestamp() >= startTime + turnTime)
                {
                    state = FINISHED;
                    driveTrain.updateTank(0, 0);
                    break;
                }
                driveTrain.updateTank(-turnSpeed*direction, -turnSpeed*direction);
                break;
        }

        }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (state == FINISHED);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
}
