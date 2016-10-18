import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class contains all analysis methods for data from the weatherstation.
 * 
 * @author Group B1
 */
public class Analysis
{       
    private double getAvgTempOfDay(LocalDate day)
    {
        Period dayPeriod = new Period(day, day);        
        ArrayList<RawMeasurement> dayRawMeasurement = dayPeriod.getRawMeasurements(new WeatherStation());        
        Statistics statisticOfDay = new Statistics(dayRawMeasurement);
        return statisticOfDay.getAverage(Statistics.Unit.OutsideTemp);
    }
    
    /*
     * Check if a certain period has a heatwave
     * @return  Returns if the period contains a heatwave.
     */
    public static boolean hasHeatWave(ArrayList<RawMeasurement> data)
    {        
        // temp 25 == 770 - fahrenheit * 10
        // temp 30 == 860 - fahrenheit * 10
        int amount25 = 0;
        int amount30 = 0;
        Statistics statistics = new Statistics(data);
        
        ArrayList<Short> days = statistics.getHighestOnDays(Statistics.Unit.OutsideTemp);
        for (int i = 0; i < days.size(); i++)
        {
            Short temp = days.get(i);
            if (temp >= 770)
            {
                if (temp >= 860)
                    amount30++;
                else
                    amount25++;
                if (amount25 + amount30 >= 5 && amount30 >= 3) 
                    return true;
            }
            else
            {
                amount25 = 0;
                amount30 = 0;
            }
        }
        
        return false;
    }
    
    /*
     * Check the most amount of rain that has fallen in sequence.
     * @return  The amount of rain that has fallen.
     */
    public int maxAmountOfSequentRain(ArrayList<RawMeasurement> data)
    {   
        int maxAmount = 0;
        int amount = 0;
        int size = data.size();
        
        for (int i = 0; i < data.size(); i++)
        {
            int rain = data.get(i).getRainRate();
            if (rain > 0)
            {
                amount += rain;
            }
           else
            {
                if (amount > maxAmount)
                {
                    maxAmount = amount;
                }
                amount = 0;
            }
        }
       
        return maxAmount;
    }

    /*
     * Check the longest period of rainfall.
     * @return  A period object with start and stopdate with longest period of rain
     */
    public DateTimePeriod longestRainfall(ArrayList<RawMeasurement> data)
    {   
        int counter = 0;
        int mostMeasurements = 0;
        DateTimePeriod longestPeriod = new DateTimePeriod();
        
        if(data.size() > 0)
        {   
            for(int i = 0; i < data.size(); i++)
            {   
                if(data.get(i).getRainRate() > 0)
                    counter++;
                else
                {   
                    if(counter > mostMeasurements)
                    {
                        mostMeasurements = counter;
                        longestPeriod.setStartDateTime(data.get(i - counter).getDateStamp().toLocalDateTime());
                        longestPeriod.setEndDateTime(data.get(i).getDateStamp().toLocalDateTime());
                    }                
                    counter = 0;
                }
            } 
        }
        
        return longestPeriod;
    }

    /*
     * Check the longest period of drought.
     * @param mNeerslag Upper bound of what the limit is for drought
     * @return  Period of longest drought
     */
    public DateTimePeriod longestDrought(ArrayList<RawMeasurement> data, int mNeerslag)//voer de periode in
    {
        ArrayList<Timestamp> time = new ArrayList<Timestamp>();
        //initaliseerst belangrijke telwaarden
        int count = 0;
        int maxCount = 0;
        //geeft een waarde voor de maximale neerslag die mag vallen om nog mee te tellen.
        int maximaleNeerslag = mNeerslag;
        //Dit i voor de weergave van de datums
        Timestamp beginDate = data.get(0).getDateStamp();
        Timestamp eindDate = data.get(0).getDateStamp();
        Timestamp TijdelijkeDate = data.get(0).getDateStamp();
        //
        
        for(int i = 0; i < data.size();i++)
        {
            if(data.get(i).getRainRate()== maximaleNeerslag)
            {
                count += 1;
            }
            else
            {
                if(count>maxCount)
                {
                    maxCount = count;
                    //
                    beginDate = TijdelijkeDate;
                    eindDate = data.get(i).getDateStamp();
                    //
                    count = 0;
                }
                TijdelijkeDate = data.get(i).getDateStamp();
            }
        }
        
        return new DateTimePeriod(beginDate.toLocalDateTime(), eindDate.toLocalDateTime());
    }
   
    /*
     * Check what the longest period of temperature rise is
     * @return Period with longest temperature rise.
     */
    public Period longestTemperatureRise(ArrayList<RawMeasurement> data,  Period period)
    {
        LocalDate beginDate = period.getStart();
        
        Period longestTempRisePeriod = new Period();
        longestTempRisePeriod.setStart(beginDate);
        longestTempRisePeriod.setEnd(beginDate.minusDays(1));
        
        double lastTemp = getAvgTempOfDay(beginDate);
        double tempOfI= 0;
        
        int periodLength = 0;
        for (int i = 1; i < period.numberOfDays(); i++)
        {
            
            LocalDate dateOfI = beginDate.plusDays(i);
            tempOfI = getAvgTempOfDay(dateOfI);
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
            longestTempRisePeriod.setEnd(period.getEnd());
        }
        
        return longestTempRisePeriod;
    }
}
