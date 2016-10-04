/**
 * This class contains all methods needed to convert the raw mesurements to dutch units
 * @author Groep B1
 * @version 1.2
 */
public class MetingenHandler
{
	/**
	* Luchtdruk
	*
	* @param mval   Meetwaarde van het vp2pro weerstation
	* @return De luchtdruk in mbar
	*/
	public static double luchtdruk(short mval)
	{
		return Math.round( (mval/100d) * 3.3864d);
	}

	/**
	* Temperatuur
	*
	* @param mval   Meetwaarde van het vp2pro weerstation
	* @return De temperatuur in graden Celcius
	*/
	public static double temperatuur(short mval)
	{
		return Math.round( ((mval / 10d) - 32) /1.8d );
	}

	/**
	* Windsnelheid
	*
	* @param mval   Meetwaarde van het vp2pro weerstation
	* @return De windsnelheid in km/h
	*/
	public static double windSnelheid(short mval)
	{
		return Math.round( (mval * 1.609344d));
	}

	/**
	* Windrichting
	*
	* @param mval   Meetwaarde van het vp2pro weerstation
	* @return De windrichting In windroos richtingen
	*/
	public static String windRichting(short mval)
	{
		String mogelijkWindrichting[] = {"N", "NO", "O", "ZO", "Z", "ZW", "W", "NW", "N"};
		return mogelijkWindrichting[mval /45];
	}

	/**
	* Regenmeter
	*
	* @param mval   Meetwaarde van het vp2pro weerstation
	* @return De hoeveelheid regen in mm
	*/
	public static double regenmeter(short mval)
	{
		return Math.round((mval *0.2));
	}

	/**
	* uvIndex
	*
	* @param mval   Meetwaarde van het vp2pro weerstation
	* @return De windrichting in graden
	*/
	public static double uvIndex(short mval)
	{
		return Math.round((mval /10d));
	}

	/**
	* batterySpanning
	*
	* @param mval   Meetwaarde van het vp2pro weerstation
	* @return De battery spanning in Volt
	*/
	public static double batterySpanning(short mval)
	{
		return Math.round(((mval *300) /512d) /100d);
	}

	/**
	 * TimeShortToString
	 *
	 * @param shortTime 	de tijd gegeven in een short
	 * @return de tijd gegeven in een string in hh:mm notatie
	 */
	private static String TimeShortToString(short shortTime)
	{
		String toReturn = Short.toString(shortTime);
		if (toReturn.length() == 3)
			return "0" + toReturn.substring(0,1) + ":" + toReturn.substring(1, 3);
		else
			return toReturn.substring(0,2) + ":" + toReturn.substring(2, 4);
	}

	/**
	* sunRise
	*
	* @param mval	Meetwaarde van het vp2pro weerstation
	* @return Zonsopkomst in hh:mm notatie
	*/
	public static String sunRise(short mval)
	{
		return TimeShortToString(mval);
	}

	/**
	* sunSet
	*
	* @param mval	Meetwaarde van het vp2pro weerstation
	* @return Zonsondergang in hh:mm notatie
	*/
	public static String sunSet(short mval)
	{
	   return TimeShortToString(mval);
	}
}
