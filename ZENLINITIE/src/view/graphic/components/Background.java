package view.graphic.components;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JPanel;

import java.awt.Image;
import java.awt.Graphics;

/**
 * This class sets a background image for the Zen initie game graphical version
 * @author Godet Louis-Xavier
 */
public class Background extends JPanel {
    
    /**
     * The image to sets as a background
     */
    private Image backgroundImage;

    /**
     * Initializes a background
     * @param fileName : the path of the image
     */
    public Background(String fileName) {
        super();
        try {
            this.backgroundImage = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.err.println("L'image d'arriere-plab n'a pas été chargé ");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0, this);
    }
}