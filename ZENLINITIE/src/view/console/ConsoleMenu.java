package view.console;

import java.util.ArrayList;

/**
 * ConsoleMenu is the class that draws the menu of the game
 * @author Godet Louis-Xavier
 */
public class ConsoleMenu {

    /**
     * Initializes a logo for the menu
     */
    public ConsoleMenu() {
        String nameGame = "____________________________________ LOUIS-XAVIER GODET PRESENTE ______________________________________" + "\n";
        nameGame = nameGame + "\n";
        nameGame = nameGame + "\n";
        nameGame = nameGame + "  -------------- --------------  ------          ---                                                   |" + "\n";
        nameGame = nameGame + "             --- ---             ---  ---        ---                                                   |" + "\n";
        nameGame = nameGame + "           ---   ---             ---   ---       ---                                                   |" + "\n";
        nameGame = nameGame + "        ---      ---             ---    ---      ---                                                   |" + "\n";
        nameGame = nameGame + "      ---        --------------  ---     ---     ---                                                   |" + "\n";
        nameGame = nameGame + "    ---          ---             ---      ---    ---                                                   |" + "\n";
        nameGame = nameGame + "  ---            ---             ---       ---   ---                                                   |" + "\n";
        nameGame = nameGame + " ---             ---             ---        ---  ---                                                   |" + "\n";
        nameGame = nameGame + " ---             ---             ---         --- ---                                                   |" + "\n";
        nameGame = nameGame + " --------------  --------------  ---          ------                                                   |" + "\n";
        nameGame = nameGame + "\n";
        nameGame = nameGame + " --------------  ------          ---  --------------  --------------  --------------  ---------------  |" + "\n";
        nameGame = nameGame + "      ---        ---  ---        ---       ---             ---             ---        ---              |" + "\n";      
        nameGame = nameGame + "      ---        ---   ---       ---       ---             ---             ---        ---              |" + "\n";
        nameGame = nameGame + "      ---        ---    ---      ---       ---             ---             ---        ---              |" + "\n";
        nameGame = nameGame + "      ---        ---     ---     ---       ---             ---             ---        ----------       |" + "\n";
        nameGame = nameGame + "      ---        ---      ---    ---       ---             ---             ---        ---              |" + "\n";
        nameGame = nameGame + "      ---        ---       ---   ---       ---             ---             ---        ---              |" + "\n";
        nameGame = nameGame + "      ---        ---        ---  ---       ---             ---             ---        ---              |" + "\n";
        nameGame = nameGame + "      ---        ---         --- ---       ---             ---             ---        ---              |" + "\n";
        nameGame = nameGame + "---------------  ---          ------  --------------       ---        --------------  --------------   |" + "\n";
        nameGame = nameGame + "\n";
        nameGame = nameGame + "\n";
        nameGame = nameGame + "_____________________________________________ BIENVENUE _______________________________________________" + "\n";
        System.out.print(nameGame);
    }

    /**
     * Display the menu of the game
     */
    public void displayMenu(){
        System.out.println("");
        System.out.println("    __ MENU PRINCIPAL __");
        System.out.println("1 : Nouvelle partie");
        System.out.println("2 : Charger partie");
        System.out.println("3 : Règles du jeu");
        System.out.println("4 : Quitter le jeu");
    }

    /**
     * Display the title of the page for new game
     */
    public void displayNewGame(){
        System.out.println("");
        System.out.println("    __ MENU PRINCIPAL / NOUVELLE PARTIE __");
        System.out.println("");
    }

    /**
     * Display the title of the page for loading game
     */
    public void displayLoad(){
        System.out.println("");
        System.out.println("    __ MENU PRINCIPAL / CHARGER UN PARTIE __");
        System.out.println("");
    }

    /**
     * Display the list of files in the repertory save
     * @param saveFiles : the list of files
     */
    public void displaySaveFiles(ArrayList<String> saveFiles) {
        if (saveFiles != null){
            for(int i = 0; i < saveFiles.size(); i++){
                System.out.println(i + " - " + saveFiles.get(i));
            }
        }
    }
}