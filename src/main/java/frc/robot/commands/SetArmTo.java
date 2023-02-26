package frc.robot.commands;

import frc.robot.subsystems.*;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;

import frc.robot.Constants;

public class SetArmTo extends CommandBase {
    private ArmSegment armSegment;
    private float rotationsSetpoint;
    private CANSparkMax motor;

    public SetArmTo(ArmSegment m_armSegment, float m_rotationsSetpoint) {
        armSegment = m_armSegment;
        rotationsSetpoint = m_rotationsSetpoint;
        motor = m_armSegment.getMotor();
        addRequirements(m_armSegment);
    }

    private PIDController pid;
    private Encoder encoder;

    @Override
    public void initialize() {
        pid = new PIDController(Constants.fArmKP, Constants.fArmKI, Constants.fArmKD);
        pid.setTolerance(1);
    }

    @Override
    public void execute() {
        // Smth smth PID loop good luck

        motor.set(pid.calculate(encoder.getDistance(), rotationsSetpoint));

    }

    @Override
    public boolean isFinished() {
        if (pid.atSetpoint()) {
            return true;
        } 
        else {
            return false;
        }
    }
}
