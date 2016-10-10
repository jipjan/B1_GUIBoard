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
public class Rainfall
{
    
    
    public Rainfall()
    {
        
    }
    
    public ArrayList<Timestamp> getLongestRainfall(Period period)
    {
        int counter = 0;
        int longestPeriod = 0;
        Timestamp startDateTime;
        Timestamp endDateTime;
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
                }
                
                counter = 0;
            }
        }
        
        return rainPeriod;
    }
    
    public int getMostRainfall(Period period)
    {
        int counter = 0;
        int biggestAmount = 0;
        RawMeasurement selectedMeasurement = new RawMeasurement();
        ArrayList<RawMeasurement> PeriodRawMeasurements = period.getRawMeasurements(new WeatherStation());
        
        for(int i = 0; i < PeriodRawMeasurements.size(); i++)
        {   
            
            if(PeriodRawMeasurements.get(i).getRainRate() > 0)
            {
                counter = counter + PeriodRawMeasurements.get(i).getRainRate();
            }
            else
            {   
                if(counter > biggestAmount)
                {
                    biggestAmount = counter;
                }
                
                counter = 0;
            }
        }
        
        return biggestAmount;
    }
}
