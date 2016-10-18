import java.lang.Thread;

/**
 * Write a description of class LoadingDataSimulation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoadingDataAnimation
{
    // instance variables - replace the example below with your own
    private boolean status;

    public LoadingDataAnimation()
    {
        status = true;
    }
    
    private void sleep()
    {
        //Sleep is in milliseconds.
        try {
            Thread.sleep(500);        
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void animation()
    {
        int counter = 0;
        
        while(status)
        {
            if(counter == 0)
            {
                GUI_Matrix_Helper.stringToMatrix("Data ophalen");
            }
            else if(counter == 1)
            {
                GUI_Matrix_Helper.stringToMatrix("Data ophalen.");
            }
            else if(counter == 2)
            {
                GUI_Matrix_Helper.stringToMatrix("Data ophalen..");
            }
            else if(counter == 3)
            {
                GUI_Matrix_Helper.stringToMatrix("Data ophalen...");
                counter = -1;
            }
            sleep();
            GUI_Matrix_Helper.clrDisplay();
            counter ++;
        }
    }
    
    public void startAnimation()
    {
        status = true;
        new Thread(() -> animation()).start();
    }

    public void stopAnimation()
    {
        status = false;        
        //Sleep to make sure the animation has stopped.
        sleep();
    }
}
