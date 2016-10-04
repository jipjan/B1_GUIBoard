import java.util.ArrayList;
/**
 * Caculates statistics for the input array of data
 */
public class Statistics
{
    //public enum Unit { InsideTemp, OutsideTemp, Windspeed, OutsideHum, RainRate, UVLevel, Solarrad, Barometer };
    
    /**
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
    */
   
    public int selectType(String choice)
    {
        int type = -1;
        
        if(choice == "InsideTemp")
        {
            type = 3;
        }
        else if(choice == "OutsideTemp")
        {
            type = 5;
        }
        else if(choice == "Windspeed")
        {
            type = 6;
        }
        else if(choice == "OutsideHum")
        {
            type = 9;
        }
        else if(choice == "RainRate")
        {
            type = 10;
        }
        else if(choice == "UVLevel")
        {
            type = 11;
        }
        else if(choice == "Solarrad")
        {
            type = 12;
        }
        else if(choice == "Barometer")
        {
            type = 2;
        }
        
        return type;
    }
    
    public double lowest(String choice)
    {
        WeatherStation weatherStation = new WeatherStation();
        ArrayList inputArray = weatherStation.getAllMeasurementsLast24h();
        
        int location = selectType(choice);
        
        double lowest = 0;
        
        if(inputArray.size() > 0)
        {
            ArrayList selected = inputArray.get(0);
            lowest = selected.get(location);
            
        
            for (int i = 1; i < inputArray.length; i++)
            {
                if(inputArray[i][location] < lowest)
                {
                    lowest = inputArray[i][location];
                }
            }
        }
        
        return lowest;
    }
}
