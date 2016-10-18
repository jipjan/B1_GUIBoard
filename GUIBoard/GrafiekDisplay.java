import java.util.ArrayList;
import java.time.*;

public class GrafiekDisplay
{
    private WeatherStation ws;
    private Period period;
    private Statistics statistics;
    private ArrayList<RawMeasurement> rm;
    private ArrayList<Short> list;
    
    public GrafiekDisplay()
    {
        IO.init();
        ws = new WeatherStation();
        period = new Period();
        period.setStart((LocalDate.now().minusMonths(1)));
        rm = period.getRawMeasurements(ws);
        statistics = new Statistics(rm);
        list = new ArrayList<Short>();
    }
    
    public void displayGrafiek()
    {
        list = statistics.getAveragesOnDays(Statistics.Unit.OutsideTemp);
        int locatie = 3;
        int temp;
        GUI_Matrix_Helper.clrDisplay();

        for(int i = 0;i < list.size();i++)
        {
            temp = 32 -(int)MetingenHandler.temperatuur(list.get(i));
            GUI_Matrix_Helper.turnPixel(true,locatie,temp);
            locatie = locatie + 4;

        }
        
    }
}
