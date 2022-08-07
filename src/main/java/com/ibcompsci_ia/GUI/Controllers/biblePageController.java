package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;

import com.ibcompsci_ia.GUI.Models.biblePageModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class biblePageController {
	
	@FXML private Label header;
	@FXML private Button darkModeBtn;
	@FXML private Button nextPageBtn;
	@FXML private Button prevPageBtn;
	@FXML private Button getCboxInputBtn;
	@FXML private Button backBtn;
	@FXML private VBox versesContainer;
	@FXML private ComboBox<String> bookCbox;
	@FXML private ComboBox<Integer> chapCbox;
	@FXML private ComboBox<Integer> verseCbox;
	biblePageModel model;

	@FXML
	public void initialize() throws IOException{
		model = new biblePageModel();
		addVerses();
		//add options to cbox
	}

	private void addVerses(){
		//ArrayList<String> verses = model.getVerses();
		for(String s:model.getVerses()){
			versesContainer.getChildren().addAll(new Label(s));
		}
	}

	@FXML
	private void getCboxInput(){
		//get input from cbox pass to model
		model.findChapFromCbox(bookCbox.getValue(),chapCbox.getValue(),verseCbox.getValue());
	}

	@FXML 
	private void darkModeBtnPress(){
		System.out.println("dark mode");
	}

	@FXML 
	private void prevBtnPress(){
		//go back to last chapter
		//LL of verses
	}

	@FXML
	private void backBtnPress(){
		//go back to main menu
		//save current book/verse
	}

}
