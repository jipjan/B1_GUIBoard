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
    
    
   public static void testWerking() {
       IO.init();
       IO.writeShort(0x22, 7); // Display het  (decimale)  getal  7  op element 0x22
   }
}
