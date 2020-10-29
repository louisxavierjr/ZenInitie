package model.zenGame;

import model.fileManager.ReadWriteFile;

/**
 * ModelManager manages most of the game features 
 * like start or load a game, open the game rules or simply leave the game
 * @author Godet Louis-Xavier
 */
public class ModelManager{
    
    /**
     * Game that the user is playing, used when the game has been created or previously load
     */
    private GameplayZen game;

    /**
     * ModelManager builds the software's model manager and the options associated
     */
    public ModelManager() {
    }

    /**
     * newGame initializes a Zen l'initie game
     * @param gameName : the name of the game
     * @param opponent : the type of opponent that the player wants to play against
     * @param firstName : the name of the first player
     * @param secondName : the name of the second player
     * @param pawnFirstPlayer : the type of pawns selected by the first player
     * @param pawnSecondPlayer : the type of pawns selected by the second player
     */
    public void newGame(String gameName, OpponentType opponent, String firstName, String secondName,TypeOfPawn pawnFirstPlayer, TypeOfPawn pawnSecondPlayer) {
        this.game = new GameplayZen(gameName, opponent, firstName, secondName, pawnFirstPlayer, pawnSecondPlayer);
    }

    /**
     * Load the game given by the parameter
     * @param choice : the index of the save file game to load
     * @throws IllegalArgumentException if the game is null
     */
    public void load(int choice) throws IllegalArgumentException{
        if(choice >= 0){        
            this.game = ReadWriteFile.readFile(choice);
        }else{
            throw new IllegalArgumentException("load() error : choice index is negative");
        }
    }

    /**
     * Gives the game previously loaded
     * @return the game loaded to play
     */
    public GameplayZen getGame() {
        return this.game;
    }
}