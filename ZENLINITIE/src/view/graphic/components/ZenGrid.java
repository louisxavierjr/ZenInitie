package view.graphic.components;

import model.zenGame.SquareGrid;
import model.zenGame.TypeOfPawn;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * ZenGrid draws the zen l'intie grid in the game version graphic
 * @author Godet Louis-Xavier
 */
public class ZenGrid extends JPanel {
    /**
     * The path that lead to brown square image of the grid
     */
    private static final String PATH_SQUARE_BROWN = "../data/images/squareGridBrown.jpg";
    
    /**
     * The path that lead to beige square image of the grid
     */
    private static final String PATH_SQUARE_BEIGE = "../data/images/squareGridBeige.jpg";

    /**
     * The path that lead to black pawn image
     */
    private static final String PATH_BLACK_PAWN = "../data/images/blackPawn.png";

    /**
     * The path that lead to white pawn image
     */
    private static final String PATH_WHITE_PAWN = "../data/images/whitePawn.png";

    /**
     * The path that lead to zen pawn image
     */
    private static final String PATH_ZEN_PAWN = "../data/images/zenPawn.png";

    /**
     * The grid of the game composed by buttons
     */
    private JButton[][] squareGridButtons;

    /**
     * List of the previous possible moves drawn previously on the grid before another new select
     */
    private ArrayList<Integer> previousPossibleMoves;

    /**
     * The coordinates of the selected square in the grid of buttons
     */
    private int[] previousSelectedSquare;
    
    /**
     * The alphabet for coordinates grid
     */
    protected final char[] ALPHABET_ABSCISSA = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K' };

    /**
     * Initializes a grid in a zen l'initie game
     */
    public ZenGrid() {
        super();
        //ini.
        this.previousPossibleMoves = new ArrayList<Integer>();
        //The rows of the grid
        GridLayout grid = new GridLayout(11,1);
        grid.setVgap(-35);
        this.setLayout(grid);
        this.setOpaque(false);
        this.iniGrid();
    }

    /**
     * Initializes the buttons of the grid (without any pawns)
     */
    private void iniGrid(){
        this.squareGridButtons = new JButton[11][11];
        for (int y = 0; y < 11; y++){
            //Creation of the y row
            JPanel row = new JPanel();
            row.setOpaque(false);
            row.setLayout(new FlowLayout());
            for (int x = 0; x < 11; x++){
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(50,50));
                if ((x % 2 == 0 && y % 2 == 0) || (x%2 != 0 && y%2 != 0)){
                    //Brown square
                    button.setIcon(new ImageIcon(PATH_SQUARE_BROWN));
                }else if((x % 2 != 0 && y % 2 == 0) || (x%2 == 0 && y%2 != 0)){
                    //Beige square
                    button.setIcon(new ImageIcon(PATH_SQUARE_BEIGE));
                }
                row.add(button);
                this.squareGridButtons[x][y] = button;
            }
            this.add(row);
        }
    }

    /**
     * Places the pawns on the grid 
     * @param grid : the grid of the game
     */
    public void placePawns(SquareGrid[][] grid){
        this.updateButton();
        for (int x = 0; x < grid.length; x++){
            for (int y = 0; y < grid.length; y++){
                if (!grid[x][y].isOccupied()){
                    if ((x % 2 == 0 && y % 2 == 0) || (x%2 != 0 && y%2 != 0)){
                        this.squareGridButtons[x][y].setIcon(new ImageIcon(PATH_SQUARE_BROWN));
                    }else if((x % 2 != 0 && y % 2 == 0) || (x%2 == 0 && y%2 != 0)){
                        this.squareGridButtons[x][y].setIcon(new ImageIcon(PATH_SQUARE_BEIGE));
                    }
                }else{  
                    JPanel pawn = new JPanel();
                    pawn.setLayout(new FlowLayout());
                    pawn.setOpaque(false);
                    if (grid[x][y].getPawnPresent().getType() == TypeOfPawn.BLACK){
                        JLabel icon = new JLabel(new ImageIcon(PATH_BLACK_PAWN));
                        icon.setVerticalAlignment(SwingConstants.TOP);
                        icon.setHorizontalAlignment(SwingConstants.CENTER);
                        pawn.add(icon);
                    }else if (grid[x][y].getPawnPresent().getType() == TypeOfPawn.WHITE){
                        JLabel icon = new JLabel(new ImageIcon(PATH_WHITE_PAWN));
                        icon.setVerticalAlignment(SwingConstants.TOP);
                        icon.setHorizontalAlignment(SwingConstants.CENTER);
                        pawn.add(icon);
                    }else if (grid[x][y].getPawnPresent().getType() == TypeOfPawn.ZEN){
                        JLabel icon = new JLabel(new ImageIcon(PATH_ZEN_PAWN));
                        icon.setVerticalAlignment(SwingConstants.TOP);
                        icon.setHorizontalAlignment(SwingConstants.CENTER);
                        pawn.add(icon);
                    }
                    FlowLayout layoutButton = new FlowLayout();
                    layoutButton.setVgap(-2);
                    this.squareGridButtons[x][y].setLayout(layoutButton);
                    this.squareGridButtons[x][y].add(pawn);
                }
            }
        }
    }
    
    /**
     * Sets the listener for buttons in the grid
     * @param a : the listener to put for buttons
     */
	public void setActionListener(ActionListener a) {
        for (int x = 0; x < this.squareGridButtons.length; x++){
            for (int y = 0; y < this.squareGridButtons.length; y++){
                this.squareGridButtons[x][y].setActionCommand("GRID"+this.ALPHABET_ABSCISSA[x]+""+y);
                this.squareGridButtons[x][y].addActionListener(a);
            }
        }
	}
    
    /**
     * Update the buttons graphically
     */
    public void updateButton(){
        for (int x = 0; x < this.squareGridButtons.length; x++){
            for (int y = 0; y < this.squareGridButtons.length; y++){
                this.squareGridButtons[x][y].removeAll();
                this.squareGridButtons[x][y].setBorder(BorderFactory.createLineBorder(Color.WHITE,0));
                this.squareGridButtons[x][y].repaint();
            }
        }
    }

    /**
     * Clear the border of previous list of moves
     */
    public void clearListOfMoves(){
        if(this.previousPossibleMoves != null){
            for (int i = 0 ; i < this.previousPossibleMoves.size(); i = i + 2){
                this.squareGridButtons[this.previousPossibleMoves.get(i)][this.previousPossibleMoves.get(i+1)].setBorder(BorderFactory.createLineBorder(Color.WHITE,0));  
            }
            this.previousPossibleMoves.clear();
        }
    }

    /**
     * Clear the broder of previous selected pawn
     */
    public void clearSelect(){
        if (this.previousSelectedSquare != null){
            this.squareGridButtons[this.previousSelectedSquare[0]][this.previousSelectedSquare[1]].setBorder(BorderFactory.createLineBorder(Color.WHITE,0));  
        }
    }

    /**
     * Draws the border of the selected square in the grid
     * @param selected : the coordinates of the selected square
     */
    public void drawSelect(int[] selected){
        //METTRE DES COORDONNES
        this.clearSelect();
        if (selected != null){
            int x = selected[0];
            int y = selected[1];
            this.previousSelectedSquare = new int[]{x,y};
            this.squareGridButtons[x][y].setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
            this.squareGridButtons[x][y].revalidate();
        }
    }

    /**
     * Draws the border of the possible moves in the grid according to the selected square
     * @param listOfMoves : the coordinates of the possible moves
     */
	public void drawListOfMoves(ArrayList<SquareGrid> listOfMoves) {
        if (listOfMoves != null){
            this.clearListOfMoves();
            for (SquareGrid s : listOfMoves){
                int[] coordinates = s.getCoordinatesSquare();
                int x = coordinates[0];
                int y = coordinates[1];
                if (s.isOccupied()){
                    //elimination
                    this.squareGridButtons[x][y].setBorder(BorderFactory.createLineBorder(new Color(175,38,11,255),3));
                }else{
                    this.squareGridButtons[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                }
                this.previousPossibleMoves.add(x);
                this.previousPossibleMoves.add(y);
            }
        }
    }
}
