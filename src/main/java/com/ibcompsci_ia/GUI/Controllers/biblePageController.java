package com.ibcompsci_ia.GUI.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class biblePageController {
	
	@FXML private Label header;
	@FXML private Button darkModeBtn;
	@FXML private Button nextPageBtn;
	@FXML private Button prevPageBtn;
	@FXML private VBox container;

	@FXML 
	private void darkModeBtnPress(){
		System.out.println("dark mode");
	}


}
