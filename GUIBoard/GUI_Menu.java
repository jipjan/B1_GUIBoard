import java.util.*;

/**
 * A Class to display, create and keep track of a menu page.
 * 
 * @author  Jacco Steegman
 * @version V1.3
 */
public class GUI_Menu
{
    private String[] menuItems;
    private int focusItem;
    private int[] itemMaxTextLength = {20,20,20};
    private int frame;
    
    /**
     * generates class menu with given string menu options.
     * 
     * givenMenuItems: an array with strings to be selected.
     */
    public GUI_Menu(String[] givenMenuItems)
    {
        menuItems = givenMenuItems;
        focusItem = 0;
        setstandardMaxTextLenght();
        frame = 0;
    }

    /**
     * generates class menu with given string menu options.
     * 
     * givenMenuItems:      an array with strings to be selected.
     * itemTahatIsInFocus : the numer for the string in the array that is first in focus.
     */
    public GUI_Menu(String[] givenMenuItems, int itemThatIsInFocus)
    {
        menuItems = givenMenuItems;
        focusItem = itemThatIsInFocus;
        setstandardMaxTextLenght();
        frame =0;
    }
    
    /**
     * generates class menu with given string menu options.
     * 
     * givenMenuItems:      an array with strings to be selected.
     * itemTahatIsInFocus : the numer for the string in the array that is first in focus.
     * maxTextLength:       the maximum length of the text 0 for first line 2 for last (will not do anyting with the items given after the first 3).
     */
    public GUI_Menu(String[] givenMenuItems, int itemThatIsInFocus, int... maxTextLength)
    {
        menuItems = givenMenuItems;
        focusItem = itemThatIsInFocus;
        for (int i= 0; i < Math.min(itemMaxTextLength.length, maxTextLength.length ); i++)                                          // moet mischien i =< math.... kan bug veroorzaken
            itemMaxTextLength[i] = maxTextLength[i];
        frame = 0;
    }
    
    /**
     * generates test class.
     */
    public GUI_Menu()
    {
        String[] tempArrayString = {"test 1", "langere test 2", "zelfs nog langere test 3", "test 4", "test 5", "test 6", "test 7"};
        menuItems = tempArrayString;
        focusItem = 0;
        setstandardMaxTextLenght();
        frame = 0;
    }
    
    /**
     * sets the MaxTextLength to its deafalt values.
     */
    private void setstandardMaxTextLenght()
    {
        itemMaxTextLength[0] = 20;
        itemMaxTextLength[1] = 20;
        itemMaxTextLength[2] = 11;
    }
    
    
    
    /**
     * setter for array menuItems.
     *      makes setFocusItem smaller if it is to big for the new menu.
     * 
     * givenMenuItems: enter all the strings you want to have in the menu.
     */
    public void setMenuItems(String... givenMenuItems)
    {
        menuItems = givenMenuItems;
        setFocusItem(FocusItem);
    }    
    
    /**
     * setter for the focus item of the menu.
     * 
     * givenFocusItem: Item of the menu (starting at 0) that must be in focus. (does take into account if the numer is higher than te menu )
     */
    public void setFocusItem(int givenFocusItem)
    {
        focusItem = givenFocusItem% menuItems.length;
    }
    
    /**
     * sets the animation frame.
     *      This needs to go up evey time you want to make the next step in the animation.
     *      
     * given frame an int that can go op as long as 
     */
    public void setFrame(int givenFrame)
    {
        frame = givenFrame % 2147483646; //max value -1 of int in java can give an animation glitch if the int goes higher
        
    }
    
    /**
     * shows the menu on the matrix screen.
     */
    public void showMenu()
    {
        int focusItemMenuHight = (focusItem/3) * 3; // iets met focus item focusItem;
        int itemsToDisplay = menuItems.length - focusItemMenuHight;
        
        if (itemsToDisplay <= 1) {
            menuToDisplay(menuItems[focusItemMenuHight], "-", "-");
        } else if (itemsToDisplay == 2) {
            menuToDisplay(menuItems[focusItemMenuHight], menuItems[(focusItemMenuHight+1)], "-");
        } else {
            menuToDisplay(menuItems[focusItemMenuHight], menuItems[(focusItemMenuHight+1)], menuItems[(focusItemMenuHight+2)]);
        }
    }

    /**
     * prepares the matrix display pushes and (posably adds the sprites in the future) adds the sprites the text to it.
     * 
     * textToDisplay1 : the first string to display at the top.
     * textToDisplay2 : the second string to display in the middle.
     * textToDisplay3 : the third string to display at the bottom.
     */
    private void menuToDisplay(String textToDisplay1, String textToDisplay2, String textToDisplay3)
    {
        int frame = 0;
        GUI_Matrix_Helper.clrDisplay();
        drawFocusLine(textToDisplay2.length());
        GUI_Matrix_Helper.stringToMatrix(textToDisplay(textToDisplay1, textToDisplay2, textToDisplay3));
    }
    
    /**
     * returns the string to send to GUI_Matrix_Helper.stringToMatrix cleans it up and  
     */
    private String textToDisplay(String text1, String text2, String text3)
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
            } else {
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
