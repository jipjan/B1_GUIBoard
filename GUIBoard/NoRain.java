import java.util.ArrayList;
/**
 * Calculates the longest period that it didn't rain.
 */
public class NoRain
{
    private ArrayList<RawMeasurement> list;
    
    private short[] Data = {1,2,0,0,0,0,4,8,5,2,0,0,0,0,0,8,3,6,0,0,0};//21
    public void regenChecker()
    {
        int Ldroogte = 0;
        int stop = 1;
        int count = 0;
        for(int i = 0; i < Data.length;i++)
        {
            if(Data[i] == 0)
            {
                count += 1;
            }
            else
            {
                if(count>Ldroogte)
                {

                }
            }
        }
    }
    
    
    public void Test(Period periode)//voer de periode in
    {
        ArrayList<RawMeasurement> list = periode.getRawMeasurements(new WeatherStation());// voer de periode array in in list
        int count = 0;
        int maxCount = 0;
        for(int i = 0; i < list.size();i++)
        {
            if(list.get(i).getRainRate()== 0)
            {
                count += 1;
            }
            else
            {
                if(count>maxCount)
                {
                    maxCount = count;
                    Begindate =
                    Einddate = 
                }
            }
        }
        
        
    }
}
