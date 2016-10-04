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
        for(int i = 0; i < list.size();i++)
        switch (kindOf)
        {
            case InsideTemp:
            average += list.get(i).getInsideTemp();
            break;

            case OutsideTemp:
            average += list.get(i).getOutsideTemp();
            break;

            case Windspeed:
            average += list.get(i).getWindSpeed();
            break;
            
            case OutsideHum:
            average += list.get(i).getOutsideHum();
            break;
            
            case RainRate:
            average += list.get(i).getRainRate();
            break;
            
            case UVLevel:
                average += list.get(i).getUVLevel();
            break;
            
            case Solarrad:
                average += list.get(i).getSolarRad();
            break;
            
            case Barometer:
                average += list.get(i).getBarometer();
            break;
        }
        
        return average /list.size();
    } 
    
    
}
