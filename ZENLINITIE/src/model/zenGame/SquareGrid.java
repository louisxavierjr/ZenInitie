package model.zenGame;

import java.io.Serializable;

/**
 * SquareGrid is a class that represents a square in the Zen L'inite grid
 * @author Godet Louis-Xavier
 */
public class SquareGrid implements Serializable {
	
	/**
	 * Default UID
	 */
	private static final long serialVersionUID = 8864930173728790199L;

	/**
	 * the position in abscissa axis of the square
	 */
	private int xPosition;

	/**
	 * the position in ordinate axis of the square
	 */
	private int yPositon;

	/**
	 * The pawns that is presents in the square
	 */
	private Pawn pawnPresent;

	/**
	 * Tells if the square is occupied by a pawn or not
	 */
	private boolean occupied;
	
	/**
	 * Initializes a square in a Zen l'initie grid
	 * @param x : the square's abscissa position
	 * @param y : the square's ordinate position
	 * @throws IllegalArgumentException if input coordinates are incorrects
	 */
	public SquareGrid(int x, int y) throws IllegalArgumentException{
		if (x >= 0 && x < 11 && y >=0 && y < 11){
			this.xPosition = x;
			this.yPositon = y;
			this.occupied = false;
		}else{
			throw new IllegalArgumentException("SquareGrid(): input coordinates are out of the grid");
		}
	}

	/**
	 * Gives the pawn presents in the square
	 * @return the pawn presents in the square
	 */
	public Pawn getPawnPresent() {
		return this.pawnPresent;
	}

	/**
	 * Gives the coordinates of the square
	 * @return the coordinates of the square
	 */
	public int[] getCoordinatesSquare() {
		return new int[]{this.xPosition, this.yPositon};
	}

	/**
	 * Sets a new pawn that will occupy the square
	 * @param newPawn : the new pawn to occupy the square
	 * @throws IllegalArgumentException if input coordinates are incorrectss
	 */
	public void setPawnPresent(Pawn newPawn) throws IllegalArgumentException {
		if (newPawn != null){
			this.pawnPresent = newPawn;
			this.pawnPresent.setCoordinates(this.xPosition, this.yPositon);
			this.occupied = true;
		}else{
			throw new IllegalArgumentException("SquareGrid : the pawn supposed to be present on the square (" + this.xPosition + " ; " + this.yPositon + ") is null");
		}
	}

	/**
	 * Remove the pawn of the square
	 */
	public void removePawnPresent() {
		this.pawnPresent = null;
		this.occupied = false;
	}

	/**
	 * Tells if the square is occupied by a pawn or not
	 * @return true if the square is occupied, false otherwise
	 */
	public boolean isOccupied() {
		return this.occupied;
	}

	/**
	 * Tells if the square contains the Zen pawn
	 * @return true if the Zen pawn is in the concerned square, false otherwise
	 */
	public boolean isZen() {
		return false;
	}
}