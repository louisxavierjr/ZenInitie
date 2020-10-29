package view.graphic;

import view.graphic.components.Background;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 * GraphicNewGame draws and displays the new game of Zen initie game
 * @author Godet Louis-Xavier
 */
public class GraphicNewGame extends JPanel {
    /**
     * The path leading to the background menu image
     */
    private static final String PATH_BACKGROUND_MENU = "../data/images/backgroundZen.jpg";

    /**
     * The path leading to the background of buttons
     */
    private static final String PATH_BACKGROUND_BUTTON = "../data/images/backgroundButton.jpg";

    /**
     * Input field for the name game
     */
    private JTextField gameName;

    /**
     * List of choice of the opponent
     */
    private JComboBox<String> choiceOpponent;

    /**
     * Input field for the first player name
     */
    private JTextField firstPlayerName;

    /**
     * Input field for the second player name
     */
    private JTextField secondPlayerName;

    /**
     * Choice of the pawn type (black) for the first player
     */
    private JRadioButton radioOne;

    /**
     * Choice of the pawn type (white) for the first player
     */
    private JRadioButton radioTwo;

    /**
     * Choice of the pawn type (black) for the second player
     */
    private JRadioButton radioThree;

    /**
     * Choice of the pawn type (white) for the second player
     */
    private JRadioButton radioFour;

    /**
     * The button to start the game
     */
    private JButton startGame;

    /**
     * The button to return to the menu
     */
    private JButton returnToMenu;

    /**
     * Initializes the new game menu
     */
    public GraphicNewGame() {
        this.setLayout(new BorderLayout());
        this.iniComponent();
    }

    /**
     * Initializes all components of the new game menu
     */
    private void iniComponent(){
        //1 - Main panel
        JPanel background = new Background(PATH_BACKGROUND_MENU);
        background.setLayout(new BorderLayout());
        background.setPreferredSize(new Dimension(1200,800));
        
        //2 - Title - NORTH
        JLabel titlePage = new JLabel("Nouvelle partie");
        titlePage.setPreferredSize(new Dimension(1200,100));
        titlePage.setHorizontalAlignment(SwingConstants.CENTER);
        titlePage.setForeground(Color.WHITE);
        titlePage.setFont(new Font(Font.DIALOG,Font.BOLD, 50));
        background.add(titlePage, BorderLayout.NORTH);

        //3 - Input user - CENTER
        //3.1 - main panel and layout for center panel
        JPanel input = new JPanel();
        GridLayout grid = new GridLayout(4,3);
        input.setLayout(grid);
        input.setOpaque(false);

        //3.2 game name input
        //panel
        JPanel groupInputGameName = new JPanel();
        FlowLayout gameNameLayout = new FlowLayout();
        gameNameLayout.setHgap(50);
        groupInputGameName.setLayout(gameNameLayout);
        groupInputGameName.setOpaque(false);
        //description
        JLabel descriptionInput = new JLabel("Nom de la partie : ");
        descriptionInput.setForeground(Color.WHITE);
        descriptionInput.setFont(new Font(Font.DIALOG,Font.BOLD,18));
        //Textfield
        this.gameName = new JTextField();
        this.gameName.setPreferredSize(new Dimension(400,30));
        //add
        groupInputGameName.add(descriptionInput);
        groupInputGameName.add(this.gameName);

        //3.3 choice opponent
        JPanel groupOpponent = new JPanel();
        FlowLayout opponentLayout = new FlowLayout();
        opponentLayout.setHgap(50);
        groupOpponent.setLayout(opponentLayout);
        groupOpponent.setOpaque(false);
        //description
        JLabel descriptionOpponent = new JLabel("Jouer contre : ");
        descriptionOpponent.setForeground(Color.WHITE);
        descriptionOpponent.setFont(new Font(Font.DIALOG,Font.BOLD,18));
        //Combobox
        String[] opponentList = {"Deuxième joueur","Ordinateur"};
        this.choiceOpponent = new JComboBox<String>(opponentList);
        this.choiceOpponent.setPreferredSize(new Dimension(420,30));
        //add
        groupOpponent.add(descriptionOpponent);
        groupOpponent.add(this.choiceOpponent);


        //3.4 firstPlayer - name and type of pawn
        JPanel groupFirstPlayer = new JPanel();
        FlowLayout playerLayout = new FlowLayout();
        playerLayout.setHgap(10);
        groupFirstPlayer.setLayout(playerLayout);
        groupFirstPlayer.setOpaque(false);
        //description
        JLabel descriptionPlayer = new JLabel("Nom joueur 1 : ");
        descriptionPlayer.setForeground(Color.WHITE);
        descriptionPlayer.setFont(new Font(Font.DIALOG,Font.BOLD,18));
        //Jtextfield
        this.firstPlayerName = new JTextField();
        this.firstPlayerName.setPreferredSize(new Dimension(350,30));
        //radio button
        this.radioOne = new JRadioButton("NOIR");
        this.radioOne.setForeground(Color.WHITE);
        this.radioOne.setOpaque(false);
        this.radioTwo = new JRadioButton("BLANC");
        this.radioTwo.setForeground(Color.WHITE);
        this.radioTwo.setOpaque(false);
        //add
        groupFirstPlayer.add(descriptionPlayer);
        groupFirstPlayer.add(this.firstPlayerName);
        groupFirstPlayer.add(this.radioOne);
        groupFirstPlayer.add(this.radioTwo);

        //3.5 - second player
        JPanel groupSecondPlayer = new JPanel();
        FlowLayout secondPlayerLayout = new FlowLayout();
        secondPlayerLayout.setHgap(10);
        groupSecondPlayer.setLayout(secondPlayerLayout);
        groupSecondPlayer.setOpaque(false);
        //description
        JLabel descriptionSecondPlayer = new JLabel("Nom joueur 2 : ");
        descriptionSecondPlayer.setForeground(Color.WHITE);
        descriptionSecondPlayer.setFont(new Font(Font.DIALOG,Font.BOLD,18));
        //textfield
        this.secondPlayerName = new JTextField();
        this.secondPlayerName.setPreferredSize(new Dimension(350,30));
        //radio button
        this.radioThree = new JRadioButton("NOIR");
        this.radioThree.setForeground(Color.WHITE);
        this.radioThree.setOpaque(false);
        this.radioFour = new JRadioButton("BLANC");
        this.radioFour.setForeground(Color.WHITE);
        this.radioFour.setOpaque(false);
        //add
        groupSecondPlayer.add(descriptionSecondPlayer);
        groupSecondPlayer.add(this.secondPlayerName);
        groupSecondPlayer.add(this.radioThree);
        groupSecondPlayer.add(this.radioFour);

        //3.6 Two Group for the combo box
        ButtonGroup colorPawnFirstPlayer = new ButtonGroup();
        ButtonGroup colorPawnSecondPlayer = new ButtonGroup();
        colorPawnFirstPlayer.add(this.radioOne);
        colorPawnFirstPlayer.add(this.radioTwo);
        colorPawnSecondPlayer.add(this.radioThree);
        colorPawnSecondPlayer.add(this.radioFour);

        //3.7 final add of center panel
        input.add(groupInputGameName);
        input.add(groupOpponent);
        input.add(groupFirstPlayer);
        input.add(groupSecondPlayer);
        background.add(input, BorderLayout.CENTER);
        

        //4. Buttons - SOUTH
        JPanel groupButton = new JPanel();
        //4.1 - layout of button's panel
        FlowLayout layoutButton = new FlowLayout();
        layoutButton.setHgap(80);
        groupButton.setLayout(layoutButton);
        //4.2. background and size
        groupButton.setOpaque(false);
        groupButton.setPreferredSize(new Dimension(1200,100));

        //4.3 - start game button
        this.startGame = new JButton();
        this.iniButtons(this.startGame, "LANCER LA PARTIE");
        groupButton.add(this.startGame);

        //4.4 - return to main menu button
        this.returnToMenu = new JButton();
        this.iniButtons(this.returnToMenu, "RETOUR AU MENU");
        groupButton.add(this.returnToMenu);
        
        //4. final add
        background.add(groupButton, BorderLayout.SOUTH);
        this.add(background, BorderLayout.WEST);
    }

    /**
     * Uniforms the shape of a button in the new game windows
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
        label.setFont(new Font(Font.DIALOG, Font.BOLD,18));
        panelButton.add(label, BorderLayout.CENTER);

        //panel added on the button
        button.setPreferredSize(new Dimension(220,80));
        button.add(panelButton, BorderLayout.CENTER);
        button.setIcon(new ImageIcon(PATH_BACKGROUND_BUTTON));
    }

    /**
     * Sets the listener for button's new game
     * @param a : the listener to set for buttons
     */
    public void setActionListener(ActionListener a){
        //1 - game name text field
        this.gameName.addActionListener(a);

        //2 - combobox of opponent
        this.choiceOpponent.addActionListener(a);

        //3 - first player name
        this.firstPlayerName.addActionListener(a);

        //4 - second player name
        this.secondPlayerName.addActionListener(a);

        //5 - JRadiobuttons
        this.radioOne.addActionListener(a);
        this.radioOne.setActionCommand("NEWGAME.BLACKPAWNPLAYERONE");

        this.radioTwo.addActionListener(a);
        this.radioOne.setActionCommand("NEWGAME.WHITEPAWNPLAYERONE");

        this.radioThree.addActionListener(a);
        this.radioOne.setActionCommand("NEWGAME.BLACKPAWNPLAYERTWO");

        this.radioFour. addActionListener(a);
        this.radioOne.setActionCommand("NEWGAME.WHITEPAWNPLAYERTWO");

        //6 - start game
        this.startGame.setActionCommand("NEWGAME.START");
        this.startGame.addActionListener(a);
        
        //7 - return to menu
        this.returnToMenu.setActionCommand("RETURN");
        this.returnToMenu.addActionListener(a);
    }

    /**
     * Gives the input text field for the name of the game
     * @return the input text field for the name of the game
     */
    public JTextField getGameName() {
        return this.gameName;
    }

    /**
     * Gives the list of opponents
     * @return the list of opponents
     */
    public JComboBox<String> getChoiceOpponent() {
        return this.choiceOpponent;
    }

    /**
     * Gives the input text field for the name of the first player
     * @return the input text field for the name of the first player
     */
    public JTextField getFirstPlayerName() {
        return this.firstPlayerName;
    }

    /**
     * Gives the input text field for the name of the second player
     * @return the input text field for the name of the second player
     */
    public JTextField getSecondPlayerName() {
        return this.secondPlayerName;
    }

    /**
     * Gives the radio button one which associated black pawns for the first player
     * @return the radio button one which associated black pawns for the first player
     */
    public JRadioButton getRadioOne() {
        return this.radioOne;
    }

     /**
     * Gives the radio button two which associated white pawns for the first player
     * @return the radio button one which associated white pawns for the first player
     */
    public JRadioButton getRadioTwo() {
        return this.radioTwo;
    }

     /**
     * Gives the radio button one which associated black pawns for the second player
     * @return the radio button one which associated black pawns for the second player
     */
    public JRadioButton getRadioThree() {
        return this.radioThree;
    }

    /**
     * Gives the radio button one which associated white pawns for the second player
     * @return the radio button one which associated white pawns for the second player
     */
    public JRadioButton getRadioFour() {
        return this.radioFour;
    }    
}