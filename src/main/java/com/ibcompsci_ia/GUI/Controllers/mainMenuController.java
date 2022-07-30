package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.GUI.Models.mainMenuModel;
import com.ibcompsci_ia.users.Session;

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
    private void darkModeBtnPress(){
        //figure out how to track dark mode;
        model.darkMode();
    }

    @FXML
    private void bibleBtnPress(){
        //switch to bible
        model.openBible();
    }

    @FXML
    private void searchBtnPress(){
        //switch to search scene
        model.openSearch();
    }
    
    @FXML
    private void bookmarkBtnPress(){
        //switch to bookmarks scene
        model.openBookmarks();
    }

    @FXML
    private void notepadBtnPress(){
        //switch to notepad
        model.openNotes();
    }

    @FXML
    private void logoutBtnPress() throws IOException{
        //logout
        System.out.println("Log out");
        model.logout();
    }


}
