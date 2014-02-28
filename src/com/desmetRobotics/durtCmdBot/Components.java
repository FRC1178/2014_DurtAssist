package com.desmetRobotics.durtCmdBot;

import com.desmetRobotics.durtCmdBot.subsystems.Drivetrain;
import com.desmetRobotics.durtCmdBot.subsystems.Shooter;
import com.desmetRobotics.durtCmdBot.subsystems.BallPickup;
import com.desmetRobotics.durtCmdBot.subsystems.Jaw;
import com.desmetRobotics.durtCmdBot.subsystems.Shoulder;
import com.desmetRobotics.durtCmdBot.subsystems.Pneumatics;

/**
 *
 * @author John
 */
public class Components {
    private static Components instance;
    
    public static final int SECONDS = 5; //in AutonmousDriveCommand and in AutonomousCommandGroup
    
    public static final int TIMEOUT = 1;
    
    public static final double AutonSpeed = .5;
    
    public static final int DRIVER_GAMEPAD_USB = 1;
    public static final int TACTICS_GAMEPAD_USB = 2;

    //Digital Input Constants
    private static final int PRESSURE_SWITCH_CHANNEL = 2; //in digital i/o

    //Analog Input Constants

    //PWM constants
    //Drivetrain constants
    // home bot  LF 1  LR 3  RF 2  RR 4
    // phoenix   LF 3  LR 7  RF 1 RR 5  no shooter 10
    // 2013 DURT LF 1  LR 2  RF 4  RR 3   shooter 5
    // 2014 kit bot "Yoshi"   Left 1  Right 4

    private static final int LEFT_FRONT_DRIVE_JAGUAR = 7;
    //private static final int LEFT_REAR_DRIVE_JAGUAR = 2;
    private static final int RIGHT_FRONT_DRIVE_JAGUAR = 5;
    //private static final int RIGHT_REAR_DRIVE_JAGUAR = 3;

   

    private static final int BALLPICKUP_VICTOR = 5;


    //Solenoid constants
    private static final int JAW_OPEN = 7;
    private static final int JAW_CLOSE = 8;
    
    private static final int SHOOTER_THROW = 4;
    //private static final int SHOOTER_STOWED = 11;

    private static final int SHOULDER_DOWN = 6;
    private static final int SHOULDER_UP = 5;

    //Relay constants
    private static final int COMPRESSOR_RELAY_CHANNEL = 2; //relay section

    //Subsystem objects
    public final Drivetrain drivetrain;
    public final Shooter shooter;
    public final BallPickup ballPickup;
    public final Jaw jaw;
    public final Shoulder shoulder;
    public final Pneumatics pneumatics;

    /**
     * Private constructor for the Components singleton. This constructor
     * is only called once and handles creating all the robot subsystems.
     */
    private Components(){
        drivetrain = new Drivetrain(LEFT_FRONT_DRIVE_JAGUAR
                                    ,RIGHT_FRONT_DRIVE_JAGUAR
                                    //,LEFT_REAR_DRIVE_JAGUAR
                                    //,RIGHT_REAR_DRIVE_JAGUAR
                                     );

        shooter = new Shooter(SHOOTER_THROW);

        ballPickup = new BallPickup(BALLPICKUP_VICTOR);

        jaw = new Jaw(JAW_OPEN,JAW_CLOSE);

        shoulder = new Shoulder(SHOULDER_DOWN,SHOULDER_UP);

        pneumatics = new Pneumatics(PRESSURE_SWITCH_CHANNEL, COMPRESSOR_RELAY_CHANNEL);
    }

    /**
     * Returns a new instance of {@link Components}, creating one if null.
     * @return {@link Components}
     */
    public static Components getInstance() {
        if(instance == null){
            instance = new Components();
        }
        return instance;
    }
}
