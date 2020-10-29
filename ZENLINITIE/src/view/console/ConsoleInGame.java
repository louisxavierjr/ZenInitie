package view.console;

import model.zenGame.GameState;
import model.zenGame.SquareGrid;
import model.zenGame.TypeOfPawn;
import model.zenGame.player.Player;

import java.util.ArrayList;

/**
 * ConsoleInGame draws on the console the game grid's, the information about the game and the result
 * @author Godet Louis-Xavier
 */
public class ConsoleInGame {
	/**
	 * The size of the grid (11*11)
	 */
    private final int GRID_HEIGHT_WIDTH = 11;

	/**
	 * This attribute is a correspondance table where the letters are ordered in
	 * alphabetical order and the index in this tab is the position on the grid. It
	 * is use to translate the letter input by the human player to a number
	 */
    protected final char[] ALPHABET_ABSCISSA = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K' };

	/**
	 * The game's grid to display
	 */
    private SquareGrid[][] gridToDisplay;

	/**
	 * Initializes a displayer of a zen l'initie game
	 * @param gridGame : the grid of the game
	 */
    public ConsoleInGame(SquareGrid[][] gridGame){
        if (gridGame != null){
            this.gridToDisplay = gridGame;
        }
    }

    /**
	 * Displays the Zen l'initie grid with the pawns on it
	 */
    public void displayGrid(){
        // Limit of the grid top
		for (int i = 0; i < this.GRID_HEIGHT_WIDTH + 1; i++) {
			System.out.print("______");
		}

		System.out.println("");
		System.out.print("     |");
		if (this.gridToDisplay != null) {
			// Letters abscissa
			for (int i = 0; i < this.ALPHABET_ABSCISSA.length; i++) {
				System.out.print("  " + this.ALPHABET_ABSCISSA[i] + "  |");
			}
			System.out.println("");
			for (int i = 0; i < this.GRID_HEIGHT_WIDTH + 1; i++) {
				System.out.print("-----|");
			}

			// number ordinate
			System.out.println("");
			for (int y = 0; y < this.GRID_HEIGHT_WIDTH; y++) {
				if (y <= 9) {
					System.out.print("  " + y + "  |");
				} else {
					System.out.print("  " + y + " |"); // one space less
				}
				for (int x = 0; x < this.GRID_HEIGHT_WIDTH; x++) {
					System.out.print("  " + this.displayPawn(this.gridToDisplay[x][y]) + "  |");
				}
				System.out.println("");
				for (int t = 0; t < this.GRID_HEIGHT_WIDTH + 1; t++) {
					System.out.print("------");
				}
				System.out.println("");
			}
		}
	}

	/**
	 * Displays a graphic representation of the pawn according to it type (if there is a pawn in the square)
	 * @param square : the square to draw
	 * @return a display of the pawn according to it type
	 */
	public String displayPawn(SquareGrid square) {
		String ret = " ";
		if (square.isOccupied()){
			if (square.getPawnPresent().getType() == TypeOfPawn.BLACK){
				ret = ("X");
			}else if (square.getPawnPresent().getType() == TypeOfPawn.WHITE){
				ret= ("0");
			}else if (square.getPawnPresent().getType() == TypeOfPawn.ZEN){
				ret= ("Z");
			}
		}
		return ret;
	}
	
	/**
	 * Displays the infomations about the game
	 * @param gameName : the name of the game
	 * @param firstPlayer : the first player
	 * @param secondPlayer : the second player
	 * @param currentPlayerName : the current player
	 */
    public void displayInformationGame(String gameName, Player firstPlayer, Player secondPlayer, String currentPlayerName){
		System.out.println("Nom de la partie :" + gameName);
		System.out.println("X : PION NOIR / 0 : PION BLANC / Z : PION ZEN");
		if (firstPlayer.getPawnType() == TypeOfPawn.BLACK) {
			System.out.println("Pion noir : " + firstPlayer.getPlayerName() + " / pions en connexion : " + firstPlayer.getNbPawnTouchZen() + " / pions totaux : " +  firstPlayer.getNbPawn());
			System.out.println("Pion blanc : " + secondPlayer.getPlayerName() + " / pions en connexion : " + secondPlayer.getNbPawnTouchZen() + " / pions totaux : " +  secondPlayer.getNbPawn());
		} else {
			System.out.println("Pion blanc : " + firstPlayer.getPlayerName() + " / pions en connexion : " + firstPlayer.getNbPawnTouchZen() + " / pions totaux : " +  firstPlayer.getNbPawn());
			System.out.println("Pion noir : " + secondPlayer.getPlayerName() + " / pions en connexion : " + secondPlayer.getNbPawnTouchZen() + " / pions totaux : " + secondPlayer.getNbPawn());
		}
        System.out.println("");
		System.out.println(currentPlayerName + " , c'est à toi de jouer");
		System.out.println("");
    }

	/**
	 * Displays the option in game
	 */
    public void displayAsks(){
        System.out.println("-- QUE FAIRE ? --");
		System.out.println("1 - Sélectionner un pion");
		System.out.println("2 - Sauvegarder la partie");
		System.out.println("3 - Règles du jeu");
		System.out.println("4 - Quitter la partie");
    }

	/**
	 * Displays the list of moves according to the selected square
	 * @param listOfMoves : the list of moves to display
	 */
    public void displayListOfMoves(ArrayList<SquareGrid> listOfMoves){
		System.out.println("Déplacements possibles : ");
		for (int i = 0; i < listOfMoves.size(); i++){
			int[] coordinates = listOfMoves.get(i).getCoordinatesSquare();
			System.out.print(this.ALPHABET_ABSCISSA[coordinates[0]] + "" + coordinates[1] +" ; ");
		}
		System.out.println("");
    }
	
	/**
	 * Displays the result of the end game
	 * @param state : the result of the end of the game
	 */
    public void displayWin(GameState state){
		if (state == GameState.DRAW ){
			String draw = "_______________________________ RESULTAT ________________________" + "\n";
			draw = draw + state;
			System.out.print(draw + " DOMMAGE !");
			System.out.println();
		}else if (state == GameState.VICTORY_PLAYER_ONE || state == GameState.VICTORY_PLAYER_TWO){
			String victory = "_______________________________ RESULTAT ________________________" + "\n";
			victory = victory + state;
			System.out.print(victory + " BIEN JOUE !");
			System.out.println();
		}
	}
}