package view.graphic;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.zenGame.SquareGrid;

/**
 * Manages the whole graphic version of Zen L'initie game
 * @author Godet Louis-Xavier
 */
public class GraphicManager extends JFrame {

    /**
     * The windows menu of the game
     */
    private GraphicMenu menu;

    /**
     * The windows where user can create a new game
     */
    private GraphicNewGame newGame;

    /**
     * The windows with the grid of the game and where can play a zen l'initie game
     */
    private GraphicGameplay inGame;

    /**
     * The windows displaying the winner of the game
     */
    private GraphicEndGame endgame;

    /**
     * The windows where the user can reload a previously game
     */
    private GraphicLoad loadGame;

    /**
     * Appoint the current window used in the frame
     */
    private JPanel currentWindow;


    /**
     * Initializes the graphic version manager of the zen l'initie game
     */
    public GraphicManager() {
        super();

        //title of the window
        this.setTitle("Zen l'initié");
        //Size of the game window
        this.setSize(1200,800);
        this.setResizable(false);
        this.setLocationRelativeTo(null); //center the window

        //Default close
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Menu frame by default when the application is executed
        this.menu = new GraphicMenu();
        this.currentWindow = this.menu;
        this.add(this.menu);
    }

     /**
     * Change current window according to the input's user
     * @param description : description of the window to execute
     */
    public void changeWindow(String description){
        this.currentWindow.setVisible(false);
        if (description.equals("NEWGAME")){
            this.newGame = new GraphicNewGame();
            this.currentWindow = this.newGame;
        }else if(description.equals("ENDGAME")){
            this.endgame = new GraphicEndGame();
            this.currentWindow = this.endgame;
        }else if (description.equals("LOADGAME")){
            this.loadGame = new GraphicLoad();
            this.currentWindow = this.loadGame;
        }else if (description.equals("MENU")){
            this.menu = new GraphicMenu();
            this.currentWindow = this.menu;
        }
        this.currentWindow.validate();
        this.add(this.currentWindow);
        this.currentWindow.setVisible(true);
    }
    
    /**
     * Displays an option panel where the user is being asked if he consider zen pawn as a friend or an ennemy
     * @return the answer of the user : A for friend or E for ennemy
     */
    public String displayAskZenEnnemy() {
        String[] consideration = {"AMI","ENNEMI"}; //A stands for friends, E stands for ennemy
        int choice = 0;
        choice = JOptionPane.showOptionDialog(null,"Considérez-vous le pion Zen comme ennemi ou ami ?", "Ami ou ennemi ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,consideration,consideration[0]);
        return consideration[choice].substring(0,1);
    }

    /**
     * Displays an option panel where the user is being asked if he really wants to leave the game or the application
     * @return the answer of the user : CONFIRMATION or ANNULER
     */
    public String displayAskConfirmation() {
        String[] leaveOption = {"CONFIRMER","ANNULER"}; //A stands for friends, E stands for ennemy
        int choice = 0;
        choice = JOptionPane.showOptionDialog(null,"Voulez-vous vraiment quitter ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, leaveOption,leaveOption[0]);
        return leaveOption[choice];
    }
    
    /** 
     * Display a confirmation about the game saved attempt
     * @param savedState : tells if the game has been saved well 
     */
    public void displaySaveConfirmation(boolean savedState){
        if (savedState){
            JOptionPane.showMessageDialog(this, "Sauvegarde réussie", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Sauvegarde non réussie", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Initialize the grid of the game
     * @param grid : the grid to draw in the in game windows
     */
    public void iniGameplayGraphic(SquareGrid[][] grid){
        this.currentWindow.setVisible(false);
        this.inGame = new GraphicGameplay();
        this.inGame.getGrid().placePawns(grid);
        this.currentWindow = this.inGame;
        this.currentWindow.validate();
        this.add(currentWindow);
        this.currentWindow.setVisible(true);
    }

    /**
     * Update the grid of the game
     * @param grid : the grid to draw in the in game windows
     */
    public void updateGrid(SquareGrid[][] grid){
        this.currentWindow.setVisible(false);
        this.inGame.getGrid().placePawns(grid);
        this.currentWindow.validate();
        this.currentWindow.setVisible(true);
    }

    /**
     * Gives the menu windows
     * @return the menu windows
     */
    public GraphicMenu getMenu() {
        return this.menu;
    }
    
    /**
     * Gives the new game menu windows
     * @return the new game menu windows
     */
    public GraphicNewGame getNewGame() {
        return this.newGame;
    }

    /**
     * Gives the in game windows
     * @return the in game windows
     */
    public GraphicGameplay getInGame() {
        return this.inGame;
    }

    /**
     * Gives the end game windows
     * @return the end game windows
     */
    public GraphicEndGame getEndGame() {
		return this.endgame;
    }

    /**
     * Gives the load menu windows
     * @return the load menu windows
     */
    public GraphicLoad getLoadGame() {
        return this.loadGame;
    }
}