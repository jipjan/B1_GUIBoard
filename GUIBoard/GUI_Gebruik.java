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

    public void countWithZeros()
    {
        for(int i = 0; i < 100000; i++)
        {
            int vakje = 0x10;
            int getal = i;
            for(int j = 0; j < 6; j++)
            {
                IO.writeShort(vakje,(getal%10));
                vakje = vakje + 0x02;
                getal = getal / 10;
            }
            IO.delay(10);
        }
    }

    public void counterWithoutZeros()
    {      
        for(int i = 0; i < 100000; i++)
        {
            int vakje = 0x10;
            int getal = i;
            int lengte = String.valueOf(i).length();
            for(int j = 0; j < lengte; j++)
            {
                IO.writeShort(vakje,(getal%10));    
                vakje = vakje + 0x02;
                getal = getal / 10;
            }
            IO.delay(100);
        }
    }

    public void counterWithButtons()
    {
        int i = 0;
        while(true)
        {
            if(IO.readShort(0x100) != 0)// Linker blauwe Knop NIET ingedrukt
            {
                if(IO.readShort(0x80) != 0 )// Roode knop NIET ingedrukt
                {
                    int vakje = 0x10;
                    int getal = i;
                    int lengte = String.valueOf(i).length();
                    for(int j = 0; j < 5; j++)
                    { 
                        IO.writeShort(vakje,0x100);
                        vakje = vakje + 0x02;
                        getal = getal / 10;
                    }
                    IO.delay(100);
                }
                else
                {
                    int vakje = 0x10;
                    int getal = i;
                    int lengte = String.valueOf(i).length();
                    for(int j = 0; j < lengte; j++)
                    {
                        if(i==0){IO.writeShort(0x10, 0);}
                        if(i==9){IO.writeShort(0x12, 0x100);}
                        if(i==99){IO.writeShort(0x14, 0x100);}
                        if(i==999){IO.writeShort(0x16, 0x100);}
                        if(i==9999){IO.writeShort(0x18, 0x100);}
                        else{IO.writeShort(vakje,(getal%10));}
                        vakje = vakje + 0x02;
                        getal = getal / 10;
                    }
                    IO.delay(100);
                }
                if ( i != 0)
                {
                    i=i-1;   
                }
            }
            else
            {
                if(IO.readShort(0x80) != 0 )
                {
                    int vakje = 0x10;
                    int getal = i;
                    int lengte = String.valueOf(i).length();
                    for(int j = 0; j < lengte; j++)
                    { 
                        IO.writeShort(vakje,0x100);
                        vakje = vakje + 0x02;
                        getal = getal / 10;
                    }
                    IO.delay(100);
                }
                else
                {
                    int vakje = 0x10;
                    int getal = i;
                    int lengte = String.valueOf(i).length();
                    for(int j = 0; j < lengte; j++)
                    {
                        IO.writeShort(vakje,(getal%10)); 
                        vakje = vakje + 0x02;
                        getal = getal / 10;
                    }
                    IO.delay(100);
                }
                if(i!=99999)
                {
                    i=i+1 ;
                }
            }
        }
    }

    public void runOwnAnimation() {        
        new Thread(() -> GUI_Digits_Helper.animationMultipleseg(0x30, 0x32, 0x34, 0x20, 0x22, 0x24)).start();
        GUI_Digits_Helper.loopAnimation(0x10, 0x12, 0x14, 0x16, 0x18);
        GUI_Digits_Helper.snakeAnimation(0x10, 0x12, 0x14, 0x16, 0x18);
    }

    public void runVideoAnimation() {
        GUI_Digits_Helper.movieAnimation();
    }

    public void drawParabola()
    {
        // x = 128, y = 32
        // y = 0,1 * x^2 -10
        GUI_Matrix_Helper.clrDisplay();
        GUI_Matrix_Helper.drawCoordinateSystem();
        for (int x = -64; x < 64; x++)
        {
            double y = 0.1 * Math.pow((double)x, 2d) - 10;
            if (y < 0 || y > 31) continue;
            GUI_Matrix_Helper.turnPixel(true, x + 64, (int)y);
        }        
    }

    public void drawTempParabola()
    {
        GUI_Matrix_Helper.clrDisplay();
        GUI_Matrix_Helper.vulWaarden();
        for (int x = 0; x < GUI_Matrix_Helper.waarden.size(); x++)
            GUI_Matrix_Helper.turnPixel(true, x, GUI_Matrix_Helper.waarden.get(x));
    }

    public static void testWerking() {
        IO.init();
        IO.writeShort(0x22, 7); // Display het  (decimale)  getal  7  op element 0x22
    }
}
