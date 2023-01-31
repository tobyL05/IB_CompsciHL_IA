package com.ibcompsci_ia.GUI.Controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.Bible.bookmarkObject;
import com.ibcompsci_ia.GUI.Models.bookmarkModel;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class bookmarkController {

	@FXML private Label header;
	@FXML private TextFlow bookmarkTextflow;
	@FXML private Button backBtn;
	@FXML private ScrollPane bookmarkScroll;
	private HashMap<String,bookmarkObject> nodes;
	bookmarkModel model;

	public void initialize(){
		model = bookmarkModel.getInstance();
		model.updateVerses();
		fillbookmarks();
	}

	public bookmarkController() {
		// TODO Auto-generated method stub
		;
	}

	private void fillbookmarks(){
		bookmarkTextflow.getChildren().clear();
		if(!model.verse.keySet().isEmpty()){
			System.out.println("Filling verses");
			System.out.println("keys " + model.verse.keySet());
			for(String id:model.verse.keySet()){
				bookmarkObject b = new bookmarkObject(id,model.verse.get(id));
				b.setNode(b);
				model.nodes.put(b.getId(),b);
				b.setOnMouseClicked(new EventHandler<Event>(){
	
					@Override
					public void handle(Event event) {
						Alert confirmation = new Alert(AlertType.CONFIRMATION);
        				confirmation.setTitle("Delete bookmark?");
        				confirmation.setHeaderText("Confirm delete");
        				Optional<ButtonType> result = confirmation.showAndWait();
        				if(result.get() == ButtonType.OK){
							//remove the account
							bookmarkTextflow.getChildren().remove(b);
							System.out.println("Deleting node: " + model.nodes.get(id));
							model.removeVerse(id);
							//bookmarkTextflow.getChildren().remove(model.nodes.get(id));
							model.nodes.remove(id);
							System.out.println("Deleted " + id);
							if(bookmarkTextflow.getChildren().size() == 0){
								System.out.println("bookmarkTextflow " + bookmarkTextflow.getChildren().size());
								nobookmarks();
							}
						}
					}
						
				});
				bookmarkTextflow.getChildren().add(b);
				//bookmarkTextflow.getChildren().add(new Text(System.lineSeparator()));
			}
		}else{
			nobookmarks();
		}
		System.out.println("model nodes size: " + model.nodes.size());
	}
	

	private void nobookmarks(){
		Text t = new Text("No bookmarks found");
		t.setFont(new Font("Verdana",14));
		bookmarkTextflow.getChildren().add(t);
		System.out.println("No verses");
		//bookmarkTextflow.getChildren().add(new Text(System.lineSeparator()));
	}

	@FXML
	private void backBtnPress() throws IOException{
		//go back to main menu
		Main.setRoot("mainMenu");
		//save current book/verse
	}


}
