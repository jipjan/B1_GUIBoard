
/**
 * Write a description of class GUI_DotMatrix here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI_DotMatrix
{
    public GUI_DotMatrix()  {
        IO.init();
    }
    
    public static void pixelTest()  {
    int opcode = 3;
    IO.writeShort(0x42, opcode << 12);  // Clear display

    int x,y;
    opcode = 1;
    for ( int idx = 0; idx < 1000; idx++ )
      { x = (int) (Math.random()*128); 
        y = (int) (Math.random()* 32);
        IO.writeShort(0x42, opcode << 12 | x << 5 | y); IO.delay(100);
      }
    }

}
