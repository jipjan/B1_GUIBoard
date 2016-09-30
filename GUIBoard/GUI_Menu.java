
/**
 * Write a description of class GUI_Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI_Menu
{
    private String[] menuItems;
    private int focusItem;
    
    public GUI_Menu(String[] givenMenuItems)
    {
        menuItems = givenMenuItems;
        focusItem = 0;
    }
    
    public GUI_Menu(String[] givenMenuItems, int itemThatIsInFocus)
    {
        menuItems = givenMenuItems;
        focusItem = itemThatIsInFocus;
    }
    
    //test creator
    public GUI_Menu()
    {
        String[] tempArrayString = {"test 1", "langere test 2", "zelfs nog langere test 3", "test 4", "test 5", "test 6", "test 7"};
        menuItems = tempArrayString;
        focusItem = 0;
    }
    
    public void setMenuItems(String[] givenMenuItems)
    {
        menuItems = givenMenuItems;
    }    
    
    public void setFocsItem(int givenFocusItem)
    {
        focusItem = givenFocusItem% menuItems.length;
    }
    
    public void showMenu()
    {
        int focusItemMenuHight = (focusItem/3) * 3; // iets met focus item focusItem;
        int itemsToDisplay = menuItems.length - focusItemMenuHight;
        if (itemsToDisplay <= 1)
        {
            menuToDisplay(menuItems[focusItemMenuHight]);
        } else if (itemsToDisplay == 2)
        {
            menuToDisplay(menuItems[focusItemMenuHight], menuItems[(focusItemMenuHight+1)]);
        } else
        {
            menuToDisplay(menuItems[focusItemMenuHight], menuItems[(focusItemMenuHight+1)], menuItems[(focusItemMenuHight+2)]);
        }
    }
    
    private void menuToDisplay(String textToDisplay)
    {
        menuToDisplay(textToDisplay, "_");
    }
    
    private void menuToDisplay(String textToDisplay1, String textToDisplay2)
    {
        menuToDisplay(textToDisplay1, textToDisplay2, "_");
    }

    private void menuToDisplay(String textToDisplay1, String textToDisplay2, String textToDisplay3)
    {
        GUI_Matrix_Helper.clrDisplay();
        String totalTextToDisplay = textToDisplay1 + "\n" + textToDisplay2 + "\n" + textToDisplay3;
        drawFocusLine(textToDisplay2.length());
        GUI_Matrix_Helper.stringToMatrix(totalTextToDisplay);
    }
    
    private void drawFocusLine(int numberOfFrontChar)
    {
        int beginLineOfset = 2;
        for (int x = beginLineOfset ; x < calculateItemLength()+ beginLineOfset; x++)
            GUI_Matrix_Helper.turnPixel(true, x, calculateYItemOfset());
    }
    
    private int calculateYItemOfset()
    {
        return focusItem%3 * 10 + 10;
    }
    
    private int calculateItemLength()
    {
        return menuItems[focusItem].length() * 6;
    }
}
