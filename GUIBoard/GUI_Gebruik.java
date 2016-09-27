/**
 * Write a description of class GuiHandler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI_Gebruik
{
    public GUI_Gebruik() {
        IO.init();
    }
    
    public void countFirstDigit() {
        for (int i = 0; i < 3; i++)
        for (int j = 0; j < 10; j++) {              
            IO.writeShort(0x10, j);
            IO.delay(100);
        }
    }
    
    public static void testWerking() {
        IO.init();
        IO.writeShort(0x22, 7); // Display het  (decimale)  getal  7  op element 0x22
    }
}
