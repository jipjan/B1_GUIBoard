import java.util.ArrayList;

public class GUI_Gebruik
{
    public GUI_Gebruik() 
    {
        IO.init();
        Period p = new Period();
        p.setStart(java.time.LocalDate.of(2015, 1, 1));
        Analysis test = new Analysis(p);
        System.out.println(test.hasHeatWave());
    }
}
