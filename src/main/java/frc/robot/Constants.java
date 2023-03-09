package frc.robot;

import java.util.HashMap;
import java.util.Map;

// Stores constants (duh)
public final class Constants {
    // The ids for the motors 
    public static final int RightLeader = 1;
    public static final int RightFollower = 0;
    public static final int LeftLeader = 13;
    public static final int LeftFollower = 12;
    public static final int RightIntake = 5;
    public static final int LeftIntake = 6; 
    public static final int FirstArm = 7; 
    public static final int SecondArm = 8; 

    // The id for the joystick 
    public static final int joystick = 0;

    // The id for the XboxController
    public static final int xboxController = 1;
    
    public static final int bStartIntake = 0;
    public static final int bStartOuttake = 0;
    public static final int bStopIntake = 0;

    // Constants for movement  
    public enum DriveMode {
        tankDrive,
        arcadeDrive;
    }

    // WPI removed the functionality to change between joysticks with a constant 
    // Determines which DriveMode is the default on startup 
    public static final DriveMode defDriveMode = DriveMode.arcadeDrive;

    // Key is the button and the value is the mult 
    public static final Map<Integer, Double> speedMults = new HashMap<>();
    static {
        speedMults.put(8, -0.5);
        speedMults.put(10, -0.75);
        speedMults.put(12, -0.9);
    }
    public static final Map<Integer, Double> rotMults = new HashMap<>();
    static {
        rotMults.put(7, 0.5);
        rotMults.put(9, 0.6);
        rotMults.put(11, 0.7);
    }
    
    public static final double defSpdMult = -0.75;
    public static final double defRotMult = 0.6;

    public static final int intakeSpeed = 1;

    // PID shit 
    public static final int fArmKP = 40; // pretty sure these should be low doubles but idk shrug
    public static final int fArmKI = 0; 
    public static final int fArmKD = 5;
    public static final int sArmKP = 40;
    public static final int sArmKI = 0; 
    public static final int sArmKD = 5;
    public static final int dTKP = 20;
    public static final int dTKI = 0;
    public static final int dTKD = 20;
    public static final double pidPeriod = 20;

    // FF thing that might get deleted later
    public static final double dTKS = 5;
    public static final double dTKV = 5;
    public static final double dTKA = 0;


    // Ronan things that Aidan will have to sort later if he wants organization
    public static final double wheelDiam = 6f;

    // tolerances
    public static final int armTolerance = 1;
}
