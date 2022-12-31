package com.ibcompsci_ia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.ibcompsci_ia.users.Session;

/**
 * JavaFX App
 */
public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setOnCloseRequest(evt -> {
            //on close event
            if(Session.loginStatus()){
                try{
                    Session.saveObj();
                }catch(Exception e){
                    System.out.println("Error saving object");
                    e.printStackTrace();
                }
            }
        });
        scene = new Scene(loadFXML("loginPage"));
        stage.setScene(scene);
        stage.show();
    }


    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}