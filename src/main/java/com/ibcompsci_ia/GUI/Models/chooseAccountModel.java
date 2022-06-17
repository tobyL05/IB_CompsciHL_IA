package com.ibcompsci_ia.GUI.Models;

import com.ibcompsci_ia.GUI.Models.createAccountModel;
import java.io.File;
import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;

public class chooseAccountModel {

	private File[] accountDir;

	public chooseAccountModel(){
		accountDir = (new File(getClass().getResource("/com/ibcompsci_ia/Accounts").getPath())).listFiles();
	}

	public void accChosen(File f){
        System.out.println(new String(createAccountModel.decryptor(f.getName().split("\\.")[0].getBytes()).split("_")[0]) + " chosen");
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Login");
        dialog.setHeaderText("Login");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        PasswordField pwd = new PasswordField();
        HBox container = new HBox();
		container.setAlignment(Pos.CENTER_LEFT);
		container.setSpacing(10);
		container.getChildren().addAll(new Label("Enter the password for " + new String(createAccountModel.decryptor(f.getName().split("\\.")[0].getBytes()).split("_")[0])),pwd);
		dialog.getDialogPane().setContent(container);
		dialog.setResultConverter(dialogButton -> {
			if(dialogButton == ButtonType.OK){
				return pwd.getText();
			}
			return null;
		});

		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()){
			System.out.println(result.get());
		}
	}

	public boolean checkAccountsFound(){
		if(accountDir.length == 1){
			return false;
		}
		return true;
	}

	public File[] getAccountDir(){
		return accountDir;
	}

	public static void main(String[] args){
		chooseAccountModel cam = new chooseAccountModel();
	}
}
