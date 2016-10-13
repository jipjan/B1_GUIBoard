
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
        analysis = new Analysis(period);
        IO.init();
    }

    public void testMaxAmountOfSequentRainDays()
    {
        analysis.maxAmountOfSequentRainDays();
    }
}
