/**
 * class that makes each menu tick
 *
 * @author Jacco Steegman
 * @version 1.0
 */
public class GUI_Gebruik {
    //every button on the gui_board
    public enum Buton {
        left, middle, red, none
    }

    //the last state each button was in (they toggle)
    private short LastLeftState;
    private short LastMiddleState;
    private short LastRedState;

    //the GUI part of the menu
    private GUI_Menu menuViewer;


    /**
     * function that starts the menu
     *
     * @param menuIdentity which menu it has to be
     */
    public GUI_Gebruik(menus menuIdentity)
    {
        IO.init();
        lastButonPrest();
        menuViewer = new GUI_Menu( menuIdentity.getMenuOptionsInString() );
        runMenus( menuIdentity);
    }

    /**
     * makes the menu run
     *
     * @param menuIdentity the identity the menu has to take form in
     */

    private void runMenus(menus menuIdentity) {
        menuViewer.showMenu(); //shows the menu on guiboard

        //declaration of beginning variables
        int menuItemIndex = 0; //menu item index
        int frame = 0;
        boolean runMenu = true;
        //runMenu will be falls to if this menu must be stopped
        while (runMenu) {
            //determine which buton is pressed and what to do with that information
            Buton pressedButon = lastButonPrest();
            switch (pressedButon) {
                case red: //if red button is pressed go back or call new function/menu
                    runMenu = menuChoices.callFunction( menuIdentity.getMenuChoice( menuItemIndex ) ); //returns falls if any of the functions want this menu to stop
                    break;
                case middle:
                    menuItemIndex++;
                    menuViewer.setFocusItem( menuItemIndex );//set next item in list
                    menuViewer.showMenu();                 //show the new menu state
                    break;
                case left:
                    menuItemIndex = -1;
                    if (menuItemIndex < 0) //catch exception if the user presses the 'up'(left) button to many times
                        menuItemIndex = +menuIdentity.functions.size();
                    menuViewer.setFocusItem( menuItemIndex );
                    menuViewer.showMenu();
                    break;
                case none:
                    try {                              //if for some reason the thread wakes up early
                        java.lang.Thread.sleep( 200 ); //make the thread wait 200 ms
                    } catch (InterruptedException e) {
                    }  //what to do if the thread wakes up early (do nothing special)
                    frame++;                     //add a frame (if the menu needs to animate something)
                    menuViewer.setFrame( frame );//set the frame count
                    menuViewer.showMenu();
                    break;
            }
        }
    }

    /**
     * a function to return the last button that is pressed on the gui board.
     * (has priorities if more than one was pressed)
     *
     * @return the buton that was lassed pressed
     */
    private Buton lastButonPrest()
    {
        short redState    = IO.readShort(0x80);
        short leftState   = IO.readShort(0x90);
        short middleState = IO.readShort(0x100);

        Buton pressedButon;
        
        if (LastRedState != redState)
            pressedButon = Buton.red;
        else if (LastLeftState != leftState)
            pressedButon = Buton.left;
        else if (LastMiddleState != middleState)
            pressedButon = Buton.middle;
        else 
            pressedButon = Buton.none;

        LastRedState    = redState;
        LastLeftState   = leftState;
        LastMiddleState = middleState;
        
        return pressedButon;
    }
}
