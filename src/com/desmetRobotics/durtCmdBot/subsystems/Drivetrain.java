package com.desmetRobotics.durtCmdBot.subsystems;

import com.desmetRobotics.durtCmdBot.Components;
import com.desmetRobotics.utils.subsystems.Subsystem1178;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;


public class Drivetrain  extends Subsystem1178{
    private RobotDrive robotDrive;
    
    private Victor leftVictor;
    private Victor rightVictor;
    private Victor rearrightVictor;
    private Victor rearleftVictor;
    
    //arcade drive parameters
    private double moveArcade;
    private double rotateArcade;
    //tank drive parameters
    private double leftTank;
    private double rightTank;
    //mecanum drive parameters
    private double magnitudeMecanum;
    private double directionMecanum;
    private double rotateMecanum;
    
    private static final double leftTankCompensate = -0.15;
    private static final double rightTankCompensate = 0.0;



    //Drive Type constants
    private final int ARCADE_DRIVE = 1;
    private final int TANK_DRIVE = 2;
    private final int MECANUM_DRIVE = 3;


    private int driveType = ARCADE_DRIVE;

    private static final DriverStationLCD.Line OUTPUT_LINE1 = DriverStationLCD.Line.kUser1;
    private DriverStationLCD textOutput;

    public Drivetrain(int frontLeft, int frontRight) {
        leftVictor = new Victor(frontLeft);
        rightVictor = new Victor(frontRight);
        //rearleftVictor = new Victor(rearLeft);
        //rearrightVictor = new Victor(rearRight);
        this.robotDrive = new RobotDrive(leftVictor, rightVictor);
//        public Drivetrain(int leftSide, int rightSide){
//        this.robotDrive = new RobotDrive(leftSide,rightSide);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);

        textOutput = DriverStationLCD.getInstance();
    }

    public void changeDriveType()
    {
        driveType++;
        if (driveType > TANK_DRIVE) driveType = ARCADE_DRIVE;
//        if (driveType > MECANUM_DRIVE) driveType = ARCADE_DRIVE;
    }

    public void setDriveType(int daDrivetype)
    {
        this.driveType = daDrivetype;
        if (driveType > TANK_DRIVE) driveType = ARCADE_DRIVE;
//        if (driveType > MECANUM_DRIVE) driveType = ARCADE_DRIVE;
    }

    public int getDriveType()
    {
        return driveType;
    }

    public void updateStuff(double moveArcade
                            ,double rotateArcade
                            ,double leftTank
                            ,double rightTank
                           // ,double magnitudeMecanum
                           // ,double directionMecanum
                           // ,double rotateMecanum
                            )
    {
        this.moveArcade = moveArcade;
        this.rotateArcade = rotateArcade;
        this.leftTank = leftTank;
        this.rightTank = rightTank;
        //this.magnitudeMecanum = magnitudeMecanum;
        //this.directionMecanum = directionMecanum;
        //this.rotateMecanum = rotateMecanum;
        update();
    }
    
    public void updateTank(double leftTank, double rightTank){
        setDriveType(TANK_DRIVE);
        this.leftTank = leftTank + leftTankCompensate;
        this.rightTank = rightTank + rightTankCompensate;
        update();
    }
    
    public void updateArcade(double moveArcade, double rotateArcade){
        setDriveType(ARCADE_DRIVE);
        this.moveArcade = moveArcade;
        this.rotateArcade = rotateArcade;
        update();
    }

    public void update(){
        //****************************************************
        // Based the selected DriveType, run the proper code
        //
        //****************************************************

        if(driveType == TANK_DRIVE){
            robotDrive.tankDrive(leftTank,rightTank);
            textOutput.println(OUTPUT_LINE1, 1, "TANK_DRIVE   ");
            textOutput.updateLCD();
        }else
        {
            if(driveType == ARCADE_DRIVE){
                // This works well even when using the Mecanum wheels
                // Forward and back. Sideways motion turns robot.
                robotDrive.arcadeDrive(moveArcade, rotateArcade);
                textOutput.println(OUTPUT_LINE1, 1, "ARCADE_DRIVE   ");
                textOutput.updateLCD();
            }//else{
             //   robotDrive.mecanumDrive_Polar(magnitudeMecanum, directionMecanum, rotateMecanum);
             //   textOutput.println(OUTPUT_LINE1, 1, "MECANUM_DRIVE");
             //  textOutput.updateLCD();
            //}
        }
    }
    
    

    
    
    
    public void setDefaultCommand(Command command){
        if(getDefaultCommand() != null){
            super.getDefaultCommand().cancel();
        }
        super.setDefaultCommand(command);
    }
}
