package com.ibcompsci_ia.GUI.Models;


import java.io.File;
import java.io.IOException;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.API.recovery_versionAPI;
import com.ibcompsci_ia.users.Session;

public class mainMenuModel {

	recovery_versionAPI recverAPI;
	public boolean internet;
	public String verse;
	private final String notesDirPath = System.getenv("APPDATA") + "/BilingualBible/notes/";
	
	public mainMenuModel(){
		recverAPI = new recovery_versionAPI();
		if(!recverAPI.connected()){
			verse = "";	
		}else{
			verse = recverAPI.getMainText();
		}
	}

	public void darkMode(){
		System.out.println("dark mode");
	}

	public void openBible() throws IOException{
		Main.setRoot("biblePage");
		System.out.println("Bible opened");
	}

	public void openSearch(){
		System.out.println("Open searched");
	}

	public void openBookmarks() throws IOException{
		System.out.println("Open bookmarks");
		Main.setRoot("bookmarksPage");
	}

	public void openNotes() throws IOException{ //https://codingpointer.com/java-tutorial/open-external-app
		System.out.println("Open notes"); //open notepad
		String notesFile= "";
		File[] notesDir = new File(notesDirPath).listFiles();
		boolean fileExists = false;
		for(File f:notesDir){
			if(f.getName().equals(Session.getFileName())){
				fileExists = true;
				notesFile = notesDirPath + f.getName();
				break;
			}
		}
		if(!fileExists){
			notesFile = notesDirPath + Session.getFileName() + ".txt";
			File text = new File(notesFile);
			text.createNewFile();
		}
		Runtime.getRuntime().exec("notepad " + notesFile);
	}
	
	public void logout() throws IOException{
        Session.saveObj();
        Main.setRoot("loginPage");
	}	

	//scene changes
	
}
