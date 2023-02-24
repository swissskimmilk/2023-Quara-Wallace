package frc.robot;

// Imports subsystems and commands 
import frc.robot.subsystems.*;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

//This class is where the bulk of the robot should be declared.
public class RobotContainer {

  // The controllers 
  public static Joystick driverDashboard = new Joystick(Constants.joystick);
  public static XboxController xController = new XboxController(Constants.xboxController);

  // Movement system
  public static Drivetrain drivetrain = new Drivetrain();
  public static Move move = new Move(drivetrain);

  // Drivetrain motors 
  public static WPI_VictorSPX rightLeader = new WPI_VictorSPX(Constants.RightLeader);
  public static WPI_VictorSPX rightFollower = new WPI_VictorSPX(Constants.RightFollower);
  public static WPI_VictorSPX leftLeader = new WPI_VictorSPX(Constants.LeftLeader);
  public static WPI_VictorSPX leftFollower = new WPI_VictorSPX(Constants.LeftFollower);

  // Intake  
  public static CANSparkMax rightIntake = new CANSparkMax(Constants.RightIntake, CANSparkMaxLowLevel.MotorType.kBrushless); 
  public static CANSparkMax leftIntake = new CANSparkMax(Constants.LeftIntake, CANSparkMaxLowLevel.MotorType.kBrushless);;
  private static Intake intake = new Intake(rightIntake, leftIntake);

  // Arm motors
  public static CANSparkMax firstArm = new CANSparkMax(Constants.FirstArm, CANSparkMaxLowLevel.MotorType.kBrushless);
  public static ArmSegment firstArmSegment = new ArmSegment(firstArm);
  public static CANSparkMax secondArm = new CANSparkMax(Constants.SecondArm, CANSparkMaxLowLevel.MotorType.kBrushless);
  public static ArmSegment secondArmSegment = new ArmSegment(secondArm);

  // The groups for the motors 
  public static MotorControllerGroup rightGroup = new MotorControllerGroup(rightLeader, rightFollower);
  public static MotorControllerGroup leftGroup = new MotorControllerGroup(leftLeader, leftFollower);

  // DifferentialDrive is what actually performs the movements 
  public static DifferentialDrive myRobot = new DifferentialDrive(leftGroup, rightGroup);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(driverDashboard, Constants.bStartIntake).onTrue(intake.intakeCommand());
    new JoystickButton(driverDashboard, Constants.bStartOuttake).onTrue(intake.outtakeCommand());
    new JoystickButton(driverDashboard, Constants.bStopIntake).onTrue(intake.stopCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
  // }
}
