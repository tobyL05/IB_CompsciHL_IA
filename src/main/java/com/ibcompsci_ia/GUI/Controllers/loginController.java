package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;

import com.ibcompsci_ia.GUI.Models.loginModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class loginController {

	loginModel model;

	@FXML private Button createAccButton;
	@FXML private Button chooseAcc;

	public loginController(){
		model = new loginModel();
	}

	@FXML
	private void createAcc() throws IOException{
		model.createAccPress();
	}
	
	@FXML
	private void chooseAccPressed() throws IOException{
		model.chooseAccPress();
	}

}
