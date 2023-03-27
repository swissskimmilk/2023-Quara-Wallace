package frc.robot;

// Imports subsystems and commands 
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import frc.robot.commands.Autonomous;
import frc.robot.subsystems.RangeSetter;  

//This class is where the bulk of the robot should be declared.
public class RobotContainer {

  // pnumatics range setter
  public static Solenoid ds = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
  public static Compressor comp = new Compressor(0, PneumaticsModuleType.CTREPCM);


  public static RangeSetter ranSet = new RangeSetter(ds, comp);


  // The controllers 
  public static Joystick driverDashboard = new Joystick(Constants.joystick);
  public static XboxController xController = new XboxController(Constants.xboxController);
  public static Joystick oldJoystick = new Joystick(Constants.oldStick);

  // Drivetrain motors 
  public static WPI_VictorSPX rightLeader = new WPI_VictorSPX(Constants.RightLeader);
  public static WPI_VictorSPX rightFollower = new WPI_VictorSPX(Constants.RightFollower);
  public static WPI_VictorSPX leftLeader = new WPI_VictorSPX(Constants.LeftLeader);
  public static WPI_VictorSPX leftFollower = new WPI_VictorSPX(Constants.LeftFollower);

  // Intake  
  public static CANSparkMax rightIntake = new CANSparkMax(Constants.RightIntake, CANSparkMaxLowLevel.MotorType.kBrushless); 
  public static CANSparkMax leftIntake = new CANSparkMax(Constants.LeftIntake, CANSparkMaxLowLevel.MotorType.kBrushless);;
  public static Intake intake = new Intake(rightIntake, leftIntake);

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
  
  // Movement system
  public static Drivetrain drivetrain = new Drivetrain();
  public static Move move = new Move(drivetrain);

  public static Autonomous autonomousCommand = new Autonomous();

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
    // This is wrong now, should be set the controller. It will break something.
    new JoystickButton(xController, Constants.bStartIntake).onTrue(intake.intakeCommand());
    new JoystickButton(xController, Constants.bStartOuttake).onTrue(intake.outtakeCommand());
    new JoystickButton(xController, Constants.bStopIntake).onTrue(intake.stopCommand());

    //new JoystickButton(oldJoystick, Constants.bJoystickFAManualUp).onTrue(firstArmSegment.moveUp(Constants.fArmSpeed));
    //new JoystickButton(oldJoystick, Constants.bJoystickFAManualDown).onTrue(firstArmSegment.moveDown(Constants.fArmSpeed));
    
    new JoystickButton(oldJoystick, Constants.bJoystickSAManualUp).onTrue(secondArmSegment.moveUp(Constants.sArmSpeed));
    new JoystickButton(oldJoystick, Constants.bJoystickSAManualDown).onTrue(secondArmSegment.moveDown(Constants.sArmSpeed));
    
    new JoystickButton(oldJoystick, Constants.bJoystickStopArm).onTrue(firstArmSegment.stopMovementCommand().alongWith(secondArmSegment.stopMovementCommand()));
    
    new JoystickButton(xController, Constants.bControllerCubeRange).onTrue(ranSet.cubeRangeCommand());
    new JoystickButton(xController, Constants.bControllerConeRange).onTrue(ranSet.coneRangeCommand());

    // // This is probabally the wrong way to call a command. Also this is 100% temporary
    new JoystickButton(oldJoystick, 8).onTrue(new SetSecondArmToPosition(secondArmSegment, 135));
    new JoystickButton(oldJoystick, 7).onTrue(new SetSecondArmToPosition(secondArmSegment, 100));
    new JoystickButton(oldJoystick, 12).onTrue(secondArmSegment.setEncoderZero());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    
    return autonomousCommand;
  }
}
