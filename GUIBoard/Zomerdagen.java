import java.util.*;
import java.time.*;
import java.util.ArrayList;
/**
 * This code will search for the longest periode of "summer days". Meaning the longest periode that the temprature 
 * was 25 *C or higher. The end result is a period of beginning date and end date.
 * 1 day is 24 hours and starts at  00:00. if any moment of the day it is registred that the temprature is
 * 25 *C or higher it will be selected as an "summerday"
 * 
 * @author Trevor Boeije 
 * @school Avans Hogenschool(breda)
 * @version 1.4
 */
public class Zomerdagen
{
    private ArrayList<RawMeasurement> list;
    private WeatherStation ws;

    public Zomerdagen()
    {
        ws = new WeatherStation();
    }

    public int getZomerdagen(int jaar, int maand, int dag)
    {
        IO.init();
        
        Period period = new Period();
        period.setStart(jaar,maand,dag);
        
        list = period.getRawMeasurements(ws);
        int summerdays = 0;
        int maxSummerdays = 0;
        boolean zomerdag = false;
        if (list.size() < 1) 
            return summerdays;

        LocalDate date = list.get(0).getDateStamp().toLocalDateTime().toLocalDate();
        for(int i = 0; i < list.size(); i++)
        {
            
            if (list.get(i).getDateStamp().toLocalDateTime().toLocalDate().equals(date))
            {
                if(list.get(i).getOutsideTemp() > 770)//heel de dag vergelijken met 770(25*C). zo ja tel 1 dag verder.
                {
                    zomerdag = true;
                }

            }
            else//als je op de volgnde dag zit.
            {
                if(zomerdag)//het is een zomerdag
                {
                    summerdays = summerdays + 1;
                }
                else//het is geen zomerdag
                {
                    if (summerdays>maxSummerdays)
                        maxSummerdays = summerdays;
                    summerdays = 0;
                }
                
                zomerdag = false;
                date = list.get(i).getDateStamp().toLocalDateTime().toLocalDate();
            }
        }
        return maxSummerdays;

    }
    //System.out.println("begindatum is" + ??? + "einddatum is" + ???;
}

   

