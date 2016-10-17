import java.util.ArrayList;
import java.util.Calendar;
import java.time.*;

public class WriteToGUI
{
    private WeatherStation ws;
    private ArrayList<RawMeasurement> rm;
    private Statistics stat;
    private Analysis analysis;
    Period period;
    LoadingDataAnimation animation = new LoadingDataAnimation();

    public WriteToGUI() 
    {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        
        IO.init();
        GUI_Matrix_Helper.clrDisplay();
        
        retrievingDataMessage();
        
        analysis = new Analysis();
        ws = new WeatherStation();
        period = new Period();
        period.setStart(year, 1, 1);
        period.setEnd(year, 12, 31);
        
        clearGUI();
        animation.stopAnimation();
        Windcompass.DrawWindcompass(45, 55);
    }
    
    public WriteToGUI(int year) 
    {
        IO.init();
        GUI_Matrix_Helper.clrDisplay();
        
        retrievingDataMessage();
        
        analysis = new Analysis();
        ws = new WeatherStation();
        period = new Period();
        period.setStart(year, 1, 1);
        period.setEnd(year, 12, 31);
        
        rm = period.getRawMeasurements(ws);
        
        animation.stopAnimation();
        clearGUI();
        Windcompass.DrawWindcompass(45, 55);
    }
    
    private String timeStampToString(LocalDateTime timeStamp)
    {
        String seconds = doubleDigits(timeStamp.getSecond());
        String minutes = doubleDigits(timeStamp.getMinute());
        String hours = doubleDigits(timeStamp.getHour());
        String days = doubleDigits(timeStamp.getDayOfMonth());
        String months = doubleDigits(timeStamp.getMonthValue());
        String years = doubleDigits(timeStamp.getYear());
        
        return years + "-" + months + "-" + days + " " + hours + ":" + minutes + ":" + seconds;
    }
    
    private String doubleDigits(int digit)
    {
        String doubleDigit;
        
        if(digit < 10)
        {
            doubleDigit = "0" + digit;
        }
        else
        {
            doubleDigit = Integer.toString(digit);
        }
        
        return doubleDigit;
    }
    
    private void retrievingDataMessage()
    {
        clearGUI();
        animation.startAnimation();
    }

    private void clearGUI()
    {
        GUI_Digits_Helper.clearAll(); 
        GUI_Matrix_Helper.clrDisplay();
    }

    private void guiWriter(int vakje, double getal)
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
        
        animation.stopAnimation();
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
        
        animation.stopAnimation();
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
        
        animation.stopAnimation();
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
    
    public void printHasHeatWave()
    {
        clearGUI();
        
        if(analysis.hasHeatWave(rm))
        {
            GUI_Matrix_Helper.stringToMatrix("Er was een hittgolf" + "\n" + "tussen: " + period.getStart() + "\n" + "en " + period.getEnd());
        }
        else
        {
            GUI_Matrix_Helper.stringToMatrix("Er was geen hittegolf" + "\n" + "tussen: " + period.getStart() + "\n" + "en " + period.getEnd());
        }
    }
    
    public void printMaxAmountOfSequentRain()
    {
        clearGUI();
        
        int maxAmountOfRain = analysis.maxAmountOfSequentRain(rm);
        guiWriter(0x10, (double)maxAmountOfRain);
        GUI_Matrix_Helper.stringToMatrix("Maximale Regenval" + "\n" + "tussen: " + period.getStart() + "\n" + "en " + period.getEnd() + " in mm.");
    }
    
    public void printLongestRainfall()
    {
        String startDateTime;
        String endDateTime;
        DateTimePeriod longestPeriod;
        
        clearGUI();
        longestPeriod = analysis.longestRainfall(rm);
        startDateTime = timeStampToString(longestPeriod.getStartDateTime());
        endDateTime = timeStampToString(longestPeriod.getEndDateTime());
        
        GUI_Matrix_Helper.stringToMatrix("De langste regenval" + "\n" + "v " + startDateTime + "\n" + "t " + endDateTime);
    }
    
    public void printLongestDrought(int maxMillimeterRainfall)
    {
        String startDateTime;
        String endDateTime;
        DateTimePeriod longestPeriod;
        
        clearGUI();
        
        longestPeriod = analysis.longestDrought(rm, maxMillimeterRainfall);
        startDateTime = timeStampToString(longestPeriod.getStartDateTime());
        endDateTime = timeStampToString(longestPeriod.getEndDateTime());
        
        GUI_Matrix_Helper.stringToMatrix("De langste droogte:" + "\n" + "van: " + startDateTime + "\n" + "tot: " + endDateTime);
    }
    
    public void printLongestTemperatureRise()
    {
        Period longestTempRisePeriod = analysis.longestTemperatureRise(rm, period);
        GUI_Matrix_Helper.stringToMatrix("De langste tempstijging:" + "\n" + "van: " + longestTempRisePeriod.getStart() + "\n" + "tot: " + longestTempRisePeriod.getEnd());
    }
}


