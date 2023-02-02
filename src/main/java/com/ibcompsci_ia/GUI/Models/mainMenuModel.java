package com.ibcompsci_ia.GUI.Models;


import java.io.File;
import java.io.IOException;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.API.recovery_versionAPI;
import com.ibcompsci_ia.Enums.paths;
import com.ibcompsci_ia.users.Session;

public class mainMenuModel {

	recovery_versionAPI recverAPI;
	public boolean internet;
	public String verse;
	private final String notesPath = getClass().getResource(paths.resourcePath.toString() + "notes").getPath();
	
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
		File notesDir = new File(notesPath);
		String notesFilePath = "";
		boolean fileExists = false;
		Runtime runtime = Runtime.getRuntime();
		for(File f:notesDir.listFiles()){
			if(createAccountModel.decryptor(f.getName().getBytes()).contains(Session.user.getCreds())){
				//open the file
				notesFilePath = f.getPath();
				fileExists = true;
				break;
			}
		}
		if(!fileExists){
			notesFilePath = notesDir + "\\"+ new String(createAccountModel.encryptor(Session.user.getCreds())) + ".txt";
			File text = new File(notesFilePath);
			text.createNewFile();
		}
		runtime.exec("notepad " + notesFilePath);
	}
	
	public void logout() throws IOException{
        Session.saveObj();
        Main.setRoot("loginPage");
	}	

	//scene changes
	
}
