import java.time.LocalDateTime;

/**
 * The class DateTimePeriod holds a start LocalDateTime and a end LocalDateTime for passing a 
 * time period from one method to another method.
 * 
 * @author (Arthur van Strien) 
 * @version (2016-10-17)
 */
public class DateTimePeriod
{
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public DateTimePeriod()
    {
        
    }

    public LocalDateTime getStartDateTime()
    {
        return startDateTime;
    }
    
    public LocalDateTime getEndDateTime()
    {
        return endDateTime;
    }
    
    public void setStartDateTime(LocalDateTime newDateTime)
    {
        startDateTime = newDateTime;
    }
    
    public void setEndDateTime(LocalDateTime newDateTime)
    {
        endDateTime = newDateTime;
    }
}
