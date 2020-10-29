package model.zenGame.player;

import model.zenGame.Pawn;
import model.zenGame.SquareGrid;
import model.zenGame.TypeOfPawn;

import java.io.Serializable;

import java.util.ArrayList;

/**
 * Player is an abstract class which defines all of states and behavior of a player in a Zen l'initie game
 * @author Godet Louis-Xavier
 */
public abstract class Player implements Serializable{

	/**
	 * Default UID
	 */
	private static final long serialVersionUID = -1159051658621600958L;

	/**
	 * The type of the player's pawns
	 */
	private TypeOfPawn pawnOwned;

	/**
	 * The pawn list of the player
	 */
	protected ArrayList<Pawn> ownedPawnsList;

	/**
	 * The name of the player
	 */
	private String name;

	/**
	 * The number of player's pawns in the grid which are touching zen
	 */
	private int nbPawnTouchZen;

	/**
	 * Initializes a player in a Zen L'initie game
	 * @param name : the name of the player
	 * @param pawnOwned : the type of the player's pawns
	 */
	public Player(String name, TypeOfPawn pawnOwned) throws IllegalArgumentException{
		if (name != null && pawnOwned != null){
			this.name = name;
			this.pawnOwned = pawnOwned;
			this.nbPawnTouchZen = 0;
		}else{
			throw new IllegalArgumentException("Player () : one of parameters is incorrect");
		}
	}

	/**
	 * Gives the name of the player
	 * @return the name of the player
	 */
	public String getPlayerName() {
		return this.name;
	}

	/**
	 * Gives the type of player's pawns
	 * @return the type of player's pawns
	 */
	public TypeOfPawn getPawnType() {
		return this.pawnOwned;
	}

	/**
	 * Gives the number of player's pawns which are touching zen
	 * @return number of player's pawns that are touching zen
	 */
	public int getNbPawnTouchZen(){
		return this.nbPawnTouchZen;
	}

	/**
	 * Increments, substitutes or keep the number of player's pawn that are touching Zen's pawn
	 * @param number : to number to add (positive number), to remove (negative number) or to keep (0)
	 */
	public void updateNbPawnTouchZen(int number) {
		if (number >= 0 && number <= 12){
			this.nbPawnTouchZen = number;
		}
	}

	/**
	 * Gives the number of player's pawns present in the game grid
	 * @return the number of player's pawns present in the game grid
	 */
	public int getNbPawn() {
		return this.ownedPawnsList.size();
	}
	
	/**
	 * Gives the list of pawns owned by the player
	 * @return the list of pawns owned by the player
	 */
	public ArrayList<Pawn> getOwnedPawnsList() {
		return this.ownedPawnsList;
	}

	/**
	 * Set the list of pawns that will owned by the player
	 * @param ownedPawnsList : the player's list of owned pawns
	 */
	public void setOwnedPawnsList(ArrayList<Pawn> ownedPawnsList) {
		if (ownedPawnsList != null && ownedPawnsList.get(0).getType() == this.pawnOwned){
			this.ownedPawnsList = ownedPawnsList;
		}else{
			throw new IllegalArgumentException("setOwnedPawnsList() : paramater is incorrect");
		}
	}	

	/**
	 * Substitute by one the number of player's pawns present in the game grid
	 * @param removedPawn : the pawn to remove
	 * @return true if the remove has been realized well, false otherwises
	 */
	public boolean removePawn(Pawn removedPawn) {
		return this.ownedPawnsList.remove(removedPawn);
	}

	/**
	 * Ask the player to select a owned pawn and look if the input is correct
	 * @param coordinates : the coordinates of the pawn in number
	 * @return the coordinates of the selected number
	 */
	public abstract int[] selectPawn(int[] coordinates);

	/**
	 * Asks the player to choose a move by input coordinates
	 * @param listOfMoves : the list of player's pawns that the player can move his selected pawn
	 * @param coordinates : the coordinates input by the user
	 * @return the input coordinates (x,y) of a pawn selected by the player
	 */
	public abstract SquareGrid selectMove(ArrayList<SquareGrid> listOfMoves, int[] coordinates);
}