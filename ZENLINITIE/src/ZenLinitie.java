import model.zenGame.ModelManager;

import view.console.ConsoleMenu;
import view.graphic.GraphicManager;

import controller.ConsoleListener;
import controller.GraphicListener;

/**
 * ZenLinitie launchs the Zen L'initié game, an old game created in 1997 There
 * are two interfaces versions : a console version and a graphic one. The game
 * can be play against a second human or can be play against a computer
 * 
 * @author Godet Louis-Xavier
 */
public class ZenLinitie {
   public static void main(String[] args) {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            // System of enconding caracters
            System.setProperty("file.encoding", "UTF-8");

            // GAME VERSION CHOICE
            if (args.length > 0) {
               if (args[0].equals("CONSOLE")) {
                  ModelManager model = new ModelManager();
                  ConsoleMenu view = new ConsoleMenu();
                  new ConsoleListener(model, view);
               } else if (args[0].equals("GRAPHIQUE")) {
                  ModelManager model = new ModelManager();
                  GraphicManager view = new GraphicManager();
                  new GraphicListener(model, view);
                  view.setVisible(true);
               }else{
                  System.out.println("Zen l'initie : Vous n'avez pas saisis un bon argument. Pour en savoir plus, veuillez lire attentivement le manuel utilisateur");  
               }
            }
         }
      });
   }
}