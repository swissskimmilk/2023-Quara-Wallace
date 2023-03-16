package frc.robot.commands;

import frc.robot.subsystems.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import com.revrobotics.SparkMaxRelativeEncoder;

public class SetFirstArmToPosition extends CommandBase {
    private ArmSegment armSegment;
    private float positionSetpoint;
    private CANSparkMax motor;

    public SetFirstArmToPosition(ArmSegment m_armSegment, float m_positionSetpoint) {
        armSegment = m_armSegment;
        positionSetpoint = m_positionSetpoint;
        motor = m_armSegment.getMotor();
        addRequirements(m_armSegment);
    }
    
    @Override
    public void initialize() {
        // This will read and use the values off smartboard and use default if it can't. The values on the dashboard are set on robot init to the constants.
        SparkMaxPIDController cPidController = armSegment.getController();
        cPidController.setP(SmartDashboard.getNumber("FA kP", Constants.fArmKP));
        cPidController.setI(SmartDashboard.getNumber("FA kI", Constants.fArmKI));
        cPidController.setD(SmartDashboard.getNumber("FA kD", Constants.fArmKD));
        
        // Do not ask me what a kQuadrature is or why use 4096. Research if it breaks. Anyways this is meant to account for gravity and gets the position of the arm to do so. 
        cPidController.setFF(SmartDashboard.getNumber("FA kF", Constants.fArmKF) * Math.cos(motor.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, 4096).getPosition() / Constants.fArmGearRatio + Constants.fArmStartingAngle));
        cPidController.setOutputRange(SmartDashboard.getNumber("Arm Error", Constants.armError), SmartDashboard.getNumber("Arm Error", Constants.armError));

        // This is probablly right 
        double rotationSetpoint = (positionSetpoint - Constants.fArmStartingAngle) * Constants.fArmGearRatio;
        cPidController.setReference(rotationSetpoint, ControlType.kPosition);

        SmartDashboard.putNumber("FA Setpoint", positionSetpoint);
        
        // This exists if needed to tune the integral term 
        //cPidController.setIZone(Constants.fArmKF);
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
        ResetArm reset = new ResetArm(armSegment);
        CommandScheduler.getInstance().schedule(reset);
    }

    // This can end immediatly since the PID controller perists and should keep the arm stationary 
    @Override
    public boolean isFinished() {
        
        return true;
    }
}
