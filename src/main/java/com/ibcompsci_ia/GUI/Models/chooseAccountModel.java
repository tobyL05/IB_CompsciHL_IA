package com.ibcompsci_ia.GUI.Models;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;

import com.ibcompsci_ia.Enums.paths;
import com.ibcompsci_ia.users.Session;
import com.ibcompsci_ia.users.User;

import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;

public class chooseAccountModel {

	private static File[] accountDir;

	public chooseAccountModel(){
		accountDir = (new File(getClass().getResource(paths.accountsPath.toString()).getPath())).listFiles();
	}

	public int accChosen(File f) throws ClassNotFoundException{
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
			try {
				ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(f));
				User user = (User) objIn.readObject();
				if(createAccountModel.decryptor(user.getPwd()).equals(result.get())){
					//make a new session
					new Session(user,f);
					objIn.close();
					return 1;
				}else{
					objIn.close();
					return 0;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 2;
	}

	public boolean checkAccountsFound(){
		if(accountDir.length == 1){
			return false;
		}
		return true;
	}

	public static File[] getAccountDir(){
		return accountDir;
	}

	//public static void main(String[] args){
		//chooseAccountModel cam = new chooseAccountModel();
	//}
}
