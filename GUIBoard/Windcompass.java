public class Windcompass
{
    /**
     * Draw a wind-flower with an arrow indicating the wind direction
     * @param degrees   amount of degrees to turn the arrow
     * @param x         The offset for the location of the wind-flower, shouldn't be higher than 95.
     */
    public static void DrawWindcompass(int degrees, int x)
    {
        // check if x is not out of bounds
        x = x > 95 ? 0 : x;
        // X lines for box
        for (int y = 0; y < 32; y++)
        {
            GUI_Matrix_Helper.turnPixel(true, x, y);
            GUI_Matrix_Helper.turnPixel(true, x + 32, y);
        }
        // Y lines for box
        for (int lineX = x; lineX < x + 32; lineX++)
        {
            GUI_Matrix_Helper.turnPixel(true, lineX, 0);
            GUI_Matrix_Helper.turnPixel(true, lineX, 31);
        }
        // draw line        
        double xE = Math.sin(Math.toRadians(degrees));
        double yE = Math.cos(Math.toRadians(degrees - 180));
        for (int r = 1; r < 7; r++)
            GUI_Matrix_Helper.turnPixel(true, x + 15 + (int) (xE * r), 15 + (int) (yE * r));

        // draw letters
        // N
        for (int nY = 2; nY < 7; nY++)
        {
            GUI_Matrix_Helper.turnPixel(true, x + 13, nY);
            GUI_Matrix_Helper.turnPixel(true, x + 17, nY);
            GUI_Matrix_Helper.turnPixel(true, x + 13 + nY - 2, nY);
        }

        // Z
        int counter = 0;
        for (int zX = 13 + x; zX < 18 + x; zX++)
        {
            GUI_Matrix_Helper.turnPixel(true, zX, 25);
            GUI_Matrix_Helper.turnPixel(true, zX, 29);
            GUI_Matrix_Helper.turnPixel(true, zX, 29 - counter);
            counter++;
        }

        // O
        for (int oX = 26 + x; oX < 30 + x; oX++)
        {
            GUI_Matrix_Helper.turnPixel(true, oX, 13);
            GUI_Matrix_Helper.turnPixel(true, oX, 17);
        }
        for (int oY = 13; oY < 18; oY++)
        {
            GUI_Matrix_Helper.turnPixel(true, x+26, oY);
            GUI_Matrix_Helper.turnPixel(true, x+30, oY);
        }

        // W
        for (int wY = 13; wY < 18; wY++)
        {
            GUI_Matrix_Helper.turnPixel(true, x + 2, wY);
            GUI_Matrix_Helper.turnPixel(true, x + 7, wY);
        }
        GUI_Matrix_Helper.turnPixel(true, x+3, 17);
        GUI_Matrix_Helper.turnPixel(true, x+4, 16);
        GUI_Matrix_Helper.turnPixel(true, x+5, 16);
        GUI_Matrix_Helper.turnPixel(true, x+6, 17);
    }
}
