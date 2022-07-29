package com.ibcompsci_ia.GUI.Models;


import com.ibcompsci_ia.API.recovery_versionAPI;

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

	//scene changes
	
}
