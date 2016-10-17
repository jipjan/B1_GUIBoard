import java.util.ArrayList;
import java.time.*;

public class WriteToGUI
{
    private WeatherStation ws;
    private ArrayList<RawMeasurement> rm;
    private Statistics stat;

    public WriteToGUI() 
    {
        IO.init();
        GUI_Matrix_Helper.clrDisplay();
        clearGUI();
        Windcompass.DrawWindcompass(45, 55);
        ws = new WeatherStation();
    }
    
    private void retrievingDataMessage()
    {
        clearGUI();
        GUI_Matrix_Helper.stringToMatrix("Data ophalen...");
    }

    public void clearGUI()
    {
        GUI_Digits_Helper.clearAll(); 
        GUI_Matrix_Helper.clrDisplay();
    }

    public void guiWriter(int vakje, double getal)
    {
        if(getal < 0)
        {
            getal = Math.abs(getal);
            int temp;
            if(getal >= 10)
            {
                for(int j = 0; j < 2; j++)
                {

                    temp = (int)(getal);
                    IO.writeShort(vakje,GUI_Digits_Helper.digitToSegments(temp%10,false));
                    vakje = vakje + 0x02;
                    getal = getal / 10;
                }
            }
            else
            {
                for(int j = 0; j < 2; j++)
                {

                    temp = (int)(getal*10);
                    IO.writeShort(vakje,GUI_Digits_Helper.digitToSegments(temp%10,vakje==0x12 || vakje==0x22 || vakje==0x32)); 
                    vakje = vakje + 0x02;
                    getal = getal / 10;
                } 
            }
            IO.writeShort(vakje,0x140);
        }
        else
        {
            int lengte = 3;
            if(getal <10)
                lengte = 2;
            for(int j = 0; j < lengte; j++)
            {
                int temp = (int)(getal*10);
                IO.writeShort(vakje,GUI_Digits_Helper.digitToSegments(temp%10,vakje==0x12 || vakje==0x22 || vakje==0x32)); 
                vakje = vakje + 0x02;
                getal = getal / 10;
            }
        }
    }

    public void printMostRecentOutsideTemp()
    {
        clearGUI();

        int vakje = 0x10;
        double getal = MetingenHandler.temperatuur(ws.getMostRecentOutsideTemp());

        guiWriter(vakje,getal);
        GUI_Matrix_Helper.stringToMatrix("Buitentemperatuur");
    }
    
    public void printLastWeekGLH()
    {
        retrievingDataMessage();
        
        Period period = new Period();
        period.setStart((LocalDate.now().minusDays(7)));
        
        rm = period.getRawMeasurements(ws);
        Statistics stat = new Statistics(rm);
        
        clearGUI();
        
        //Top digits, Average
        int vakje = 0x10;
        double getal = MetingenHandler.temperatuurDouble(stat.getAverage(Statistics.Unit.OutsideTemp));
        guiWriter(vakje,getal);
        
        //Top digits, Lowest
        vakje = 0x20;
        getal = MetingenHandler.temperatuur(stat.getLowest(Statistics.Unit.OutsideTemp));
        guiWriter(vakje,getal);
        
        //Top digits, Highest
        vakje = 0x30;
        getal = MetingenHandler.temperatuur(stat.getHighest(Statistics.Unit.OutsideTemp));
        guiWriter(vakje,getal);
        
        //Tekst on dot matrix
        GUI_Matrix_Helper.stringToMatrix(
        "Temperatuur last week"+"\n"+"     Gemiddelde"+"\n"+"Laagste      Hoogste"

        );
    }
    
    public void printLastWeekMMS()
    {
        retrievingDataMessage();
        
        Period period = new Period();
        period.setStart((LocalDate.now().minusDays(7)));
        
        rm = period.getRawMeasurements(ws);
        Statistics stat = new Statistics(rm);
        
        clearGUI();
        
        //Top digits, Standaardafwijking
        int vakje = 0x10;
        double getal = MetingenHandler.temperatuurDouble(stat.getDeviant(Statistics.Unit.OutsideTemp));
        guiWriter(vakje,getal);
        
        //Top digits, Modus
        vakje = 0x20;
        getal = MetingenHandler.temperatuur(stat.getModus(Statistics.Unit.OutsideTemp));
        guiWriter(vakje,getal);
        
        //Top digits, Mediaan
        vakje = 0x30;
        getal = MetingenHandler.temperatuurDouble(stat.getMedian(Statistics.Unit.OutsideTemp));
        guiWriter(vakje,getal);
        
        //Tekst on dot matrix
        GUI_Matrix_Helper.stringToMatrix(
        "Temperatuur last week"+"\n"+"Standaardafwijking"+"\n"+"Modus         Mediaan"

        );
    }
    
    public void printLastMonthGLH()
    {
        retrievingDataMessage();
        
        Period period = new Period();
        period.setStart((LocalDate.now().minusMonths(1)));
        
        rm = period.getRawMeasurements(ws);
        Statistics stat = new Statistics(rm);
        
        clearGUI();
        
        //Top digits, Average
        int vakje = 0x10;
        double getal = MetingenHandler.temperatuurDouble(stat.getAverage(Statistics.Unit.OutsideTemp));
        guiWriter(vakje,getal);
        
        //Top digits, Lowest
        vakje = 0x20;
        getal = MetingenHandler.temperatuur(stat.getLowest(Statistics.Unit.OutsideTemp));
        guiWriter(vakje,getal);
        
        //Top digits, Highest
        vakje = 0x30;
        getal = MetingenHandler.temperatuur(stat.getHighest(Statistics.Unit.OutsideTemp));
        guiWriter(vakje,getal);
        
        //Tekst on dot matrix
        GUI_Matrix_Helper.stringToMatrix(
        "Temperatuur last week"+"\n"+"     Gemiddelde"+"\n"+"Laagste      Hoogste"

        );
    }
    
    public void printLastMonthMMS()
    {
        retrievingDataMessage();
        
        Period period = new Period();
        period.setStart((LocalDate.now().minusMonths(1)));
        
        rm = period.getRawMeasurements(ws);
        Statistics stat = new Statistics(rm);
        
        clearGUI();
        
        //Top digits, Standaardafwijking
        int vakje = 0x10;
        double getal = MetingenHandler.temperatuurDouble(stat.getDeviant(Statistics.Unit.OutsideTemp));
        guiWriter(vakje,getal);
        
        //Top digits, Modus
        vakje = 0x20;
        getal = MetingenHandler.temperatuur(stat.getModus(Statistics.Unit.OutsideTemp));
        guiWriter(vakje,getal);
        
        //Top digits, Mediaan
        vakje = 0x30;
        getal = MetingenHandler.temperatuurDouble(stat.getMedian(Statistics.Unit.OutsideTemp));
        guiWriter(vakje,getal);
        
        //Tekst on dot matrix
        GUI_Matrix_Helper.stringToMatrix(
        "Temperatuur last week"+"\n"+"Standaardafwijking"+"\n"+"Modus         Mediaan"

        );
    }
    
    public void printCurrentWindSpeed()
    {
        clearGUI();
        
        int vakje = 0x10;
        double getal = MetingenHandler.windSnelheid(ws.getMostRecentWindSpeed());

        guiWriter(vakje,getal);
        GUI_Matrix_Helper.stringToMatrix("Windsnelheid"+"\n"+"in km/u");
    }
}
