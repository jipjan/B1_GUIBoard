import java.util.ArrayList;

public class GUI_Gebruik
{  
    public enum Buton {left, middle, red, none};
    
    private boolean runProgram;
    
    private short LastLeftState;
    private short LastMiddleState;
    private short LastRedState;
    
    public GUI_Gebruik() 
    {
        IO.init();
        
        runProgram = true;
        lastButonPrest();
    }
    
    public void setRunProgram(boolean programStateRun)
    { 
        runProgram = programStateRun;
    } 

    public Buton lastButonPrest()
    {
        short redState    = IO.readShort(0x80);
        short leftState   = IO.readShort(0x90);
        short middleState = IO.readShort(0x100);
        
        Buton pressedButon = Buton.none;
        
        if (LastRedState != redState)
            pressedButon = Buton.red;
        else if (LastLeftState != leftState)
            pressedButon = Buton.left;
        else if (LastMiddleState != middleState)
            pressedButon = Buton.middle;
        else 
            pressedButon = Buton.none;

        LastRedState    = redState;
        LastLeftState   = leftState;
        LastMiddleState = middleState;
        
        return pressedButon;
    }
}
