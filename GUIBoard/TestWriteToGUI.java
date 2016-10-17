import java.util.Calendar;

/**
 * Write a description of class TestWriteToGUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestWriteToGUI
{
    WriteToGUI writeToGUI;
    
    /**
     * Constructor for objects of class TestWriteToGUI
     */
    public TestWriteToGUI()
    {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        writeToGUI = new WriteToGUI(year);
    }
    
    
    public TestWriteToGUI(int year)
    {
        writeToGUI = new WriteToGUI(year);
    }

    public void testPrintMostRecentOutsideTemp()
    {
        writeToGUI.printMostRecentOutsideTemp();
    }
    
    public void testPrintLastWeekGLH()
    {
        writeToGUI.printLastWeekGLH();
    }
    
    public void testPrintLastWeekMMS()
    {
        writeToGUI.printLastWeekMMS();
    }
    
    public void testPrintLastMonthGLH()
    {
        writeToGUI.printLastMonthGLH();
    }
    
    public void testPrintLastMonthMMS()
    {
        writeToGUI.printLastMonthMMS();
    }
    
    public void testPrintCurrentWindSpeed()
    {
        writeToGUI.printCurrentWindSpeed();
    }
    
    public void testPrintHasHeatWave()
    {
        writeToGUI.printHasHeatWave();
    }
    
    public void testPrintMaxAmountOfSequentRain()
    {
        writeToGUI.printMaxAmountOfSequentRain();
    }
    
    public void testPrintLongestRainfall()
    {
        writeToGUI.printLongestRainfall();
    }
    
    public void testPrintLongestDrought()
    {
        writeToGUI.printLongestDrought(0);
    }
    
    public void testPrintTemperatureRise()
    {
        writeToGUI.printLongestTemperatureRise();
    }
}
