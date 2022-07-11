package com.ibcompsci_ia.GUI.Controllers;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.GUI.Models.createAccountModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class createAccountController {
	
	@FXML private TextField usrField;
	@FXML private PasswordField pwdField;
	@FXML private Button createAccBtn;
	@FXML private Button backBtn;
	@FXML private Label errorTxt;
	private createAccountModel model;

	public createAccountController(){
		model = new createAccountModel();
	}

	@FXML
	private void createAccPress() throws IOException{
		if(usrField.getText().equals("") || pwdField.getText().equals("")){
			errorTxt.setOpacity(1);
		}else if(false){
			//check duplicate usernames
		}else{
			model.createAccount(usrField.getText(), pwdField.getText());
		}
	}
	
	@FXML
	private void backtoStartpage() throws IOException{
		Main.setRoot("loginPage");
	}
}
