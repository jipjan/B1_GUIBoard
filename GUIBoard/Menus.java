import java.util.ArrayList;
import java.util.Collections;

/**
 * a enumerator for every menu and its contents.
 *
 * @author Jacco Steegman
 * @version 1.0
 */
public enum Menus
{
    //all the menus that exist and the contents of them
    mainMenu(       menuChoices.statistics,
                    menuChoices.metingen,
                    menuChoices.setData,
                    menuChoices.quit),
    statistics(     menuChoices.printGLH,
                    menuChoices.printmms,
                    menuChoices.printLongestTemperatureRise,
                    menuChoices.printHasHeatWave,
                    menuChoices.printMaxAmountOfSequentRain,
                    menuChoices.printLongestRainfall,
                    menuChoices.back),
    metingen(       menuChoices.printMostRecentOutsideTemp,
                    menuChoices.printCurrentWindSpeed,
                    menuChoices.back),
    setData(        menuChoices.getDataYearBack,
                    menuChoices.getDataOfYear,
                    menuChoices.back),
    getDataOfYear(  menuChoices.year09,
                    menuChoices.year10,
                    menuChoices.year11,
                    menuChoices.year12,
                    menuChoices.year13,
                    menuChoices.year14,
                    menuChoices.year15,
                    menuChoices.year16,
                    menuChoices.back),
    printGLH(       menuChoices.printLastWeekGLH,
                    menuChoices.printLastMonthGLH,
                    menuChoices.back),
    printmms(       menuChoices.printLastWeekMMS,
                    menuChoices.printLastMonthMMS,
                    menuChoices.back);

    //in what the contents of the menus is written
    public final ArrayList<menuChoices> functions = new ArrayList<>();

    //creator for each menu
    Menus(menuChoices... options)
    {
        Collections.addAll( this.functions, options );
    }

    /**
     * get the menuchoice with a given index
     * @param index     the index of the menuChoice to get (accounted for higher than list indexes)
     * @return the menuChoce corresponding with the index
     */
    public menuChoices getMenuChoice(int index)
    {
        return functions.get( index % functions.size() );
    }

    /**
     * gets all the menu options in string form
     *
     * @return the menu options as a string in a array
     */
    public String[] getMenuOptionsInString() {
        String[] arrayStringList = new String[functions.size()];

        for (int i = 0; i < functions.size(); i++)
            arrayStringList[i] = functions.get(i).getName();
        return arrayStringList;
    }
}
