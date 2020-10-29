package model.zenGame;

import model.zenGame.player.Computer;
import model.zenGame.player.Human;
import model.zenGame.player.Player;

import java.util.ArrayList;

import java.io.Serializable;

/**
 * GameplayZen is a class that manages a Zen L'initie gameplay
 * @author Godet Louis-Xavier
 */
public class GameplayZen implements Serializable {

	/**
	 * Default UID
	 */
	private static final long serialVersionUID = 8542222848564084858L;

	/**
	 * The name of the game which will be the name of the save file
	 */
	private String gameName;

	/**
	 * This attribute is a correspondance table where the letters are ordered in
	 * alphabetical order and the index in this tab is the position on the grid. It
	 * is use to translate the letter input by the human player to a number
	 */
	protected final char[] ALPHABET_ABSCISSA = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K' };

	/**
	 * the size of the grid : 11x11 (width x height)
	 */
	private final int GRID_HEIGHT_WIDTH = 11;

	/**
	 * the grid of the Zen L'initie game
	 */
	private SquareGrid[][] grid;

	/**
	 * A list of squares where there moves can be possible for a selected pawn
	 */
	private ArrayList<SquareGrid> listOfMoves;

	/**
	 * The list of black pawns placed in the grid (the maximum is 12)
	 */
	private ArrayList<Pawn> blackPawns;

	/**
	 * The list of white pawns placed in the grid (the maximum is 12)
	 */
	private ArrayList<Pawn> whitePawns;

	/**
	 * The Zen pawn which a player must make a chain with his pawns
	 */
	private Pawn zenPawn;

	/**
	 * The selected pawn in the grid by a player
	 */
	private SquareGrid selectedSquare;

	/**
	 * The black chain linked to the zen pawn
	 */
	private ArrayList<Pawn> blackChain;

	/**
	 * The white chain linked to the zen pawn
	 */
	private ArrayList<Pawn> whiteChain;

	/**
	 * the type of the opponent player : either a human, either a computer
	 */
	private OpponentType opponentType;

	/**
	 * a first player of a Zen l'initie game party (the first player is always a human)
	 */
	private Player firstPlayer;

	/**
	 * a second player of a Zen l'initie game party (human or computer)
	 */
	private Player secondPlayer;

	/**
	 * the current player who must play his turn
	 */
	private Player currentPlayer;

	/**
	 * Tells if the user consider the zen pawn as an ennemy or not.
	 */
	private boolean zenEnnemy;

	/**
	 * The previous position of zen pawn saved in order to ban the next player to replace it
	 */
	private SquareGrid previousZenPosition;

	/**
	 * GameplayZen initializes a game manager for a Zen l'initie party The
	 * parameters of the game are fill by the inputs of the user
	 * @param gameName : the name of the game (also the name of the save file)
	 * @param opponent : the type of the main user's opponent
	 * @param firstName : the name of the first player
	 * @param secondName : the name of the second player
	 * @param pawnFirstPlayer : the type of pawn chosen by the first player
	 * @param pawnSecondPlayer : the type of pawn chosen by the second player
	 */
	public GameplayZen(final String gameName, final OpponentType opponent, final String firstName, final String secondName, final TypeOfPawn pawnFirstPlayer, final TypeOfPawn pawnSecondPlayer) throws IllegalArgumentException {
		if (gameName != null && opponent != null && firstName != null && secondName != null && pawnFirstPlayer != null && pawnSecondPlayer != null) {
			this.gameName = gameName;
			this.opponentType = opponent;
			if (this.opponentType == OpponentType.COMPUTER) {
				this.firstPlayer = new Human(firstName, pawnFirstPlayer);
				this.secondPlayer = new Computer(secondName, pawnSecondPlayer);
			} else if (this.opponentType == OpponentType.HUMAN) {
				this.firstPlayer = new Human(firstName, pawnFirstPlayer);
				this.secondPlayer = new Human(secondName, pawnSecondPlayer);
			}
			//Ini. grid and the pawns
			this.iniGridAndPawns();

			// Choose randomly the first player who must play his turn
			int randomSelectFirst = 1 + (int) (Math.random() * 2);
			if (randomSelectFirst == 1) {
				this.currentPlayer = this.firstPlayer;
			} else if (randomSelectFirst == 2) {
				this.currentPlayer = this.secondPlayer;
			}
		} else {
			throw new IllegalArgumentException("GameplayZen : one of the parameters in the constructor is null");
		}
	}

	/**
	 * Initializes the grid of the Zen l'initie game and sets the position of the different pawns on it.
	 */
	private void iniGridAndPawns() {
		//1 - Ini. squares objects
		this.grid = new SquareGrid[this.GRID_HEIGHT_WIDTH][this.GRID_HEIGHT_WIDTH];
		for (int x = 0; x < this.GRID_HEIGHT_WIDTH; x++) {
			for (int y = 0; y < this.GRID_HEIGHT_WIDTH; y++) {
				this.grid[x][y] = new SquareGrid(x, y);
			}
		}

		//2 - Ini. pawns objects and sets the list of pawns
		// Blacks pawns
		this.blackPawns = new ArrayList<Pawn>();
		this.blackPawns.add(new Pawn(5, 0, TypeOfPawn.BLACK));
		this.blackPawns.add(new Pawn(10, 0, TypeOfPawn.BLACK));
		this.blackPawns.add(new Pawn(3, 2, TypeOfPawn.BLACK));
		this.blackPawns.add(new Pawn(7, 2, TypeOfPawn.BLACK));
		this.blackPawns.add(new Pawn(1, 4, TypeOfPawn.BLACK));
		this.blackPawns.add(new Pawn(9, 4, TypeOfPawn.BLACK));
		this.blackPawns.add(new Pawn(1, 6, TypeOfPawn.BLACK));
		this.blackPawns.add(new Pawn(9, 6, TypeOfPawn.BLACK));
		this.blackPawns.add(new Pawn(3, 8, TypeOfPawn.BLACK));
		this.blackPawns.add(new Pawn(7, 8, TypeOfPawn.BLACK));
		this.blackPawns.add(new Pawn(5, 10, TypeOfPawn.BLACK));
		this.blackPawns.add(new Pawn(0, 10, TypeOfPawn.BLACK));
		// Blacks pawns placed on the grid
		for (Pawn pawn : this.blackPawns) {
			this.grid[pawn.getXPosition()][pawn.getYPosition()].setPawnPresent(pawn);
		}

		// Zen pawn
		this.zenPawn = new Pawn(5, 5, TypeOfPawn.ZEN);
		this.grid[5][5].setPawnPresent(this.zenPawn);
		this.previousZenPosition = new SquareGrid(5, 5);

		// White pawns
		this.whitePawns = new ArrayList<Pawn>();
		this.whitePawns.add(new Pawn(0, 0, TypeOfPawn.WHITE));
		this.whitePawns.add(new Pawn(4, 1, TypeOfPawn.WHITE));
		this.whitePawns.add(new Pawn(6, 1, TypeOfPawn.WHITE));
		this.whitePawns.add(new Pawn(2, 3, TypeOfPawn.WHITE));
		this.whitePawns.add(new Pawn(8, 3, TypeOfPawn.WHITE));
		this.whitePawns.add(new Pawn(0, 5, TypeOfPawn.WHITE)); // middle
		this.whitePawns.add(new Pawn(10, 5, TypeOfPawn.WHITE)); // middle
		this.whitePawns.add(new Pawn(2, 7, TypeOfPawn.WHITE));
		this.whitePawns.add(new Pawn(8, 7, TypeOfPawn.WHITE));
		this.whitePawns.add(new Pawn(4, 9, TypeOfPawn.WHITE));
		this.whitePawns.add(new Pawn(6, 9, TypeOfPawn.WHITE));
		this.whitePawns.add(new Pawn(10, 10, TypeOfPawn.WHITE));
		// White pawns placed on the grid
		for (Pawn pawn : this.whitePawns) {
			this.grid[pawn.getXPosition()][pawn.getYPosition()].setPawnPresent(pawn);
		}

		//3 - Shares the pawns list to players according to their type of pawns
		if (this.firstPlayer.getPawnType() == TypeOfPawn.BLACK) {
			this.firstPlayer.setOwnedPawnsList(this.blackPawns);
			this.secondPlayer.setOwnedPawnsList(this.whitePawns);
		} else {
			this.firstPlayer.setOwnedPawnsList(this.whitePawns);
			this.secondPlayer.setOwnedPawnsList(this.blackPawns);
		}
	}

	/**
	 * Executes the computer turn
	 */
	public void computerPlay() {
		//By default, the player considering the zen pawn has an ennemy one
		this.zenEnnemy = true;
		if (this.zenPawn != null) {
			int r = (int) (Math.random() * 2); // 0 is ennemy, 1 a friend
			if (r == 1) {
				this.setZenEnnemy("A");
			}
		}
		//2 - select a pawn of his type
		int[] selectionCoordinates = this.currentPlayer.selectPawn(null); //empty parameters because there is no coordinates input
		this.selectSquare(selectionCoordinates);
		//3 - Calculs possibles moves according to the position of the selected pawn and the rules and make the move
		this.calculMove();
		this.makeMove(this.currentPlayer.selectMove(this.listOfMoves, null)); //empty parameters because there is no coordinates input
	}

	/**
	 * Sets the consideration of the zen pawn
	 * @param answer : the answer gived by the user or by the bot
	 */
	public void setZenEnnemy(final String answer) {
		if (answer.equals("A")) {
			this.zenEnnemy = false;
		} else if (answer.equals("E")) {
			this.zenEnnemy = true;
		}
		if (!this.zenEnnemy && this.zenPawn != null) {
			this.currentPlayer.getOwnedPawnsList().add(this.zenPawn);
		}
	}

	/**
	 * Sets the square which coordinates are gived throught parameters selected.
	 * @param selectCoordinates : the coordinates of the square concerned
	 * @return true if the selection has been successful, false otherwise
	 */
	public boolean selectSquare(final int[] selectCoordinates) throws IllegalArgumentException{
		boolean succeed = false;
		if (selectCoordinates != null && selectCoordinates[0] >= 0 && selectCoordinates[0] < this.GRID_HEIGHT_WIDTH && selectCoordinates[1] >= 0 && selectCoordinates[1] < this.GRID_HEIGHT_WIDTH){
			this.selectedSquare = this.grid[selectCoordinates[0]][selectCoordinates[1]];
			succeed = true;
		}else{
			System.err.println("Erreur de selection du pion");
		}
		return succeed;
	}

	/**
	 * Translates coordinates from letternumber to numbernumber
	 * @param coordinates : the coordinates writed like that : LETTERNUMBER
	 * @return : the coordinates writed like that : NUMBERNUMBER
	 */
	public int[] getDigitCoordinates(String coordinates) {
		int[] ret = new int[2];
		int i = 0; // parcours
		while (i < this.ALPHABET_ABSCISSA.length) {
			if (coordinates.charAt(0) == this.ALPHABET_ABSCISSA[i]) {
				int x = i;
				int y = Integer.parseInt(coordinates.substring(1, coordinates.length()));
				ret[0] = x;
				ret[1] = y;
			}
			i++;
		}
		return ret;
	}

	/**
	 * Calculs and checks horizontally the possible moves for the selected pawn
	 */
	private void horizontalCheckMoves() {
		this.listOfMoves = new ArrayList<SquareGrid>();

		//1- ini the maximal length of the move and the coordinates of the selected pawns
		int lengthMove = 0; // Normally at least one because of the pawn itself
		int xPosition = this.selectedSquare.getPawnPresent().getXPosition();
		int yPosition = this.selectedSquare.getPawnPresent().getYPosition();

		//2 - length's move calcul
		for (int x = 0; x < this.GRID_HEIGHT_WIDTH; x++) {
			if (this.grid[x][yPosition].isOccupied()) {
				lengthMove++;
			}
		}

		//3 - LEFT MOVE
		//3.1 - Verify if the move don't go out of the grid
		if (xPosition - lengthMove >= 0) {
			//3.2 - Verify if there is an opponent that can block a possible move
			int l = 0;
			boolean pawnOpponent = false;
			while (l < lengthMove && !pawnOpponent) {
				if (this.grid[xPosition - l][yPosition].isOccupied()) {
					Pawn pawnInPath = this.grid[xPosition - l][yPosition].getPawnPresent();
					if ((pawnInPath.getType() != this.currentPlayer.getPawnType() && pawnInPath != this.zenPawn) || (this.zenEnnemy && pawnInPath == this.zenPawn)) {
						pawnOpponent = true;
					}
				}
				l++;
			}
			//3.3 - Decide if the move to left is possible
			if (this.verifyRules(xPosition - lengthMove, yPosition) && !pawnOpponent) {
				this.listOfMoves.add(this.grid[xPosition - lengthMove][yPosition]);
			}
		}
		
		//4 - RIGHT MOVE
		//4.1 - Verify if the move don't go out of the grid
		if (xPosition + lengthMove < this.GRID_HEIGHT_WIDTH) {
			//4.2 - Verify if there is an opponent that can block a possible move
			int l = 0;
			boolean pawnOpponent = false;
			while (l < lengthMove && !pawnOpponent) {
				if (this.grid[xPosition + l][yPosition].isOccupied()) {
					Pawn pawnInPath = this.grid[xPosition + l][yPosition].getPawnPresent();
					if ((pawnInPath.getType() != this.currentPlayer.getPawnType() && pawnInPath != this.zenPawn) || (this.zenEnnemy && pawnInPath == this.zenPawn)) {
						pawnOpponent = true;
					}
				}
				l++;
			}
			//4.3 - Decide if the move to left is possible
			if (this.verifyRules(xPosition + lengthMove, yPosition) && !pawnOpponent) {
				this.listOfMoves.add(this.grid[xPosition + lengthMove][yPosition]);
			}
		}
	}

	/**
	 * Calculs and checks vertically the possible moves for the selected pawn
	 */
	private void verticalCheckMoves() {
		if (this.listOfMoves == null) {
			this.listOfMoves = new ArrayList<SquareGrid>();
		}

		//1- ini the maximal length of the move and the coordinates of the selected pawns
		int lengthMove = 0; // Normally at least one because of the pawn itself
		int xPosition = this.selectedSquare.getPawnPresent().getXPosition();
		int yPosition = this.selectedSquare.getPawnPresent().getYPosition();

		//2 - length's move calcul
		for (int y = 0; y < this.GRID_HEIGHT_WIDTH; y++) {
			if (this.grid[xPosition][y].isOccupied()) {
				lengthMove++;
			}
		}

		//3 - TOP MOVE
		//3.1 - Verify if the move don't go out of the grid
		if (yPosition - lengthMove >= 0) {
			//3.2 - Verify if there is an opponent that can block a possible move
			int l = 0;
			boolean pawnOpponent = false;
			while (l < lengthMove && !pawnOpponent) {
				if (this.grid[xPosition][yPosition - l].isOccupied()) {
					Pawn pawnInPath = this.grid[xPosition][yPosition - l].getPawnPresent();
					if ((pawnInPath.getType() != this.currentPlayer.getPawnType() && pawnInPath != this.zenPawn) || (this.zenEnnemy && pawnInPath == this.zenPawn)) {
						pawnOpponent = true;
					}
				}
				l++;
			}
			//3.3 - Decide if the move to left is possible
			if (this.verifyRules(xPosition, yPosition - lengthMove) && !pawnOpponent) {
				this.listOfMoves.add(this.grid[xPosition][yPosition - lengthMove]);
			}
		}

		//4 - BOTTOM MOVE
		//4.1 - Verify if the move don't go out of the grid
		if (yPosition + lengthMove < this.GRID_HEIGHT_WIDTH) {
			//4.2 - Verify if there is an opponent that can block a possible move
			int l = 0;
			boolean pawnOpponent = false;
			while (l < lengthMove && !pawnOpponent) {
				if (this.grid[xPosition][yPosition + l].isOccupied()) {
					Pawn pawnInPath = this.grid[xPosition][yPosition + l].getPawnPresent();
					if ((pawnInPath.getType() != this.currentPlayer.getPawnType() && pawnInPath != this.zenPawn) || (this.zenEnnemy && pawnInPath == this.zenPawn)) {
						pawnOpponent = true;
					}
				}
				l++;
			}
			//4.3 - Decide if the move to left is possible
			if (this.verifyRules(xPosition, yPosition + lengthMove) && !pawnOpponent) {
				this.listOfMoves.add(this.grid[xPosition][yPosition + lengthMove]);
			}
		}
	}

	/**
	 * Calculs and checks diagonally the possibles moves for the selected pawn
	 */
	private void diagonalCheckMoves() {
		//1 - Ini.
		int lengthMoveLR = 1; // LR = DIAGONAL LEFT TO RIGHT
		int lengthMoveRL = 1; // RL = DIAGONAL RIGHT TO LEFT
		int xPosition = this.selectedSquare.getPawnPresent().getXPosition();
		int yPosition = this.selectedSquare.getPawnPresent().getYPosition();

		//2 -DIAGONAL LEFT TO RIGHT
		//2.1 - top.left -> Backward in x; backward in y
		int tempX = xPosition - 1;
		int tempY = yPosition - 1;
		while (tempX >= 0 && tempY >= 0) {
			if (this.grid[tempX][tempY].isOccupied()) {
				lengthMoveLR++;
			}
			tempX--;
			tempY--;
		}
		//2.2 - bottom.right -> Forward in x; forward in y
		tempX = xPosition + 1;
		tempY = yPosition + 1;
		while (tempX < this.GRID_HEIGHT_WIDTH && tempY < this.GRID_HEIGHT_WIDTH) {
			if (this.grid[tempX][tempY].isOccupied()) {
				lengthMoveLR++;
			}
			tempX++;
			tempY++;
		}

		//3 TOP-LEFT
		if (yPosition - lengthMoveLR >= 0 && xPosition - lengthMoveLR >= 0) {
			//3.1 - Verify if the move don't go out of the gride
			int l = 0;
			boolean pawnOpponent = false;
			while (l < lengthMoveLR && !pawnOpponent) {
				if (this.grid[xPosition - l][yPosition - l].isOccupied()) {
					Pawn pawnInPath = this.grid[xPosition - l][yPosition - l].getPawnPresent();
					if ((pawnInPath.getType() != this.currentPlayer.getPawnType() && pawnInPath != this.zenPawn) || (this.zenEnnemy && pawnInPath == this.zenPawn)) {
						pawnOpponent = true;
					}
				}
				l++;
			}
			//3.2 - Decide if the move to left is possible
			if (this.verifyRules(xPosition - lengthMoveLR, yPosition - lengthMoveLR) && !pawnOpponent) {
				this.listOfMoves.add(this.grid[xPosition - lengthMoveLR][yPosition - lengthMoveLR]);
			}
		}

		//4 BOTTOM-RIGHT
		if (yPosition + lengthMoveLR < this.GRID_HEIGHT_WIDTH && xPosition + lengthMoveLR < this.GRID_HEIGHT_WIDTH) {
			//4.1 - Verify if the move don't go out of the gride
			int l = 0;
			boolean pawnOpponent = false;
			while (l < lengthMoveLR && !pawnOpponent) {
				if (this.grid[xPosition + l][yPosition + l].isOccupied()) {
					Pawn pawnInPath = this.grid[xPosition + l][yPosition + l].getPawnPresent();
					if ((pawnInPath.getType() != this.currentPlayer.getPawnType() && pawnInPath != this.zenPawn) || (this.zenEnnemy && pawnInPath == this.zenPawn)) {
						pawnOpponent = true;
					}
				}
				l++;
			}
			//4.2 - Decide if the move to b is possible
			if (this.verifyRules(xPosition + lengthMoveLR, yPosition + lengthMoveLR) && !pawnOpponent) {
				this.listOfMoves.add(this.grid[xPosition + lengthMoveLR][yPosition + lengthMoveLR]);
			}
		}

		//5. DIAGONAL RIGHT TO LEFT
		//5.1 top - right -> forward in x; backward in y
		tempX = xPosition + 1;
		tempY = yPosition - 1;
		while (tempX < this.GRID_HEIGHT_WIDTH && tempY >= 0) {
			if (this.grid[tempX][tempY].isOccupied()) {
				lengthMoveRL++;
			}
			tempX++;
			tempY--;
		}
		//5.2 bottom - left -> Forward in y; backward in x
		tempX = xPosition - 1;
		tempY = yPosition + 1;
		while (tempX >= 0 && tempY < this.GRID_HEIGHT_WIDTH) {
			if (this.grid[tempX][tempY].isOccupied()) {
				lengthMoveRL++;
			}
			tempX--;
			tempY++;
		}

		//6 TOP - RIGHT
		if (yPosition - lengthMoveRL >= 0 && xPosition + lengthMoveRL < this.GRID_HEIGHT_WIDTH) {
			//6.1 - Verify if the move don't go out of the gride
			int l = 0;
			boolean pawnOpponent = false;
			while (l < lengthMoveRL && !pawnOpponent) {
				if (this.grid[xPosition + l][yPosition - l].isOccupied()) {
					Pawn pawnInPath = this.grid[xPosition + l][yPosition - l].getPawnPresent();
					if ((pawnInPath.getType() != this.currentPlayer.getPawnType() && pawnInPath != this.zenPawn) || (this.zenEnnemy && pawnInPath == this.zenPawn)) {
						pawnOpponent = true;
					}
				}
				l++;
			}
			//6.2 - Decide if the move to top-right is possible
			if (this.verifyRules(xPosition + lengthMoveRL, yPosition - lengthMoveRL) && !pawnOpponent) {
				this.listOfMoves.add(this.grid[xPosition + lengthMoveRL][yPosition - lengthMoveRL]);
			}
		}

		//7 BOTTOM-LEFT
		if (yPosition + lengthMoveRL < this.GRID_HEIGHT_WIDTH && xPosition - lengthMoveRL >= 0) {
			//7.1 - Verify if the move don't go out of the gride
			int l = 0;
			boolean pawnOpponent = false;
			while (l < lengthMoveRL && !pawnOpponent) {
				if (this.grid[xPosition - l][yPosition + l].isOccupied()) {
					Pawn pawnInPath = this.grid[xPosition - l][yPosition + l].getPawnPresent();
					if ((pawnInPath.getType() != this.currentPlayer.getPawnType() && pawnInPath != this.zenPawn) || (this.zenEnnemy && pawnInPath == this.zenPawn)) {
						pawnOpponent = true;
					}
				}
				l++;
			}
			//7.2 - Decide if the move to bottom-left is possible
			if (this.verifyRules(xPosition - lengthMoveRL, yPosition + lengthMoveRL) && !pawnOpponent) {
				this.listOfMoves.add(this.grid[xPosition - lengthMoveRL][yPosition + lengthMoveRL]);
			}
		}
	}
	
	/**
	 * Verify most of the rules of zen l'initie game 
	 * @param xMove : the x coordinate of the square where the player want to move
	 * @param yMove : the y coordinate of the square where the player want to move
	 * @return true if the move respects the rules of zen l'initie, false if not
	 */
	private boolean verifyRules(int xMove, int yMove){
		boolean finalDecision = false;
		if (xMove >= 0 && yMove >=0 && xMove < this.GRID_HEIGHT_WIDTH && yMove < this.GRID_HEIGHT_WIDTH){
			boolean disableReplace = false;
			boolean typeEquals = false;
			boolean disableRemoveZen = false;
			boolean zenMovable = true;
			//1 - Verify if the pawn is zen and if it is verify if the move cannot be at a previous position
			if (this.selectedSquare.getPawnPresent() == this.zenPawn && this.previousZenPosition == this.grid[xMove][yMove]) {
				disableReplace = true;
			}
			//2 - Verify if the move doesn't target a pawn that is the same type of the current player
			if (!disableReplace && this.grid[xMove][yMove].getPawnPresent() != null && (this.grid[xMove][yMove].getPawnPresent().getType() == this.currentPlayer.getPawnType())) {
				typeEquals = true;
			}
			//3 - Verify this : if the player consider that zen pawn is a friend, then he can't remove it
			if (!disableReplace && !typeEquals && !this.zenEnnemy && this.grid[xMove][yMove].getPawnPresent() == this.zenPawn) {
				disableRemoveZen = true;
			}
			//4 - Verify this : if the player consider that zen pawn is a friend, then he can move it only if at the end the zen pawn is next to at least one pawn (black or white)
			if (!disableReplace && !typeEquals && !disableRemoveZen && this.zenEnnemy && this.selectedSquare.getPawnPresent() == this.zenPawn){
				Pawn pawnChecker = new Pawn(xMove, yMove, TypeOfPawn.ZEN); //Simulate the move of the zen
				if (this.beyondListCheck(pawnChecker, TypeOfPawn.BLACK).isEmpty() && this.beyondListCheck(pawnChecker, TypeOfPawn.WHITE).isEmpty()){
					zenMovable = false;
				}
			}
			//5 - Decide if the move is possible
			if (!disableReplace && !typeEquals && !disableRemoveZen && zenMovable) {
				finalDecision = true;
			}
		}
		return finalDecision;
	}

	/**
	 * Asks the user to input coordinates of square he want to move Checks if the
	 * inputs are in the list of possible moves Moves the pawn selected if the input
	 * is correct
	 * @return a list of square where the player can move his owned pawn
	 */
	public ArrayList<SquareGrid> calculMove() {
		this.horizontalCheckMoves();
		this.verticalCheckMoves();
		this.diagonalCheckMoves();
		return this.listOfMoves;
	}

	/**
	 * Play the turn by making the movement decided by the player
	 * @param targetPosition : the position targeted by the player
	 */
	public void makeMove(final SquareGrid targetPosition) {
		if (targetPosition != null) {
			if (targetPosition.isOccupied() && targetPosition.getPawnPresent().getType() != this.currentPlayer.getPawnType()) {
				this.removePawn(targetPosition); // remove opponent pawn before move the pawn selected
			}
			this.movePawn(targetPosition);
			//Once the turn has been play, re-initialize the attribute for the next turn
			this.listOfMoves = new ArrayList<SquareGrid>();
			//If the player has considered the zen pawn as a friend, remove the zen pawn of his list of pawns
			if (!zenEnnemy && this.zenPawn != null) {
				this.currentPlayer.removePawn(this.zenPawn);
			}
			this.calculZenChain(); //search from the zen pawn the chain maked with white and black pawns
		}else{
			throw new IllegalArgumentException("makeMove() : target position null");
		}
	}

	/**
	 * Moves the pawn selected to the square wanted
	 * @param squarePosition : the square which the user wants to move his pawn
	 */
	public void movePawn(final SquareGrid squarePosition) {
		if (this.selectedSquare.getPawnPresent() == this.zenPawn) {
			this.previousZenPosition = this.grid[this.zenPawn.getXPosition()][this.zenPawn.getYPosition()]; // save the current position of zen pawn
		}
		if (squarePosition != null){
			squarePosition.setPawnPresent(this.selectedSquare.getPawnPresent()); // move to the target position
			this.selectedSquare.removePawnPresent(); // remove the pawn at the previous square
		} else {
			throw new IllegalArgumentException("movePawn() error : the position  of the square is null");
		}
	}

	/**
	 * Removes the opponent pawn presents in the square targeted by a player,
	 * according to the rules of Zen l'initie
	 * @param squarePosition : the square targeted where the opponent pawn is presents
	 */
	public void removePawn(final SquareGrid squarePosition) {
		if (squarePosition != null) {
			Pawn presentPawn = squarePosition.getPawnPresent();
			if (presentPawn.getType() == TypeOfPawn.BLACK) {
				this.blackPawns.remove(presentPawn);
			} else if (presentPawn.getType() == TypeOfPawn.WHITE) {
				this.whitePawns.remove(presentPawn);
			} else if (presentPawn.getType() == TypeOfPawn.ZEN) {
				this.zenPawn = null;
			}
		}else{
			throw new IllegalArgumentException("removePawn() : squarePosition is null");
		}
	}

	/**
	 * Calculs the zen chain by look around the zen pawn what pawns there are
	 */
	public void calculZenChain() {
		//1 - ini
		this.blackChain = new ArrayList<Pawn>();
		this.whiteChain = new ArrayList<Pawn>();
		//2- Defines the starting point of the chain
		if (this.zenPawn != null) {
			this.blackChain.add(this.zenPawn);
			this.whiteChain.add(this.zenPawn);
		} else {
			this.blackChain.add(this.blackPawns.get(0));
			this.whiteChain.add(this.whitePawns.get(0));
		}
		//3 - calcul recursively
		if (this.zenPawn != null) {
			this.researchBeyond(this.zenPawn, TypeOfPawn.BLACK);
			this.researchBeyond(this.zenPawn, TypeOfPawn.WHITE);
		} else {
			this.researchBeyond(this.blackPawns.get(0), TypeOfPawn.BLACK);
			this.researchBeyond(this.whitePawns.get(0), TypeOfPawn.WHITE);
		}
		//4 - if the zen pawn is always here, the chain can't include the zen pawn
		int substract = 1;
		if (this.zenPawn == null) {
			substract = 0;
		}
		//5- update the number of pawns forming the chain with zen pawn
		if (this.firstPlayer.getPawnType() == TypeOfPawn.BLACK) {
			this.firstPlayer.updateNbPawnTouchZen(this.blackChain.size() - substract);
			this.secondPlayer.updateNbPawnTouchZen(this.whiteChain.size() - substract);
		} else {
			this.secondPlayer.updateNbPawnTouchZen(this.whiteChain.size() - substract);
			this.secondPlayer.updateNbPawnTouchZen(this.blackChain.size() - substract);
		}
	}

	/**
	 * Researches recursively the pawns forming the chain
	 * @param pawn : the pawn to look around what pawns are next it
	 * @param type : the type of the pawn to compare with pawns beyond
	 */
	private void researchBeyond(final Pawn pawn, final TypeOfPawn type) {
		ArrayList<Pawn> chainToUse = new ArrayList<Pawn>();
		if (type == TypeOfPawn.BLACK) {
			chainToUse = this.blackChain;
		} else {
			chainToUse = this.whiteChain;
		}
		ArrayList<Pawn> toCheck = this.beyondListCheck(pawn, type);
		for (Pawn p : toCheck) {
			if (!this.alreadyInChain(p)) {
				chainToUse.add(p);
				this.researchBeyond(p, type);
			}
		}
	}

	/**
	 * Researches beyond the pawn specified the pawns with the same type
	 * @param pawn : the pawn to look around what pawns are next it
	 * @param type : the type of the pawn to compare with pawns beyond
	 * @return the list of pawns beyond with the same type of the pawn specified
	 */
	private ArrayList<Pawn> beyondListCheck(final Pawn pawn, final TypeOfPawn type) {
		ArrayList<Pawn> list = new ArrayList<Pawn>();
		// top of the pawn
		if (pawn.getYPosition() - 1 >= 0) {
			Pawn pawnOne = this.grid[pawn.getXPosition()][pawn.getYPosition() - 1].getPawnPresent();
			if (pawnOne != null && pawnOne.getType() == type) {
				list.add(pawnOne);
			}
			if (pawn.getXPosition() + 1 < this.GRID_HEIGHT_WIDTH) {
				Pawn pawnTwo = this.grid[pawn.getXPosition() + 1][pawn.getYPosition() - 1].getPawnPresent();
				if (pawnTwo != null && pawnTwo.getType() == type) {
					list.add(pawnTwo);
				}
			}
		}

		// right
		if (pawn.getXPosition() + 1 < this.GRID_HEIGHT_WIDTH) {
			Pawn pawnThree = this.grid[pawn.getXPosition() + 1][pawn.getYPosition()].getPawnPresent();
			if (pawnThree != null && pawnThree.getType() == type) {
				list.add(pawnThree);
			}
			if (pawn.getYPosition() + 1 < this.GRID_HEIGHT_WIDTH) {
				Pawn pawnFour = this.grid[pawn.getXPosition() + 1][pawn.getYPosition() + 1].getPawnPresent();
				if (pawnFour != null && pawnFour.getType() == type) {
					list.add(pawnFour);
				}
			}
		}

		// bottom
		if (pawn.getYPosition() + 1 < this.GRID_HEIGHT_WIDTH) {
			Pawn pawnFive = this.grid[pawn.getXPosition()][pawn.getYPosition() + 1].getPawnPresent();
			if (pawnFive != null && pawnFive.getType() == type) {
				list.add(pawnFive);
			}
			if (pawn.getXPosition() - 1 >= 0) {
				Pawn pawnSix = this.grid[pawn.getXPosition() - 1][pawn.getYPosition() + 1].getPawnPresent();
				if (pawnSix != null && pawnSix.getType() == type) {
					list.add(pawnSix);
				}
			}
		}

		// left
		if (pawn.getXPosition() - 1 >= 0) {
			Pawn pawnSeven = this.grid[pawn.getXPosition() - 1][pawn.getYPosition()].getPawnPresent();
			if (pawnSeven != null && pawnSeven.getType() == type) {
				list.add(pawnSeven);
			}
			if (pawn.getYPosition() - 1 >= 0) {
				Pawn pawnEight = this.grid[pawn.getXPosition() - 1][pawn.getYPosition() - 1].getPawnPresent();
				if (pawnEight != null && pawnEight.getType() == type) {
					list.add(pawnEight);
				}
			}
		}
		return list;
	}

	/**
	 * Says if the pawn is already include in the calcul of zen chain
	 * @param pawn : the pawn to check if it is include in the calcul of zen chain
	 * @return true if the pawn to check is already in the calcul of zen chain, false if it is not
	 */
	private boolean alreadyInChain(final Pawn pawn) {
		boolean alreadyInChain = false;
		int i = 0;
		if (pawn.getType() == TypeOfPawn.BLACK) {
			while (i < this.blackChain.size() && !alreadyInChain) {
				if (pawn == this.blackChain.get(i)) {
					alreadyInChain = true;
				}
				i++;
			}
		} else if (pawn.getType() == TypeOfPawn.WHITE) {
			while (i < this.whiteChain.size() && !alreadyInChain) {
				if (pawn == this.whiteChain.get(i)) {
					alreadyInChain = true;
				}
				i++;
			}
		}
		return alreadyInChain;
	}

	/**
	 * Tells if all pawns owned by the current player touch the Zen pawn
	 * @return true if all pawns touch the zen (victory for the current player), false if it's not the case
	 */
	public GameState allPawnTouch() {
		GameState state = GameState.LOOP;
		if (this.blackChain != null && this.whiteChain != null) {
			if (this.firstPlayer.getNbPawn() == this.firstPlayer.getNbPawnTouchZen() && this.secondPlayer.getNbPawn() == this.secondPlayer.getNbPawnTouchZen()) {
				state = GameState.DRAW;
			} else if (this.firstPlayer.getNbPawn() == this.firstPlayer.getNbPawnTouchZen()) {
				state = GameState.VICTORY_PLAYER_ONE;
			} else if (this.secondPlayer.getNbPawn() == this.secondPlayer.getNbPawnTouchZen()) {
				state = GameState.VICTORY_PLAYER_TWO;
			}
		}
		return state;
	}

	/**
	 * Simply changes the player after his move.
	 */
	public void changeCurrentPlayer() {
		if (this.currentPlayer == this.firstPlayer) {
			this.currentPlayer = this.secondPlayer;
		} else if (this.currentPlayer == this.secondPlayer) {
			this.currentPlayer = this.firstPlayer;
		}
	}

	/**
	 * Gives the first player of the game 
	 * @return the first player of the game
	 */
	public Player getFirstPlayer() {
		return this.firstPlayer;
	}

	/**
	 * Gives the second player of the game 
	 * @return the second player of the game
	 */
	public Player getSecondPlayer() {
		return this.secondPlayer;
	}

	/**
	 * Gives the current player of the game
	 * @return the current player of the game
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	public void setCurrentPlayer(final Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Gives the grid of the game 
	 * @return the grid of the game
	 */
	public SquareGrid[][] getGrid() {
		return this.grid;
	}

	/**
	 * Gives the name of the game
	 * @return the name of the game
	 */
	public String getGameName() {
		return this.gameName;
	}

	/**
	 * Gives the type of the game's opponent
	 * @return the type of the game's opponent
	 */
	public OpponentType getOpponentType() {
		return this.opponentType;
	}

	/**
	 * Gives the zen pawn 
	 * @return the zen pawn
	 */
	public Pawn getZenPawn() {
		return this.zenPawn;
	}

	/**
	 * Gives the list of moves of the selected pawn
	 * @return the list of moves of the selected pawn
	 */
	public ArrayList<SquareGrid> getListOfMoves() {
		return this.listOfMoves;
	}
}