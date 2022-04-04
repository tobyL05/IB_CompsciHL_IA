package com.ibcompsci_ia.GUI.Models;

import java.io.IOException;

import com.example.Main;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.prefs.Preferences;

public class loginModel {
	private final StringProperty user;
	private final StringProperty pwd;
	private final String def = "file_name";
	private static String details;
	Preferences prefs;

	public loginModel(){
		this.prefs = Preferences.userNodeForPackage(com.example.models.loginModel.class);
		this.user = new SimpleStringProperty("");
		this.pwd = new SimpleStringProperty("");
	}

	public void setDetails(String usr,String pass){
		details = usr + "_" + pass;
		prefs.put(details,def);
	}

	public static String getDetails(){
		return details;
	}

	public String getUser(){
		return user.get();
	}

	public StringProperty UserProperty(){
		return user;
	}

	public String getPwd(){
		return pwd.get();
	}

	public StringProperty PwdProperty(){
		return pwd;
	}

	public void loginPressed() throws IOException{
		Main.setRoot("mainMenu");
	}

	public void createAccPress() throws IOException{
		Main.setRoot("mainMenu");
	}

	public void chooseAccPress() throws IOException{
		Main.setRoot("chooseAccount");
	}

}
