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
     * @param givenMenuItems    an array with strings to be selected.
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
     * @param givenMenuItems        an array with strings to be selected.
     * @param itemThatIsInFocus    the numer for the string in the array that is first in focus.
     */
    public GUI_Menu(String[] givenMenuItems, int itemThatIsInFocus)
    {
        menuItems = givenMenuItems;
        focusItem = itemThatIsInFocus;
        setstandardMaxTextLenght();
        frame = 0;
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
        System.arraycopy( maxTextLength, 0, itemMaxTextLength, 0, Math.min( itemMaxTextLength.length, maxTextLength.length ) );
        frame = 0;
    }


    /**
     * sets the MaxTextLength to its default values.
     */
    private void setstandardMaxTextLenght()
    {
        itemMaxTextLength[0] = 20;
        itemMaxTextLength[1] = 20;
        itemMaxTextLength[2] = 20;
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
        setFocusItem(focusItem);
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
     * given frame an int that can go op as long as you want
     */
    public void newFrame()
    {
        if(menuItems[focusItem].length() >= itemMaxTextLength[focusItem%3]) {
            int givenFrame = frame + 1;
            frame = givenFrame <= Integer.MAX_VALUE ? givenFrame : 0;
            System.out.println("new frame set");
            showMenu();
        }
        //frame = givenFrame % 2147483646; //max value -1 of int in java can give an animation glitch if the int goes higher        
    }
    
    /**
     * shows the menu on the matrix screen.
     */
    public void showMenu()
    {
        int focusItemMenuFirstInPageHight = (focusItem/3) * 3; // which page to display
        int itemsToDisplay = menuItems.length - focusItemMenuFirstInPageHight; //look how many items there need to be displayd

        //fill empty points in the menu
        switch(itemsToDisplay) {
            case 0:
                menuToDisplay( "-", "-", "-" ); // this should never happen
                break;
            case 1:
                menuToDisplay( menuItems[focusItemMenuFirstInPageHight], "-", "-" );
                break;
            case 2:
                menuToDisplay( menuItems[focusItemMenuFirstInPageHight], menuItems[(focusItemMenuFirstInPageHight + 1)], "-" );
                break;
            default:
                menuToDisplay( menuItems[focusItemMenuFirstInPageHight], menuItems[(focusItemMenuFirstInPageHight + 1)], menuItems[(focusItemMenuFirstInPageHight + 2)] );
                break;
        }
    }

    /**
     * prepares the matrix display draws focusLine and sends text to display
     * 
     * textToDisplay1 : the first string to display at the top.
     * textToDisplay2 : the second string to display in the middle.
     * textToDisplay3 : the third string to display at the bottom.
     */
    private void menuToDisplay(String textToDisplay1, String textToDisplay2, String textToDisplay3)
    {
        GUI_Matrix_Helper.clrDisplay();
        drawFocusLine();
        GUI_Matrix_Helper.stringToMatrix(textToDisplay(textToDisplay1, textToDisplay2, textToDisplay3));
    }
    
    /**
     * returns the string to send to GUI_Matrix_Helper.stringToMatrix cleans it up and  
     */
    private String textToDisplay(String text1, String text2, String text3)
    {
        char[][] textToDisplay = {text1.toCharArray(), text2.toCharArray(), text3.toCharArray()}; //makes character array of the individual strings and puts them in a array[menu hight][each Char]
        int focusItemMenuHight = focusItem %3; //hight of the focus Item in the menu
        int animationStep = (frame% (textToDisplay[focusItemMenuHight].length - itemMaxTextLength[focusItemMenuHight]+1));
        String ReturnString = "";
        
        for (int i = 0; i < 3; i++) { //voor elke rij in het menu
            if ((i == focusItem) && (itemMaxTextLength[i] < textToDisplay[i].length)) { //if the focus Item is longer than the max length it may display animate it
                for (int j = 0; j < itemMaxTextLength[i]; j++)  //for every leter in the array (to display)
                    ReturnString = ReturnString + textToDisplay[i][j+animationStep]; //add it to the return string from the point animation step point
            } else {                                                                    //else dont animate the text
                for (int j = 0; j < Math.min(textToDisplay[i].length, itemMaxTextLength[i]); j++)
                    ReturnString = ReturnString + textToDisplay[i][j];
            }
            ReturnString = ReturnString + '\n';
        }
        return ReturnString;
    }
    
    private void drawFocusLine()
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
        return Math.min(menuItems[focusItem].length() * 6, itemMaxTextLength[focusItem%3] * 6);
    }
}
