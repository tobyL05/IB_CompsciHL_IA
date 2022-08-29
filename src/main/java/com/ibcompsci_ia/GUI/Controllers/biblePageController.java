package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.GUI.Models.biblePageModel;
import com.ibcompsci_ia.parser.findChapter;

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
	@FXML private ComboBox<String> verseCbox;
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
		//chapCbox = new ComboBox<String>();

		//verseCbox = new ComboBox<Integer>();
		//read number of verses according to chapter

		addVerses();
		//add options to cbox, read books.csv
		//set book name and chapter
		header.setText(model.getCurrBook() + " " + model.getCurrChap());
	}

	@FXML
	private void vboxAddVerses(){
		System.out.println("Adding verses");
		verseCbox.getItems().clear();
		try{
			ArrayList<String> verse = new ArrayList<>();
			int chapsize = findChapter.getChapSize(bookCbox.getValue(),chapCbox.getValue());
			for(int i = 0;i < chapsize;i++){
				verse.add(String.format("%s",i+1));
			}
			verse.add(0, String.format("1" + " - " + "%s",chapsize));
			ObservableList<String> verseList = FXCollections.observableArrayList(verse);
			verseCbox.getItems().addAll(verseList);
		}catch(Exception e){
			System.out.println(e);
		}
	}

	@FXML
	private void cboxAddChapter(){
		System.out.println("add chapters");
		chapCbox.getItems().clear();
		try{
			ArrayList<String> chaps = new ArrayList<>();
			int n = model.bookMap.get(bookCbox.getValue());
			for(int i = 0;i < n;i++){
				chaps.add(String.format("%s",i+1));
			}
			//int[] verses = new int[model.bookMap.get(bookCbox.getValue())];
			ObservableList<String> chapList = FXCollections.observableArrayList(chaps);
			chapCbox.getItems().addAll(chapList);
		}catch(Exception e){
			System.out.println(e);
		}
	}


	private void addVerses(){
		//ArrayList<String> verses = model.getVerses();
		for(String s:model.getVerses()){
			//System.out.println(s);
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
		model.findChapFromCbox(bookCbox.getValue(),chapCbox.getValue(),Integer.toString(verseCbox.getSelectionModel().getSelectedIndex()));
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
