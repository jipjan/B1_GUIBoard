import java.util.*;
/**
 * Caculates statistics for the input array of data
 */
public class Statistics
{
    public enum Unit { InsideTemp, OutsideTemp, Windspeed, OutsideHum, RainRate, UVLevel, Solarrad, Barometer };

    public double getAverage(ArrayList<RawMeasurement> list, Unit kindOf)
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

    /**
     * Calculates the mode.
     */
    public double modusHelper(int count, int maxCount,double maxValue, double i)
    {
        if(count > maxCount)
        {
            maxCount = count;
            maxValue = i;
        }
        return maxValue;
    }

    public double getModus(ArrayList<RawMeasurement> list, Unit kindOf)
    {
        double modus;
        int count = 0;
        int maxCount = 0;
        double maxValue = 0;
        for(int i = 0; i < list.size();i++)
            switch (kindOf)
            {
                case InsideTemp:
                for(int j = 0; j <list.size();j++)
                {
                    if(list.get(i).getInsideTemp()==list.get(j).getInsideTemp())
                    {
                        count = count + 1;
                    }
                }
                maxValue = modusHelper(count, maxCount, maxValue, list.get(i).getInsideTemp());
                break;

                case OutsideTemp:
                for(int j = 0; j <list.size();j++)
                {
                    if(list.get(i).getOutsideTemp()==list.get(j).getOutsideTemp())
                    {
                        count = count + 1;
                    }
                }
                maxValue = modusHelper(count, maxCount, maxValue, list.get(i).getOutsideTemp());
                break;

                case Windspeed:
                for(int j = 0; j <list.size();j++)
                {
                    if(list.get(i).getWindSpeed()==list.get(j).getWindSpeed())
                    {
                        count = count + 1;
                    }
                }
                maxValue = modusHelper(count, maxCount, maxValue, list.get(i).getWindSpeed());

                case OutsideHum:
                for(int j = 0; j <list.size();j++)
                {
                    if(list.get(i).getOutsideHum()==list.get(j).getOutsideHum())
                    {
                        count = count + 1;
                    }
                }
                maxValue = modusHelper(count, maxCount, maxValue, list.get(i).getOutsideHum());
                break;

                case RainRate:
                for(int j = 0; j <list.size();j++)
                {
                    if(list.get(i).getRainRate()==list.get(j).getRainRate())
                    {
                        count = count + 1;
                    }
                }
                maxValue = modusHelper(count, maxCount, maxValue, list.get(i).getRainRate());
                break;

                case UVLevel:
                for(int j = 0; j <list.size();j++)
                {
                    if(list.get(i).getUVLevel()==list.get(j).getUVLevel())
                    {
                        count = count + 1;
                    }
                }
                maxValue = modusHelper(count, maxCount, maxValue, list.get(i).getUVLevel());
                break;

                case Solarrad:
                for(int j = 0; j <list.size();j++)
                {
                    if(list.get(i).getSolarRad()==list.get(j).getSolarRad())
                    {
                        count = count + 1;
                    }
                }
                maxValue = modusHelper(count, maxCount, maxValue, list.get(i).getSolarRad());
                break;

                case Barometer:
                for(int j = 0; j <list.size();j++)
                {
                    if(list.get(i).getBarometer()==list.get(j).getBarometer())
                    {
                        count = count + 1;
                    }
                }
                maxValue = modusHelper(count, maxCount, maxValue, list.get(i).getBarometer());
                break;
            }
        return maxValue;
    }  

    public short nieuwModus(ArrayList<RawMeasurement> list, Unit unit)
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

    public double getHighest(ArrayList<RawMeasurement> list, Unit kindOf)
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
}
