import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * The test class TestStatistics.
 *
 * @author  Jaap-Jan de Wit
 * @version 18-10-2016
 */
public class TestStatistics
{   
    Statistics s;
    ArrayList<RawMeasurement> data;
    
    public TestStatistics()
    {
        data = new ArrayList<RawMeasurement>();
        s = new Statistics(data);
    }
    
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        data.clear();
    }
    
    
    @Test
    public void getAverageTest()
    {      
        assertEquals("Average no ok for an empty data array", 0, s.getAverage(Statistics.Unit.InsideTemp), 0);

        RawMeasurement rm = new RawMeasurement(); 
        rm.setInsideTemp((short)799);
        RawMeasurement rm2 = new RawMeasurement();
        rm2.setInsideTemp((short)433);
        RawMeasurement rm3 = new RawMeasurement();
        rm3.setInsideTemp((short)234);        
        
        data.add(rm);        
        assertEquals("Average not ok 1 item", 799d, s.getAverage(Statistics.Unit.InsideTemp), 0);
                        
        data.add(rm2);
        data.add(rm3);
        assertEquals("Average not ok 3 items", 488d, s.getAverage(Statistics.Unit.InsideTemp), 1);
    }
    
    @Test
    public void getLowest()
    {
        assertEquals("Lowest no ok for an empty data array", 0, s.getLowest(Statistics.Unit.InsideTemp));

        RawMeasurement rm = new RawMeasurement(); 
        rm.setInsideTemp((short)799);
        RawMeasurement rm2 = new RawMeasurement();
        rm2.setInsideTemp((short)433);
        RawMeasurement rm3 = new RawMeasurement();
        rm3.setInsideTemp((short)234); 
        
        data.add(rm);
        data.add(rm2);
        data.add(rm3);
        
        assertEquals("Lowest not ok for 3 items filled array", 234, s.getLowest(Statistics.Unit.InsideTemp));
    }
    
    @Test
    public void getHighest()
    {
        assertEquals("Highest not ok for an empty data array", 0, s.getHighest(Statistics.Unit.InsideTemp));

        RawMeasurement rm = new RawMeasurement(); 
        rm.setInsideTemp((short)799);
        RawMeasurement rm2 = new RawMeasurement();
        rm2.setInsideTemp((short)433);
        RawMeasurement rm3 = new RawMeasurement();
        rm3.setInsideTemp((short)234); 
        
        data.add(rm);
        data.add(rm2);
        data.add(rm3);
        
        assertEquals("Highest not ok for 3 items filled array", 799, s.getHighest(Statistics.Unit.InsideTemp));
    }
    
    @Test
    public void testGetModus()
    {
        assertEquals("Modus not ok for an empty data array", 0, s.getModus(Statistics.Unit.InsideTemp), 0);
        
        RawMeasurement rm = new RawMeasurement(); 
        rm.setInsideTemp((short)799);
        RawMeasurement rm2 = new RawMeasurement();
        rm2.setInsideTemp((short)433);
        RawMeasurement rm3 = new RawMeasurement();
        rm3.setInsideTemp((short)234); 
        
        data.add(rm);
        data.add(rm);
        data.add(rm2);
        data.add(rm2);
        data.add(rm2);
        data.add(rm3);
        
        assertEquals("Modus not ok for 3 items filled array", 433, s.getModus(Statistics.Unit.InsideTemp), 1);
    }
    
    @Test
    public void testGetDeviant()
    {
        assertEquals("Deviant not ok for an empty data array", 0, s.getDeviant(Statistics.Unit.InsideTemp), 0);
        
        RawMeasurement rm = new RawMeasurement(); 
        rm.setInsideTemp((short)799);
        RawMeasurement rm2 = new RawMeasurement();
        rm2.setInsideTemp((short)433);
        RawMeasurement rm3 = new RawMeasurement();
        rm3.setInsideTemp((short)234); 
        
        data.add(rm);
        data.add(rm);
        data.add(rm2);
        data.add(rm2);
        data.add(rm2);
        data.add(rm3);
        
        assertEquals("Deviant not ok for 3 items filled array", 228, s.getDeviant(Statistics.Unit.InsideTemp), 1);
    }
    
    @Test
    public void testMedian()
    {
        assertEquals("Median not ok for an empty data array", 0, s.getMedian(Statistics.Unit.InsideTemp), 0);
        
        RawMeasurement rm = new RawMeasurement(); 
        rm.setInsideTemp((short)799);
        RawMeasurement rm2 = new RawMeasurement();
        rm2.setInsideTemp((short)433);
        RawMeasurement rm3 = new RawMeasurement();
        rm3.setInsideTemp((short)234); 
        
        data.add(rm);
        
        assertEquals("Median not ok for 1 item filled array", 799, s.getMedian(Statistics.Unit.InsideTemp), 1);
        
        data.add(rm);
        data.add(rm2);
        data.add(rm2);
        data.add(rm2);
        data.add(rm3);
        
        assertEquals("Median not ok for 3 items filled array", 433, s.getMedian(Statistics.Unit.InsideTemp), 1);
    }
    
}
