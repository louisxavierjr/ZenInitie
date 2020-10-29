package controller;

import model.fileManager.ReadWriteFile;
import model.zenGame.GameState;
import model.zenGame.GameplayZen;
import model.zenGame.ModelManager;
import model.zenGame.OpponentType;
import model.zenGame.SquareGrid;
import model.zenGame.TypeOfPawn;
import model.zenGame.rules.GameRules;

import view.console.ConsoleInGame;
import view.console.ConsoleMenu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ConsoleListener is a class which links the manager of the game and the display of all game elements (menu, grid, informations, etc..)
 * @author Godet Louis-Xavier
 */
public class ConsoleListener{

    /**
     * The scanner for user's inputs
     */
    private Scanner askUser;

    /**
     * The manager of software data
     */
    private ModelManager modelManager;

    /**
     * The graphical manager of the game
     */
    private ConsoleMenu viewManager;

    /**
     * Initializes a listener that ask the user to input something with the keyboard which will be manage by the model
     * @param model : the data manager of the game
     * @param view : the view manager of the game
     */
    public ConsoleListener(ModelManager model, ConsoleMenu view){
        if (model != null && view != null){
            this.askUser = new Scanner(System.in);
            this.modelManager = model;
            this.viewManager = view;
        }
        this.loopMenu();
    }

    /**
     * Loops the menu where the user is questioned to choose in option (new game, load game, open rules, leave the game)
     */
    public void loopMenu(){
        boolean end = false; //true if leave option is chosen
        int answer; //user's answer;
        while (!end) {
            //menu to display
            viewManager.displayMenu();
            try{
                System.out.print("Saisir le numéro correspondant à votre choix : ");
                answer = askUser.nextInt();
                if (answer == 1) {
                    //create a new game
                    this.loopNewGame();
                    if (this.modelManager.getGame() != null){
                        this.loopPlay();
                    }
                } else if (answer == 2){
                    //load a previous game
                    this.loopLoad();
                } else if (answer == 3) {
                    // Open the rules
                    GameRules.openRulesFile();
                } else if (answer == 4) {
                    //ask the user if he really wants to leave the software
                    System.out.print("Voulez-vous vraiment quitter le jeu ? Confirmer en saisissant '4' ou '0' pour revenir en arrière : ");
                    if (askUser.nextInt() == 4) {
                        end = true;
                    }
                } else {
                    System.out.print("Chiffre saisie invalide.");
                }
            }catch(InputMismatchException i){
                end = true;
                System.out.println("ERREUR ! Vous avez saisis une lettre au lieu d'un chiffre, veuillez relancer le jeu");
            }
        }
        askUser.close();
    }

    /**
     * loop the menu of new game
     */
    private void loopNewGame() {
        boolean end = false; //true if leaves's option is chosen
        this.viewManager.displayNewGame();
        while (!end){
            //Nom de la partie
            System.out.print("NOM DE LA PARTIE : ");
            String gameName = this.askUser.next();

            //Adversaire
            OpponentType opponent = OpponentType.COMPUTER;
            System.out.print("JOUER CONTRE (saisir le numéro correspondant) -> 1. Ordinateur    2. Deuxième joueur : ");
            boolean valid = false;
            int numberOpponent = this.askUser.nextInt();
            while (!valid){
                try{
                    if (numberOpponent == 1){
                        valid = true;
                    }else if (numberOpponent == 2){
                        opponent = OpponentType.HUMAN;
                        valid = true;
                    }else{
                        System.out.print("Erreur de saisie, veuillez indiquer un numéro valide correspondant au choix de l'adversaire : ");
                        numberOpponent = this.askUser.nextInt();
                    }
                }catch(InputMismatchException i){
                    end = true;
                    System.out.println("Vous n'avez pas saisie un nombre/chiffre mais une lettre/un chaine de caractères");
                }
            }
        
            //joueurs
            //nom
            System.out.print("NOM DU JOUEUR 1 : ");
            String firstName = this.askUser.next();
            System.out.print("NOM DE JOUEUR 2 : ");
            String secondName = this.askUser.next();
            
            //type de pion
            TypeOfPawn pawnFirstPlayer = TypeOfPawn.BLACK; //Noir par defaut pour le premier joueur
            TypeOfPawn pawnSecondPlayer = TypeOfPawn.WHITE;
            System.out.print("JOUEUR 1, CHOISSISEZ LA COULEUR DE VOTRE PION (saisir le numéro correspondant) -> 1. Noir  2. Blanc : ");
            valid = false;
            int numberType = this.askUser.nextInt();
            while (!valid){
                try{
                    if (numberType == 1){
                        valid = true;
                    }else if (numberType == 2){
                        pawnFirstPlayer = TypeOfPawn.WHITE;
                        pawnSecondPlayer = TypeOfPawn.BLACK;
                        valid = true;
                    }else if (numberOpponent != 1 && numberOpponent != 2){
                        System.out.println("Erreur de saisie, veuillez indiquer un numéro valide correspondant au choix de la couleur du pion");
                        numberType = this.askUser.nextInt();
                    }
                }catch (InputMismatchException i){
                    end = true;
                    System.out.println("Vous n'avez pas saisie un nombre/chiffre mais une lettre/un chaine de caractères");
                }
            }

            //confirmation
            if (!end){
                System.out.print("Confirmez-vous ces paramètres ? Saisir JOUER pour lancer la partie, CHANGER pour ressaisir les paramètres, ou QUITTER pour revenir au menu : ");
                String confirm = this.askUser.next();
                valid = false;
                while (!valid){
                    if (confirm.equals("JOUER")){
                        valid = true;
                        try{
                            this.modelManager.newGame(gameName, opponent, firstName, secondName, pawnFirstPlayer, pawnSecondPlayer);
                            end = true;
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else if (confirm.equals("CHANGER")){
                        valid = true;
                    }else if (confirm.equals("QUITTER")){
                        valid = true;
                        end = true;
                    }else{
                        System.out.println("Erreur de saisie, veuillez indiquer une option valide parmis les suivantes : JOUER, CHANGER, QUITTER");
                        confirm = this.askUser.next();
                    }
                }
            }
        }
    }

    /**
     * Loop the gameplay of zen l'initie game
     */
    private void loopPlay() {
        //The game to play
        GameplayZen gameToPlay = this.modelManager.getGame();
        //the displayer of the game
        ConsoleInGame displayGame = new ConsoleInGame(gameToPlay.getGrid());
        int choice = 0;
        while (choice != 4 && gameToPlay.allPawnTouch() == GameState.LOOP){
            boolean turnPlay = false; //can be true only if the playeer has move a pawn of his color
            displayGame.displayGrid();
            displayGame.displayInformationGame(gameToPlay.getGameName(),gameToPlay.getFirstPlayer(), gameToPlay.getSecondPlayer(), gameToPlay.getCurrentPlayer().getPlayerName());
            if (gameToPlay.getOpponentType() == OpponentType.COMPUTER && gameToPlay.getCurrentPlayer() == gameToPlay.getSecondPlayer()){
                gameToPlay.computerPlay();
                turnPlay = true;
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                displayGame.displayAsks();
                boolean valid = false; //until the player inputs wrong numbers, loop asking
			    while (!valid){
				    try{
                        System.out.print("Saisissez le numéro correspondant à votre choix : ");
                        choice = this.askUser.nextInt();
                        if (choice == 1){
                            valid = true;
                            boolean validInput = false;
                            String answer;
                            gameToPlay.setZenEnnemy("E");
                            if(gameToPlay.getZenPawn() != null){
                                System.out.print("Considérez-vous le pion Zen comme ami ou ennemi ? Saisissez la lettre A pour ami, E pour ennemi : ");
                                while(!validInput){
                                    answer = this.askUser.next();
                                    if (answer.equals("A") || answer.equals("E")){
                                        validInput = true;
                                        gameToPlay.setZenEnnemy(answer);
                                    }else{
                                        System.out.println("Mauvaise saisie");
                                    }
                                }
                            }
                            validInput = false;
                            while(!validInput){
                                System.out.print("Saissisez les coordonnées d'un pion de votre couleur que vous souhaitez sélectionner (LETTRECHIFFRE comme par exemple A1) : ");
                                answer = this.askUser.next();
                                int [] select = gameToPlay.getCurrentPlayer().selectPawn(gameToPlay.getDigitCoordinates(answer));
                                if (select != null){
                                    gameToPlay.selectSquare(select);
                                    ArrayList<SquareGrid> listOfMoves = gameToPlay.calculMove();
                                    displayGame.displayListOfMoves(listOfMoves);
                                    System.out.print("Quel déplacement choisir ? Saisissez les coordonnées sous la forme LETTRECHIFFRE(ex : A1) : ");
                                    while(!validInput){
                                        answer = this.askUser.next();
                                        SquareGrid toMove = gameToPlay.getCurrentPlayer().selectMove(listOfMoves, gameToPlay.getDigitCoordinates(answer));
                                        if (toMove != null){
                                            validInput = true;
                                            gameToPlay.makeMove(toMove);
                                            turnPlay = true;
                                        }
                                    }
                                }else{
                                    System.out.println("Mauvaise saisie ! Veuillez recommencer");
                                }
                            }
                        }else if (choice == 2){
                            if(ReadWriteFile.writeFile(gameToPlay)){
                                System.out.println("Partie sauvegardée");
                            }else{
                                System.out.println("erreur : partie non sauvegardée");
                            }
                            valid = true;
                        }else if (choice == 3){
                            GameRules.openRulesFile();
                            valid = true;
                        }else if (choice == 4){
                            valid = true;
                            System.out.print("Voulez-vous vraiment retourner à l'écran titre ? les données non sauvegardées seront perdues (saisir 4 pour confirmer ou 0 pour annuler) : ");
						    choice = this.askUser.nextInt(); //if 4 leave the main loop, if 0 do another loop
                        }else{
                            System.out.println("Vous n'avez pas saisie un bon numéro ! veuillez réessayer : ");
                        }
                    }catch (InputMismatchException i){
                        valid = true;
					    choice = 4;
                        i.printStackTrace();
                    }
                }
            }
            if (turnPlay){
                gameToPlay.changeCurrentPlayer();
            }
        }
        if (choice != 4 && gameToPlay.allPawnTouch() != GameState.LOOP){
            displayGame.displayGrid();
            displayGame.displayInformationGame(gameToPlay.getGameName(), gameToPlay.getFirstPlayer(), gameToPlay.getSecondPlayer(), gameToPlay.getCurrentPlayer().getPlayerName());
            displayGame.displayWin(gameToPlay.allPawnTouch());
        }
    }

    /**
     * Loop to load menu
     */
    public void loopLoad(){
        boolean end = false; //end of loop
        //display
        this.viewManager.displayLoad();
        ArrayList<String> filesList = ReadWriteFile.getAllFiles();
        this.viewManager.displaySaveFiles(filesList);

        if(filesList.isEmpty()){
            end = true; //don't ask the user if the list of saved file is empty
        }

        while (!end){
            //ask
            System.out.print("Saisissez le numéro de la partie de la sauvegarde que vous souhaitez charger : ");
            int choice = this.askUser.nextInt();
            try{
                if (choice >= 0 &&  choice < filesList.size()){
                    System.out.print("Confirmez-vous le choix de la partie à charger ? Saisir JOUER pour relancer la partie, CHANGER pour modifier votre sélection, ou QUITTER pour revenir au menu : ");
                    String confirm = this.askUser.next();
                    boolean valid = false;
                    while (!valid){
                        if (confirm.equals("JOUER")){
                            valid = true;
                            this.modelManager.load(choice);
                            this.loopPlay();
                            end = true;
                        }else if (confirm.equals("CHANGER")){
                            valid = true;
                        }else if (confirm.equals("QUITTER")){
                            valid = true;
                            end = true;
                        }else{
                            System.out.println("Erreur de saisie, veuillez indiquer une option valide parmis les suivantes : JOUER, CHANGER, QUITTER");
                            confirm = this.askUser.next();
                        }
                    }
                }else{
                    System.out.println("Erreur de saisie ! Veuillez saisir le chiffre correspondant à la partie que vous souhaitez charger");
                }
            }catch (InputMismatchException i){
                end = true;
                System.out.println("Vous n'avez pas saisie un nombre/chiffre mais une lettre/un chaine de caractères");
            }
        }
    }
}