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
    public GUI_Gebruik(Menus menuIdentity)
    {
        IO.init();
        lastButonPrest();
        menuViewer = new GUI_Menu( menuIdentity.getMenuOptionsInString() );
        menuViewer.showMenu();
        runMenus( menuIdentity);
    }

    /**
     * makes the menu run
     *
     * @param menuIdentity the identity the menu has to take form in
     */

    private void runMenus(Menus menuIdentity) {

        //declaration of beginning variables
        int menuItemIndex = 0; //menu item index
        boolean runMenu = true;
        //runMenu will be falls to if this menu must be stopped
        while (runMenu) {
            System.out.println("restart botton test");
            Buton pressedButon = lastButonPrest(); //determine which buton is pressed and what to do with that information
            switch (pressedButon) {
                case red: //if red button is pressed go back or call new function/menu
                    runMenu = menuChoices.callFunction( menuIdentity.getMenuChoice( menuItemIndex ), menuViewer); //returns falls if any of the functions want this menu to stop
                    lastButonPrest();
                    break;
                case middle:
                    menuItemIndex++;
                    menuViewer.setFocusItem( menuItemIndex );//set next item in list//show the new menu state
                    lastButonPrest();
                    menuViewer.showMenu();
                    break;
                case left:
                    menuItemIndex = menuItemIndex - 1 ;
                    if (menuItemIndex < 0) //catch exception if the user presses the 'up'(left) button to many times
                        menuItemIndex += menuIdentity.functions.size();
                    menuViewer.setFocusItem( menuItemIndex );
                    lastButonPrest();
                    menuViewer.showMenu();
                    break;
                case none:
                    try {                              //if for some reason the thread wakes up early
                        java.lang.Thread.sleep( 200 ); //make the thread wait 200 ms
                    } catch (InterruptedException e) {
                        System.out.println("Thread did not sleep long enough end endid " + e+ "early");
                    }                                  //what to do if the thread wakes up early (do nothing special)
                    menuViewer.newFrame();//set a new frame
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
