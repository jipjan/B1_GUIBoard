import java.util.ArrayList;

public class OpdrachtDrieJJ
{
    public static boolean hasHeatWave(Period periode)
    {        
        ArrayList<RawMeasurement> list = periode.getRawMeasurements(new WeatherStation());
        int amount25 = 0;
        int amount30 = 0;        
        for (int i = 0; i < list.size(); i++)
        {
            int temp = list.get(i).getOutsideTemp();
            if (temp >= 25)
            {
                if (temp >= 30)
                {
                    amount30++;
                    continue;
                }
                amount25++;
            }
            else
            {
                if (amount25 + amount30 >= 5 && amount30 >= 3) return true;
                amount25 = 0;
                amount30 = 0;
            }
        }
        return false;
    }
}
