package com.ibcompsci_ia.GUI.Controllers;

import com.ibcompsci_ia.GUI.Models.mainMenuModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class mainMenuController {

    mainMenuModel model;
    @FXML private Button darkModeBtn;
    @FXML private Button bibleBtn;
    @FXML private Button searchBtn;
    @FXML private Button bookmarkBtn;
    @FXML private Button notepadBtn;
    @FXML private Button logoutBtn;
    @FXML private Label verseTxt;
    @FXML private Label txtCopy;

    @FXML
    public void initialize(){
        String verse;
        model = new mainMenuModel();
        //get a random verse

        try{
            verse = model.verse;
        }catch(Exception e){
            System.out.println("Check internet/API down");
            verse = "";
        }

        verseTxt.setText(verse);
    }

    @FXML
    private void bibleBtnPress(){
        //switch to bible
    }

    @FXML
    private void searchBtnPress(){
        //switch to search scene
    }
    
    @FXML
    private void bookmarkBtnPress(){
        //switch to bookmarks scene
    }

    @FXML
    private void notepadBtnPress(){
        //switch to notepad
    }

    @FXML
    private void logoutPress(){
        //logout
    }


}
