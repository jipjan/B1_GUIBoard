
/**
 * Write a description of class GUI_Matrix_Helper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI_Matrix_Helper
{
    public static void stringToMatrix(String str)
    {
        char[] arr = str.toCharArray();
        for(int i = 0; i < str.length(); i++)
            IO.writeShort(0x40, arr[i]);
    }

    public static void clrDisplay() 
    {
        IO.writeShort(0x40,0xFE);
        IO.writeShort(0x40,0x01);
    }

    public static void turnPixel(boolean on, int x, int y)
    {        
        if (on)
            IO.writeShort(0x42, 1 << 12 | x << 5 | y);
        else
            IO.writeShort(0x42, 1 << 12 | x << 5 | y);
    }
    
    public static void drawCoordinateSystem()
    {
        for (int x = 0; x < 128; x++)        
            turnPixel(true, x, 15);
        for (int y = 0; y < 32; y++)
            turnPixel(true, 63, y);
    }
}
