import java.util.*;
import java.time.*;
import java.sql.*;
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

    
    private short getValue(int i, Unit unit)
    {
        return (short) getValue(i, unit, false);
    }
    
    /**
     * Get specific unit from the list.
     * @param i     Index to use for the list
     * @param unit  Unit to retrieve from the list
     * @return      The value from the list
     */
    private double getValue(int i, Unit unit, boolean converted)
    {
        if (i < list.size())
            switch (unit)
            {
                case InsideTemp: return converted ? MetingenHandler.temperatuur(list.get(i).getInsideTemp()) : list.get(i).getInsideTemp();
                case OutsideTemp: return converted ? MetingenHandler.temperatuur(list.get(i).getOutsideTemp()) : list.get(i).getOutsideTemp();
                case Windspeed: return converted ? MetingenHandler.windSnelheid(list.get(i).getWindSpeed()) : list.get(i).getWindSpeed();
                case OutsideHum: return list.get(i).getOutsideHum();
                case RainRate: return converted ? MetingenHandler.regenmeter(list.get(i).getRainRate()) : list.get(i).getRainRate();
                case UVLevel: return converted ? MetingenHandler.uvIndex(list.get(i).getUVLevel()) : list.get(i).getUVLevel();
                case Solarrad: return list.get(i).getSolarRad();
                case Barometer: return list.get(i).getBarometer();
            }
        return 0;
    }

    
    public double getAverage(Unit unit)
    {
        return getAverage(unit, false);
    }
    
    /**
     * Get average of the value in the measurements.
     * @param unit      Unit to retrieve from the list
     * @param converted Convert value before calculation
     * @return          The average
     */
    public double getAverage(Unit unit, boolean converted)
    {
        if (list.size() == 0) return 0;
        double average = 0;
        for(int i = 0; i < list.size(); i++)
            average += getValue(i, unit, converted);
        return average / list.size();
    }

    public ArrayList<Short> getAveragesOnDays(Unit unit)
    {
        ArrayList<Short> toReturn = new ArrayList<Short>();    
        if (list.size() < 1) return toReturn;
        int count = 0;
        int total = 0;
        Timestamp date = list.get(0).getDateStamp();
        for(int i = 0; i < list.size(); i++)
        {
            if (dateSame(list.get(i).getDateStamp(), date))
            {
                count++;
                total += getValue(i, unit);
            }
            else
            {
                if (count == 0)
                    return toReturn;
                date = list.get(i).getDateStamp();
                toReturn.add(new Short((short)(total/count)));
                count = 0;
                total = 0;
            }
        }
        return toReturn;
    }

    public ArrayList<Short> getHighestOnDays(Unit unit)
    {
        ArrayList<Short> toReturn = new ArrayList<Short>();    
        if (list.size() < 1) return toReturn;
        short highest = 0;
        Timestamp date = list.get(0).getDateStamp();
        for(int i = 0; i < list.size(); i++)
        {
            if (dateSame(list.get(i).getDateStamp(), date))
            {
                short temp = getValue(i, unit);
                if (temp > highest)
                    highest = temp;
            }
            else
            {
                date = list.get(i).getDateStamp();
                toReturn.add(new Short(highest));
                highest = 0;
            }
        }
        return toReturn;
    }

    private boolean dateSame(Timestamp t1, Timestamp t2)
    {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(t1);
        cal2.setTime(t2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);  
    }

    /**
     * Get lowest value of the unit in the list.
     * @param unit  The unit to retrieve the lowest value from.
     * @return      Lowest value.
     */
    public short getLowest(Unit unit)
    {
        if (list.size() == 0) return 0;
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
        short modus = 0;
        Iterator it = map.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            if ((int) pair.getValue() > modus)
                modus = (short) pair.getKey();            
        }
        return modus;
    }

    /**
     * Get highest value of the unit in the list.
     * @param unit  The unit to retrieve the highest value from.
     * @return      Highest value.
     */
    public short getHighest(Unit unit)
    {
        if (list.size() == 0) return 0;
        short highest = Short.MIN_VALUE;
        for(int i = 0; i < list.size(); i++)
        {
            short value = getValue(i, unit);
            if (highest < value)
                highest = value;
        }
        return highest;
    }

    public double getDeviant(Unit unit)
    {
        double average = getAverage(unit, true);
        double deviant = 0;
        for (int i = 0; i < list.size(); i++)        
            deviant += Math.pow(getValue(i, unit, true) - average, 2);
        return Math.pow(deviant / (list.size() - 1), 0.5d);
    }

    /**
     * Calculate Median from a unit in the list.
     * @param unit  The unit to calculate the median of
     * @return      The median
     */
    public double getMedian(Unit unit)
    {
        if (list.size() == 0) return 0;
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
