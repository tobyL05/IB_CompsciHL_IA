package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.GUI.Models.chooseAccountModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class chooseAccountController {

    @FXML private Button backBtn;
    @FXML private VBox accountVBox;
    @FXML private Label noAccountsTxt;
    private chooseAccountModel model;

    public chooseAccountController(){
        model = new chooseAccountModel();
        checkAccounts(model.checkAccountsFound());
    }

    private void checkAccounts(boolean acc){
        if(!acc){
            System.out.println("No accounts");
        }else{
            System.out.println("Accoutns found");
        }
    }

    @FXML
    private void backBtnPress() throws IOException {
        Main.setRoot("loginPage");
    }

}