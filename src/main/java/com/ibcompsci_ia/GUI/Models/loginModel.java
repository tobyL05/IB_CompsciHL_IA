package com.ibcompsci_ia.GUI.Models;

import java.io.IOException;

import com.ibcompsci_ia.Main;

public class loginModel {

	public loginModel(){
	}

	public void createAccPress() throws IOException{
		Main.setRoot("createAccount");
	}

	public void chooseAccPress() throws IOException{
		Main.setRoot("chooseAccount");
	}

}
