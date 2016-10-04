import java.util.ArrayList;
/**
 * Caculates statistics for the input array of data
 */
public class Statistics
{
    public enum Unit { InsideTemp, OutsideTemp, Windspeed, OutsideHum, RainRate, UVLevel, Solarrad, Barometer };

    public double getAverage(ArrayList<RawMeasurement> list, Unit kindOf)
    {
        double average = 0;
        switch (kindOf)
        {
            case InsideTemp:
            for (int i = 0; i < list.size(); i++)
                average += list.get(i).getInsideTemp();
            average /= list.size();
            break;

            case OutsideTemp:
            for (int i = 0; i < list.size(); i++)
                average += list.get(i).getOutsideTemp();
            average /= list.size();
            break;

            case Windspeed:
            for (int i = 0; i < list.size(); i++)
                average += list.get(i).getWindSpeed();
            average /= list.size();
            break;
            
            case OutsideHum:
            for (int i = 0; i < list.size(); i++)
                average += list.get(i).getOutsideHum();
            average /= list.size();
            break;
            
            case RainRate:
            for (int i = 0; i < list.size(); i++)
                average += list.get(i).getRainRate();
            average /= list.size();
            break;
            
            case UVLevel:
            for (int i = 0; i < list.size(); i++)
                average += list.get(i).getUVLevel();
            average /= list.size();
            break;
            
            case Solarrad:
            for (int i = 0; i < list.size(); i++)
                average += list.get(i).getSolarRad();
            average /= list.size();
            break;
            
            case Barometer:
            for (int i = 0; i < list.size(); i++)
                average += list.get(i).getBarometer();
            average /= list.size();
            break;
        }
        return average;
    }    
}
