package com.ibcompsci_ia.GUI.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.Enums.fxmlStyles;
import com.ibcompsci_ia.Enums.paths;
import com.ibcompsci_ia.GUI.Models.chooseAccountModel;
import com.ibcompsci_ia.GUI.Models.createAccountModel;


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
    //private ArrayList<BorderPane> accountsBP = new ArrayList<BorderPane>();
    private HashMap<String,BorderPane> accountIndexes = new HashMap<String,BorderPane>();

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

    private void accChosenPress(File f) throws ClassNotFoundException{
        switch(model.accChosen(f)){
            case 0:
                //io error
                System.out.println("wrong pwd");
                break;
            case 1:
                //login
                System.out.println("Logged in");
                try {
                    Main.setRoot("mainMenu");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case 2:
                //wrong pwd
                System.out.println("io error");
                break;
        }
    }

    private void showAccounts(){
        for(File f:chooseAccountModel.getAccountDir()){
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
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }); //f from accChosenPress
            //ask for password confirmation
            remove.setOnAction(e -> removeAcc(f.getName()));
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


    private void removeAcc(String file){
        String file_name = new String(createAccountModel.decryptor(file.split("\\.")[0].getBytes()));
        Alert confirmation = new Alert(AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm account removal");
        confirmation.setHeaderText("Remove " + file_name.split("_")[0] + "?" );
        Optional<ButtonType> result = confirmation.showAndWait();
        if(result.get() == ButtonType.OK){
            //remove the account
            accountVBox.getChildren().remove(accountIndexes.get(file));
            if(accountVBox.getChildren().size() == 0){
                noAccs.setOpacity(1);
            }
            System.out.println("Remove acc");

            //delete the file
            new File(getClass().getResource(paths.accountsPath.toString()).getPath() + file).delete();
        }else{
            ; //do nothing
        }
        System.out.println(file_name + " " + accountIndexes.get(file));

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

    public static void main(String[] args) {
        chooseAccountController cac = new chooseAccountController();
        cac.showAccounts();
    }

}