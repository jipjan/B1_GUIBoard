import java.util.ArrayList;

public class GUI_Gebruik
{
    public GUI_Gebruik() 
    {
        IO.init();
    }
    
    
    public void testAvg()
    {
        ArrayList<RawMeasurement> list = new ArrayList<RawMeasurement>();
        
        RawMeasurement item = new RawMeasurement();
        item.setOutsideTemp((short) 5);
        
        RawMeasurement item2 = new RawMeasurement();
        item2.setOutsideTemp((short)-3);
        
        RawMeasurement item3 = new RawMeasurement();
        item3.setOutsideTemp((short)10);
        
        list.add(item);
        list.add(item2);
        list.add(item3);
        
        
        Statistics stat = new Statistics(list);
        
        System.out.println(stat.getAverage(Statistics.Unit.OutsideTemp));
        System.out.println(stat.getDeviant(Statistics.Unit.OutsideTemp));
    }
    
    public void testMedian()
    {
        ArrayList<RawMeasurement> list = new ArrayList<RawMeasurement>();
        
        RawMeasurement item = new RawMeasurement();
        item.setOutsideTemp((short) 5);
        
        RawMeasurement item2 = new RawMeasurement();
        item2.setOutsideTemp((short)-3);
        
        RawMeasurement item3 = new RawMeasurement();
        item3.setOutsideTemp((short)10);
        
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.add(item3);
        
        
        Statistics stat = new Statistics(list);
        
        System.out.println(stat.getMedian(Statistics.Unit.OutsideTemp));
    }
    
    public void testModus()
    {
        ArrayList<RawMeasurement> list = new ArrayList<RawMeasurement>();
        
        RawMeasurement item = new RawMeasurement();
        item.setOutsideTemp((short) 5);
        
        RawMeasurement item2 = new RawMeasurement();
        item2.setOutsideTemp((short)-3);
        
        RawMeasurement item3 = new RawMeasurement();
        item3.setOutsideTemp((short)10);
        
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.add(item3);
        list.add(item3);
        list.add(item2);
        
        Statistics stat = new Statistics(list);
        
        System.out.println(stat.getModus(Statistics.Unit.OutsideTemp));
    }
    
    public void testHeatWave()
    {
        Period p = new Period();
        p.setStart(2013, 1, 1);
        p.setEnd(2015, 12, 31);
        System.out.println(OpdrachtDrieJJ.hasHeatWave(p));
    }
    
    public void testMaxRain()
    {
        Period p = new Period();
        p.setStart(2015, 1, 1);
        System.out.println(OpdrachtDrieJJ.maxAmountOfRainSeqDays(p));
    }
}
