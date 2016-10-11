import java.util.ArrayList;
/**
 * Calculates the longest period that it didn't rain.
 */
public class NoRain
{
    public ArrayList<java.sql.Timestamp> maxDroogePeriode(Period periode, int mNeerslag)//voer de periode in
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
        System.out.println("Periode: "+Begindate+" tot "+Einddate);
        time.add(Begindate);
        time.add(Einddate);
        return time;
    }
}