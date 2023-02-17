package com.ibcompsci_ia.GUI.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.Enums.fxmlStyles;
import com.ibcompsci_ia.GUI.Models.chooseAccountModel;
import com.ibcompsci_ia.GUI.Models.createAccountModel;
import com.ibcompsci_ia.users.Session;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class chooseAccountController implements Initializable{

    @FXML private Button backBtn;
    @FXML private VBox accountVBox;
    @FXML private ScrollPane accountScrollPane;
    @FXML private Label noAccs;
    @FXML private Label wrongpwd;
    private chooseAccountModel model;
    //private ArrayList<BorderPane> accountsBP = new ArrayList<BorderPane>();
    private HashMap<String,BorderPane> accountIndexes = new HashMap<String,BorderPane>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            model = new chooseAccountModel();
            checkAccounts(model.checkAccountsFound());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkAccounts(boolean acc){
        if(!acc){
            System.out.println("No accounts");
        }else{
            System.out.println("Accounts found");
            noAccs.setOpacity(0);
            showAccounts();
        }
    }

    private void accChosenPress(File f) throws ClassNotFoundException{       
        switch(model.accChosen(f)){
            case 0:
                System.out.println("wrong pwd");
                wrongpwd.setOpacity(1);
                break;
            case 1:
                //login
                System.out.println("Logged in");
                try {
                    Main.setRoot("mainMenu");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
            	//cancel (do nothing)
                break;
        }
    }

    private void showAccounts(){
        for(File f:model.getAccounts()){
            if(f.getName().equals("temp")){
                continue;
            }
            Button b = new Button(new String(createAccountModel.decryptor(f.getName().split("\\.")[0].getBytes()).split("_")[0]));
            Button remove = new Button("Remove");
            BorderPane bp = new BorderPane();
            b.setOnAction(e-> {
                try {
                    accChosenPress(f);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }); //f from accChosenPress
            //ask for password confirmation
            remove.setOnAction(e -> removeAcc(f));
            bp.setPrefWidth(accountVBox.getWidth());
            bp.setPrefHeight(50);
            bp.setPadding(new Insets(5,5,5,5));
            b.setStyle(fxmlStyles.transp_button.toString());
            remove.setStyle(fxmlStyles.transp_button.toString());
            bp.setLeft(b);
            bp.setRight(remove);
            accountVBox.getChildren().add(bp);
            accountIndexes.put(f.getName(), bp);
        }
    }


    private void removeAcc(File file){
        //file is encrypted
        String file_name = new String(createAccountModel.decryptor(file.getName().split("\\.")[0].getBytes()));
        
        //check account password
        String user = file_name.split("_")[0];
        String pass = file_name.split("_")[1];
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Confirm remove account");
        dialog.setHeaderText("Remove " + user + "?");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        PasswordField pwd = new PasswordField();
        pwd.setPromptText("Enter password for " + user);
        HBox container = new HBox();
		container.setAlignment(Pos.CENTER_LEFT);
		container.setSpacing(10);
        container.getChildren().add(pwd);
		//container.getChildren().addAll(new Label("Enter the password for " + new String(createAccountModel.decryptor(f.getName().split("\\.")[0].getBytes()).split("_")[0])),pwd);
		dialog.getDialogPane().setContent(container);
		dialog.setResultConverter(dialogButton -> {
			if(dialogButton == ButtonType.OK){
				return pwd.getText();
			}
			return null;
		});

		Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){
            if(result.get().equals(pass)){
                //delete
                accountVBox.getChildren().remove(accountIndexes.get(file.getName()));
                if(accountVBox.getChildren().size() == 0){
                    noAccs.setOpacity(1);
                }
                //System.out.println("Remove acc");
    
                //delete the file
                System.out.println("deleting: " + model.getAccountsPath() + "/" + file);
                new File(model.getAccountsPath() + "/" + file.getName()).delete();
                new File(model.getNotesPath() + "/" + file.getName() + ".txt").delete();
            }else{
                wrongpwd.setOpacity(1);
            }
            //remove the account

        }
    }

    @FXML
    private void backBtnPress() throws IOException {
        Main.setRoot("loginPage");
    }


    public static void main(String[] args) {
        chooseAccountController cac = new chooseAccountController();
        cac.showAccounts();
    }

}