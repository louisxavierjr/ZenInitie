package controller;

import model.fileManager.ReadWriteFile;
import model.zenGame.GameState;
import model.zenGame.GameplayZen;
import model.zenGame.ModelManager;
import model.zenGame.OpponentType;
import model.zenGame.SquareGrid;
import model.zenGame.TypeOfPawn;
import model.zenGame.rules.GameRules;

import view.graphic.GraphicManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

/**
 * GraphicListener is the listener of all user action on the zen l'initie application 
 * @author Godet Louis-Xavier
 */
public class GraphicListener implements ActionListener{

    /**
     * Graphic manager of the graphic version of Zen initie game
     */
    private GraphicManager viewManager;

    /**
     * Model manager of the Zen initie game
     */
    private ModelManager modelManager;

    /**
     * Initializes a new listener for the graphical version of Zen L'initie game
     * @param model : the model manager of Zen l'initie game
     * @param view  : the graphic manager of the graphic version of Zen initie game
     */
    public GraphicListener(ModelManager model, GraphicManager view) {
        if (model != null && view != null) {
            this.modelManager = model;
            this.viewManager = view;
            this.viewManager.changeWindow("MENU");
            this.viewManager.getMenu().setActionListener(this);
        } else {
            throw new IllegalArgumentException("GraphicListener() : paramètres null");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("MENU.NEWGAME")) {
            //MENU -> NEW GAME
            this.viewManager.changeWindow("NEWGAME");
            this.viewManager.getNewGame().setActionListener(this);
        }else if (e.getActionCommand().contains("NEWGAME.")) {
            //NEW GAME -> STARTGAME
            this.actionForNewGame(e);
        }else if (e.getActionCommand().contains("GRID") || e.getActionCommand().equals("INGAME.LEAVE") ){
            //ACTION ON THE GAME
            this.actionForGame(e);
        } else if (e.getActionCommand().equals("MENU.LOADGAME")) {
            //MENU -> LOAD GAME
            this.viewManager.changeWindow("LOADGAME");
            this.viewManager.getLoadGame().setSaveGamesList(ReadWriteFile.getAllFiles());
            this.viewManager.getLoadGame().setActionListener(this);
        } else if (e.getActionCommand().contains("LOADGAME.")){
            //RESTART A GAME
            this.viewManager.getLoadGame().setActionListener(this);
            this.actionForLoadGame(e);
        }else if (e.getActionCommand().equals("SAVE")){
            if (this.modelManager.getGame() != null){
               this.viewManager.displaySaveConfirmation(ReadWriteFile.writeFile(this.modelManager.getGame()));
            }
        }else if (e.getActionCommand().equals("RULES")) {
            GameRules.openRulesFile();
        }else if (e.getActionCommand().equals("RETURN")){
            this.viewManager.changeWindow("MENU");
            this.viewManager.getMenu().setActionListener(this);
        }else if (e.getActionCommand().equals("MENU.LEAVESOFTWARE")) {
            if (this.viewManager.displayAskConfirmation().equals("CONFIRMER")) {
                System.exit(0);
            }
        }
    }

    /**
     * Defines what to do after an action in new game menu
     * @param e : the action made by the user
     */
    public void actionForNewGame(ActionEvent e) {
        if (e.getActionCommand().equals("NEWGAME.START")) {
            // 1 - game name
            String gameName = this.viewManager.getNewGame().getGameName().getText();
            // 2 - choice of the opponent
            String textOpponent = (String) this.viewManager.getNewGame().getChoiceOpponent().getSelectedItem();
            OpponentType opponent = OpponentType.COMPUTER;
            if (textOpponent.equals("Deuxième joueur")) {
                opponent = OpponentType.HUMAN;
            }
            // 3-players names
            String firstName = this.viewManager.getNewGame().getFirstPlayerName().getText();
            String secondName = this.viewManager.getNewGame().getSecondPlayerName().getText();
            // 4-Type of pawn for player
            TypeOfPawn pawnFirstPlayer = TypeOfPawn.BLACK;
            TypeOfPawn pawnSecondPlayer = TypeOfPawn.WHITE;
            if (this.viewManager.getNewGame().getRadioTwo().isSelected()) {
                pawnFirstPlayer = TypeOfPawn.WHITE;
            } else if (this.viewManager.getNewGame().getRadioThree().isSelected()) {
                pawnFirstPlayer = TypeOfPawn.BLACK;
            }
            //5- creation of the game
            this.modelManager.newGame(gameName, opponent, firstName, secondName, pawnFirstPlayer, pawnSecondPlayer);
            GameplayZen gameToPlay = this.modelManager.getGame();
            this.viewManager.iniGameplayGraphic(gameToPlay.getGrid());
            this.viewManager.getInGame().iniStaticInformation(gameName, firstName, secondName, pawnFirstPlayer.toString(), pawnSecondPlayer.toString());
            this.viewManager.getInGame().updateDynamicInformation(gameToPlay.getCurrentPlayer().getPlayerName(),gameToPlay.getFirstPlayer().getNbPawn(),gameToPlay.getSecondPlayer().getNbPawn(),gameToPlay.getFirstPlayer().getNbPawnTouchZen(),gameToPlay.getSecondPlayer().getNbPawnTouchZen());
            gameToPlay.setZenEnnemy("E");
            if(gameToPlay.getZenPawn() != null){
                gameToPlay.setZenEnnemy(this.viewManager.displayAskZenEnnemy());
            }
            this.viewManager.getInGame().setActionListener(this);
        }
    }

    /**
     * Manages user's actions in a zen l'initie game
     * @param e : the action of the user
     */
    private void actionForGame(ActionEvent e) {
        if (e.getActionCommand().equals("INGAME.LEAVE")){
            if (this.viewManager.displayAskConfirmation().equals("CONFIRMER")){
                this.viewManager.changeWindow("MENU");
                this.viewManager.getMenu().setActionListener(this);
            }
        }else{
            //The game to play
            GameplayZen gameToPlay = this.modelManager.getGame();
            int lengthString = e.getActionCommand().length();
            String coordinates = e.getActionCommand().substring(4,lengthString); //Get the coordinates of the button pressed
            if (gameToPlay.allPawnTouch() == GameState.LOOP){
                int [] coorNumber = gameToPlay.getDigitCoordinates(coordinates); //LETTERNUMBER TO NUMBERNUMBER
                if (coorNumber  != null){
                    int x = coorNumber[0];
                    int y = coorNumber[1];
                    SquareGrid[][] grid = gameToPlay.getGrid();
                    SquareGrid squareClicked = grid[x][y];
                    boolean inListOfMoves = false; //if the selected square (button clicked) is in the list of moves, then it's a move. Otherwise it's a selection
                    if (gameToPlay.getListOfMoves() != null){
                        for (SquareGrid s : gameToPlay.getListOfMoves()){
                            if (squareClicked == s){
                                inListOfMoves = true;
                            }
                        }
                    }
                    if (inListOfMoves){
                        //Move
                        SquareGrid toMove = gameToPlay.getCurrentPlayer().selectMove(gameToPlay.getListOfMoves(),gameToPlay.getDigitCoordinates(coordinates));
                        if (toMove != null){
                            gameToPlay.makeMove(toMove);
                            gameToPlay.changeCurrentPlayer();
                            this.viewManager.updateGrid(gameToPlay.getGrid());
                            if (gameToPlay.getOpponentType() == OpponentType.COMPUTER && gameToPlay.getCurrentPlayer() == gameToPlay.getSecondPlayer()){
                                gameToPlay.computerPlay();
                                gameToPlay.changeCurrentPlayer();
                                this.viewManager.updateGrid(gameToPlay.getGrid());
                            }
                            this.viewManager.getInGame().updateDynamicInformation(gameToPlay.getCurrentPlayer().getPlayerName(),gameToPlay.getFirstPlayer().getNbPawn(),gameToPlay.getSecondPlayer().getNbPawn(),gameToPlay.getFirstPlayer().getNbPawnTouchZen(),gameToPlay.getSecondPlayer().getNbPawnTouchZen());
                            gameToPlay.setZenEnnemy("E");
                            if(gameToPlay.getZenPawn() != null){
                                gameToPlay.setZenEnnemy(this.viewManager.displayAskZenEnnemy());
                            }
                        }
                    }else{
                        //Select
                        int[] number = gameToPlay.getCurrentPlayer().selectPawn(gameToPlay.getDigitCoordinates(coordinates));
                        boolean succeed = gameToPlay.selectSquare(number);
                        if (succeed){
                            ArrayList<SquareGrid> listOfMoves = gameToPlay.calculMove();
                            this.viewManager.getInGame().getGrid().drawSelect(number);
                            this.viewManager.getInGame().getGrid().drawListOfMoves(listOfMoves);
                        }
                    }
                }
            }
            if (gameToPlay.allPawnTouch() != GameState.LOOP){
                GameState statement = gameToPlay.allPawnTouch();
                String statementToText = "";
                if (statement == GameState.DRAW){
                    statementToText = "Match nul !";
                }else if (statement == GameState.VICTORY_PLAYER_ONE){
                    statementToText = gameToPlay.getFirstPlayer().getPlayerName() + " GAGNE LA PARTIE ! ";
                }else if (statement == GameState.VICTORY_PLAYER_TWO){
                    statementToText = gameToPlay.getSecondPlayer().getPlayerName() + " GAGNE LA PARTIE ! ";
                }
                this.viewManager.changeWindow("ENDGAME");
                this.viewManager.getEndGame().setEndGameState(statementToText);
                this.viewManager.getEndGame().setActionListener(this);
            }
        }
    }

    /**
     * Manages user's actions on the load game menu
     * @param e : the action of the user
     */
    private void actionForLoadGame(ActionEvent e){
        if (e.getActionCommand().equals("LOADGAME.RESTART")){
            boolean fileNotFound = true;
            int i = 0;
            while (i < this.viewManager.getLoadGame().getGroupButton().size() && fileNotFound){
                if (this.viewManager.getLoadGame().getGroupButton().get(i).isSelected()){
                    this.modelManager.load(i);
                    GameplayZen gameToPlay = this.modelManager.getGame();
                    this.viewManager.iniGameplayGraphic(this.modelManager.getGame().getGrid());
                    this.viewManager.getInGame().iniStaticInformation(gameToPlay.getGameName(), gameToPlay.getFirstPlayer().getPlayerName(), gameToPlay.getSecondPlayer().getPlayerName(), gameToPlay.getFirstPlayer().getPawnType().toString(), gameToPlay.getSecondPlayer().getPawnType().toString());
                    this.viewManager.getInGame().updateDynamicInformation(gameToPlay.getCurrentPlayer().getPlayerName(),gameToPlay.getFirstPlayer().getNbPawn(),gameToPlay.getSecondPlayer().getNbPawn(),gameToPlay.getFirstPlayer().getNbPawnTouchZen(),gameToPlay.getSecondPlayer().getNbPawnTouchZen());
                    this.viewManager.getInGame().setActionListener(this);
                    fileNotFound = false;
                }else{
                    i++;
                }
            }
        }
    }
}