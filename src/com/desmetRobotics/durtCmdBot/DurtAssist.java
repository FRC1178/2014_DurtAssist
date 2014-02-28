/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.desmetRobotics.durtCmdBot;

import com.desmetRobotics.durtCmdBot.commands.AutonomousDriveCommand;
import com.desmetRobotics.durtCmdBot.commands.AutonomousTurnCommand;
import com.desmetRobotics.durtCmdBot.commands.AutonomousStopDriveCommand;
import com.desmetRobotics.durtCmdBot.commands.BallPickupReverseCommand;
import com.desmetRobotics.durtCmdBot.commands.BallPickupStopCommand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


import com.desmetRobotics.durtCmdBot.subsystems.Drivetrain;
import com.desmetRobotics.durtCmdBot.subsystems.Shooter;
import com.desmetRobotics.durtCmdBot.subsystems.BallPickup;
import com.desmetRobotics.durtCmdBot.subsystems.Jaw;
import com.desmetRobotics.durtCmdBot.subsystems.Shoulder;
import com.desmetRobotics.durtCmdBot.subsystems.Pneumatics;
import com.desmetRobotics.utils.commands.MaintainStateCommand;
import com.desmetRobotics.utils.gamepad.Gamepad_XBox360;
import com.desmetRobotics.durtCmdBot.commands.GamepadDriveCommand;
import com.desmetRobotics.durtCmdBot.commands.NewBallPickupCommand;
import com.desmetRobotics.durtCmdBot.commands.PneumaticsOnCommand;
import com.desmetRobotics.durtCmdBot.commands.SetJawCommand;
import com.desmetRobotics.durtCmdBot.commands.SetShooterCommand;
import com.desmetRobotics.durtCmdBot.commands.SetShoulderCommand;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.command.WaitUntilCommand;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class DurtAssist extends IterativeRobot {
    private Command autonomousCommand;

    /**
     * This function is called when the robot switches between modes (eg. Autonomous,
     * Teleop) to reset running subsystems.
     */
    public void betweenModes() {
        Drivetrain drivetrain = Components.getInstance().drivetrain;
        drivetrain.setDefaultCommand(new MaintainStateCommand(drivetrain));

        Pneumatics compressor = Components.getInstance().pneumatics;
        compressor.setDefaultCommand(new MaintainStateCommand(compressor));

        Shooter shooter = Components.getInstance().shooter;
        shooter.setDefaultCommand(new MaintainStateCommand(shooter));

        BallPickup Ballpickup = Components.getInstance().ballPickup;
        Ballpickup.setDefaultCommand(new MaintainStateCommand(Ballpickup));

        Jaw jaw = Components.getInstance().jaw;
        jaw.setDefaultCommand(new MaintainStateCommand(jaw));

        Shoulder shoulder = Components.getInstance().shoulder;
        shoulder.setDefaultCommand(new MaintainStateCommand(shoulder));

        compressor.setPneumaticsState(Pneumatics.COMPRESSOR_ON);
        stop();
        if(autonomousCommand != null) {
            autonomousCommand.cancel();
            
        }
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        Components.getInstance(); //Create all robot subsystems.
        Controls.getInstance(); //Create all robot controls.
        System.out.println("Robot initialized.");
        }

    public void disabledInit() {
        betweenModes();
    }

    public void disabledPeriodic() {
        
        //Components.getInstance().shoulder.setShoulderState(Shoulder.SHOULDER_UP);
        //Components.getInstance().jaw.setJawState(Jaw.JAW_CLOSED);
        
        stop();
    }

    /**
     * This function is called once at the start of autonomous mode.
     */
    public void autonomousInit(){
        betweenModes();
        
         //Creates Autonomous Command Group
         CommandGroup autonCommand = new CommandGroup();
         autonCommand.addSequential(new PrintCommand("Starting autonomous"));
         autonCommand.addSequential(new AutonomousDriveCommand(AutonomousDriveCommand.DRIVE_FWD, 0.3, 2.0));
         autonCommand.addSequential(new AutonomousTurnCommand(AutonomousTurnCommand.TURN_CLOCKWISE,0.5,1.0));
         autonCommand.addSequential(new WaitCommand(2.0)); //seconds
         

         //Setting the autonCommand = to the autonomousCommand defined at the beginning of this class
         autonomousCommand = autonCommand;
         autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run(); 
        Watchdog.getInstance().feed();
    }

    /**
     * This function is called once at the start of teleop mode.
     */
    public void teleopInit(){
        betweenModes();
        Gamepad_XBox360 driveGamepad = Controls.getInstance().gamepad1;
        Components.getInstance().drivetrain.setDefaultCommand(new GamepadDriveCommand(driveGamepad));
    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called once at the start of test mode.
     */
    public void testInit() {
        betweenModes();
        LiveWindow.setEnabled(false);
        teleopInit();
    }

    /**
     * This function is called periodically during test mode.
     */
    public void testPeriodic() {
        teleopPeriodic();

    }

    /**
     * This function is called to stop <i>each</i> subsystem.
     */
    public void stop() {
        Components.getInstance().drivetrain.updateStuff(0,0,0,0);
        //Components.getInstance().shooter.setShooterState(Shooter.SHOOTER_OFF);
        Components.getInstance().ballPickup.setConveyorSpeed(BallPickup.ROLLER_STOP);
        Components.getInstance().jaw.setJawState(Jaw.JAW_CLOSED);       
        Components.getInstance().shoulder.setShoulderState(Shoulder.SHOULDER_DOWN);
        Components.getInstance().pneumatics.setPneumaticsState(Pneumatics.COMPRESSOR_ON);
    }
}
