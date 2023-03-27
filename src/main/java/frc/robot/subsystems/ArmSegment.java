package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkMaxRelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class ArmSegment extends SubsystemBase {
    private CANSparkMax armMotor;
    private SparkMaxPIDController pidController;
    private RelativeEncoder encoder;
    
    public ArmSegment(CANSparkMax m_armMotor) {
        armMotor = m_armMotor;
        pidController = armMotor.getPIDController();
        armMotor.set(0);
        encoder = armMotor.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
        encoder.setPosition(0);
    }

    public CANSparkMax getMotor() {
        return armMotor;
    }

    public double getPosition() {
        return encoder.getPosition();
    }

    public SparkMaxPIDController getController() {
        return pidController;
    }

    public Command moveUp(double speed) {
        return this.runOnce(() -> armMotor.set(speed));
    }

    public Command moveDown(double speed) {
        return this.runOnce(() -> armMotor.set(-speed));
    }

    public Command stopMovementCommand() {
        return this.runOnce(() -> armMotor.set(0));
    }

    public Command setEncoderZero() {
        return this.runOnce(() -> encoder.setPosition(0));
    }
}
