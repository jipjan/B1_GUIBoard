/**
 * Calculates the longest period that it didn't rain.
 */
public class NoRain
{
    private ArrayList<RawMeasurement> list;

    public Statistics(ArrayList<RawMeasurement> measurements)
    {
        list = measurements;
    }

    public enum Unit { InsideTemp, OutsideTemp, Windspeed, OutsideHum, RainRate, UVLevel, Solarrad, Barometer };

    public void getArray()
    {
        double median = 0;
        ArrayList<Short> medianList = new ArrayList<Short>();
        for(int i = 0; i < list.size();i++)
            switch (unit)
            {
                case InsideTemp:
                medianList.add(list.get(i).getInsideTemp());
                break;

                case OutsideTemp:
                medianList.add(list.get(i).getOutsideTemp());
                break;

                case Windspeed:
                medianList.add(list.get(i).getWindSpeed());
                break;

                case OutsideHum:
                medianList.add(list.get(i).getOutsideHum());
                break;

                case RainRate:
                medianList.add(list.get(i).getRainRate());
                break;

                case UVLevel:
                medianList.add(list.get(i).getUVLevel());
                break;

                case Solarrad:
                medianList.add(list.get(i).getSolarRad());
                break;

                case Barometer:
                medianList.add(list.get(i).getBarometer());
                break;
            }
    }
    private short[] Data = {1,2,0,0,0,0,4,8,5,2,0,0,0,0,0,8,3,6,0,0,0};//21
    public void regenChecker()
    {
        int Ldroogte = 0;
        int stop = 1;
        int count = 0;
        for(int i = 0; i < Data.length;i++)
        {
            if(reeks[i] == 0)
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

}
