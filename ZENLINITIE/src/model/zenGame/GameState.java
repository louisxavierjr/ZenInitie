package model.zenGame;

/**
 * Defines the state of a zen l'initie game
 * @author Godet Louis-Xavier
 */
public enum GameState {
    /**
     * Draw : both of the player has win
     */
    DRAW,

    /**
     * Victory of the player one
     */
    VICTORY_PLAYER_ONE,

    /**
     * Victory of the player two
     */
    VICTORY_PLAYER_TWO,

    /**
     * The game has to continue
     */
    LOOP;
}