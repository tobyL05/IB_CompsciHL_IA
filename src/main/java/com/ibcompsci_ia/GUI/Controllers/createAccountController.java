package com.ibcompsci_ia.GUI.Controllers;

import com.ibcompsci_ia.GUI.Models.createAccountModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.Properties;

public class createAccountController {
	
	@FXML private TextField usrField;
	@FXML private PasswordField pwdField;
	@FXML private Button createAccBtn;
	private createAccountModel model;

	public createAccountController(){
		model = new createAccountModel();
	}

	@FXML
	private void createAccPress(){
		;
	}
}
