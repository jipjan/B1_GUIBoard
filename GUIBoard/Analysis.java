import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Timestamp;

/**
 * This class contains all analysis methods for data from the weatherstation.
 * 
 * @author Group B1
 */
public class Analysis
{  
    Period _period;
    ArrayList<RawMeasurement> _list;
    Statistics _statistics;
    
    public Analysis(Period measurements)
    {
        _period = measurements;
        _list = measurements.getRawMeasurements(new WeatherStation());
        _statistics = new Statistics(_list);
    }
    
    
    /*
     * Check if a certain period has a heatwave
     * @return  Returns if the period contains a heatwave.
     */
    public void hasHeatWave()
    {        
        // temp 25 == 770 - fahrenheit * 10
        // temp 30 == 860 - fahrenheit * 10
        int amount25 = 0;
        int amount30 = 0;
        
        clearAll();
        
        ArrayList<Short> days = _statistics.getAveragesOnDays(Statistics.Unit.OutsideTemp);
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
                    GUI_Matrix_Helper.stringToMatrix("Er was een hittgolf" + "\n" + "tussen: " + _period.getStart() + "\n" + "en " + _period.getEnd());
            }
            else
            {
                amount25 = 0;
                amount30 = 0;
            }
        }
        
        GUI_Matrix_Helper.stringToMatrix("Er was geen hittegolf" + "\n" + "tussen: " + _period.getStart() + "\n" + "en " + _period.getEnd());
    }

    /*
     * Check the most amount of rain that has fallen in sequence.
     * @return  The amount of rain that has fallen.
     */
    public void maxAmountOfSequentRain()
    {   
        int maxAmount = 0;
        int amount = 0;
        int position = 0x10;
        int numberOfDigits;
        
        clearAll();
       
        int size = _list.size();
        
        for (int i = 0; i < _list.size(); i++)
        {
            int rain = _list.get(i).getRainRate();
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
       
        numberOfDigits = (int) Math.log10(maxAmount) + 1;
        
        for(int i = 0; i < numberOfDigits; i++)
        {
            IO.writeShort(position, GUI_Digits_Helper.digitToSegments(maxAmount%10, false));
            maxAmount = maxAmount / 10;
            position = position + 0x02;
        }
        
        GUI_Matrix_Helper.stringToMatrix("Maximale Regenval" + "\n" + "tussen: " + _period.getStart() + "\n" + "en " + _period.getEnd() + " in mm.");
    }

    /*
     * Check the longest period of rainfall.
     * @return  A period object with start and stopdate with longest period of rain
     */
    public void getLongestRainfall()
    {   
        int counter = 0;
        int longestPeriod = 0;
        //Period newPeriod = new Period();
        Timestamp startDate;
        Timestamp endDate;
        
        if(_list.size() > 0)
        {
            startDate = _list.get(0).getDateStamp();
            endDate = _list.get(0).getDateStamp();
            
            clearAll();
            
            for(int i = 0; i < _list.size(); i++)
            {   
                if(_list.get(i).getRainRate() > 0)
                    counter++;
                else
                {   
                    if(counter > longestPeriod)
                    {
                        longestPeriod = counter;
                        //newPeriod.setStart(_list.get(i - counter).getDateStamp().toLocalDateTime().toLocalDate());
                        //newPeriod.setEnd(_list.get(i).getDateStamp().toLocalDateTime().toLocalDate());
                        startDate = _list.get(i - counter).getDateStamp();
                        endDate = _list.get(i).getDateStamp();
                    }                
                    counter = 0;
                }
            }
            
            if(longestPeriod > 0)
            {
                GUI_Matrix_Helper.stringToMatrix("De langste regenval" + "\n" + "v " + startDate + "\n" + "t " + endDate);
            }
        }
    }

    /*
     * Check the longest period of drought.
     * @param mNeerslag Upper bound of what the limit is for drought
     * @return  Period of longest drought
     */
    public Period longestDrought(int mNeerslag)//voer de periode in
    {
        ArrayList<Timestamp> time = new ArrayList<Timestamp>();
        //initaliseerst belangrijke telwaarden
        int count = 0;
        int maxCount = 0;
        //geeft een waarde vor de maximale neerslag die mag vallen o nog mee te tellen.
        int maximaleNeerslag = mNeerslag;
        //Dit i voor de weergave van de datums
        Timestamp Begindate = _list.get(0).getDateStamp();
        Timestamp Einddate = _list.get(0).getDateStamp();
        Timestamp TijdelijkeDate = _list.get(0).getDateStamp();
        //
        for(int i = 0; i < _list.size();i++)
        {
            if(_list.get(i).getRainRate()== maximaleNeerslag)
            {
                count += 1;
            }
            else
            {
                if(count>maxCount)
                {
                    maxCount = count;
                    //
                    Begindate = TijdelijkeDate;
                    Einddate = _list.get(i).getDateStamp();
                    //
                    count = 0;
                }
                TijdelijkeDate = _list.get(i).getDateStamp();
            }
        }
        return new Period(Begindate.toLocalDateTime().toLocalDate(), Einddate.toLocalDateTime().toLocalDate());
    }
    
    /*
     * Check what the longest period of temperature rise is
     * @return          Period with longest temperature rise.
     */
    public Period getLongestTemperatureRise()
    {
        LocalDate beginDate = _period.getStart();
        
        Period longestTempRisePeriod = new Period();
        longestTempRisePeriod.setStart(beginDate);
        longestTempRisePeriod.setEnd(beginDate.minusDays(1));
        
        double lastTemp = getAvgTempOfDay(beginDate);
        double tempOfI= 0;
        
        int periodLength = 0;
        for (int i = 1; i < _period.numberOfDays(); i++)
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
            longestTempRisePeriod.setEnd(_period.getEnd());
        }
        return longestTempRisePeriod;
    }
    
    public void clearAll()
    {
        GUI_Digits_Helper.clearAll();
        GUI_Matrix_Helper.clrDisplay();
    }
    
    private double getAvgTempOfDay(LocalDate day)
    {
        Period dayPeriod = new Period(day, day);        
        ArrayList<RawMeasurement> dayRawMeasurement = dayPeriod.getRawMeasurements(new WeatherStation());        
        Statistics statisticOfDay = new Statistics(dayRawMeasurement);
        return statisticOfDay.getAverage(Statistics.Unit.OutsideTemp);
    }
}
