import java.util.ArrayList;
import java.util.Collections;

/**
 * Caculates statistics for the input array of data
 */
public class Statistics
{
    private ArrayList<RawMeasurement> list;
    
    public Statistics(ArrayList<RawMeasurement> measurements)
    {
        list = measurements;
    }
    
    public enum Unit { InsideTemp, OutsideTemp, Windspeed, OutsideHum, RainRate, UVLevel, Solarrad, Barometer };

    public double getAverage(Unit kindOf)
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
    
    public double getDeviant(Unit unit)
    {
        double deviant = 0;
        double average = getAverage(unit);
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
    
        public double getMedian(Unit unit)
    {
        double median = 0;
        ArrayList medianList = new ArrayList();
        for(int i = 0; i < list.size();i++)
        switch (unit)
        {
            case InsideTemp:
            medianList += list.get(i).getInsideTemp();
            break;
            
            case OutsideTemp:
            medianList += list.get(i).getOutsideTemp();
            break;

            case Windspeed:
            medianList += list.get(i).getWindSpeed();
            break;
            
            case OutsideHum:
            medianList += list.get(i).getOutsideHum();
            break;
            
            case RainRate:
            medianList += list.get(i).getRainRate();
            break;
            
            case UVLevel:
            medianList += list.get(i).getUVLevel();
            break;
            
            case Solarrad:
            medianList += list.get(i).getSolarRad();
            break;
            
            case Barometer:
            medianList += list.get(i).getBarometer();
            break;            
        }
        Collections.sort(medianList);
        
        int middle = ((medianList.size) / 2);
        if(medianList.size % 2 == 0){
           int numberA = medianList[middle];
           int numberB = medianList[middle-1];
           median = (numberA + numberB) / 2;
        }
        else{
           median = medianList[middle + 1];
        }
        return median /list.size();
    } 
}
