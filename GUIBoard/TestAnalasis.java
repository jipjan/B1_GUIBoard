
/**
 * Write a description of class TestAnalasis here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestAnalasis
{
    Period period = new Period();
    Analysis analysis; 
    
    public TestAnalasis()
    {
        period.setStart(2016, 1, 1);
        period.setEnd(2016, 12, 31);
        analysis = new Analysis(period);
        IO.init();
    }

    public void testHasHeatWave()
    {
        analysis.hasHeatWave();
    }
    
    public void testMaxAmountOfSequentRainDays()
    {
        analysis.maxAmountOfSequentRainDays();
    }
    
    public void testGetLongestRainfall()
    {
        analysis.getLongestRainfall();
    }
}
