
/**
 * Write a description of class GUI_Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI_Menu
{
    String[] menuItems;

    public GUI_Menu(String[] GivenMenuItems)
    {
        menuItems = GivenMenuItems;
    }
    
    public void showMenu()
    {
        int itemsToDisplay = menuItems.length;
        int i = 0;
        if (itemsToDisplay <= 1)
        {
            menuToDisplay(menuItems[i]);
        } else if (itemsToDisplay == 2)
        {
            menuToDisplay(menuItems[i], menuItems[(i+1)]);
        } else
        {
            menuToDisplay(menuItems[i], menuItems[(i+1)], menuItems[(i+2)]);
        }
    }
    
    private void menuToDisplay(String textToDisplay)
    {
        menuToDisplay("_", textToDisplay);
    }
    
    private void menuToDisplay(String textToDisplay1, String textToDisplay2)
    {
        menuToDisplay(textToDisplay1, textToDisplay2, "_");
    }

    private void menuToDisplay(String textToDisplay1, String textToDisplay2, String textToDisplay3)
    {
        GUI_Matrix_Helper.clrDisplay();
        String totalTextToDisplay = textToDisplay1 + "\n" + textToDisplay2 + "\n" + textToDisplay3;
        drawArrow(textToDisplay2.length());
        GUI_Matrix_Helper.stringToMatrix(totalTextToDisplay);
    }
    
    private void drawArrow(int numberOfFrontChar)
    {
        /**
         *   []
         * [][][][][]
         * [][][][][]
         *   []
         **/
         
        int PixelOfset = (numberOfFrontChar * 6) +1;
        GUI_Matrix_Helper.turnPixel(true, PixelOfset + 2, 14);
        GUI_Matrix_Helper.turnPixel(true, PixelOfset + 1, 15);
        GUI_Matrix_Helper.turnPixel(true, PixelOfset + 2, 15);
        GUI_Matrix_Helper.turnPixel(true, PixelOfset + 3, 15);
        GUI_Matrix_Helper.turnPixel(true, PixelOfset + 4, 15);
        GUI_Matrix_Helper.turnPixel(true, PixelOfset + 5, 15);
        GUI_Matrix_Helper.turnPixel(true, PixelOfset + 1, 16);
        GUI_Matrix_Helper.turnPixel(true, PixelOfset + 2, 16);
        GUI_Matrix_Helper.turnPixel(true, PixelOfset + 3, 16);
        GUI_Matrix_Helper.turnPixel(true, PixelOfset + 4, 16);
        GUI_Matrix_Helper.turnPixel(true, PixelOfset + 5, 16);
        GUI_Matrix_Helper.turnPixel(true, PixelOfset + 2, 17);
    }
}
