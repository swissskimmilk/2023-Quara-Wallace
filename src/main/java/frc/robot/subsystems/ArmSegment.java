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
        armMotor.
        // RobotContainer.joystick.toggleWhenActive(new Move(), true);
    }

    // This method will be called once per scheduler run
    @Override
    public void periodic() {
        RobotContainer.move.execute();
    }
}
