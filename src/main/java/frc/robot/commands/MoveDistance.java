package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class MoveDistance extends CommandBase {
    private Drivetrain driveTrain;
    private float distance;
    public MoveDistance(Drivetrain m_driveTrain, float m_distance) {
        driveTrain = m_driveTrain;
        distance = m_distance;
        addRequirements(m_driveTrain);
    }

    public void execute() {
        // Smth smth PID good luck 
        // Use WPILIB PID for this 
    }
} 
