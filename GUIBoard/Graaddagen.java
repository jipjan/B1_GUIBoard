import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Write a description of class Graaddagen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Graaddagen
{
    private ArrayList<RawMeasurement> list;
    private WeatherStation weatherStation;
    private Statistics _statistics;

    /**
     * Retourneert het aantal ongewogen graaddagen in een periode.
     */ 
    public int getGraaddagen()
    {
		Period periode = new Period();
		periode.setStart(2015,1,1);
		periode.setEnd(2015,6,30);

        ArrayList<RawMeasurement> list = periode.getRawMeasurements(new WeatherStation());
        _statistics = new Statistics(list);

        int referentieTemp = 18;    //18°C = meest gebruikte referentietemperatuur.
        int graadDagen = 0;

        ArrayList<Short> averageList = _statistics.getAveragesOnDays(Statistics.Unit.OutsideTemp);

        for(int i = 0; i < averageList.size();i++) {
            double averageTemp = ((((averageList.get(i) /10)-32)/1.8d));//rekent de gemiddelde temperatuur om naar Celsius.
            System.out.println("Average temp = "+averageTemp);
            if(averageTemp < referentieTemp) {
                graadDagen += (referentieTemp - averageTemp);
            }
        }

        System.out.println("Graaddagen: " + graadDagen);
        return graadDagen;
    }
}

