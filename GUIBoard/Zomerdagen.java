import java.util.ArrayList;
/**
 * This code will search for the longest periode of "summer days". Meaning the longest periode that the temprature 
 * was 25 *C or higher. The end result is a period of beginning date and end date.
 * 1 day is 24 hours and starts at  00:00. if any moment of the day it is registred that the temprature is
 * 25 *C or higher it will be selected as an "summerday"
 * 
 * @author Trevor Boeije 
 * @school Avans Hogenschool(breda)
 * @version 1.0
 */
public class Zomerdagen
{

    //bedoeling van public getLongestSunnyDays is het filteren van alle waardes onder de 25*c
    public Period getLongestSunnyDays(Period periode)
    {
        
        Period p;
        
        p = new Period();
        
        //verbind met weather station als ArrayList uit de periode komt.
        ArrayList<RawMeasurement> list = periode.getRawMeasurements(new WeatherStation());
        ArrayList<RawMeasurement> zomerDagMetingen = new ArrayList<RawMeasurement>();
        //Intitalisatie van verschillende eenheden
        int count = 0;
        int maxcount = 0;
        boolean zomerdag = false;
        java.sql.Timestamp beginDate        = list.get(0) .getDateStamp();
        java.sql.Timestamp eindDate         = list.get(0) .getDateStamp();
        java.sql.Timestamp tijdelijkeDate   = list.get(0) .getDateStamp();
        
        
        for (RawMeasurement rm:list)
        {
           if rm.getOutsideTemp > 770);
            zomerDagMetingen.Add (rm);
        }
        

        for (int i = 0; i < (list.size()-1440); i= i + 1440)
        {
            for(int j = i; j<1440; j++)
            {
                if(list.get(i).getOutsideTemp()>770)
                {
                    zomerdag = true;
                }
            }
            if (zomerdag) 
            //vergelijken of de dag een hogere waarden heeft dan 25*C wat met onze eenheden farenheit x10 is
            {
                count = count+1;
            }
            else
            {
                if (count > maxcount)
                {
                    maxcount=count;

                    beginDate= tijdelijkeDate;
                    eindDate = list.get(i +1440).getDateStamp();
                    //de hoogste periode die op dat moment is berekend wordt opgeslagen.
                }

                tijdelijkeDate = list.get(i + 1440).getDateStamp();
                //anders vraagt hij de laaste niet op.

                count = 0;
            }   
        } 
        System.out.println("begindatum is" + beginDate + "einddatum is" + eindDate);
        
        return p;

    }
    //public void test()
    //{
     //   ArrayList<RawMeasurement> list = periode.getRawMeasurements(new WeatherStation());
        
     //   for(int i = 0; i < list.size();i++)
       // {
         //   if(list.get(i).getTimestamp())
   // {
  //  }
}