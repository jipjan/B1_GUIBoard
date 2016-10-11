import java.util.*;
import java.time.*;
/**
 * Caculates statistics for the input array of data
 */
public class Statistics
{
    // private list to use in the statistics calculations.
    private ArrayList<RawMeasurement> list;
    
    /**
     * Enum with all the possible units to use in the statistics calculations.
     */
    public enum Unit { InsideTemp, OutsideTemp, Windspeed, OutsideHum, RainRate, UVLevel, Solarrad, Barometer };

    /**
     * Constructor which takes the measurements you want to analyze.
     * @param measurements  The list with measurements to run the methods on.
     */
    public Statistics(ArrayList<RawMeasurement> measurements)
    {
        list = measurements;
    }   

    /**
     * Get specific unit from the list.
     * @param i     Index to use for the list
     * @param unit  Unit to retrieve from the list
     * @return      The value from the list
     */
    private short getValue(int i, Unit unit)
    {
        switch (unit)
        {
            case InsideTemp: return list.get(i).getInsideTemp();
            case OutsideTemp: return list.get(i).getOutsideTemp();
            case Windspeed: return list.get(i).getWindSpeed();
            case OutsideHum: return list.get(i).getOutsideHum();
            case RainRate: return list.get(i).getRainRate();
            case UVLevel: return list.get(i).getUVLevel();
            case Solarrad: return list.get(i).getSolarRad();
            case Barometer: return list.get(i).getBarometer();
        }
        return 0;
    }

    /**
     * Get average of the value in the measurements.
     * @param unit  Unit to retrieve from the list
     * @return      The average
     */
    public int getAverage(Unit unit)
    {
        int average = 0;
        for(int i = 0; i < list.size(); i++)
            average += getValue(i, unit);
        return average / list.size();
    }
    
    public ArrayList<Short> getAveragesOnDays(Unit unit)
    {
        ArrayList<Short> toReturn = new ArrayList<Short>();    
        if (list.size() < 1) return toReturn;
        int count = 0;
        int total = 0;
        LocalDate date = list.get(0).getDateStamp().toLocalDateTime().toLocalDate();
        for(int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getDateStamp().toLocalDateTime().toLocalDate().equals(date))
            {
                count++;
                total += getValue(i, unit);
            }
            else
            {
                if (count == 0) return toReturn;
                date = list.get(0).getDateStamp().toLocalDateTime().toLocalDate();
                toReturn.add(new Short((short)(total/count)));
                count = 0;
                total = 0;
            }
        }
        return toReturn;
    }

    /**
     * Get lowest value of the unit in the list.
     * @param unit  The unit to retrieve the lowest value from.
     * @return      Lowest value.
     */
    public short getLowest(Unit unit)
    {
        short lowest = Short.MAX_VALUE;
        for(int i = 0; i < list.size(); i++)
        {
            short value = getValue(i, unit);
            if (lowest > value)
                lowest = value;
        }
        return lowest;
    }

    /**
     * Get modus of the units in the list.
     * @param unit  The unit to retrieve the modus of.
     * @return      The modus.
     */
    public short getModus(Unit unit)
    {
        HashMap<Short, Integer> map = new HashMap<Short, Integer>();
        for (int i = 0; i < list.size(); i++)
        {
            short item = getValue(i, unit);
            if (map.containsKey(item))
                map.put(item, map.get(item) + 1);
            else
                map.put(item, 1);
        }
        short median = 0;
        int amount = 0;
        Iterator it = map.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if ((int) pair.getValue() > amount)
                median = (short) pair.getKey();
        }
        return median;
    }

    /**
     * Get highest value of the unit in the list.
     * @param unit  The unit to retrieve the highest value from.
     * @return      Highest value.
     */
    public short getHighest(Unit unit)
    {
        short highest = Short.MIN_VALUE;
        for(int i = 0; i < list.size(); i++)
        {
            short value = getValue(i, unit);
            if (highest < value)
                highest = value;
        }
        return highest;
    }

    /**
     * Calculate deviant from a unit in the list.
     * @param unit  The unit to calculate the deviant of
     * @return      The deviant as a double
     */
    public double getDeviant(Unit unit)
    {
        double deviant = 0;
        double average = getAverage(unit);
        for (int i = 0; i < list.size(); i++)
            deviant += Math.pow(getValue(i, unit) - average, 2);
        return Math.pow(deviant / (list.size() - 1), 0.5);
    }

    /**
     * Calculate Median from a unit in the list.
     * @param unit  The unit to calculate the median of
     * @return      The median
     */
    public double getMedian(Unit unit)
    {
        ArrayList<Short> medianList = sortList(unit);
        int middle = medianList.size() / 2;
        return medianList.size() % 2 == 0 ?
            ((medianList.get(middle - 1) + medianList.get(middle)) / 2d) : medianList.get(middle);
    }

    /**
     * Build a sorted list based on a unit.
     * @param unit  The unit to use as sort operator.
     * @return      The sorted array.
     */
    private ArrayList<Short> sortList(Unit unit)
    {
        ArrayList<Short> sList = new ArrayList<Short>();
        for(int i = 0; i < list.size(); i++)
            sList.add(getValue(i, unit));
        Collections.sort(sList);
        return sList;
    }
}
