import java.util.ArrayList;
import java.util.Collections;
import java.sql.Timestamp;

/**
 * Calculates the longest period of rainfall in that is present in the given period.
 * Needs a period as input.
 * Gives an array with two dateTimes as output.
 * 
 * @author (ArthurvanStrien) 
 * @version (2016-10-06)
 */
public class LongestConstantRainfall
{
    
    
    public LongestConstantRainfall()
    {
        
    }
    
    public ArrayList<Timestamp> getLongestRainfall(Period period)
    {
        int counter = 0;
        int longestPeriod = 0;
        Timestamp startDateTime;
        Timestamp endDateTime;
        RawMeasurement selectedMeasurement = new RawMeasurement();
        ArrayList<Timestamp> rainPeriod = new ArrayList<Timestamp>();
        ArrayList<RawMeasurement> PeriodRawMeasurements = period.getRawMeasurements(new WeatherStation());
        
        for(int i = 0; i < PeriodRawMeasurements.size(); i++)
        {   
            if(PeriodRawMeasurements.get(i).getRainRate() > 0)
            {
                counter = counter + 1;
            }
            else
            {   
                if(counter > longestPeriod)
                {
                    longestPeriod = counter;
                    startDateTime = PeriodRawMeasurements.get(i - counter).getDateStamp();
                    endDateTime = PeriodRawMeasurements.get(i).getDateStamp();
                    rainPeriod.clear();
                    rainPeriod.add(startDateTime);
                    rainPeriod.add(endDateTime);
                    System.out.println(startDateTime + " - " + endDateTime);
                }
                
                counter = 0;
            }
        }
        
        return rainPeriod;
    }
}
