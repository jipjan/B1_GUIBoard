import java.util.ArrayList;
/**
 * Displays the averaget temperature per day of the last 30 days.
 */
public class GrafiekDisplay
{
    private WeatherStation ws;
    private Period period;
    private Statistics statistics;
    private ArrayList<RawMeasurement> rm;
    
    public GrafiekDisplay()
    {
        IO.init();
        ws = new WeatherStation();
        period = new Period();
        rm = period.getRawMeasurements(ws);
        statistics = new Statistics(rm);
    }

    public void displayGrafiek()
    {
        ArrayList<Short> list = statistics.getAveragesOnDays(Statistics.Unit.OutsideTemp);
        int locatie = 3;
        int temp;
        GUI_Matrix_Helper.clrDisplay();
        //omcirkel hier de hele matrix
        for(int i = 0;i < list.size();i++)
        {
            //32 hoog, 128 lang dus 4 pixels per dag. temperatuur is 1 per pixel.beginnend bij -5
            //bij temp van 3 tot 29 =28 items in totaal
            temp = (int)MetingenHandler.temperatuur(list.get(i));
            
            GUI_Matrix_Helper.turnPixel(true,locatie,temp);
            locatie = locatie + 4;
            
        }
        
    }
}
