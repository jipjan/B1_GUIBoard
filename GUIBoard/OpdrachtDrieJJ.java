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
                    amount30++;
                else
                    amount25++;
                if (amount25 + amount30 >= 5 && amount30 >= 3) return true;
            }
            else
            {
                amount25 = 0;
                amount30 = 0;
            }
        }
        return false;
    }

    public static int maxAmountOfRainSeqDays(Period periode)
    {
        ArrayList<RawMeasurement> list = periode.getRawMeasurements(new WeatherStation());
        int maxAmount = 0;
        int amount = 0;
        for (int i = 0; i < list.size(); i++)
        {
            int rain = list.get(i).getRainRate();
            if (rain > 0)
                amount += rain;
            else
            {
                if (amount > maxAmount)
                    maxAmount = amount;
                amount = 0;
            }

        }
        return maxAmount;
    }
}	
