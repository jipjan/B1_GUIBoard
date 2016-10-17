
/**
 * all the choices for menu. and what to do if that choice is made
 * <p>
 * todo add all the functions to be able to call in our demo
 *
 * @author Jacco Steegman
 * @version 1.0
 */
public enum menuChoices {
    //all the functions a menu can get
    mainMenu, statistics, metingen, back, quit, test;

    //what to do if the function gets chosen
    public static boolean callFunction(menuChoices menuChoice) {
        switch (menuChoice) {
            default:
                return false;//exit that menu
            case back:
                return false;
            case quit:
                return false;
            case mainMenu:
                new GUI_Gebruik( menus.mainMenu );
                break; //make new menu
            case statistics:
                new GUI_Gebruik( menus.statistics );
                break;
            case metingen:
                new GUI_Gebruik( menus.metingen );
                break;
            case test:
                startTest(); //test function
        }
        return true;
    }

    /**
     * a test function to test if te menu calls the function correctly
     * todo delete when done with task
     */
    private static void startTest() {
        System.out.println( "function test was correctly called" );
    }
}
