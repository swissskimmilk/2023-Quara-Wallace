package frc.robot.commands;
import frc.robot.subsystems.*;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SetArmTo extends CommandBase {
    private ArmSegment armSegment;
    private float rotationsSetpoint;
    private CANSparkMax motor;
    public SetArmTo(ArmSegment m_armSegment, float m_rotationsSetpoint) {
        armSegment = m_armSegment;
        rotationsSetpoint = m_rotationsSetpoint;
        motor = m_armSegment.getMotor();
        addRequirements(m_armSegment);
    }   

    public void execute() {
        // Smth smth PID loop good luck 
    }
}
