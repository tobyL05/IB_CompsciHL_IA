package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;

import com.
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginController {

	loginModel model;

	@FXML private TextField user;
	@FXML private PasswordField pwd;
	@FXML private Button loginButton;
	@FXML private Button createAccButton;
	@FXML private Button chooseAcc;

	public loginController(){
		model = new loginModel();
	}

	@FXML
	private void login() throws IOException{
		model.setDetails(user.getText(),pwd.getText());
		model.loginPressed();
	}

	@FXML
	private void createAcc(){
		try {
			model.createAccPress();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void chooseAccPressed() throws IOException{
		model.chooseAccPress();
	}
}
