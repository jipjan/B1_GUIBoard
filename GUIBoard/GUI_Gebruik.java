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
        
        System.out.println(Statistics.getAverage(list, Statistics.Unit.OutsideTemp));
        System.out.println(Statistics.getDeviant(list, Statistics.Unit.OutsideTemp));
    }
}
