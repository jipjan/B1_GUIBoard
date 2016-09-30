import java.util.*;

/**
 * A Class to display, create and keep track of a menu page
 * 
 * @author  Jacco Steegman
 * @version V1.2
 */
public class GUI_Menu
{
    private String[] menuItems;
    private int focusItem;
    private int[] itemMaxTextLength = {20,20,20};
    
    public GUI_Menu(String[] givenMenuItems)
    {
        menuItems = givenMenuItems;
        focusItem = 0;
        setstandardMaxTextLenght();
    }
    
    public GUI_Menu(String[] givenMenuItems, int itemThatIsInFocus)
    {
        menuItems = givenMenuItems;
        focusItem = itemThatIsInFocus;
        setstandardMaxTextLenght();
    }
    
    
    
    public GUI_Menu(String[] givenMenuItems, int itemThatIsInFocus, int maxTextLength1, int maxTextLength2, int maxTextLength3)
    {
        menuItems = givenMenuItems;
        focusItem = itemThatIsInFocus;
        
        itemMaxTextLength[0] = maxTextLength1;
        itemMaxTextLength[1] = maxTextLength2;
        itemMaxTextLength[2] = maxTextLength3;
    }
    
    //test creator
    public GUI_Menu()
    {
        String[] tempArrayString = {"test 1", "langere test 2", "zelfs nog langere test 3", "test 4", "test 5", "test 6", "test 7"};
        menuItems = tempArrayString;
        focusItem = 0;
        setstandardMaxTextLenght();
    }
    
    private void setstandardMaxTextLenght()
    {
        itemMaxTextLength[0] = 20;
        itemMaxTextLength[1] = 20;
        itemMaxTextLength[2] = 11;
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
            menuToDisplay(menuItems[focusItemMenuHight], "-", "-");
        } else if (itemsToDisplay == 2)
        {
            menuToDisplay(menuItems[focusItemMenuHight], menuItems[(focusItemMenuHight+1)], "-");
        } else
        {
            menuToDisplay(menuItems[focusItemMenuHight], menuItems[(focusItemMenuHight+1)], menuItems[(focusItemMenuHight+2)]);
        }
    }

    private void menuToDisplay(String textToDisplay1, String textToDisplay2, String textToDisplay3)
    {
        int frame = 0;
        GUI_Matrix_Helper.clrDisplay();
        drawFocusLine(textToDisplay2.length());
        GUI_Matrix_Helper.stringToMatrix(textToDisplay(frame, textToDisplay1, textToDisplay2, textToDisplay3));
    }
    
    private String textToDisplay(int frame,String text1, String text2, String text3)
    {
        char[][] textToDisplay = {text1.toCharArray(), text2.toCharArray(), text3.toCharArray()};
        
        int animationStep = frame% (itemMaxTextLength[focusItem]+1);
        String ReturnString = "";
        int textLength;
        
        for (int i = 0; i < 3; i++)
        {
            if ((i == focusItem) && (itemMaxTextLength[i] < textToDisplay[i].length))
            {
                for (int j = 0; j < itemMaxTextLength[i]; j++)
                {
                    ReturnString = ReturnString + textToDisplay[i][j+animationStep];
                }
            } else 
            {
                for (int j = 0; j < Math.min(textToDisplay[i].length, itemMaxTextLength[i]); j++)
                {
                    ReturnString = ReturnString + textToDisplay[i][j];
                }
            }
            ReturnString = ReturnString + '\n';
        }
        
        return ReturnString;
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
        return Math.min(menuItems[focusItem].length() * 6, itemMaxTextLength[focusItem] * 6);
    }
}
