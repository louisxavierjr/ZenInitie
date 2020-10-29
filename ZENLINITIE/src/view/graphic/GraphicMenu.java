package view.graphic;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.graphic.components.Background;

/**
 * Graphic menu draws and displays the menu of Zen initie game
 * @author Godet Louis-Xavier
 */
public class GraphicMenu extends JPanel {
    /**
     * The path leading to the background menu image
     */
    private static final String PATH_BACKGROUND_MENU = "../data/images/backgroundMenu.jpg";

    /**
     * The path leading to the background of buttons
     */
    private static final String PATH_BACKGROUND_BUTTON = "../data/images/backgroundButton.jpg";

    /**
     * The button to create a new zen initie game
     */
    private JButton newGame;

    /**
     * The button to load a previous zen initie game
     */
    private JButton loadGame;

    /**
     * The button to open the rules of the game
     */
    private JButton rules;

    /**
     * The button to leave the software
     */
    private JButton leaveSoftware;

    /**
     * Initializes the menu of the software game
     */
    public GraphicMenu() {
        this.setLayout(new BorderLayout());
        this.iniComponent();
    }

    /**
     * Initializes all components of the menu
     */
    private void iniComponent(){
        //Main panel
        JPanel background = new Background(PATH_BACKGROUND_MENU);
        background.setLayout(new BorderLayout());
        background.setPreferredSize(new Dimension(1200,800));
        
        //Buttons
        JPanel groupButton = new JPanel();
        //layout of button's panel
        FlowLayout layoutButton = new FlowLayout();
        layoutButton.setHgap(80);
        groupButton.setLayout(layoutButton);
        //background and size
        groupButton.setOpaque(false);
        groupButton.setPreferredSize(new Dimension(1200,100));

        //1 - new game button
        this.newGame = new JButton();
        this.iniButtons(this.newGame, "Nouvelle partie");
        groupButton.add(this.newGame);

        //2 - load game button
        this.loadGame = new JButton();
        this.iniButtons(this.loadGame, "Charger une partie");
        groupButton.add(this.loadGame);
        
        //3 - rules button
        this.rules = new JButton();
        this.iniButtons(this.rules, "Règles du jeu");
        groupButton.add(this.rules);

        //4 - leave software button
        this.leaveSoftware = new JButton();
        this.iniButtons(this.leaveSoftware,"Quitter le jeu");
        groupButton.add(this.leaveSoftware);
        
        //final add
        background.add(groupButton, BorderLayout.SOUTH);
        this.add(background, BorderLayout.CENTER);
    }

    /**
     * Uniforms the shape of a button in the menu
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
        button.setPreferredSize(new Dimension(210,80));
        button.add(panelButton, BorderLayout.CENTER);
        button.setIcon(new ImageIcon(PATH_BACKGROUND_BUTTON));
    }

    /**
     * Sets the listener for button's menu
     * @param a : the listener to set for buttons
     */
    public void setActionListener(ActionListener a){
        this.newGame.setActionCommand("MENU.NEWGAME");
        this.newGame.addActionListener(a);
        
        this.loadGame.setActionCommand("MENU.LOADGAME");
        this.loadGame.addActionListener(a);

        this.rules.setActionCommand("RULES");
        this.rules.addActionListener(a);

        this.leaveSoftware.setActionCommand("MENU.LEAVESOFTWARE");
        this.leaveSoftware.addActionListener(a);
    }
}