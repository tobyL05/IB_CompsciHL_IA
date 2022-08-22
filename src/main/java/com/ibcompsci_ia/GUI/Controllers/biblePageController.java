package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.GUI.Models.biblePageModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
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
	@FXML private ComboBox<String> chapCbox;
	@FXML private ComboBox<Integer> verseCbox;
	biblePageModel model;

	@FXML
	public void initialize() throws IOException{
		//model reads book.csv
		model = new biblePageModel();

		//add books to cbox

		System.out.println(model.books.get(0));

		ObservableList<String> books = FXCollections.observableArrayList(model.books);
		bookCbox.getItems().addAll(books);

		//add chapters to cbox (another method)
		chapCbox = new ComboBox<String>();

		//verseCbox = new ComboBox<Integer>();
		//read number of verses according to chapter

		addVerses();
		//add options to cbox, read books.csv
		//set book name and chapter
		header.setText(model.getCurrBook() + " " + model.getCurrChap());
	}

	@FXML
	private void cboxAddChapter(){
		try{
			ArrayList<String> verses = new ArrayList<>();
			int n = model.bookMap.get(bookCbox.getValue());
			for(int i = 0;i < n;i++){
				verses.add(String.format("%s",i+1));
			}
			//int[] verses = new int[model.bookMap.get(bookCbox.getValue())];
			ObservableList<String> versesList = FXCollections.observableArrayList(verses);
			chapCbox.getItems().addAll(versesList);
		}catch(Exception e){
			System.out.println(e);
		}
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
	private void prevPageBtnPress(){
		//check for 0
		//go back to last chapter
		//LL of verses
		System.out.println("Prev page");
	}

	@FXML 
	private void nextPageBtnPress(){
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
