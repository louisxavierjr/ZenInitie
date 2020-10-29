package model.fileManager;

import model.zenGame.GameplayZen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

/**
 * ReadWriteFile is a class which reads and writes configuration file of Zen
 * games.
 * 
 * @author Godet Louis-Xavier
 */
public class ReadWriteFile {

	/**
	 * The path leading the repertory of saved games
	 */
	private static final String SAVE_DIRECTORY = "../data/save";
	
	/**
	 * The list of save files names
	 */
	public static ArrayList<String> listOfFiles;

	/**
	 * readFile scans configuration files of Zen games
	 * @param indexFile : the index position of the file on the save configuration file list
	 * @return a GameplayZen object which can be used to resume a game saved.
	 */
	public static GameplayZen readFile(int indexFile) {
		GameplayZen ret = null;
		try {
			FileInputStream fileName = new FileInputStream("../data/save/" + listOfFiles.get(indexFile));
			ObjectInputStream in = new ObjectInputStream(fileName);
			try {
				ret = (GameplayZen) in.readObject();
			} catch (ClassNotFoundException c) {
				c.printStackTrace();
			}
			in.close();
		}catch(IOException i){
			i.printStackTrace();
		}
		return ret;
	}

	/**
	 * Saves the configuration (grid, pawns, position,etc..) in the file named by the name of the zen l'initie game.
	 * N.B : This operation doesn't leave the game in progress
	 * @param gameManager : the game to save
	 * @return true if the game has been saved well, false if not
	 */
	public static boolean writeFile(GameplayZen gameManager) {
		boolean saveSuccessful = false;
		try {
			FileOutputStream fileName = new FileOutputStream("../data/save/" + gameManager.getGameName() + ".bin");
            ObjectOutputStream out = new ObjectOutputStream(fileName);
			out.writeObject(gameManager);
			saveSuccessful = true;
            out.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
		return saveSuccessful;
	}

	/**
	 * This method displays all the configuration files in the saved game repertory.
	 * @return the list of saved games
	 */
	public static ArrayList<String> getAllFiles(){
		listOfFiles = new ArrayList<String>();
		File directory = new File(SAVE_DIRECTORY);
		File[] listFiles = directory.listFiles();
		for (File file : listFiles){
			listOfFiles.add(file.getName());
		}
		if (listOfFiles.isEmpty()){
			System.out.println("ReadWriteFile warning : Aucune partie sauvegardées");
		}
		return listOfFiles;
	}
}