import java.time.*;
import java.time.temporal.*;
import java.util.ArrayList;

/**
 * Write a description of class Longest_temp_rise here.
 * 
 * @author Jacco Steegman 
 * @version 1.0
 */
public class Longest_temp_rise
{
    
    
    public static Period Get_Longest_temperature_rise(Period periodToCheckOut, WeatherStation weatherStation)
    {
        LocalDate beginDate = periodToCheckOut.getStart();
        
        Period longestTempRisePeriod = new Period();
        longestTempRisePeriod.setStart(beginDate);
        longestTempRisePeriod.setEnd(beginDate.minusDays(1));
        
        double lastTemp = getAvgTempOfDay(beginDate, weatherStation);
        double tempOfI= 0;
        
        int periodLength = 0;
        for (int i = 1; i < periodToCheckOut.numberOfDays(); i++)
        {
            LocalDate dateOfI = beginDate.plusDays(i);
            tempOfI = getAvgTempOfDay(dateOfI, weatherStation);
            periodLength++;
            
            if (tempOfI <= lastTemp)
            {
                periodLength = 0;
            } else if(periodLength >= longestTempRisePeriod.numberOfDays())
            {
                longestTempRisePeriod.setStart(dateOfI.minusDays(periodLength));
                longestTempRisePeriod.setEnd(dateOfI);
                periodLength = 0;
            }
            
            
            lastTemp = tempOfI;
        }
        
        if (longestTempRisePeriod.getStart().compareTo(longestTempRisePeriod.getEnd()) <= 0)
        {
            longestTempRisePeriod.setEnd(periodToCheckOut.getEnd());
        }
        return longestTempRisePeriod;
    }
        
    private static double getAvgTempOfDay(LocalDate day, WeatherStation weatherStation)
    {
        Period dayPeriod = new Period(day, day);
        
        ArrayList<RawMeasurement> dayRawMeasurement = dayPeriod.getRawMeasurements(weatherStation);
        //dayRawMeasurement.remove(dayRawMeasurement.size());
        
        Statistics statisticOfDay = new Statistics(dayRawMeasurement);
        return statisticOfDay.getAverage(Statistics.Unit.OutsideTemp);
    }
}

