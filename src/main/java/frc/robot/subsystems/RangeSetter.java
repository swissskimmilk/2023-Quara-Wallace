package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

// I know nothing. Figure it out Jordan... "im literally just praying this works"
public class RangeSetter extends SubsystemBase {
    public RangeSetter() {}
 
    public void initialize() { // should set the robot to neutral (cone state) and turn solenoid off(?)
        exampleDoublePCM.set(DoubleSolenoid.Value.kReverse);
        exampleDoublePCM.set(DoubleSolenoid.Value.kOff);
    }

     public Command coneRangeCommand() { 
        exampleDoublePCM.set(DoubleSolenoid.Value.kReverse);
        return this.runOnce();
    }

     public Command cubeRangeCommand() {
        exampleDoublePCM.set(DoubleSolenoid.Value.kForward);
        return this.runOnce();
     }

       


}
