package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;

import com.ibcompsci_ia.launch;
import com.ibcompsci_ia.GUI.Models.mainMenuModel;
import com.ibcompsci_ia.parser.CSVParser;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class mainMenuController {

    mainMenuModel model;
    @FXML private Button darkModeBtn;
    @FXML public Button bibleBtn;
    @FXML private Button bookmarkBtn;
    @FXML private Button notepadBtn;
    @FXML private Button logoutBtn;
    @FXML private Label verseTxt;
    @FXML private Label txtCopy;
    @FXML private Label bibleloading;
    @FXML private ImageView bibleBtnIcon;

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
        bibleBtnIcon.setOpacity(0.1);
        bibleloading.setOpacity(1);
        bibleBtn.setDisable(true);
        checkBible();
    }

    @FXML
    private void darkModeBtnPress(){
        //figure out how to track dark mode;
        model.darkMode();
    }


    /**
     * Checks if BibleArr is fully initialized
     */
    private void checkBible(){
        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                boolean done = false;
                boolean nullval = false;
                while(!done){
                    for(int i = 0;i < 66;i++){
                        if(launch.bible.books[i] == null || launch.bible.books[i].chapter.size() != CSVParser.bookMap.get(launch.bible.books[i].getBookName())){
                            nullval = true;
                            break;
                        }
                    }
                    if(!nullval){ //exit while
                        done = true;
                    }
                    nullval = false;
                }
            return null;
        }};
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
        task.setOnSucceeded(e -> {
            bibleBtn.setDisable(false);
            bibleloading.setOpacity(0);
            bibleBtnIcon.setOpacity(1);
        });
    }

    @FXML
    private void bibleBtnPress() throws IOException{
        //switch to bible
        model.openBible();
    }

    @FXML
    private void searchBtnPress(){
        //switch to search scene
        model.openSearch();
    }
    
    @FXML
    private void bookmarkBtnPress() throws IOException{
        //switch to bookmarks scene
        model.openBookmarks();
    }

    @FXML
    private void notepadBtnPress() throws IOException{
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
