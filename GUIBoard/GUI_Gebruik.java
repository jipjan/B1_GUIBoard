/**
 * class that makes each menu tick
 *
 * @author Jacco Steegman
 * @version 1.0
 */
public class GUI_Gebruik {
    //every button on the gui_board
    public enum Button {
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
        lastButtonPressed();
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
            Button pressedButton = lastButtonPressed(); //determine which buton is pressed and what to do with that information
            switch (pressedButton) {
                case red: //if red button is pressed go back or call new function/menu
                    runMenu = menuChoices.callFunction( menuIdentity.getMenuChoice( menuItemIndex ), menuViewer, this); //returns falls if any of the functions want this menu to stop
                    lastButtonPressed();
                    break;
                case middle:
                    menuItemIndex++;
                    menuViewer.setFocusItem( menuItemIndex );//set next item in list//show the new menu state
                    lastButtonPressed();
                    menuViewer.showMenu();
                    break;
                case left:
                    menuItemIndex = menuItemIndex - 1 ;
                    if (menuItemIndex < 0) //catch exception if the user presses the 'up'(left) button to many times
                        menuItemIndex += menuIdentity.functions.size();
                    menuViewer.setFocusItem( menuItemIndex );
                    lastButtonPressed();
                    menuViewer.showMenu();
                    break;
                case none:
                    waitThread( 300 );
                    menuViewer.newFrame();//set a new frame
                    break;
            }
        }
    }

    /**
     * waits the tread for some time
     * @param msWaitTime time in ms to make the thread wait.
     */
    private void waitThread(int msWaitTime)
    {
        try {                              //if for some reason the thread wakes up early
            java.lang.Thread.sleep( msWaitTime ); //make the thread wait 200 ms
        } catch (InterruptedException e) {
        }                                  //what to do if the thread wakes up early (do nothing special)
    }

    /**
     * wait for a button to be pressed
     * @param msTimeToWait      the amount of ms to wait to see if the button is pressed
     * @param buttonToWaitFor    witch button to wait for
     */
    public void waitForButton(int msTimeToWait, Button buttonToWaitFor)
    {
        lastButtonPressed();
        while(true)
        {
            if (lastButtonPressed() == buttonToWaitFor)
                break;
            waitThread( msTimeToWait );
        }
    }

    /**
     * a function to return the last button that is pressed on the gui board.
     * (has priorities if more than one was pressed)
     *
     * @return the button that was last pressed
     */
    private Button lastButtonPressed()
    {
        short redState    = IO.readShort(0x80);
        short leftState   = IO.readShort(0x90);
        short middleState = IO.readShort(0x100);

        Button pressedButton;
        
        if (LastRedState != redState)
            pressedButton = Button.red;
        else if (LastLeftState != leftState)
            pressedButton = Button.left;
        else if (LastMiddleState != middleState)
            pressedButton = Button.middle;
        else 
            pressedButton = Button.none;

        LastRedState    = redState;
        LastLeftState   = leftState;
        LastMiddleState = middleState;
        
        return pressedButton;
    }
}
