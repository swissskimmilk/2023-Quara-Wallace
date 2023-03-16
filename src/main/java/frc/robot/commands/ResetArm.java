package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSegment;

public class ResetArm extends CommandBase {
    private ArmSegment armSegment; 
    
    public ResetArm(ArmSegment m_armSegment) {
        armSegment = m_armSegment;
    }

    @Override
    public void initialize() {
        armSegment.getMotor().set(-0.5);
    }

    @Override
    public boolean isFinished() {
        // no clue if this works, also dk how the get position works exactly / if resets
        if (armSegment.getMotor().getEncoder().getPosition() == 0) {
            armSegment.getMotor().set(0);
            return true;
        } else {
            return false;
        }
    }   
}
