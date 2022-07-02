package com.ibcompsci_ia.GUI.Models;


import com.ibcompsci_ia.API.recovery_versionAPI;

public class mainMenuModel {

	recovery_versionAPI recverAPI;
	public boolean internet;
	public String verse;
	
	public mainMenuModel(){
		recverAPI = new recovery_versionAPI();
		
	}

	//scene changes
	
	//random verse
	public String getVerse(){
		return recverAPI.getMainText();
	}
}
