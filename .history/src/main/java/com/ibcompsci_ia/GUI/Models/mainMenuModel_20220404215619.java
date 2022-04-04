package com.ibcompsci_ia.GUI.Models;

import java.util.prefs.Preferences;

public class mainMenuModel {
	
	private final Preferences prefs;
	
	public mainMenuModel(){
		prefs = Preferences.userNodeForPackage(com..models.loginModel.class);
	}

	public String[] retrieveDetails(){
		return loginModel.getDetails().split("_");
	}

	public String getPropertiesFilePath(){
		return prefs.get(loginModel.getDetails(),"default");
	}
}
