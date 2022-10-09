package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.launch;
import com.ibcompsci_ia.Bible.BookAppender;
import com.ibcompsci_ia.GUI.Models.biblePageModel;
import com.ibcompsci_ia.parser.CSVParser;
import com.ibcompsci_ia.parser.findChapter;
import com.ibcompsci_ia.users.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class biblePageController {
	
	@FXML private Label header;
	@FXML private Button darkModeBtn;
	@FXML private Button nextPageBtn;
	@FXML private Button prevPageBtn;
	@FXML private Button getCboxInputBtn;
	@FXML private Button langBtn;
	@FXML private Button backBtn;
	@FXML private VBox versesContainer;
	@FXML private TextFlow verseTextflow;
	@FXML private ScrollPane versesScroll;
	@FXML private ComboBox<String> bookCbox;
	@FXML private ComboBox<String> chapCbox;
	@FXML private ComboBox<String> verseCbox;
	biblePageModel model;

	@FXML
	public void initialize() throws IOException{
		//model reads book.csv
		model = new biblePageModel();


		//add books to cbox


		ObservableList<String> books = FXCollections.observableArrayList(CSVParser.books);
		bookCbox.getItems().addAll(books);

		//add chapters to cbox (another method)
		//chapCbox = new ComboBox<String>();

		//verseCbox = new ComboBox<Integer>();
		//read number of verses according to chapter

		addVerses();
		//add options to cbox, read books.csv
		//set book name and chapter
		header.setText(launch.bible.getHeader());
		//header.setText(model.getCurrBook() + " " + (Integer.parseInt(model.getCurrChap()) + 1));
	}

	@FXML
	private void vboxAddVerses(){
		System.out.println("Adding verses");
		verseCbox.getItems().clear();
		try{
			ArrayList<String> verse = new ArrayList<>();
			int chapsize = findChapter.getChapSize(bookCbox.getValue(),chapCbox.getValue()); //get number of verses in given chapter
			for(int i = 0;i < chapsize;i++){
				verse.add(String.format("%s",i+1));
			}
			verse.add(0, String.format("1" + " - " + "%s",chapsize));
			ObservableList<String> verseList = FXCollections.observableArrayList(verse);
			verseCbox.getItems().addAll(verseList);
		}catch(Exception e){
			System.out.println("vbox add verse: " + e);
			e.printStackTrace();
		}
	}

	@FXML
	private void cboxAddChapter(){ //method called when a book is selected.
		System.out.println("add chapters");
		chapCbox.getItems().clear();
		try{
			ArrayList<String> chaps = new ArrayList<>();
			int n = CSVParser.bookMap.get(bookCbox.getValue());
			for(int i = 0;i < n;i++){
				chaps.add(String.format("%s",i+1));
			}
			//int[] verses = new int[model.bookMap.get(bookCbox.getValue())];
			ObservableList<String> chapList = FXCollections.observableArrayList(chaps);
			chapCbox.getItems().addAll(chapList);
		}catch(Exception e){
			System.out.println("cboxAddchap: " + e);
			e.printStackTrace();
		}
	}


	private void addVerses(){
		//get verse from LL
		ArrayList<String> verses = new ArrayList<>();
		System.out.println("From Session.user: " + Session.user.getCurrBook() + " " + Session.user.getCurrChap());
		verses = launch.bible.getFirstVerse(Session.user.getCurrBook(), Session.user.getCurrChap());
		//txtfp.setPrefWidth(100);
		//txtfp.setLineSpacing(3.0);
		//txtfp.setTextAlignment(TextAlignment.JUSTIFY);
		//txtfp.prefWidthProperty().bind(versesContainer.widthProperty());

		for(String s:verses){
			//System.out.println(s);
			Text verseLabel = new Text(s);
			verseLabel.setFont(new Font("Verdana",14));
			verseTextflow.getChildren().add(verseLabel);
			verseTextflow.getChildren().add(new Text(System.lineSeparator()));
			//adjust margins/word wrap/font size
		}
		//versesContainer.setCenter(txtfp);
		//versesContainer.getChildren().addAll(new Label(""));
	}

	private void addVerses(ArrayList<String> verses){//make sure it actually adds the verse
		if(verses != null){
			verseTextflow.getChildren().clear(); //clear vbox
			//System.out.println("Current chap: " + model.getCurrChap());
			//System.out.println("No of chaps: " + CSVParser.bookMap.get(model.getCurrBook()));
			header.setText(launch.bible.getHeader());
			//if(Integer.parseInt(model.getCurrChap()) >= CSVParser.bookMap.get(model.getCurrBook())){//if next chap is next book
				//model.setCurrBook(CSVParser.books.get(CSVParser.books.indexOf(model.getCurrBook())+1));
				//System.out.println("Current book: " + model.getCurrBook());
				//header.setText(model.getCurrBook() + " " + 1);
				//model.resetChap();
			//}else{
				//header.setText(model.getCurrBook() + " " + (Integer.parseInt(model.getCurrChap()) + 1)); //currChap starts from 0
			//}
			//txtfp.setPrefWidth(1000);

			//txtfp.setLineSpacing(3.0);
			//txtfp.setTextAlignment(TextAlignment.JUSTIFY);
			//txtfp.prefWidthProperty().bind(versesContainer.widthProperty());
			for(String s:verses){

				//System.out.println(s);
				Text verseLabel = new Text(s);
				verseLabel.setFont(new Font("Verdana",14));
				verseTextflow.getChildren().add(verseLabel);
				verseTextflow.getChildren().add(new Text(System.lineSeparator()));
				//adjust margins/word wrap/font size
			}
			//versesContainer.setCenter(txtfp);
			//versesContainer.getChildren().addAll(new Label(""));
		}
	}

	@FXML
	private void getCboxInput(){ // done 10 oct, make sure next and prev page works after doing this
		//get input from cbox pass to model
		String bookName = bookCbox.getValue();
		String chapNo = Integer.toString(Integer.parseInt(chapCbox.getValue())-1);
		int verses = verseCbox.getSelectionModel().getSelectedIndex();
		if(verses == 0){
			if(launch.bible.contains(bookName)){
				addVerses(launch.bible.findChap(bookName, chapNo));
			}else{
				launch.bible.add(bookName);
				addVerses(launch.bible.findChap(bookName, chapNo));
			}
		}else{
			ArrayList<String> chap = new ArrayList<>();
			chap.add(launch.bible.findChap(bookName, chapNo).get(verses));
			addVerses(chap);
			header.setText(launch.bible.getHeader() + ":" + Integer.toString(verses));
		}
		//model.findChapFromCbox(bookCbox.getValue(),chapCbox.getValue(),Integer.toString(verseCbox.getSelectionModel().getSelectedIndex()));

	}

	@FXML 
	private void darkModeBtnPress(){
		System.out.println("dark mode");
	}

	@FXML 
	private void prevPageBtnPress(){//done 10 oct
		//go back to last chapter
		//LL of verses
		versesScroll.setVvalue(0);
		model.decCurrChap();
		addVerses(launch.bible.getPrevChap());
		System.out.println("Prev page");
	}

	@FXML 
	private void nextPageBtnPress(){// done 8 oct
		versesScroll.setVvalue(0);
		//currChap + 1
		//check LL of verses
		//if next exists, go to it
		model.incCurrChap();
		addVerses(launch.bible.getNextChap());
		//if not, insert to LL of verses
		//check for end of book (if session chap > bookmap.get currbook )
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
