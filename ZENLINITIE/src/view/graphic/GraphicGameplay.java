package view.graphic;

import view.graphic.components.Background;
import view.graphic.components.ZenGrid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * GraphicGameplay draws and manage the graphic part of a zen l'initie game
 * @author Godet Louis-Xavier
 */
public class GraphicGameplay extends JPanel{
    
    /**
     * The path leading to the image in the background of the ingame windows
     */
    private static final String PATH_BACKGROUND_GAME = "../data/images/backgroundGame.jpg";

    /**
     * The path leading to the background image of buttons
     */
    private static final String PATH_BACKGROUND_BUTTON = "../data/images/backgroundButton.jpg";

    /**
     * The graphic grid of the game
     */
    private ZenGrid grid;

    /**
     * the label with the name of the game
     */
    private JLabel gameNameLabel;
    
    /**
     * the label with the name of the current players
     */
    private JLabel currentPlayerLabel;

    /**
     * the information about the first player
     */
    private JTextArea playerOneInformation;

    /**
     * the information about the second player
     */
    private JTextArea playerTwoInformation;

    /**
     * The button to save the game
     */
    private JButton saveButton;

    /**
     * The button to open the rules
     */
    private JButton rulesButton;

    /**
    * The button to leave the game
    */
    private JButton leaveButton;

    /**
     * Save the static information about the first player (name of the player and the type owned) in order to keep it
     * when dynamic information (number of pawn touching zen for example) is being remove and updated
     */
    private String staticInformationPlayerOne;

    /**
     * Save the static information about the second player (name of the player and the type owned) in order to keep it
     * when dynamic information (number of pawn touching zen for example) is being remove and updated
     */
    private String staticInformationPlayerTwo;

    /**
     * Initializes a windows for the gameplay of a zen l'initie game
     */
    public GraphicGameplay() {
        //1 - layout
        this.setLayout(new BorderLayout());

        //2 - main panel
        Background mainPanel = new Background(PATH_BACKGROUND_GAME);
        mainPanel.setLayout(new BorderLayout());
        // mainPanel.setPreferredSize(new Dimension(1200,800));

        //3 - TOP 
        JPanel top = new JPanel(new GridLayout(3,1));
        top.setOpaque(false);
        // title
        this.gameNameLabel = new JLabel();
        this.gameNameLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        this.gameNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.gameNameLabel.setForeground(Color.white);
        this.gameNameLabel.setOpaque(false);
        //current player
        this.currentPlayerLabel = new JLabel("Le tour est à : ");
        this.currentPlayerLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        this.currentPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.currentPlayerLabel.setForeground(Color.white);
        this.currentPlayerLabel.setOpaque(false);
        //top add
        top.add(this.gameNameLabel);
        top.add(this.currentPlayerLabel);
        top.add(new JLabel(""));

        //4 - CENTER : the grid
        this.grid = new ZenGrid();

        //5 - Bottom : buttons
        FlowLayout layoutBottom = new FlowLayout();
        layoutBottom.setHgap(70);
        //panel
        JPanel bottom = new JPanel(layoutBottom);
        bottom.setAlignmentY(SwingConstants.TOP);
        bottom.setOpaque(false);
        //buttons
        this.saveButton = new JButton();
        this.iniButtons(this.saveButton,"Sauvegarder");
        bottom.add(this.saveButton);
        this.rulesButton = new JButton();
        this.iniButtons(this.rulesButton,"Regles du jeu");
        bottom.add(this.rulesButton);
        this.leaveButton = new JButton();
        this.iniButtons(this.leaveButton,"Quitter la partie");
        bottom.add(this.leaveButton);

        //6 - Left
        JPanel left = new JPanel();
        left.setLayout(new GridLayout(5,1));
        left.setOpaque(false);
        //information player one
        this.playerOneInformation = new JTextArea();
        this.playerOneInformation.setOpaque(false);
        this.playerOneInformation.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        this.playerOneInformation.setForeground(Color.white);
        //Create empty panel in order to center the information panel
        JPanel emptyOne = new JPanel();
        emptyOne.setOpaque(false);
        JPanel emptyTwo = new JPanel();
        emptyTwo.setOpaque(false);
        left.add(emptyOne);
        left.add(emptyTwo);
        //add the information panel
        left.add(this.playerOneInformation);
        //Create empty panel in order to center the information panel
        JPanel emptyThree = new JPanel();
        emptyThree.setOpaque(false);
        JPanel emptyFour = new JPanel();
        emptyFour.setOpaque(false);
        left.add(emptyThree);
        left.add(emptyFour);

        //7 - Right
        JPanel right = new JPanel();
        right.setLayout(new GridLayout(5,1));
        right.setOpaque(false);
        //information player one
        this.playerTwoInformation = new JTextArea();
        this.playerTwoInformation.setOpaque(false);
        this.playerTwoInformation.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        this.playerTwoInformation.setForeground(Color.white);
        //Create empty panel in order to center the information panel
        emptyOne = new JPanel();
        emptyOne.setOpaque(false);
        emptyTwo = new JPanel();
        emptyTwo.setOpaque(false);
        right.add(emptyOne);
        right.add(emptyTwo);
        //add the information panel
        right.add(this.playerTwoInformation);
        //Create empty panel in order to center the information panel
        emptyThree = new JPanel();
        emptyThree.setOpaque(false);
        emptyFour = new JPanel();
        emptyFour.setOpaque(false);
        right.add(emptyThree);
        right.add(emptyFour);
        
        //8 - final add
        mainPanel.add(top, BorderLayout.NORTH);
        mainPanel.add(this.grid, BorderLayout.CENTER);
        mainPanel.add(bottom, BorderLayout.SOUTH);
        mainPanel.add(left, BorderLayout.WEST);
        mainPanel.add(right, BorderLayout.EAST);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Uniforms the shape of a button in ingame windows
     * @param button : the button to uniform
     * @param description : the description of the button
     */
    public void iniButtons(JButton button, String description){
        //button's panel
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BorderLayout());
        panelButton.setOpaque(false);

        //Text on the button
        JLabel label = new JLabel(description);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font(Font.DIALOG, Font.BOLD,15));
        panelButton.add(label, BorderLayout.CENTER);

        //panel added on the button
        button.setPreferredSize(new Dimension(153,53));
        button.add(panelButton, BorderLayout.CENTER);
        button.setIcon(new ImageIcon(PATH_BACKGROUND_BUTTON));
    }
    
    /**
     * Sets the listener for button's ingame
     * @param a : the listener to set for ingame
     */
	public void setActionListener(ActionListener a) {
        this.grid.setActionListener(a);

        this.saveButton.addActionListener(a);
        this.saveButton.setActionCommand("SAVE");

        this.rulesButton.addActionListener(a);
        this.rulesButton.setActionCommand("RULES");

        this.leaveButton.addActionListener(a);
        this.leaveButton.setActionCommand("INGAME.LEAVE");
    }

    /**
     * Gives the graphical grid of the game
     * @return  the graphical grid of the game
     */
    public ZenGrid getGrid() {
        return this.grid;
    }

    /**
    * Initializes the static information(gived by parameters) about players of the zen l'initie game
    * @param gameName : the name of the game
    * @param playerOneName : the name of the first player
    * @param playerTwoName : the name of the second player
    * @param pawnPlayerOne : the type of pawn owned by the first player
    * @param pawnPlayerTwo : the type of pawn owned by the second player
    */
    public void iniStaticInformation(String gameName, String playerOneName, String playerTwoName, String pawnPlayerOne, String pawnPlayerTwo){
        this.gameNameLabel.setText(gameName);
        String translatePawnPlayerOne = "";
        String translatePawnPlayerTwo = "";
        if (pawnPlayerOne.equals("BLACK")){
            translatePawnPlayerOne= "Noir";
            translatePawnPlayerTwo = "Blanc";
        }else{
            translatePawnPlayerOne= "Blanc";
            translatePawnPlayerTwo = "Noir";
        }
        this.staticInformationPlayerOne = playerOneName + "\n" + "Couleur du pion : " + translatePawnPlayerOne + "\n";
        this.playerTwoInformation.setText(this.staticInformationPlayerOne);
        this.staticInformationPlayerTwo = playerTwoName + "\n" + "Couleur du pion : " + translatePawnPlayerTwo + "\n";
        this.playerTwoInformation.setText(this.staticInformationPlayerTwo);
    }
    
    /**
     * Updates the dynamic information(gived by parameters) about players of the zen l'initie game
     * @param currentPlayerName : the name of the current player who must play his turn
     * @param nbPawnsPlayerOne : the number of pawns owned by the first player
     * @param nbPawnsPlayerTwo : the number of pawns owned by the second player
     * @param nbChainedPlayerOne : the number of pawns owned by the first player which participate to the chain
     * @param nbChainedPlayerTwo : the number of pawns owned by the second player which participate to the chain
     */
    public void updateDynamicInformation(String currentPlayerName, int nbPawnsPlayerOne, int nbPawnsPlayerTwo, int nbChainedPlayerOne, int nbChainedPlayerTwo){
        this.currentPlayerLabel.setText("Le tour est à : " + currentPlayerName);
        this.playerOneInformation.setText(this.staticInformationPlayerOne + "Pions possédés : " + nbPawnsPlayerOne + "\n" + "Pions formant la chaine : " + nbChainedPlayerOne);
        this.playerTwoInformation.setText(this.staticInformationPlayerTwo + "Pions possédés : " + nbPawnsPlayerTwo + "\n" + "Pions formant la chaine : " + nbChainedPlayerTwo);
    }
}