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
import edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

//This class is where the bulk of the robot should be declared.
public class RobotContainer {

  // pnumatics range setter
  public static DoubleSolenoid ds = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
  public static Compressor comp = new Compressor(0, PneumaticsModuleType.CTREPCM);

  public static RangeSetter ranSet = new RangeSetter(ds, comp);


  // The controllers 
  public static Joystick driverDashboard = new Joystick(Constants.joystick);
  public static XboxController xController = new XboxController(Constants.xboxController);
  public static Joystick oldJoystick = new Joystick(Constants.oldStick);

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

  // Pneumatics stuff
  public static Compressor pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM); //i dont even know if we are gonna use this
  public static DoubleSolenoid DoublePCM = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
  

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

    new JoystickButton(driverDashboard, Constants.bFAManualUp).onTrue(firstArmSegment.moveUp(Constants.fArmSpeed));
    new JoystickButton(driverDashboard, Constants.bFAManualDown).onTrue(firstArmSegment.moveDown(Constants.fArmSpeed));
    
    new JoystickButton(driverDashboard, Constants.bSAManualUp).onTrue(secondArmSegment.moveUp(Constants.sArmSpeed));
    new JoystickButton(driverDashboard, Constants.bSAManualDown).onTrue(secondArmSegment.moveDown(Constants.sArmSpeed));
    
    new JoystickButton(driverDashboard, Constants.bStopArm).onTrue(firstArmSegment.stopMovementCommand().alongWith(secondArmSegment.stopMovementCommand()));
    
<<<<<<< HEAD
    // Hi Jordan. Make this work. "o/ - Jordan" 
    new JoystickButton(driverDashboard, Constants.bCubeRange).onTrue(rangeSetter.cubeRangeCommand);
    new JoystickButton(driverDashboard, Constants.bConeRange).onTrue(rangeSetter.smthidk);
=======
    // Hi Jordan. Make this work. 
    new JoystickButton(driverDashboard, Constants.bCubeRange).onTrue(ranSet.cubeRangeCommand());
    new JoystickButton(driverDashboard, Constants.bConeRange).onTrue(ranSet.coneRangeCommand());
>>>>>>> 0d5e51feb2677fe2447bbdd4a5f3b939b457858d

    // This is probabally the wrong way to call a command. Also this is 100% temporary
    new JoystickButton(driverDashboard, Constants.bUpperScoring).onTrue(new SetFirstArmToPosition(firstArmSegment, 90));
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
