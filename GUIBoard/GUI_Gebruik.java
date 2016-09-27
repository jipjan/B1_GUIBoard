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
    
    public void countAllDigits() {
        for (int i = 0; i < 3; i++)
        for (int j = 0; j < 10; j++) {              
            IO.writeShort(0x10, j);
            IO.writeShort(0x12, j);
            IO.writeShort(0x14, j);
            IO.writeShort(0x16, j);
            IO.writeShort(0x18, j);
            IO.delay(100);
        }
    }
    
    public void countToMaxWithLeading() {
        for (int i = 0; i < 100000; i++)
            GUI_Helper.intToGui(i, true);
    }
    
    public static void testWerking() {
        IO.init();
        IO.writeShort(0x22, 7); // Display het  (decimale)  getal  7  op element 0x22
    }
}
