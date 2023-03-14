package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

// I know nothing. Figure it out Jordan... "im literally just praying this works"
public class RangeSetter extends SubsystemBase {
    private DoubleSolenoid sole;
    private Compressor comp;
    public RangeSetter(DoubleSolenoid ds, Compressor c) {
        sole = ds;
        comp = c;
    }
    @Override
    public void initialize() {
        comp.enableDigital();
        sole.set(DoubleSolenoid.Value.kReverse);
        boolean enabled = pcmCompressor.enabled();
    }

<<<<<<< HEAD
    // public Command coneRangeCommand() {
    //     return this.runOnce();
    // }
=======

>>>>>>> c9bb7c1315cbec179d63528f34934b61fa1d0e78
    public Command coneRangeCommand() {
        return this.runOnce(() -> sole.set(DoubleSolenoid.Value.kReverse));
    }

    public Command cubeRangeCommand() {
        return this.runOnce(() -> sole.set(DoubleSolenoid.Value.kForward));
    }

}
