package frc.team5181.sensors;

/**
 * Copyright
 */

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

final public class Gamepad { //Why final? Because then Java would try to final every method it can, which is faster.

    public enum Mode {
        GAMEPAD,
        JOYSTICK
    }

    final public class ValueContainer {
        //Gamepad
        public double jLeftX = 0;
        public double jLeftY = 0;
        public double jRightX = 0;
        public double jRightY = 0;
        public boolean jLeft = false;
        public boolean jRight = false;
        public int dPad = -1;
        public boolean A = false;
        public boolean B = false;
        public boolean X = false;
        public boolean Y = false;
        public boolean LB = false;
        public boolean RB = false;
        public double LT = 0;
        public double RT = 0;
        public boolean back = false;
        public boolean start = false;

        //Joystick
        public boolean js_trigger = false;
        public double js_x = 0;
        public double js_y = 0;

        //POV
        public int POV = -1;
    }

    private ValueContainer previous = new ValueContainer();
    public  ValueContainer current = new ValueContainer();
    private ValueContainer temp;

    private Mode CONTROLLER_MODE;
    private int portNumber;
    private int xStickPort;
    private int yStickPort;
    private int triggerPort;

    private  XboxController xGP;
    private  Joystick jJS;

    //Gamepad
    public boolean jLeftX_state = false;
    public boolean jLeftY_state = false;
    public boolean jRightX_state = false;
    public boolean jRightY_state = false;
    public boolean jLeft_state = false;
    public boolean jRight_state = false;
    public boolean dPad_state = false;
    public boolean A_state = false;
    public boolean B_state = false;
    public boolean X_state = false;
    public boolean Y_state = false;
    public boolean LB_state = false;
    public boolean RB_state = false;
    public boolean LT_state = false;
    public boolean RT_state = false;
    public boolean back_state = false;
    public boolean start_state = false;

    //Joystick
    public boolean js_trigger_state = false;
    public boolean js_x_state = false;
    public boolean js_y_state = false;

    //POV
    public boolean POV_state = false;
    
    //TODO redo mapping

    public Gamepad(int xboxPort) {
        this.portNumber= xboxPort;
        this.CONTROLLER_MODE = Mode.GAMEPAD;
        xGP = new XboxController(portNumber);
    }
    
    public Gamepad(int jStickPort, int xStickPort, int yStickPort, int triggerPort) {
        this.portNumber = jStickPort;
        this.xStickPort = xStickPort;
        this.yStickPort = yStickPort;
        this.triggerPort = triggerPort;
        
        jJS = new Joystick(portNumber);
    }

    public void reset() {
        switch(this.CONTROLLER_MODE) {
            case GAMEPAD : xGP = new XboxController(portNumber);break;
            case JOYSTICK : jJS = new Joystick(portNumber);break;
            default : break; // tan90 boys
        }
        current = new ValueContainer();
        previous = new ValueContainer();
        updateStatus();
    }

    public void updateStatus() {
        //Get Data
        switch(this.CONTROLLER_MODE) {
            case GAMEPAD :
                this.current.jLeftX  = xGP.getX(Hand.kLeft);
                this.current.jLeftY  = xGP.getY(Hand.kLeft);
                this.current.jRightX = xGP.getX(Hand.kRight);
                this.current.jRightY = xGP.getY(Hand.kRight);
                this.current.A       = xGP.getAButton();
                this.current.B       = xGP.getBButton();
                this.current.X       = xGP.getXButton();
                this.current.Y       = xGP.getYButton();
                this.current.LB      = xGP.getBumper(Hand.kLeft);
                this.current.RB      = xGP.getBumper(Hand.kRight);
                this.current.back    = xGP.getBackButton();
                this.current.start   = xGP.getStartButton();
                this.current.jLeft   = xGP.getStickButton(Hand.kLeft);
                this.current.jRight  = xGP.getStickButton(Hand.kRight);
                this.current.LT      = xGP.getTriggerAxis(Hand.kLeft);
                this.current.RT      = xGP.getTriggerAxis(Hand.kRight);
                this.current.dPad   = xGP.getPOV();
                break;
            case JOYSTICK :
                this.current.LT         = xGP.getTriggerAxis(Hand.kLeft);
                this.current.RT         = xGP.getTriggerAxis(Hand.kRight);
                this.current.js_trigger = jJS.getRawButton(this.triggerPort);
                this.current.js_x       = jJS.getRawAxis(this.xStickPort);
                this.current.js_y       = jJS.getRawAxis(this.yStickPort);
                this.current.POV        = jJS.getPOV();
                break;
            default : break; // tan90 boys
        }

        // Compare Data for state

        switch(this.CONTROLLER_MODE) {
            case GAMEPAD :
                this.jLeftX_state = current.jLeftX != previous.jLeftX;
                this.jLeftY_state = current.jLeftY != previous.jLeftY;
                this.jRightX_state = current.jRightX != previous.jRightX;
                this.jRightY_state = current.jRightY != previous.jRightY;
                this.jLeft_state = current.jLeft != previous.jLeft;
                this.jRight_state = current.jRight != previous.jRight;
                this.dPad_state =  current.dPad != previous.dPad;
                this.A_state = current.A != previous.A;
                this.B_state = current.B != previous.B;
                this.X_state = current.X != previous.X;
                this.Y_state = current.Y != previous.Y;
                this.LB_state = current.LB != previous.LB;
                this.RB_state = current.RB != previous.RB;
                this.LT_state = current.LT != previous.LT;
                this.RT_state = current.RT != previous.RT;
                this.back_state = current.back != previous.back;
                this.start_state = current.start != previous.start;
                break;
            case JOYSTICK :
                this.js_trigger_state = current.js_trigger != previous.js_trigger;
                this.js_x_state = current.js_x != previous.js_x;
                this.js_y_state = current.js_y != previous.js_y;
                this.POV_state = current.POV != previous.POV;
                break;
            default : break; // tan90 boys
        }
        this.temp = this.current;
        this.previous = this.temp;

    }

}
