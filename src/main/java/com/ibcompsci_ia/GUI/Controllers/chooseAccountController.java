package com.ibcompsci_ia.GUI.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.Enums.fxmlStyles;
import com.ibcompsci_ia.GUI.Models.chooseAccountModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class chooseAccountController implements Initializable{

    @FXML private Button backBtn;
    @FXML private VBox accountVBox;
    @FXML private Label noAccs;
    private chooseAccountModel model;

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
        for(File f:model.getAccountDir()){
            if(f.getName().equals("temp")){
                continue;
            }
            Button b = new Button(f.getName());
            Button remove = new Button("Remove");
            BorderPane bp = new BorderPane();
            b.setOnAction(e-> System.out.println(f.getName() + " chosen"));
            remove.setOnAction(e -> System.out.println("Removed " + f.getName()));
            bp.setPrefWidth(accountVBox.getWidth());
            bp.setPrefHeight(50);
            bp.setPadding(new Insets(5,5,5,5));
            b.setStyle(fxmlStyles.transp_button.toString());
            remove.setStyle(fxmlStyles.transp_button.toString());
            bp.setLeft(b);
            bp.setRight(remove);
            accountVBox.getChildren().add(bp);
        }
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