
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
    mainMenu( "Start menu " ),
    statistics("Statestieke " ),
    metingen(),
    back("terug " ),
    quit("stop " ),
    //datesetting
    setData( "wijzig data " ),
    getDataYearBack( "Haal data van 1 jaar op " ),
    getDataOfYear( "Haal data van x jaar op " ),
    year09( "2009" ), year10( "2010" ), year11( "2011" ), year12( "2012" ), year13( "2013" ), year14( "2014" ), year15( "2015" ), year16( "2016" ),
    //metingen
    printMostRecentOutsideTemp( "recente buiten temperatuur " ),
    printCurrentWindSpeed(),
    //statistics
    printGLH( "laagste, hoogste, gemiddlede " ),  //todo
    printmms( "median,modus,standraard afwijking " ),  //todo
    printLastWeekGLH(),
    printLastWeekMMS(),
    printLastMonthGLH(),
    printLastMonthMMS(),
    printHasHeatWave(),
    printMaxAmountOfSequentRain(),
    printLongestRainfall(),
    printLongestTemperatureRise();


    public final String name;

    menuChoices() {
        name = this.toString();
    }

    menuChoices(String givenName) {
        name = givenName;
    }


    //what to do if the function gets chosen
    public static boolean callFunction(menuChoices menuChoice, GUI_Menu menuToShow, GUI_Gebruik GUI_User, WriteToGUI guiWriter) {
        switch (menuChoice) {
            case back:
                return false;
            case quit:
                return false;
            //menus
            case mainMenu:
                new GUI_Gebruik( Menus.mainMenu, guiWriter );
                menuToShow.showMenu();
                break; //make new menu
            case statistics:
                new GUI_Gebruik( Menus.statistics, guiWriter );
                menuToShow.showMenu();
                break;
            case metingen:
                new GUI_Gebruik( Menus.metingen, guiWriter );
                menuToShow.showMenu();
                break;
            case printGLH:
                new GUI_Gebruik( Menus.printGLH, guiWriter );
                menuToShow.showMenu();
                break;
            case printmms:
                new GUI_Gebruik( Menus.printmms, guiWriter );
                //getting data
            case setData:
                new GUI_Gebruik( Menus.setData, guiWriter );
                break;
            case getDataOfYear:
                new GUI_Gebruik( Menus.getDataOfYear, guiWriter );
                break;
            case getDataYearBack:
                guiWriter.setNewYear( java.time.LocalDateTime.now().getYear() );
                break;
            case year09:
                guiWriter.setNewYear( 2009 );
                break;
            case year10:
                guiWriter.setNewYear( 2010 );
                break;
            case year11:
                guiWriter.setNewYear( 2011 );
                break;
            case year12:
                guiWriter.setNewYear( 2012 );
                break;
            case year13:
                guiWriter.setNewYear( 2013 );
                break;
            case year14:
                guiWriter.setNewYear( 2014 );
                break;
            case year15:
                guiWriter.setNewYear( 2015 );
                break;
            case year16:
                guiWriter.setNewYear( 2016 );
                break;
            //metingen
            case printMostRecentOutsideTemp:
                guiWriter.printMostRecentOutsideTemp();
                waitRedButtonShowMenu( menuToShow, GUI_User );
                break;
            case printCurrentWindSpeed:
                guiWriter.printCurrentWindSpeed();
                waitRedButtonShowMenu( menuToShow, GUI_User );
                break;
            //statistics
            case printLastWeekGLH:
                guiWriter.printLastWeekGLH();
                waitRedButtonShowMenu( menuToShow, GUI_User );
                break;
            case printLastMonthGLH:
                guiWriter.printLastMonthGLH();
                waitRedButtonShowMenu( menuToShow, GUI_User );
                break;
            case printLastWeekMMS:
                guiWriter.printLastWeekMMS();
                waitRedButtonShowMenu( menuToShow, GUI_User );
                break;
            case printLastMonthMMS:
                guiWriter.printLastMonthMMS();
                waitRedButtonShowMenu( menuToShow, GUI_User );
                break;
            case printHasHeatWave:
                guiWriter.printHasHeatWave();
                waitRedButtonShowMenu( menuToShow, GUI_User );
                break;
            case printMaxAmountOfSequentRain:
                guiWriter.printMaxAmountOfSequentRain();
                waitRedButtonShowMenu( menuToShow, GUI_User );
                break;
            case printLongestRainfall:
                guiWriter.printLongestRainfall();
                break;
            case printLongestTemperatureRise:
                guiWriter.printLongestTemperatureRise();
                waitRedButtonShowMenu( menuToShow, GUI_User );
                break;
            default:
                return false;//exit that menu
        }
        return true;
    }

    private static void waitRedButtonShowMenu(GUI_Menu guiInterface, GUI_Gebruik guiGebruiker) {
        guiGebruiker.waitForButton( 100, GUI_Gebruik.Button.red );
        guiInterface.showMenu();
    }

    public String getName() {
        return name;
    }
}

