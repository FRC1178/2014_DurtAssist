package com.desmetRobotics.durtCmdBot.commands;

import com.desmetRobotics.durtCmdBot.Components;
import edu.wpi.first.wpilibj.command.Command;
import com.desmetRobotics.durtCmdBot.subsystems.Shooter;
/**
 *
 * @author John
 */
public class SetShooterCommand extends Command {
    private Shooter shooter;
    private boolean shooterState;

    public SetShooterCommand(boolean shooterState) {
        super("SetShooter");
        this.shooter = Components.getInstance().shooter;
        this.shooterState = shooterState;
        requires(shooter);
    }

    protected void initialize() {
        shooter.setShooterState(shooterState);
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {

    }

    protected void interrupted() {

    }

}