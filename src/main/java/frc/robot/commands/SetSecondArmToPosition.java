package frc.robot.commands;

import frc.robot.subsystems.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import com.revrobotics.SparkMaxRelativeEncoder;

public class SetSecondArmToPosition extends CommandBase {
    private ArmSegment armSegment;
    private float positionSetpoint;
    private CANSparkMax motor;

    public SetSecondArmToPosition(ArmSegment m_armSegment, float m_positionSetpoint) {
        armSegment = m_armSegment;
        positionSetpoint = m_positionSetpoint;
        motor = m_armSegment.getMotor();
        addRequirements(m_armSegment);
    }
    
    @Override
    public void initialize() {
        // This will read and use the values off smartboard and use default if it can't. The values on the dashboard are set on robot init to the constants.
        SparkMaxPIDController cPidController = armSegment.getController();
        cPidController.setP(SmartDashboard.getNumber("SA kP", Constants.sArmKP));
        cPidController.setI(SmartDashboard.getNumber("SA kI", Constants.sArmKI));
        cPidController.setD(SmartDashboard.getNumber("SA kD", Constants.sArmKD));
        
        // Do not ask me what a kQuadrature is or why use 4096. Research if it breaks. Anyways this is meant to account for gravity and gets the position of the arm to do so. 
        cPidController.setFF(SmartDashboard.getNumber("SA kF", Constants.sArmKF) * Math.cos(motor.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, 4096).getPosition() / Constants.sArmGearRatio + Constants.sArmStartingAngle));
        cPidController.setOutputRange(SmartDashboard.getNumber("Arm Error", Constants.armError), SmartDashboard.getNumber("Arm Error", Constants.armError));

        // This is probablly right 
        double rotationSetpoint = (positionSetpoint - Constants.sArmStartingAngle) * Constants.sArmGearRatio;
        cPidController.setReference(rotationSetpoint, ControlType.kPosition);

        SmartDashboard.putNumber("SA Setpoint", positionSetpoint);
        
        // This exists if needed to tune the integral term 
        //cPidController.setIZone(Constants.fArmKF);
    }

    @Override
    public void execute() {

    }

    // This can end immediatly since the PID controller perists and should keep the arm stationary 
    @Override
    public boolean isFinished() {
        return true;
    }
}
