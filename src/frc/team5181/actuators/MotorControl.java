package frc.team5181.actuators;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Spark;

public class MotorControl {
    public enum Model {
        VICTOR_SP,
        SPARK,
        VICTOR,
    }

    private VictorSP vsp;
    private Spark spark;
    private double speedLimit = 1.0;
    private Model model;

    public MotorControl (int port) {
        this(port, Model.SPARK);
    }

    public MotorControl (int port, Model model) {
        this(port , model, false);
    }

    public MotorControl (int port, boolean isReverse) {
        this(port, Model.SPARK, isReverse);
    }

    public MotorControl (int port, Model motorModel,boolean isReverse) {

        this.model = motorModel;

        switch(motorModel) {
            case VICTOR:
            case VICTOR_SP:
                this.vsp = new VictorSP(port);
                this.vsp.setInverted(isReverse);
                break;
            case SPARK:
                this.spark = new Spark(port);
                this.spark.setInverted(isReverse);
            default : break;
        }
    }

    public void setSpeedLimit(double newSpeedLimit) {
        this.speedLimit = newSpeedLimit;
    }


    public void move(double value) {

        switch(this.model) {
            case VICTOR:
            case VICTOR_SP:
                this.vsp.set(value*speedLimit);
                break;
            case SPARK:
                this.spark.set(value*speedLimit);
                break;
            default: break;
        }
    }

    public void move(boolean forward, boolean reverse) {
        if (forward == reverse) this.move(0);
        else if(forward) this.move(1);
        else this.move(-1);
    }
}
