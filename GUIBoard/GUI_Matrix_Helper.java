
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
}
