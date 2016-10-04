/**
 * Class with functions that return the current calculated data.
 */
public class Measurement
{
   private MetingenHandler M;
   private RawMeasurement rawMeasurement;
   
   public Measurement(RawMeasurement raw)
   {
       M = new MetingenHandler();
       rawMeasurement = raw;
   }
   
   public java.sql.Timestamp Time()
   {
       return rawMeasurement.getDateStamp();
   }
   public double BarometerData()
   {
       return M.luchtdruk(rawMeasurement.getBarometer());
   }
  
   public double TemperatuurInsideData()
   {
       return M.temperatuur(rawMeasurement.getInsideTemp());
   }
   
   public double LuchtvochtigheidInsideData()
   {
       return rawMeasurement.getInsideHum();
   }
   
   public double TemperatuurOutsideData()
   {
       return M.temperatuur(rawMeasurement.getOutsideTemp());
   }
   
   public double WindSpeedData()
   {
       return M.windSnelheid(rawMeasurement.getWindSpeed());
   }
   
   public double WindSpeedAvData()
   {
       return M.windSnelheid(rawMeasurement.getAvgWindSpeed());       
   }
   
   public String WindRichtingData()
   {
       return M.windRichting(rawMeasurement.getWindDir());
   }
   
   public double LuchtvochtigheidOutsideData()
   {
       return rawMeasurement.getOutsideHum();
   }
   
   public double RainRateData()
   {
       return M.regenmeter(rawMeasurement.getRainRate());
   }
   
   public double UVIndexData()
   {
       return M.uvIndex(rawMeasurement.getUVLevel());
   }
   
   public short SolarRad()
   {
       return rawMeasurement.getSolarRad();
   }
   
   public double BatteryData()
   {
       return M.batterySpanning(rawMeasurement.getBattLevel());
   }
   
   public String SunRiseData()
   {
       return M.sunRise(rawMeasurement.getSunrise());
   }
   
   public String SunSetData()
   {
       return M.sunSet(rawMeasurement.getSunset());
   }
   
   public String heatIndex()
    {
        final double C1 = -42.379,
                     C2 = 2.04901523,
                     C3 = 10.14333127,
                     C4 = -0.22475541,
                     C5 = -0.00683783,
                     C6 = -5.481717E-2,
                     C7 = 1.22874E-3,
                     C8 = 8.5282E-4,
                     C9 = -1.99E-6;
        double T = rawMeasurement.getOutsideTemp()/10;
        double R = LuchtvochtigheidOutsideData();
        double T2 = T * T;
        double R2 = R * R;
        String answer;
        double HI;
        if(TemperatuurOutsideData()>26)
        {
            HI = Math.round(((C1 + (C2 * T) + (C3 * R) + (C4 * T * R) + (C5 * T2) + (C6 * R2) + (C7 * T2 * R) + (C8 * T * R2) + (C9 * T2 * R2))-32)/1.8);
            answer = String.valueOf(HI+"°C");
        }
        else
        {
            answer = "De temperatuur is te laag om de heat index te berekenen.";
        }
        return answer;
    }
   
   public String windChill()
   {
       double T = TemperatuurOutsideData();
       double V = WindSpeedData();
       String answer;
       double WC;
       if(T<10 && V>1.3)
       {
           WC = 13.12 + (0.6215*T) - (11.37*(Math.pow(V,0.16))+(0.3965*(T*Math.pow(V,0.16))));
           answer = String.valueOf(WC+"°C");
       }
       else if(T>=10 && V<=1.3)
       {
           answer = "De temperatuur is te hoog en de windsnelheid is te laag.";
       }
       else if(T>=10) 
       {
           answer = "De Temperatuur is te hoog.";
       }
       else
       {
           answer = "De windsnelheid is te laag.";
       }
       return answer; 
   }
   
   public String dewPoint()
   {
       double T = TemperatuurOutsideData();
       double R = LuchtvochtigheidOutsideData();
       String answer;
       double D;
       double a = 17.27;
       double b = 237.7;
       double TRL = ((a*T)/(b+T))+ Math.log(R/100);
       if(0<T && T<100 && 1<R && R<100)
       {
           D = Math.round((b*TRL)/(a-TRL));
           answer = String.valueOf(D+"°C");
       }
       else
       {
           answer = "Het dauwpunt kan niet worden berekend.";
       }
       return answer;
   }
   
   public void PrintAlles()
   {
       System.out.println(
       "Datum en tijd: \t\t\t"+Time()+
       "\nBarometer: \t\t\t"+BarometerData()+"kPa"+
       "\nBinnentemperatuur: \t\t"+TemperatuurInsideData()+"°C"+
       "\nBuitentemperatuur: \t\t"+TemperatuurOutsideData()+"°C"+
       "\nLuchtvochtigheid binnen: \t"+LuchtvochtigheidInsideData()+"%"+
       "\nLuchtvochtigheid buiten: \t"+LuchtvochtigheidOutsideData()+"%"+
       "\nWindsnelheid: \t\t\t"+WindSpeedData()+" Km/U"+
       "\nWindrichting: \t\t\t"+WindRichtingData()+
       "\nWindchill: \t\t\t"+windChill()+
       "\nBatteryspanning: \t\t"+BatteryData()+ " Volt"+
       "\nUV index: \t\t\t"+ UVIndexData()+
       "\nZonneStraling: \t\t\t"+SolarRad()+" W/m²"+
       "\nHeat index: \t\t\t"+heatIndex()+
       "\nDauwpunt: \t\t\t"+dewPoint()+
       "\nZonsopgang: \t\t\t"+ SunRiseData()+
       "\nZonsondergang: \t\t\t"+ SunSetData()
       
       );
   }
}
