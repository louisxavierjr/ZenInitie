package model.zenGame;

import java.io.Serializable;

/**
 * Pawns is a class representing a pawn in a Zen L'inite game
 * @author Godet Louis-Xavier
 */
public class Pawn implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6419966223771971036L;

	/**
	 * The type of the pawn which can be either white, either black or either the
	 * Zen pawn.
	 */
	private TypeOfPawn pawnType;

	/**
	 * the position in abscissa axis of the pawn
	 */
	private int xPosition;

	/**
	 * the position in ordinate axis of the pawn
	 */
	private int yPosition;

	/**
	 * Initializes a pawn in the Zen l'inite grid
	 * @param x : the pawn's abscissa position in the grid
	 * @param y : the pawn's ordinate position in the grid
	 * @param type : the type of the pawn
	 * @throws IllegalArgumentException if input coordinates or input type is invalid
	 */
	public Pawn(int x, int y, TypeOfPawn type) throws IllegalArgumentException {
		if (x >= 0 && y >= 0 && x < 11 && y < 11 && type != null){
			this.xPosition = x;
			this.yPosition = y;
			this.pawnType = type;
		}else{
			throw new IllegalArgumentException("Pawn() : les coordonées saisies dans l'initialisation sont incorrectes");
		}
	}

	/**
	 * Gives the type of the pawn concerned
	 * @return the type of the pawn
	 */
	public TypeOfPawn getType() {
		return this.pawnType;
	}

	/**
	 * Gives the pawn's abscissa position in the grid
	 * @return the pawn's abscissa position in the grid
	 */
	public int getXPosition() {
		return this.xPosition;
	}

	/**
	 * Gives the pawn's ordinate position in the grid
	 * @return the pawn's ordinate position in the grid
	 */
	public int getYPosition() {
		return this.yPosition;
	}

	/**
	 * Sets a new position for the pawn concerned
	 * @param x : the pawn's new abscissa position
	 * @param y : the pawn's new ordinate position
	 */
	public void setCoordinates(int x, int y) {
		if (x >= 0 && y >= 0 && x < 11 && y < 11){
			this.xPosition = x;
			this.yPosition = y;
		}else{
			throw new IllegalArgumentException("SetCoordinates : les nouvelles coordonnes sont invalides " + x + " : " + y);
		}
	}

	/**
	 * Set the type of the pawn
	 * @param type : the type of the pawn
	 */
	public void setType(TypeOfPawn type) {
		this.pawnType = type;
	}
}