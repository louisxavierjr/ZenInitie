package model.zenGame.player;

import model.zenGame.SquareGrid;
import model.zenGame.TypeOfPawn;

import java.util.ArrayList;

/**
 * AutoPlayer is a class which specifies the behaviour of the game for computers players
 * @author Godet Louis-Xavier
 */
public class Computer extends Player{

	/**
	 * Default UID
	 */
	private static final long serialVersionUID = -146492661692648636L;

	/**
	 * Initializes a computer player in a Zen l'initie game
	 * @param name : the name of the computer player
	 * @param pawnOwned : the type of the computer player's pawns
	 */
	public Computer(String name, TypeOfPawn pawnOwned) throws IllegalArgumentException{
		super(name, pawnOwned);
	}

	@Override
	public int[] selectPawn(int[] coordinates) {
		int[] ret = new int[2];
		int indexPawn = (int) (Math.random() * this.ownedPawnsList.size());
		ret[0] = this.ownedPawnsList.get(indexPawn).getXPosition();
		ret[1] = this.ownedPawnsList.get(indexPawn).getYPosition();
		return ret;
	}

	@Override
	public SquareGrid selectMove(ArrayList<SquareGrid> listOfMoves, int[] coordinates) {
		int indexSquare = (int) (Math.random() * listOfMoves.size());
		return listOfMoves.get(indexSquare);
	}
}