import java.util.ArrayList;
/**
 * Write a description of class Analysis here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Analysis
{
    public static boolean hasHeatWave(Period periode)
    {        
        // temp 25 == 770
        // temp 30 == 860
        ArrayList<RawMeasurement> list = periode.getRawMeasurements(new WeatherStation());
        int amount25 = 0;
        int amount30 = 0;        
        for (int i = 0; i < list.size(); i++)
        {
            int temp = list.get(i).getOutsideTemp();
            if (temp >= 770)
            {
                if (temp >= 860)
                    amount30++;
                else
                    amount25++;
                if (amount25 + amount30 >= 5 && amount30 >= 3) return true;
            }
            else
            {
                amount25 = 0;
                amount30 = 0;
            }
        }
        return false;
    }

    public static int maxAmountOfRainSeqDays(Period periode)
    {
        ArrayList<RawMeasurement> list = periode.getRawMeasurements(new WeatherStation());
        int maxAmount = 0;
        int amount = 0;
        for (int i = 0; i < list.size(); i++)
        {
            int rain = list.get(i).getRainRate();
            if (rain > 0)
                amount += rain;
            else
            {
                if (amount > maxAmount)
                    maxAmount = amount;
                amount = 0;
            }
        }
        return maxAmount;
    }

    public static Period getLongestRainfall(Period period)
    {
        int counter = 0;
        int longestPeriod = 0;
        Period newPeriod = new Period();
        ArrayList<RawMeasurement> PeriodRawMeasurements = period.getRawMeasurements(new WeatherStation());

        for(int i = 0; i < PeriodRawMeasurements.size(); i++)
        {   
            if(PeriodRawMeasurements.get(i).getRainRate() > 0)
                counter++;
            else
            {   
                if(counter > longestPeriod)
                {
                    longestPeriod = counter;
                    newPeriod.setStart(PeriodRawMeasurements.get(i - counter).getDateStamp().toLocalDateTime().toLocalDate());
                    newPeriod.setEnd(PeriodRawMeasurements.get(i).getDateStamp().toLocalDateTime().toLocalDate());
                }                
                counter = 0;
            }
        }       
        return newPeriod;
    }

    public static Period maxDroogePeriode(Period periode, int mNeerslag)//voer de periode in
    {
        ArrayList<RawMeasurement> list = periode.getRawMeasurements(new WeatherStation());// voer de periode array in in list
        ArrayList<java.sql.Timestamp> time = new ArrayList<java.sql.Timestamp>();
        //initaliseerst belangrijke telwaarden
        int count = 0;
        int maxCount = 0;
        //geeft een waarde vor de maximale neerslag die mag vallen o nog mee te tellen.
        int maximaleNeerslag = mNeerslag;
        //Dit i voor de weergave van de datums
        java.sql.Timestamp Begindate = list.get(0).getDateStamp();
        java.sql.Timestamp Einddate = list.get(0).getDateStamp();
        java.sql.Timestamp TijdelijkeDate = list.get(0).getDateStamp();
        //
        for(int i = 0; i < list.size();i++)
        {
            if(list.get(i).getRainRate()== maximaleNeerslag)
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
                    Einddate = list.get(i).getDateStamp();
                    //
                    count = 0;
                }
                TijdelijkeDate = list.get(i).getDateStamp();
            }
        }
        return new Period(Begindate.toLocalDateTime().toLocalDate(), Einddate.toLocalDateTime().toLocalDate());
    }
    
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
        Statistics statisticOfDay = new Statistics(dayRawMeasurement);
        return statisticOfDay.getAverage(Statistics.Unit.OutsideTemp);
    }
}
