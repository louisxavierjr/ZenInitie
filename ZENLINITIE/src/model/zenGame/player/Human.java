package model.zenGame.player;

import model.zenGame.Pawn;
import model.zenGame.SquareGrid;
import model.zenGame.TypeOfPawn;

import java.util.ArrayList;

/**
 * HumanPlayer is a class which specifies the behaviour of the game for humans players
 * @author Godet Louis-Xavier
 */
public class Human extends Player{

	/**
	 * Default UID
	 */
	private static final long serialVersionUID = 8916168177201218582L;

	/**
	 * Initializes a human player in a Zen l'initie game
	 * @param name : the name of the human player
	 * @param pawnOwned : the type of the human player's pawns
	 */
	public Human(String name, TypeOfPawn pawnOwned) throws IllegalArgumentException{
		super(name, pawnOwned);
	}

	
	@Override
	public int[] selectPawn(int[] coordinates) {
		int[] ret = null;
		int x = coordinates[0];
		int y = coordinates[1];
		//Verify the user's input
		if (x >= 0 && y >= 0 && x < 11 && y < 11){
			int j = 0;
			boolean pawnFound = false;
			while(j < this.ownedPawnsList.size() && !pawnFound){
				Pawn thePawn = this.ownedPawnsList.get(j);
				if (thePawn.getXPosition() == x && thePawn.getYPosition() == y){
					pawnFound = true;
					ret = new int[2];
					ret[0] = x;
					ret[1] = y;
				}else{
					j++;
				}
			}
		}
		return ret;
	}

	@Override
	public SquareGrid selectMove(ArrayList<SquareGrid> listOfMoves, int[] coordinates) {
		SquareGrid ret = null; //while is not valid, ask the user
		int x = coordinates[0];
		int y = coordinates[1];
		//Verify if the coordinates targeted is a valid movement
		if (x >= 0 && y >= 0 && x < 11 && y < 11){
			int j = 0;
			boolean moveFound = false;
			while(j < listOfMoves.size() && !moveFound){
				SquareGrid theSquare = listOfMoves.get(j);
				int [] squareCoord = theSquare.getCoordinatesSquare();
				if (squareCoord[0] == x && squareCoord[1] == y){
					moveFound = true;
					ret = theSquare;
				}else{
					j++;
				}
			}
		}
		return ret;
	}
}