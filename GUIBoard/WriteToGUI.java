import java.util.ArrayList;
import java.time.*;

public class WriteToGUI
{
    private WeatherStation ws;
    private ArrayList<RawMeasurement> rm;
    private Statistics stat;
    private Analysis analysis;
    Period period;
    LoadingDataAnimation animation = new LoadingDataAnimation();

    /**
     * This constructor starts the class with the current year.
     */
    public WriteToGUI() 
    {
        LocalDateTime current = LocalDateTime.now();
        int year = current.getYear();
        initialise();
        loadData(year);
    }
    
    /**
     * This constructor starts the class with a given year.
     */
    public WriteToGUI(int year) 
    {
        initialise();
        loadData(year);
    }
    
    /**
     * This method initialises the class.
     * Multiple Constructors can call this saving code.
     */
    private void initialise()
    {   
        IO.init();
        GUI_Matrix_Helper.clrDisplay();
        
        analysis = new Analysis();
        ws = new WeatherStation();
        period = new Period();
    }
    
    /**
     * This method loads the data from the database into the variabele rm.
     * While loading this method calls the start animation method so a loading animation is showed in the GUI.
     * The parameter year is the year you want the data to load from.
     */
    private void loadData(int year)
    {
        LocalDateTime current = LocalDateTime.now();
        int currentYear = current.getYear();
        int currentMonth = current.getMonthValue();
        int currentDay = current.getDayOfMonth();
        
        retrievingDataMessage();
        period.setStart(year, 1, 1);
        
        if(year == currentYear || year > currentYear)
        {
            period.setEnd(currentYear, currentMonth, currentDay);
        }
        else
        {
            period.setEnd(year, 12, 31);
        }
        
        rm = period.getRawMeasurements(ws);
        
        animation.stopAnimation();
        clearGUI();
        Windcompass.DrawWindcompass(90, 55);
    }
    
    /**
     * This method turns a timestamp in a more pretty to display format and returns that as a String.
     */
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
    
    /**
     * This method returns a given digit in a string.
     * If the digit is below 10 we make it a double digit again by adding a 0 in front of it.
     * Example: 9 becomed 09. 10 stays 10. Both return as String.
     */
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
    
    /**
     * This method calls the method to clear the GUI and calls the method to start a loading animation.
     */
    private void retrievingDataMessage()
    {
        clearGUI();
        animation.startAnimation();
    }

    /**
     * This method clears all the fields of the GUI.
     */
    private void clearGUI()
    {
        GUI_Digits_Helper.clearAll(); 
        GUI_Matrix_Helper.clrDisplay();
    }

    /**
     * This method writes a number to the given location on the GUI board.
     * Note: you cannot write to the matrix with this method.
     */
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
    
    /**
     * This method prints the most recent outside temperature from the database of the weatherstation to the GUI.
     */
    public void printMostRecentOutsideTemp()
    {
        clearGUI();

        int vakje = 0x10;
        double getal = MetingenHandler.temperatuur(ws.getMostRecentOutsideTemp());

        guiWriter(vakje,getal);
        GUI_Matrix_Helper.stringToMatrix("Buitentemperatuur");
    }
    
    /**
     * This method prints a week worth of data to the GUI, starting exactly 7 days ago than the date of today.
     * G: gemiddelde.
     * L: laagste.
     * H: hoogste.
     */
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
    
    /**
     * This method prints a week worth of data to the GUI, starting exactly 7 days ago than the date of today.
     * M: mediaan.
     * M: modus.
     * S: standaardafwijking.
     */
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
       
    /**
     * This method prints a month worth of data to the GUI, starting exactly a month ago than the date of today.
     * G: gemiddelde.
     * L: laagste.
     * H: hoogste.
     */
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
        "Temperatuur last month"+"\n"+"     Gemiddelde"+"\n"+"Laagste      Hoogste"

        );
    }
    
    /**
     * This method prints a month worth of data to the GUI, starting exactly a month ago than the date of today.
     * M: mediaan.
     * M: modus.
     * S: standaardafwijking.
     */
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
        "Temperatuur last month"+"\n"+"Standaardafwijking"+"\n"+"Modus         Mediaan"

        );
    }
    
    /**
     * This writes the longest temperature rise to the GUI that was found in the data
     * of variable rm.
     */
    public void printCurrentWindSpeed()
    {
        clearGUI();
        
        int vakje = 0x10;
        double getal = MetingenHandler.windSnelheid(ws.getMostRecentWindSpeed());

        guiWriter(vakje,getal);
        GUI_Matrix_Helper.stringToMatrix("Windsnelheid"+"\n"+"in km/u");
    }
    
    /**
     * This writes to the GUI if the data of variable rm contains a heatwave.
     */
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
    
    /**
     * This writes the most sequential rain that has fallen to the GUI that was found in the data
     * of variable rm.
     */
    public void printMaxAmountOfSequentRain()
    {
        clearGUI();
        
        int maxAmountOfRain = analysis.maxAmountOfSequentRain(rm);
        guiWriter(0x10, (double)maxAmountOfRain);
        GUI_Matrix_Helper.stringToMatrix("Maximale Regenval" + "\n" + "tussen: " + period.getStart() + "\n" + "en " + period.getEnd() + " in mm.");
    }
    
    /**
     * This writes the longest rainfall to the GUI that was found in the data
     * of variable rm.
     */
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
    
    /**
     * This writes the longest drought to the GUI that was found in the data
     * of variable rm.
     */
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
    
    /**
     * This writes the longest temperature rise to the GUI that was found in the data
     * of variable rm.
     */
    public void printLongestTemperatureRise()
    {
        Period longestTempRisePeriod = analysis.longestTemperatureRise(rm, period);
        GUI_Matrix_Helper.stringToMatrix("De langste tempstijging:" + "\n" + "van: " + longestTempRisePeriod.getStart() + "\n" + "tot: " + longestTempRisePeriod.getEnd());
    }
    
    /**
     * This method returns the start year of the loaded data where the main
     * methods run there code on.
     */
    public int getYear()
    {
        LocalDate startDate = period.getStart();
        return startDate.getYear();
    }
    
    /**
     * This method returns the period of the loaded data where the main
     * methods run there code on. 
     */
    public Period getPeriod()
    {
        return period;
    }
    
    /**
     * This method call a method that loads the weatherstation data of the given year.
     */
    public void setYear(int year)
    {
        loadData(year);
    }
}


