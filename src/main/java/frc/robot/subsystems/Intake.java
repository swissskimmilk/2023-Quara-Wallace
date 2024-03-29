package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {
    private CANSparkMax rightIntake; 
    private CANSparkMax leftIntake;

    public Intake(CANSparkMax m_rightIntake, CANSparkMax m_leftIntake) {
        rightIntake = m_rightIntake;
        leftIntake = m_leftIntake;
        // Second parameter inverts direction since the motors should be turning opposite directions at all times  
        leftIntake.follow(rightIntake, true);
        rightIntake.set(0);
    }

    public Command intakeCommand() {
        return this.runOnce(() -> rightIntake.set(Constants.intakeSpeed));
    }

    public Command outtakeCommand() {
        return this.runOnce(() -> rightIntake.set(-Constants.outtakeSpeed));
    }

    public Command stopCommand() {
        return this.runOnce(() -> rightIntake.set(0));
    }

    public Command autoOuttakeCommand() {
        return this.runOnce(() -> rightIntake.set(-Constants.autoOuttakeSpeed));
    }


}
