package model.zenGame.rules;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * This class opens and displays the rules contained in a PDF file
 * 
 * @author Godet Louis-Xavier
 */
public class GameRules {

	/**
	 * The path where the PDF file containing the rules of Zen L'initie game are
	 */
	private final static String PATH_RULES = "../data/GAME_RULES.pdf";

	/**
	 * Opens the rules contained in a PDF file with an external reader of PDF file 
	 */
	public static void openRulesFile() {
		File pdfFile = new File(PATH_RULES);
    	try {
            if (Desktop.isDesktopSupported()) {
               	Desktop.getDesktop().open(pdfFile);
            } else {
            	System.out.println("FAILED TO OPEN THE RULES: Awt Desktop is not supported!");
			}
      	}catch (IOException i) {
        	i.printStackTrace();
      }
    }
}