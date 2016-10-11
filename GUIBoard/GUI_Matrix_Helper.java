import java.util.ArrayList;
/**
 * Write a description of class GUI_Matrix_Helper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI_Matrix_Helper
{
    /**
     * Display a string on the matrix display.
     * @param str   String to display
     */
    public static void stringToMatrix(String str)
    {
        char[] arr = str.toCharArray();
        for(int i = 0; i < str.length(); i++)
            IO.writeShort(0x40, arr[i]);
    }

    /**
     * Clear display.
     */
    public static void clrDisplay() 
    {
        IO.writeShort(0x40,0xFE);
        IO.writeShort(0x40,0x01);
    }

    /**
     * Turn a pixel on or off on a location (x, y)
     * @param on    True turns on, false turns off
     * @param x     X location
     * @param y     Y location
     */
    public static void turnPixel(boolean on, int x, int y)
    {        
        if (on)
            IO.writeShort(0x42, 1 << 12 | x << 5 | y);
        else
            IO.writeShort(0x42, 1 << 12 | x << 5 | y);
    }

    /**
     * Draw lines for coordinate system
     */
    public static void drawCoordinateSystem()
    {
        for (int x = 0; x < 128; x++)        
            turnPixel(true, x, 15);
        for (int y = 0; y < 32; y++)
            turnPixel(true, 63, y);
    }

    static ArrayList<Integer> waarden ;  // de ArrayList met de temperatuurWaarden
    public static void vulWaarden()
    { 
        waarden = new ArrayList<Integer>();
        waarden.add(new Integer(22)); waarden.add(new Integer(22)); waarden.add(new Integer(22));
        waarden.add(new Integer(22)); waarden.add(new Integer(21)); waarden.add(new Integer(21));
        waarden.add(new Integer(21)); waarden.add(new Integer(21)); waarden.add(new Integer(21));
        waarden.add(new Integer(20)); waarden.add(new Integer(20)); waarden.add(new Integer(20));
        waarden.add(new Integer(20)); waarden.add(new Integer(20)); waarden.add(new Integer(19));

        waarden.add(new Integer(19)); waarden.add(new Integer(19)); waarden.add(new Integer(19));
        waarden.add(new Integer(19)); waarden.add(new Integer(19)); waarden.add(new Integer(19));
        waarden.add(new Integer(18)); waarden.add(new Integer(18)); waarden.add(new Integer(18));
        waarden.add(new Integer(18)); waarden.add(new Integer(18)); waarden.add(new Integer(18));
        waarden.add(new Integer(17)); waarden.add(new Integer(18)); waarden.add(new Integer(18));

        waarden.add(new Integer(18)); waarden.add(new Integer(18)); waarden.add(new Integer(18));
        waarden.add(new Integer(19)); waarden.add(new Integer(19)); waarden.add(new Integer(20));
        waarden.add(new Integer(20)); waarden.add(new Integer(21)); waarden.add(new Integer(21));
        waarden.add(new Integer(22)); waarden.add(new Integer(22)); waarden.add(new Integer(23));
        waarden.add(new Integer(23)); waarden.add(new Integer(24)); waarden.add(new Integer(24));

        waarden.add(new Integer(24)); waarden.add(new Integer(25)); waarden.add(new Integer(25));
        waarden.add(new Integer(25)); waarden.add(new Integer(25)); waarden.add(new Integer(25));
        waarden.add(new Integer(26)); waarden.add(new Integer(26)); waarden.add(new Integer(27));
        waarden.add(new Integer(26)); waarden.add(new Integer(26)); waarden.add(new Integer(26));
        waarden.add(new Integer(27)); waarden.add(new Integer(27)); waarden.add(new Integer(27));

        waarden.add(new Integer(27)); waarden.add(new Integer(27)); waarden.add(new Integer(27));
        waarden.add(new Integer(26)); waarden.add(new Integer(27)); waarden.add(new Integer(27));
        waarden.add(new Integer(27)); waarden.add(new Integer(27)); waarden.add(new Integer(27));
        waarden.add(new Integer(27)); waarden.add(new Integer(25)); waarden.add(new Integer(23));
        waarden.add(new Integer(24)); waarden.add(new Integer(24)); waarden.add(new Integer(24));

        waarden.add(new Integer(24)); waarden.add(new Integer(24)); waarden.add(new Integer(23));
        waarden.add(new Integer(23)); waarden.add(new Integer(23)); waarden.add(new Integer(23));
        waarden.add(new Integer(22)); waarden.add(new Integer(22)); waarden.add(new Integer(22));
        waarden.add(new Integer(21)); waarden.add(new Integer(21)); waarden.add(new Integer(21));
        waarden.add(new Integer(21)); waarden.add(new Integer(20)); waarden.add(new Integer(20));

        waarden.add(new Integer(20)); waarden.add(new Integer(20)); waarden.add(new Integer(20));
        waarden.add(new Integer(20)); waarden.add(new Integer(19)); waarden.add(new Integer(19));

        waarden.add(new Integer(17)); waarden.add(new Integer(15)); waarden.add(new Integer(13));
        waarden.add(new Integer(11)); waarden.add(new Integer( 9)); waarden.add(new Integer( 7));
        waarden.add(new Integer( 5)); waarden.add(new Integer( 3)); waarden.add(new Integer( 1));
    } 
}
