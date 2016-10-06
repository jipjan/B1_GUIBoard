import java.util.*;
/**
 * Caculates statistics for the input array of data
 */
public class Statistics
{
    private ArrayList<RawMeasurement> list;

    public Statistics(ArrayList<RawMeasurement> measurements)
    {
        list = measurements;
    }

    public enum Unit { InsideTemp, OutsideTemp, Windspeed, OutsideHum, RainRate, UVLevel, Solarrad, Barometer };

    public double getAverage(Unit kindOf)
    {
        double average = 0;
        for(int i = 0; i < list.size();i++)
            switch (kindOf)
            {
                case InsideTemp:
                average += list.get(i).getInsideTemp();
                break;

                case OutsideTemp:
                average += list.get(i).getOutsideTemp();
                break;

                case Windspeed:
                average += list.get(i).getWindSpeed();
                break;

                case OutsideHum:
                average += list.get(i).getOutsideHum();
                break;

                case RainRate:
                average += list.get(i).getRainRate();
                break;

                case UVLevel:
                average += list.get(i).getUVLevel();
                break;

                case Solarrad:
                average += list.get(i).getSolarRad();
                break;

                case Barometer:
                average += list.get(i).getBarometer();
                break;
            }

        return average /list.size();
    } 

    /**
     * Assists getLowest.
     */
    public double lowestHelper(double L, double lowest)
    {
        if(lowest > L)
        {
            lowest = L;
        }
        return lowest;
    }

    public double getLowest(ArrayList<RawMeasurement> list, Unit kindOf)
    {
        double lowest = Integer.MAX_VALUE;
        for(int i = 0; i < list.size();i++)
            switch (kindOf)
            {
                case InsideTemp:
                lowest = lowestHelper(list.get(i).getInsideTemp(), lowest);
                break;

                case OutsideTemp:
                lowest = lowestHelper(list.get(i).getOutsideTemp(), lowest);
                break;

                case Windspeed:
                lowest = lowestHelper(list.get(i).getWindSpeed(), lowest);
                break;

                case OutsideHum:
                lowest = lowestHelper(list.get(i).getOutsideHum(), lowest);
                break;

                case RainRate:
                lowest = lowestHelper(list.get(i).getRainRate(), lowest);
                break;

                case UVLevel:
                lowest = lowestHelper(list.get(i).getUVLevel(), lowest);
                break;

                case Solarrad:
                lowest = lowestHelper(list.get(i).getSolarRad(), lowest);
                break;

                case Barometer:
                lowest = lowestHelper(list.get(i).getBarometer(), lowest);
                break;
            }

        return lowest;
    } 

    public short getModus(Unit unit)
    {
        HashMap<Short, Integer> map = new HashMap<Short, Integer>();
        for (int i = 0; i < list.size(); i++)
            switch (unit)
            {
                case InsideTemp:
                addItemToMap(map, list.get(i).getInsideTemp());
                break;

                case OutsideTemp:
                addItemToMap(map, list.get(i).getOutsideTemp());
                break;

                case Windspeed:
                addItemToMap(map, list.get(i).getWindSpeed());
                break;

                case OutsideHum:
                addItemToMap(map, list.get(i).getOutsideHum());
                break;

                case RainRate:
                addItemToMap(map, list.get(i).getRainRate());
                break;

                case UVLevel:
                addItemToMap(map, list.get(i).getUVLevel());
                break;

                case Solarrad:
                addItemToMap(map, list.get(i).getSolarRad());
                break;

                case Barometer:
                addItemToMap(map, list.get(i).getBarometer());
                break;
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
    
    private void addItemToMap(HashMap<Short, Integer> map, short item)
    {
        if (map.containsKey(item))
            map.put(item, map.get(item)+1);
        else
            map.put(item, 1);
    }

    /**
     * Assists getHighest.
     */
    public double highestHelper(double H, double highest)
    {
        if(highest < H)
        {
            highest = H;
        }
        return highest;
    }

    public double getHighest(Unit kindOf)
    {
        double highest = Integer.MIN_VALUE;
        for(int i = 0; i < list.size();i++)
            switch (kindOf)
            {
                case InsideTemp:
                highest = highestHelper(list.get(i).getInsideTemp(), highest);
                break;

                case OutsideTemp:
                highest = highestHelper(list.get(i).getInsideTemp(), highest);
                break;

                case Windspeed:
                highest = highestHelper(list.get(i).getInsideTemp(), highest);
                break;

                case OutsideHum:
                highest = highestHelper(list.get(i).getInsideTemp(), highest);
                break;

                case RainRate:
                highest = highestHelper(list.get(i).getInsideTemp(), highest);;
                break;

                case UVLevel:
                highest = highestHelper(list.get(i).getInsideTemp(), highest);
                break;

                case Solarrad:
                highest = highestHelper(list.get(i).getInsideTemp(), highest);
                break;

                case Barometer:
                highest = highestHelper(list.get(i).getInsideTemp(), highest);
                break;
            }
        return highest;
    } 

    public double getDeviant(Unit unit)
    {
        double deviant = 0;
        double average = getAverage(unit);
        switch (unit)
        {
            case InsideTemp:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getInsideTemp() - average, 2);
            break;

            case OutsideTemp:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getOutsideTemp() - average, 2);
            break;

            case Windspeed:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getWindSpeed() - average, 2);
            break;

            case OutsideHum:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getOutsideHum() - average, 2);
            break;

            case RainRate:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getRainRate() - average, 2);
            break;

            case UVLevel:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getUVLevel() - average, 2);
            break;

            case Solarrad:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getSolarRad() - average, 2);
            break;

            case Barometer:
            for (int i = 0; i < list.size(); i++)
                deviant += Math.pow(list.get(i).getBarometer() - average, 2);
            break;
        }
        return Math.pow(deviant / (list.size() - 1), 0.5);
    }

    public short getMedian(Unit unit)
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
        Collections.sort(medianList);
        int middle = medianList.size() / 2;
        if(medianList.size() % 2 == 0)
            return (short) Math.round(((medianList.get(middle - 1) + medianList.get(middle)) / 2d));
        else
            return medianList.get(middle);
    }

    private ArrayList<Short> sortList(Unit unit)
    {
        ArrayList<Short> sList = new ArrayList<Short>();
        for(int i = 0; i < list.size();i++)
            switch (unit)
            {
                case InsideTemp:
                sList.add(list.get(i).getInsideTemp());
                break;

                case OutsideTemp:
                sList.add(list.get(i).getOutsideTemp());
                break;

                case Windspeed:
                sList.add(list.get(i).getWindSpeed());
                break;

                case OutsideHum:
                sList.add(list.get(i).getOutsideHum());
                break;

                case RainRate:
                sList.add(list.get(i).getRainRate());
                break;

                case UVLevel:
                sList.add(list.get(i).getUVLevel());
                break;

                case Solarrad:
                sList.add(list.get(i).getSolarRad());
                break;

                case Barometer:
                sList.add(list.get(i).getBarometer());
                break;
            }
        Collections.sort(sList);
        return sList;
    }
}
