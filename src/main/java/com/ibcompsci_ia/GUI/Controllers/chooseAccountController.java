package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.GUI.Models.chooseAccountModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class chooseAccountController {

    @FXML private Button backBtn;
    @FXML private VBox accountVBox;
    private chooseAccountModel model;

    public chooseAccountController(){
        model = new chooseAccountModel();
        if(!model.checkAccountsFound()){
            System.out.println("No accounts");
        }
    }

    @FXML
    private void backBtnPress() throws IOException {
        Main.setRoot("loginPage");
    }

}