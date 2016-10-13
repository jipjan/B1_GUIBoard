public class Windroos
{
    /**
     * Draw a wind-flower with an arrow indicating the wind direction
     * @param degrees   amount of degrees to turn the arrow
     * @param x         The offset for the location of the wind-flower, shouldn't be higher than 95.
     */
    public void DrawWindroos(int degrees, int x)
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
        int center = x + 15;
        int radius = 10;
        drawLine(center, center, degrees);
    }
    
    public void drawLine(int xS, int yS, int degrees)
    {        
        double xE = Math.sin(degrees);
        double yE = Math.cos(degrees);
        for (int r = 1; r < 16; r++)
            GUI_Matrix_Helper.turnPixel(true, xS + (int) (xE * r), yS + (int) (yE * r));
    }
}
