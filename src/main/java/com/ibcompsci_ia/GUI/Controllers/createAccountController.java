package com.ibcompsci_ia.GUI.Controllers;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.Enums.paths;
import com.ibcompsci_ia.GUI.Models.createAccountModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class createAccountController {
	
	@FXML private TextField usrField;
	@FXML private PasswordField pwdField;
	@FXML private Button createAccBtn;
	@FXML private Button backBtn;
	@FXML private Label errorTxt;
	private createAccountModel model;
	private ArrayList<String> usernames;

	public createAccountController(){
		usernames = new ArrayList<>();
		File[] accountDir = (new File(getClass().getResource(paths.accountsPath.toString()).getPath())).listFiles();
		for(File f:accountDir){
			usernames.add(createAccountModel.decryptor(f.getName().split("\\.")[0].getBytes()).split("_")[0]);
		}
		
		model = new createAccountModel();
	}

	@FXML
	private void createAccPress() throws IOException{
		if(usrField.getText().equals("") || pwdField.getText().equals("")){
			errorTxt.setText("Fill in all the fields!");
			errorTxt.setOpacity(1);
		}else if(usernames.contains(usrField.getText())){
			errorTxt.setText("Username is taken");
			errorTxt.setOpacity(1);
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
