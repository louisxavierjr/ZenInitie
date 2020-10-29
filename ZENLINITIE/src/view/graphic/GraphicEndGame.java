package view.graphic;

import view.graphic.components.Background;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * GraphicEndGame is a panel displaying the name of the winner
 * @author Godet Louis-Xavier
 */
public class GraphicEndGame extends JPanel{
   /**
     * The path leading to the background of the endgame windows
     */
    private static final String PATH_BACKGROUND_MENU = "../data/images/backgroundZen.jpg";

    /**
     * The path leading to the background for the button
     */
    private static final String PATH_BACKGROUND_BUTTON = "../data/images/backgroundButton.jpg";

    /**
     * The return to menu button 
     */
    private JButton returnButton;

    /**
     * The winner of the game to display
     */
    private String winnerName;

    /**
     * Initializes the pannel displaying the name of the winner
     */
    public GraphicEndGame(){
        super();
        this.iniComponents();
    }

    /**
     * Initializes all components
     */
    public void iniComponents(){
        //1-main layout and panels
        this.setLayout(new BorderLayout());
        Background background = new Background(PATH_BACKGROUND_MENU);
        background.setLayout(new BorderLayout());

        //2- the name of the winner - center
        JLabel state = new JLabel(this.winnerName);
        state.setForeground(Color.WHITE);
        state.setFont(new Font(Font.DIALOG,Font.BOLD,70));
        state.setHorizontalAlignment(SwingConstants.CENTER);

        //3- bottom with one button
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.setLayout(new FlowLayout());
        this.returnButton = new JButton();
        this.iniButtons(this.returnButton, "Retour au menu");
        bottom.add(this.returnButton);
        background.add(bottom,BorderLayout.SOUTH);

        //4-final add
        background.add(state, BorderLayout.CENTER);
        background.add(bottom,BorderLayout.SOUTH);
        this.add(background, BorderLayout.CENTER);

    }

    /**
     * Uniforms the shape of a button in the new game window
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
     * Sets action listener for return button
     * @param a : the listener to add
     */
    public void setActionListener(ActionListener a){
        //1 - return to menu
        this.returnButton.setActionCommand("RETURN");
        this.returnButton.addActionListener(a);
    }

    /**
     * Sets the end of the game statement
     * @param winnerName : the name of the winner
     */
    public void setEndGameState(String winnerName){
        this.winnerName = winnerName;
        //1- translate
        this.iniComponents();
    }
}