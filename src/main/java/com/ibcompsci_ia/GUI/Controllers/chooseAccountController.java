package com.ibcompsci_ia.GUI.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.Enums.fxmlStyles;
import com.ibcompsci_ia.GUI.Models.chooseAccountModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class chooseAccountController implements Initializable{

    @FXML private Button backBtn;
    @FXML private VBox accountVBox;
    @FXML private Label noAccs;
    private chooseAccountModel model;
    private ArrayList<BorderPane> accountsBP = new ArrayList<BorderPane>();
    private HashMap<String,Integer> accountIndexes = new HashMap<String,Integer>();

    public chooseAccountController(){
        model = new chooseAccountModel();
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

    private void showAccounts(){
        int i = 0;
        for(File f:model.getAccountDir()){
            if(f.getName().equals("temp")){
                continue;
            }
            Button b = new Button(f.getName());
            Button remove = new Button("Remove");
            BorderPane bp = new BorderPane();
            b.setOnAction(e-> System.out.println(f.getName() + " chosen"));
            remove.setOnAction(e -> removeAcc(f.getName()));
            bp.setPrefWidth(accountVBox.getWidth());
            bp.setPrefHeight(50);
            bp.setPadding(new Insets(5,5,5,5));
            b.setStyle(fxmlStyles.transp_button.toString());
            remove.setStyle(fxmlStyles.transp_button.toString());
            bp.setLeft(b);
            bp.setRight(remove);
            accountVBox.getChildren().add(bp);
            accountsBP.add(bp);
            accountIndexes.put(f.getName(), i);
            i++;
        }
    }

    private void removeAcc(String file_name){
        Alert confirmation = new Alert(AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm account removal");
        confirmation.setHeaderText("Remove " + "account_name " + "?" );
        Optional<ButtonType> result = confirmation.showAndWait();
        if(result.get() == ButtonType.OK){
            //remove the account
            //delete the file
            //
        }else{
            ; //do nothing
        }
        System.out.println(file_name + " " + accountsBP.get(accountIndexes.get(file_name)));

    }

    @FXML
    private void backBtnPress() throws IOException {
        Main.setRoot("loginPage");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        checkAccounts(model.checkAccountsFound());
    }

}