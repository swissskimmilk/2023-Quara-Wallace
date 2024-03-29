package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;
import frc.robot.commands.*;

public class Autonomous extends SequentialCommandGroup {
  public Autonomous() {
    addCommands(
        RobotContainer.secondArmSegment.moveDown(Constants.sArmSpeed),

        new Delay(0.3),

        RobotContainer.secondArmSegment.stopMovementCommand(),

        new DriveForTime(RobotContainer.drivetrain, 0.5, true),

        RobotContainer.intake.autoOuttakeCommand(),

        new Delay(5), 

        RobotContainer.intake.stopCommand()

        // new StopIntake(RobotContainer.mIntake, RobotContainer.climber),
        
        );
  }
}