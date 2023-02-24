package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.*;
import frc.robot.RobotContainer;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;

public class MoveDistance extends CommandBase {
    private Drivetrain driveTrain;
    private double distance;
    public MoveDistance(Drivetrain m_driveTrain, double m_distance) {
        driveTrain = m_driveTrain;
        distance = m_distance;
        addRequirements(m_driveTrain);
    }

    private Encoder encoder;
    private PIDController pid;
    

    @Override
    public void initialize() { // ngl idk why this exists, if we know speed motor go turny and we know the distance we want to go why do we need any of this
        // encodery boi get exist
        encoder = new Encoder(0,1,false, Encoder.EncodingType.k4X);
        // idk if encoder will be saving distance between uses of this thing so begone encoder data just in case
        encoder.reset();

        // PID boi get exist(idk what pid period is but we will deal with that later)
        pid = new PIDController(Constants.dTKP, Constants.dTKI, Constants.dTKD, Constants.pidPeriod);
        
        // guess what, 2(PI)(r)^2 is circumfrence I think maybe
        double distPerRot = Math.PI * Math.pow(Constants.wheelDiam/2, 2); 
        encoder.setDistancePerPulse(distPerRot);

        // // ff boi exists now
        // // idk what motors we got on train, if not "permanent-magnet DC motors" disable feedforward
        // SimpleMotorFeedforward smff = new SimpleMotorFeedforward(Constants.dTKS, Constants.dTKV, Constants.dTKA);
        // smff.calc

    }

    @Override
    public void execute() {
        // Smth smth PID good luck 
        // Use WPILIB PID for this 
        
        double pidWantSpeed = pid.calculate(encoder.getDistance(), distance);
        RobotContainer.myRobot.tankDrive(pidWantSpeed, pidWantSpeed);
    }
} 
