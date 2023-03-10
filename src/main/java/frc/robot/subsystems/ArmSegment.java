package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class ArmSegment extends SubsystemBase {
    private CANSparkMax armMotor;
    private SparkMaxPIDController pidController;
    
    public ArmSegment(CANSparkMax m_armMotor) {
        armMotor = m_armMotor;
        pidController = armMotor.getPIDController();
    }
    
    public void initialize() {
        armMotor.set(0);
        // RobotContainer.joystick.toggleWhenActive(new Move(), true);
    }

    public CANSparkMax getMotor() {
        return armMotor;
    }

    public SparkMaxPIDController getController() {
        return pidController;
    }

    public Command moveUp(double speed) {
        return this.runOnce(() -> armMotor.set(speed));
    }

    public Command moveDown(double speed) {
        return this.runOnce(() -> armMotor.set(speed));
    }
}
