package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.launch;
import com.ibcompsci_ia.Bible.Book;
import com.ibcompsci_ia.Bible.Chapter;
import com.ibcompsci_ia.Bible.VerseObject;
import com.ibcompsci_ia.GUI.Models.biblePageModel;
import com.ibcompsci_ia.parser.CSVParser;
import com.ibcompsci_ia.parser.findChapter;

import javafx.collections.FXCollections;  //collections used by JavaFX
import javafx.collections.ObservableList; //collections used by JavaFX
import javafx.fxml.FXML; //annotation for FXML
import javafx.scene.control.Button; //GUI component
import javafx.scene.control.ComboBox; //GUI component
import javafx.scene.control.Label; //GUI component
import javafx.scene.control.ScrollPane; //GUI component
import javafx.scene.layout.VBox; //GUI component
import javafx.scene.text.Text; //to configure labels
import javafx.scene.text.TextFlow; //to configure labels

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
		model = biblePageModel.getInstance();
		ObservableList<String> books = FXCollections.observableArrayList(CSVParser.books);
		bookCbox.getItems().addAll(books);
		System.out.println(model.getCurrBookidx() + " " + model.getCurrChapidx());
		addVerses(model.getCurrBookidx(),model.getCurrChapidx(),model.getCurrLang());
	}

	@FXML
	private void vboxAddVerses(){
		if(chapCbox.getValue() != null){
			System.out.println("Adding verses");
			verseCbox.getItems().clear();
			try{
				ArrayList<String> verse = new ArrayList<>();
				int chapsize = findChapter.getChapSize(bookCbox.getValue(),Integer.parseInt(chapCbox.getValue())-1); //get number of verses in given chapter
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

	@FXML
	private void clearBoxes(){
		chapCbox.getItems().clear();
		verseCbox.getItems().clear();
	}

	/**
	 * Add all verses
	 * @param bookIdx
	 * @param chapIdx
	 * @param lang
	 */
	private void addVerses(int bookIdx,int chapIdx, String lang){ //add multiple verses
		ArrayList<String> verses = new ArrayList<>();
		verseTextflow.getChildren().clear();
		Book b = launch.bible.books[bookIdx];
		Chapter c = b.chapter.get(chapIdx); //length 0?
		if(lang.equals("en")){
			header.setText(CSVParser.books.get(bookIdx) + " " + (chapIdx + 1));
			verses = c.getEnVerses();
		}else{
			header.setText(CSVParser.idBooks.get(bookIdx) + " " + (chapIdx + 1));
			verses = c.getIdVerses();
		}
		int i = 0;
		int intlang = 0;
		if(lang.equals("en")){
			intlang = 1;
		}else{
			intlang = 0;
		}
		for(String verse:verses){
			VerseObject v = new VerseObject(bookIdx, chapIdx,i , verse, intlang);
			v.setNode(v);
			verseTextflow.getChildren().add(v);

			verseTextflow.getChildren().add(new Text(System.lineSeparator()));
			i++;
			//adjust margins/word wrap/font size
		}
	}

	/**
	 * Adds a single verse
	 * @param bookIdx
	 * @param chapIdx
	 * @param verseIdx
	 * @param lang
	 */
	private void addVerses(int bookIdx,int chapIdx,int verseIdx,String lang){ //add single verse
		int intlang = 0;
		ArrayList<String> verses = new ArrayList<>();
		verseTextflow.getChildren().clear();
		Book b = launch.bible.books[bookIdx];
		Chapter c = b.chapter.get(chapIdx);
		verseTextflow.getChildren().clear();
		if(lang.equals("en")){
			header.setText(CSVParser.books.get(bookIdx) + " " + (chapIdx + 1));
			verses = c.getEnVerses();
			intlang = 1;
		}else{
			header.setText(CSVParser.idBooks.get(bookIdx) + " " + (chapIdx + 1));
			verses = c.getIdVerses();
			intlang = 0;
		}
		verseTextflow.getChildren().add(new VerseObject(bookIdx,chapIdx,verseIdx,verses.get(verseIdx),intlang));
		verseTextflow.getChildren().add(new Text(System.lineSeparator()));
		//adjust margins/word wrap/font size

	}

	public biblePageModel getModel(){
		return model;
	}

	@FXML
	private void getCboxInput(){ // done 10 oct, make sure next and prev page works after doing this
		//get input from cbox pass to model
		boolean checkbookInput = bookCbox.getSelectionModel().isEmpty();
		boolean checkchapInput = chapCbox.getSelectionModel().isEmpty();
		boolean checkverseInput = verseCbox.getSelectionModel().isEmpty();
		if(!checkbookInput && !checkchapInput && !checkverseInput){
			int bookIdx = bookCbox.getSelectionModel().getSelectedIndex();
			int chapIdx = chapCbox.getSelectionModel().getSelectedIndex(); 
			int verses = verseCbox.getSelectionModel().getSelectedIndex(); //if 0, return whole chapter
			model.setCurrBookidx(bookIdx);
			model.setCurrChapidx(chapIdx);
			if(verses == 0){//add whole chapter
				addVerses(bookIdx,chapIdx,model.getCurrLang());
			}else{
				verses--;
				addVerses(bookIdx,chapIdx,verses,model.getCurrLang());
			}
		System.out.println("verse idx: " + verses);
		}
	}

	@FXML 
	private void prevPageBtnPress(){//done 10 oct
		model.decCurrChap();
		versesScroll.setVvalue(0);
		addVerses(model.getCurrBookidx(),model.getCurrChapidx(),model.getCurrLang());
	}

	@FXML 
	private void nextPageBtnPress(){// done 8 oct
		versesScroll.setVvalue(0);
		model.incCurrChap();
		addVerses(model.getCurrBookidx(),model.getCurrChapidx(),model.getCurrLang());
	}

	@FXML
	private void backBtnPress() throws IOException{
		//go back to main menu
		Main.setRoot("mainMenu");
		//save current book/verse
	}

	@FXML
	private void langBtnPress() throws IOException{ //model will control
		System.out.println("Switch lang mode");
		String nextScene = model.getNextScene();
		System.out.println(nextScene);
		if(nextScene.equals("biblePage")){
			if(model.getCurrLang().equals("id")){
				model.setCurrLang("en");
				addVerses(model.getCurrBookidx(), model.getCurrChapidx(), model.getCurrLang());
			}else{
				model.setCurrLang("id");
				addVerses(model.getCurrBookidx(), model.getCurrChapidx(), model.getCurrLang());
			}
		}else{
			Main.setRoot(nextScene);
		}
	}

}
