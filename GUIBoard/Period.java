import java.time.*;
import java.time.temporal.*;

/**
 * A class to contain a period of time
 * 
 * @author Johan Talboom
 * @version 1.0
 */
public class Period
{
    private LocalDate beginPeriod;
    private LocalDate endPeriod;

    /** 
     * default constructor, sets the period to today
     */
    public Period()
    {
        beginPeriod = LocalDate.now();
        endPeriod = LocalDate.now();
    }
    
    /**
     * Simple setter for start of period
     */
    public void setStart(int year, int month, int day)
    {
        beginPeriod = LocalDate.of(year, month, day);
    }

    /**
     * simple setter for end of period
     */
    public void setEnd(int year, int month, int day)
    {
        endPeriod = LocalDate.of(year, month, day);
    }

    /**
     * calculates the number of days in the period
     */
    public long numberOfDays()
    {
        return ChronoUnit.DAYS.between(beginPeriod, endPeriod);
    }
    
    
    /*
    public ArrayList<RawMeasurement> getRawMeasurements(WeatherStation weatherStation)
    {
        return weatherStation.getAllMeasurementsBetween(beginPeriod, endPeriod);
    }*/
    
    /**
     * Todo
     */
    public boolean hasHeatWave()
    {
        return true;
    }
    
    /**
     * Todo
     */
    public Period longestDraught()
    {
        return new Period();
    }
    
    /**
     * Todo more methods
     */
    
    
    
    
}
