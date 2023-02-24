package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class ArmSegment extends SubsystemBase {
    private CANSparkMax armMotor;
    
    public ArmSegment(CANSparkMax m_armMotor) {
        armMotor = m_armMotor;
    }
    
    public void initialize() {
        armMotor.set(0);
        // RobotContainer.joystick.toggleWhenActive(new Move(), true);
    }

    public CANSparkMax getMotor() {
        return armMotor;
    }
}
