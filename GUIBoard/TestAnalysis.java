
/**
 * Write a description of class TestAnalasis here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestAnalysis
{
    Period period = new Period();
    Analysis analysis; 
    
    public TestAnalysis()
    {
        period.setStart(2016, 1, 1);
        period.setEnd(2016, 12, 31);
        analysis = new Analysis(period);
        IO.init();
    }
    
    public void setPeriodTo2013()
    {
        period.setStart(2013, 1, 1);
        period.setEnd(2013, 12, 31);
    }
    
    public void setPeriodTo2014()
    {
        period.setStart(2014, 1, 1);
        period.setEnd(2014, 12, 31);
    }
    
    public void setPeriodTo2015()
    {
        period.setStart(2015, 1, 1);
        period.setEnd(2015, 12, 31);
    }
    
    public void setPeriodTo2016()
    {
        period.setStart(2016, 1, 1);
        period.setEnd(2016, 12, 31);
    }

    public void testHasHeatWave()
    {
        analysis.hasHeatWave();
    }
    
    public void testMaxAmountOfSequentRainDays()
    {
        analysis.maxAmountOfSequentRain();
    }
    
    public void testGetLongestRainfall()
    {
        analysis.getLongestRainfall();
    }
}
