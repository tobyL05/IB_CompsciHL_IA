package com.ibcompsci_ia.GUI.Models;


import java.io.IOException;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.API.recovery_versionAPI;
import com.ibcompsci_ia.users.Session;

public class mainMenuModel {

	recovery_versionAPI recverAPI;
	public boolean internet;
	public String verse;
	
	public mainMenuModel(){
		recverAPI = new recovery_versionAPI();
		if(!recverAPI.connected()){
			verse = "";	
		}else{
			verse = recverAPI.getMainText();
		}
	}

	public void darkMode(){
		
	}

	public void openBible(){
		System.out.println("Bible opened");
	}

	public void openSearch(){
		System.out.println("Open searched");
	}

	public void openBookmarks(){
		System.out.println("Open bookmarks");
	}

	public void openNotes(){
		System.out.println("Open notes");
	}
	
	public void logout() throws IOException{
        Session.saveObj();
        Main.setRoot("loginPage");
	}	

	//scene changes
	
}
