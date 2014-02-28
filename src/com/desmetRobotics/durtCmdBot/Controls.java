package com.desmetRobotics.durtCmdBot;

import com.desmetRobotics.durtCmdBot.commands.BallPickupCommand;
import com.desmetRobotics.durtCmdBot.commands.BallPickupReverseCommand;
import com.desmetRobotics.durtCmdBot.commands.BallPickupStopCommand;
import com.desmetRobotics.utils.gamepad.Gamepad_XBox360;
import com.desmetRobotics.durtCmdBot.commands.ChangeDriveTypeCommand;
import com.desmetRobotics.durtCmdBot.commands.NewBallPickupCommand;
//import com.desmetRobotics.durtCmdBot.commands.JawCloseCommand;
//import com.desmetRobotics.durtCmdBot.commands.JawOpenCommand;
//import com.desmetRobotics.durtCmdBot.commands.CloseJawCommand;
//import com.desmetRobotics.durtCmdBot.commands.OpenJawCommand;
import com.desmetRobotics.durtCmdBot.commands.PneumaticsOffCommand;
import com.desmetRobotics.durtCmdBot.commands.PneumaticsOnCommand;
import com.desmetRobotics.durtCmdBot.commands.SetJawCommand;
import com.desmetRobotics.durtCmdBot.commands.SetShooterCommand;
import com.desmetRobotics.durtCmdBot.commands.SetShoulderCommand;
import com.desmetRobotics.durtCmdBot.subsystems.BallPickup;
import com.desmetRobotics.durtCmdBot.subsystems.Jaw;
//import com.desmetRobotics.durtCmdBot.commands.SetJawCommand;
import com.desmetRobotics.durtCmdBot.subsystems.Shooter;
import com.desmetRobotics.durtCmdBot.subsystems.Shoulder;
import com.desmetRobotics.utils.gamepad.Gamepad_PS3;
import com.desmetRobotics.utils.gamepad.Gamepad_XBox360Number2;
//import com.desmetRobotics.durtCmdBot.subsystems.Jaw;


public class Controls {
    private static Controls instance;
    private static final double ONE_JOYSTICK_MAGNITUDE = 1;

    public final Gamepad_XBox360 gamepad1;
    public final Gamepad_XBox360 gamepad2;
    

    private Controls(){
        
        gamepad1 = new Gamepad_XBox360(Components.DRIVER_GAMEPAD_USB);
        gamepad2 = new Gamepad_XBox360(Components.TACTICS_GAMEPAD_USB);
        
        
        //gamepad1
        gamepad1.LEFT_JOYSTICK_BUTTON.whenPressed(new PneumaticsOnCommand());
        gamepad1.RIGHT_JOYSTICK_BUTTON.whenPressed(new PneumaticsOffCommand());
        
        //Drivetrain Direction Toggle
        gamepad1.BACK_BUTTON.whenReleased(new ChangeDriveTypeCommand());
        
        
        
        
        
        //gamepad2
        
        gamepad1.START_BUTTON.whenPressed(new SetShooterCommand(Shooter.SHOOTER_ON));
        gamepad1.START_BUTTON.whenReleased(new SetShooterCommand(true));
        
        
        
        
        gamepad1.A_BUTTON.whenPressed(new NewBallPickupCommand(BallPickup.ROLLER_SHOOT_IN));
        gamepad1.A_BUTTON.whenReleased(new NewBallPickupCommand(BallPickup.ROLLER_STOP));
        
        gamepad1.B_BUTTON.whenPressed(new NewBallPickupCommand(BallPickup.ROLLER_SHOOT_OUT));
        gamepad1.B_BUTTON.whenReleased(new NewBallPickupCommand(BallPickup.ROLLER_STOP));
        
        gamepad1.Y_BUTTON.whenPressed(new SetJawCommand(Jaw.JAW_OPEN));
        gamepad1.X_BUTTON.whenPressed(new SetJawCommand(Jaw.JAW_CLOSED));

        gamepad1.RIGHT_BUMPER.whenPressed(new SetShoulderCommand(Shoulder.SHOULDER_UP));
        gamepad1.LEFT_BUMPER.whenPressed(new SetShoulderCommand(Shoulder.SHOULDER_DOWN));
         
    }

    /**
     * Returns the proper instance of Controls.
     * This method creates a new Controls object the first time it is called
     * and returns that object for each subsequent call.
     * @return The current instance of Controls.
     */
    public static Controls getInstance(){
        if(instance == null){
            instance = new Controls();
        }
        return instance;
    }
}
