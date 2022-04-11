package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.GUI.Models.loginModel;
import com.ibcompsci_ia.GUI.Models.mainMenuModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class mainMenuController {

    mainMenuModel model;
    @FXML private Label userText;
    @FXML private Label pwdText;


    public mainMenuController(){
        model = new mainMenuModel();
    }

    @FXML
    public void initialize(){
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        Main.setRoot("loginPage");
    }
}
