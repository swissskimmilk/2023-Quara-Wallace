package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

// I know nothing. Figure it out Jordan...
public class RangeSetter extends SubsystemBase {
    private Solenoid sole;
    private Compressor comp;
    public RangeSetter(Solenoid ds, Compressor c) {
        sole = ds;
        comp = c;
        comp.enableDigital();
        sole.set(false);
    }

    public Command coneRangeCommand() {
        return this.runOnce(() -> sole.set(false));
    }

    public Command cubeRangeCommand() {
        return this.runOnce(() -> sole.set(true));
    }

}
