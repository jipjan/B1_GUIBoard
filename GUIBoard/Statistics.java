import java.util.ArrayList;
/**
 * Caculates statistics for the input array of data
 */
public class Statistics
{
    public static enum Unit { InsideTemp, OutsideTemp, Windspeed, OutsideHum, RainRate, UVLevel, Solarrad, Barometer };

    public static double getAverage(ArrayList<RawMeasurement> list, Unit kindOf)
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
    
    public static double getDeviant(ArrayList<RawMeasurement> list, Unit unit)
    {
        double deviant = 0;
        double average = getAverage(list, unit);
        switch (unit)
        {
            case InsideTemp:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getInsideTemp() - average, 2);
            break;

            case OutsideTemp:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getOutsideTemp() - average, 2);
            break;

            case Windspeed:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getWindSpeed() - average, 2);
            break;
            
            case OutsideHum:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getOutsideHum() - average, 2);
            break;
            
            case RainRate:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getRainRate() - average, 2);
            break;
            
            case UVLevel:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getUVLevel() - average, 2);
            break;
            
            case Solarrad:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getSolarRad() - average, 2);
            break;
            
            case Barometer:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getBarometer() - average, 2);
            break;
        }
        return Math.pow(deviant / (list.size() - 1), 0.5);
    }
}
