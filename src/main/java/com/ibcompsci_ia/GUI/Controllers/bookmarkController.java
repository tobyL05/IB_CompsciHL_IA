package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;
import java.util.HashMap;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.Bible.VerseObject;
import com.ibcompsci_ia.Bible.bookmarkObject;
import com.ibcompsci_ia.GUI.Models.bookmarkModel;
import com.ibcompsci_ia.users.Session;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class bookmarkController {
	@FXML private Label header;
	@FXML private TextFlow bookmarkTextflow;
	@FXML private Button backBtn;
	@FXML private ScrollPane bookmarkScroll;
	private HashMap<Integer,bookmarkObject> nodes;
	bookmarkModel model;

	public void initialize() {
		// TODO Auto-generated method stub
		model = new bookmarkModel();
		nodes = new HashMap<>();
		if(model.getBookNames().isEmpty()){
			nobookmarks();
		}
		fillbookmarks();
	}

	private void fillbookmarks(){
		for(int i = 0;i < model.getBookNames().size();i++){
			bookmarkObject b = new bookmarkObject(i,model.getBookNames().get(i) + " " + model.getVerses().get(i));
			b.setNode(b);
			nodes.put(i,b);
			bookmarkTextflow.getChildren().add(b);
			 bookmarkTextflow.getChildren().add(new Text(System.lineSeparator()));
		}
	}

	private void nobookmarks(){
		Text t = new Text("No bookmarks yet");
		t.setFont(new Font("Verdana",14));
		bookmarkTextflow.getChildren().add(t);
		bookmarkTextflow.getChildren().add(new Text(System.lineSeparator()));
	}

	public void deletebookmark(){
		//delete bookmark using hashmap
	}


	@FXML
	private void backBtnPress() throws IOException{
		//go back to main menu
		Main.setRoot("mainMenu");
		//save current book/verse
	}


}
