
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
    mainMenu("Start menu "),
    statistics(),
    metingen(),
    back(),
    quit(),
    test("dit is een erg lange test functie om te kijken hoe goed de scroll functie werkt ");

    public final String name;

    menuChoices()
    {
        name = this.toString();
    }

    menuChoices(String givenName)
    {
        name = givenName;
    }


    //what to do if the function gets chosen
    public static boolean callFunction(menuChoices menuChoice, GUI_Menu menuToShow, GUI_Gebruik GUI_User) {
        switch (menuChoice) {
            default:
                return false;//exit that menu
            case back:
                return false;
            case quit:
                return false;
            case mainMenu:
                new GUI_Gebruik( Menus.mainMenu );
                menuToShow.showMenu();
                break; //make new menu
            case statistics:
                new GUI_Gebruik( Menus.statistics );
                menuToShow.showMenu();
                break;
            case metingen:
                new GUI_Gebruik( Menus.metingen );
                menuToShow.showMenu();
                break;
            case test:
                startTest(); //test function
                waitRedButtonShowMenu(menuToShow, GUI_User);
        }
        return true;
    }

    private static void waitRedButtonShowMenu(GUI_Menu guiInterface, GUI_Gebruik guiGebruiker)
    {
        guiGebruiker.waitForButton( 100, GUI_Gebruik.Button.red );
        guiInterface.showMenu();
    }

    public  String getName()
    {
        return name;
    }

    /**
     * a test function to test if te menu calls the function correctly
     * todo delete when done with task
     */
    private static void startTest() {
        System.out.println( "function test was correctly called" );
    }
}
