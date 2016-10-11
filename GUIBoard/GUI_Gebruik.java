import java.util.ArrayList;

public class GUI_Gebruik
{
    private WeatherStation ws;

    public GUI_Gebruik() 
    {
        IO.init();
        ws = new WeatherStation();
    }

    public void printMostRecentOutsideTemp()
    {
        GUI_Digits_Helper.clearAll(); 
        GUI_Matrix_Helper.clrDisplay();
        int vakje = 0x10;//is het bovenste vakje
        double getal = MetingenHandler.temperatuur(ws.getMostRecentOutsideTemp());
        System.out.println(getal);
        if(getal < 0) //als het een negatief getal is
        {
            for(int j = 0; j < 2; j++)
            {
                int temp;
                if(getal <= -10)
                    temp = (int)(getal*10);
                else
                    temp = (int)(getal);
                IO.writeShort(vakje,GUI_Digits_Helper.digitToSegments(temp%10,vakje==0x12)); 
                vakje = vakje + 0x02;
                getal = getal / 10;
            }
        }
        else //als het een positief getal is
        {
            for(int j = 0; j < 3; j++)
            {
                int temp = (int)(getal*10);
                IO.writeShort(vakje,GUI_Digits_Helper.digitToSegments(temp%10,vakje==0x12)); 
                vakje = vakje + 0x02;
                getal = getal / 10;
            }
        }

        GUI_Matrix_Helper.stringToMatrix("Buitentemperatuur");
    }

    public void testNegatief()
    {
        GUI_Digits_Helper.clearAll(); 
        GUI_Matrix_Helper.clrDisplay();
        int vakje = 0x10;//is het bovenste vakje
        double getal = -2.5;
        System.out.println(getal);
        if(getal < 0) //als het een negatief getal is
        {
            getal = Math.abs(getal);
            int temp;
            if(getal >= 10)
            {
                for(int j = 0; j < 2; j++)
                {

                    temp = (int)(getal);
                    IO.writeShort(vakje,GUI_Digits_Helper.digitToSegments(temp%10,false));
                    vakje = vakje + 0x02;
                    getal = getal / 10;
                }
            }
            else
            {
                for(int j = 0; j < 2; j++)
                {

                    temp = (int)(getal*10);
                    IO.writeShort(vakje,GUI_Digits_Helper.digitToSegments(temp%10,vakje==0x12)); 
                    vakje = vakje + 0x02;
                    getal = getal / 10;
                } 
            }
            IO.writeShort(vakje,0x140);
        }
        else //als het een positief getal is
        {
            for(int j = 0; j < 3; j++)
            {
                int temp = (int)(getal*10);
                IO.writeShort(vakje,GUI_Digits_Helper.digitToSegments(temp%10,vakje==0x12)); 
                vakje = vakje + 0x02;
                getal = getal / 10;
            }
        }

        GUI_Matrix_Helper.stringToMatrix("Buitentemperatuur");
    }

}
