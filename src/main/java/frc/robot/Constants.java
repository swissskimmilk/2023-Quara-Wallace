package frc.robot;

import java.util.HashMap;
import java.util.Map;

// Stores constants (duh)
public final class Constants {
    // Motor ids - should be setup as of 3/9
    public static final int RightLeader = 1;
    public static final int RightFollower = 4;
    public static final int LeftLeader = 3;
    public static final int LeftFollower = 2;
    public static final int RightIntake = 7;
    public static final int LeftIntake = 6; 
    public static final int FirstArm = 5; 
    public static final int SecondArm = 8; 

    // Controller ids
    public static final int joystick = 0;
    public static final int xboxController = 1;
    public static final int oldStick = 2;
    
    // Driver station buttons 
    public static final int bConeRange = 1;
    public static final int bCubeRange = 2;

    public static final int bUpperScoring = 6;
    public static final int bMiddleScoring = 10;
    public static final int bLowerScoring = 14;

    public static final int bKnockedIntake = 11;
    public static final int bPortalIntake = 12;
    public static final int bFullyRetract = 13;

    public static final int bFAManualUp = 3;
    public static final int bFAManualDown = 7;

    public static final int bSAManualUp = 4;
    public static final int bSAManualDown = 8;

    public static final int bStopArm = 5;

    // Controller buttons 
    public static final int bStartIntake = 5;
    public static final int bStartOuttake = 6;
    public static final int bStopIntake = 2;

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

    // Motor speeds
    public static final double intakeSpeed = 1;
    public static final double fArmSpeed = 1;
    public static final double sArmSpeed = 1;

    // PID for first arm
    public static final double fArmKP = 10; 
    public static final double fArmKI = 0; 
    public static final double fArmKD = 1;
    public static final double fArmKF = 7;
    public static final double fArmGearRatio = 360;
    public static final double fArmStartingAngle = 30;

    // PID for second arm
    public static final double sArmKP = 5;
    public static final double sArmKI = 0; 
    public static final double sArmKD = 1;
    public static final double sArmKF = 7;
    // Update later 
    public static final double sArmGearRatio = 150;
    public static final double sArmStartingAngle = -145;

    // PID for both arms 
    public static final double armError = 0.5;

    public static final double dTKP = 10;
    public static final double dTKI = 0;
    public static final double dTKD = 2;

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
