
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
    test("dit is een erg lange test functie om te kijken hoe goed de scroll functie werkt "),
    setData("Set the data to get"),
    getDataYearBack("get data from one year back till now"),
    getDataOfYear("get data from a specific year"),
    year09(),
    year10(),
    year11(),
    year12(),
    year13(),
    year14(),
    year15(),
    year16();

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
    public static boolean callFunction(menuChoices menuChoice, GUI_Menu menuToShow, GUI_Gebruik GUI_User, WriteToGUI guiWriter) {
        switch (menuChoice) {
            case back:
                return false;
            case quit:
                return false;
            case mainMenu:
                new GUI_Gebruik( Menus.mainMenu, guiWriter );
                menuToShow.showMenu(); break; //make new menu
            case statistics:
                new GUI_Gebruik( Menus.statistics, guiWriter );
                menuToShow.showMenu();break;
            case metingen:
                new GUI_Gebruik( Menus.metingen, guiWriter );
                menuToShow.showMenu();
            case setData:
                new GUI_Gebruik( Menus.setData, guiWriter );break;
            case getDataOfYear:
                new GUI_Gebruik( Menus.getDataOfYear, guiWriter );break;
            case getDataYearBack:
                guiWriter.setNewYear( java.time.LocalDateTime.now().getYear() );break;
            case year09: guiWriter.setNewYear( 2009 );break;
            case year10: guiWriter.setNewYear( 2010 );break;
            case year11: guiWriter.setNewYear( 2011 );break;
            case year12: guiWriter.setNewYear( 2012 );break;
            case year13: guiWriter.setNewYear( 2013 );break;
            case year14: guiWriter.setNewYear( 2014 );break;
            case year15: guiWriter.setNewYear( 2015 );break;
            case year16: guiWriter.setNewYear( 2016 );break;
            case test:
                startTest(); //test function
                waitRedButtonShowMenu(menuToShow, GUI_User);
            default:
                return false;//exit that menu
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
