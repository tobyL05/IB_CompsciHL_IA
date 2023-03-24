package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.Bible.VerseObject;
import com.ibcompsci_ia.GUI.Models.biblePageModel;
import com.ibcompsci_ia.parser.CSVParser;
import com.ibcompsci_ia.users.Session;

import javafx.collections.FXCollections;  //collections used by JavaFX
import javafx.collections.ObservableList; //collections used by JavaFX
import javafx.fxml.FXML; //annotation for FXML
import javafx.scene.control.Button; //GUI component
import javafx.scene.control.ComboBox; //GUI component
import javafx.scene.control.Label; //GUI component
import javafx.scene.text.TextFlow; //to configure labels
import javafx.scene.control.ScrollPane; //GUI component
import javafx.scene.layout.VBox; //GUI component
import javafx.scene.text.Text; //to configure labels

public class duolangPageController{
	
	@FXML private Label header;
	@FXML private Button darkModeBtn;
	@FXML private Button nextPageBtn;
	@FXML private Button prevPageBtn;
	@FXML private Button getCboxInputBtn;
	@FXML private Button langBtn;
	@FXML private Button backBtn;
	@FXML private VBox versesContainer;
	@FXML private TextFlow enVerseTextflow;
	@FXML private TextFlow idVerseTextflow;
	@FXML private ScrollPane versesScroll;
	@FXML private ComboBox<String> bookCbox;
	@FXML private ComboBox<String> chapCbox;
	@FXML private ComboBox<String> verseCbox;
	private final biblePageModel model;

	public duolangPageController() throws IOException{
		model = biblePageModel.getInstance();
	}
	//fix this

	@FXML
	public void initialize() throws IOException{
		ObservableList<String> books = FXCollections.observableArrayList(CSVParser.books);
		bookCbox.getItems().addAll(books);
		bookCbox.getSelectionModel().select(model.getCurrBookidx());
		cboxAddChapter();
		chapCbox.getSelectionModel().select(model.getCurrChapidx());
		vboxAddVerses();
		System.out.println(model.getCurrBookidx() + " " + model.getCurrChapidx());
		addVerses(model.getCurrBookidx(),model.getCurrChapidx());
		System.out.println(CSVParser.books.get(model.getCurrBookidx()) + " " + model.getCurrChapidx()+1);
	}

	@FXML
	private void vboxAddVerses(){
		if(chapCbox.getValue() != null){
			System.out.println("Adding verses");
			verseCbox.getItems().clear();
			try{
				ArrayList<String> verse = new ArrayList<>();
				int chapSize = model.getChapSize(bookCbox.getSelectionModel().getSelectedIndex(),Integer.parseInt(chapCbox.getValue())-1);
				for(int i = 0;i < chapSize;i++){
					verse.add(String.format("%s",i+1));
				}
				verse.add(0, String.format("1" + " - " + "%s",chapSize));
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

	private void addVerses(int bookIdx,int chapIdx) throws IOException{ //add multiple verses
		enVerseTextflow.getChildren().clear();
		idVerseTextflow.getChildren().clear();
		header.setText(CSVParser.books.get(bookIdx) + " " + (chapIdx + 1) + "/" + CSVParser.idBooks.get(bookIdx) + " " + (chapIdx + 1));
		ArrayList<String> enVerses = model.getVersesinChapter(bookIdx, chapIdx, "en");
		ArrayList<String> idVerses = model.getVersesinChapter(bookIdx, chapIdx, "id");

		for(int i = 0;i < enVerses.size();i++){
			VerseObject idVerse = new VerseObject(bookIdx, chapIdx, i, idVerses.get(i), "id");
			VerseObject enVerse = new VerseObject(bookIdx, chapIdx, i, enVerses.get(i), "en");
			enVerseTextflow.getChildren().add(enVerse);
			enVerseTextflow.getChildren().add(new Text(System.lineSeparator()));
			idVerseTextflow.getChildren().add(idVerse);
			idVerseTextflow.getChildren().add(new Text(System.lineSeparator()));
		}

	}
	private void addVerses(int bookIdx,int chapIdx,int verseIdx) throws IOException{ //add single verse
		enVerseTextflow.getChildren().clear();
		idVerseTextflow.getChildren().clear();
		header.setText(CSVParser.books.get(bookIdx) + " " + (chapIdx + 1) + "/" + CSVParser.idBooks.get(bookIdx) + " " + (chapIdx + 1));
		String enVerse = model.getVersesinChapter(bookIdx, chapIdx, "en").get(verseIdx);
		String idVerse = model.getVersesinChapter(bookIdx, chapIdx, "id").get(verseIdx);
		enVerseTextflow.getChildren().add(new VerseObject(bookIdx, chapIdx, verseIdx, enVerse, "en"));
		idVerseTextflow.getChildren().add(new VerseObject(bookIdx, chapIdx, verseIdx, idVerse, "id"));
		enVerseTextflow.getChildren().add(new Text(System.lineSeparator()));
		idVerseTextflow.getChildren().add(new Text(System.lineSeparator()));
	}

	@FXML
	private void getCboxInput() throws IOException{ // done 10 oct, make sure next and prev page works after doing this
		//dont run when input is empty
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
				addVerses(bookIdx,chapIdx);
			}else{
				addVerses(bookIdx,chapIdx,verses-1);
			}
		}
	}

	@FXML 
	private void darkModeBtnPress(){
		System.out.println("dark mode");
	}

	@FXML 
	private void prevPageBtnPress() throws IOException{//done 10 oct
		model.decCurrChap();
		versesScroll.setVvalue(0);
		//System.out.println("(Book,model) " + model.getCurrBookidx() + "," + model.getCurrChapidx());
		addVerses(model.getCurrBookidx(),model.getCurrChapidx());
		System.out.println("Prev page");
		//go back to last chapter
		//LL of verses
	}

	@FXML 
	private void nextPageBtnPress() throws IOException{// done 8 oct
		versesScroll.setVvalue(0);
		model.incCurrChap();
		addVerses(model.getCurrBookidx(),model.getCurrChapidx());
		bookCbox.getSelectionModel().select(model.getCurrBookidx());
		cboxAddChapter();
		chapCbox.getSelectionModel().select(model.getCurrChapidx());
		System.out.println(model.getCurrBookidx() + " " + model.getCurrChapidx());
		System.out.println("Next page");
	}

	@FXML
	private void backBtnPress() throws IOException{
		//go back to main menu
		Main.setRoot("mainMenu");
		Session.user.setCurrBook(model.getCurrBookidx());	
		Session.user.setCurrChap(model.getCurrChapidx());
		//save current book/verse
	}
	@FXML
	private void langBtnPress() throws IOException{ //change this, let model control.
		System.out.println("Switch lang mode");
		String nextScene = model.getNextScene();
		System.out.println(nextScene);
		if(nextScene.equals("biblePage")){
			if(model.getCurrLang().equals("id")){
				model.setCurrLang("en");
				//addVerses(model.getCurrBookidx(), model.getCurrChapidx());
			}else{
				model.setCurrLang("id");
				//addVerses(model.getCurrBookidx(), model.getCurrChapidx());
			}
		}
		Main.setRoot(nextScene);
	}
}

//TEST LANGUAGE BUTTON MAKE SURE ALL VERSES ADDED PROPERLY
//NEXT: ACTUALLY SAVE STUFF
