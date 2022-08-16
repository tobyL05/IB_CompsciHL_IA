package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.GUI.Models.biblePageModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class biblePageController {
	
	@FXML private Label header;
	@FXML private Button darkModeBtn;
	@FXML private Button nextPageBtn;
	@FXML private Button prevPageBtn;
	@FXML private Button getCboxInputBtn;
	@FXML private Button langBtn;
	@FXML private Button backBtn;
	@FXML private VBox versesContainer;
	@FXML private ComboBox<String> bookCbox;
	@FXML private ComboBox<Integer> chapCbox;
	@FXML private ComboBox<Integer> verseCbox;
	biblePageModel model;

	@FXML
	public void initialize() throws IOException{
		//model reads book.csv
		model = new biblePageModel();

		bookCbox = new ComboBox<String>();
		bookCbox.getItems().addAll(model.books);
		chapCbox = new ComboBox<Integer>();
		chapCbox.getItems().addAll(model.chaps);

		//verseCbox = new ComboBox<Integer>();
		//read number of verses according to chapter

		addVerses();
		//add options to cbox, read books.csv
		//set book name and chapter
		header.setText(model.getCurrBook() + " " + model.getCurrChap());
	}

	private void addVerses(){
		//ArrayList<String> verses = model.getVerses();
		for(String s:model.getVerses()){
			System.out.println(s);
			Label verseLabel = new Label(s);
			verseLabel.setWrapText(true);
			verseLabel.setMaxWidth(1200);
			verseLabel.setFont(new Font("Verdana",14));
			versesContainer.getChildren().addAll(verseLabel);
			//adjust margins/word wrap/font size
		}
		versesContainer.getChildren().addAll(new Label(""));
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
		//check for 0
		//go back to last chapter
		//LL of verses
		System.out.println("Prev page");
	}

	@FXML 
	private void nextBtnPress(){
		//currChap + 1
		//check LL of verses
		//if next exists, go to it
		//if not, insert to LL of verses
		//check for end of book
		System.out.println("Next page");
	}

	@FXML
	private void backBtnPress() throws IOException{
		//go back to main menu
		Main.setRoot("mainMenu");
		//save current book/verse
	}

	@FXML
	private void langBtnPress(){
		System.out.println("Switch lang mode");
	}

}
