package view.graphic;

import view.graphic.components.Background;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

/**
 * GraphicLoad draws the load menu windows
 * @author Godet Louis-Xavier
 */
public class GraphicLoad extends JPanel{
    /**
     * The path leading to the background of the load menu
     */
    private static final String PATH_BACKGROUND_ZEN = "../data/images/backgroundZen.jpg";

    /**
     * The path leading to the background for each buttons
     */
    private static final String PATH_BACKGROUND_BUTTON = "../data/images/backgroundButton.jpg";

    /**
     * The list of the radio buttons corresponding to save files
     */
    private ArrayList<JRadioButton> groupButton;

    /**
     * The restart button 
     */
    private JButton restartButton;

    /**
     * The return to the menu button
     */
    private JButton returnButton;

    /**
     * Initializes the windows displaying the saved games
     */
    public GraphicLoad() {
        super();
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
    }

    /**
     * Initializes components
     * @param savedGames : the saved games list
     */
    public void iniComponent(ArrayList<String> savedGames){
        //main panel
        Background background = new Background(PATH_BACKGROUND_ZEN);
        background.setLayout(new BorderLayout());
        background.setOpaque(false);

        //title
        JLabel label = new JLabel("Charger une partie");
        label.setOpaque(false);
        label.setForeground(Color.WHITE);
        label.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        background.add(label, BorderLayout.NORTH);


        //Jlist
        //group radio buttons
        this.groupButton = new ArrayList<JRadioButton>();
        ButtonGroup groupRadio = new ButtonGroup();
        //center panel
        JPanel center = new JPanel();
        center.setLayout(new FlowLayout());
        center.setOpaque(false);
        //panel
        JPanel interlocking = new JPanel();
        interlocking.setOpaque(false);
        interlocking.setLayout(new BoxLayout(interlocking,BoxLayout.Y_AXIS));
        for (int i = 0; i < savedGames.size(); i++){
            JRadioButton buttonRadio = new JRadioButton(savedGames.get(i));
            buttonRadio.setOpaque(false);
            buttonRadio.setForeground(Color.WHITE);
            buttonRadio.setFont(new Font(Font.DIALOG, Font.BOLD,18));
            buttonRadio.setPreferredSize(new Dimension(500,200));
            groupRadio.add(buttonRadio);
            groupButton.add(buttonRadio);
            interlocking.add(buttonRadio);
        }
        center.add(interlocking);
        background.add(center, BorderLayout.CENTER);

        //JButton
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        bottom.setOpaque(false);

        this.restartButton = new JButton();
        this.iniButtons(this.restartButton, "Relancer la partie");
        bottom.add(this.restartButton);

        this.returnButton = new JButton();
        this.iniButtons(this.returnButton, "Retour au menu");
        bottom.add(this.returnButton);
        background.add(bottom,BorderLayout.SOUTH);

        this.add(background, BorderLayout.CENTER);
    }

    /**
     * Uniforms the shape of a button in the load menu
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
     * Sets the listener for button's load menu
     * @param a : the listener to set for buttons
     */
    public void setActionListener(ActionListener a) {
        this.restartButton.setActionCommand("LOADGAME.RESTART");
        this.restartButton.addActionListener(a);
        this.returnButton.setActionCommand("RETURN");
        this.returnButton.addActionListener(a);
    }

    /**
     * Sets the save game list
     * @param gameSavedList save game list
     */
    public void setSaveGamesList(ArrayList<String> gameSavedList){
        if (gameSavedList != null){
            this.iniComponent(gameSavedList);
        }
    }
    
    /**
     * Gives the list of radio buttons
     * @return the list of radio buttons
     */
    public ArrayList<JRadioButton> getGroupButton() {
        return this.groupButton;
    }
}