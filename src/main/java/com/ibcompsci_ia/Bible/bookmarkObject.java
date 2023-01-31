package com.ibcompsci_ia.Bible;

import java.io.Serializable;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.GUI.Controllers.bookmarkController;
import com.ibcompsci_ia.parser.CSVParser;
import com.ibcompsci_ia.users.Session;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class bookmarkObject extends Text{
	
	private String verse;
	private String id;
	private Tooltip del;
	private Node thisNode;
	
	public bookmarkObject(String id, String verse){
		super(verse);
		this.verse = verse;
		this.id = id;
		initTooltip();
		setFont(new Font("Verdana",14));
		setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				onHover();			
			}
				
		});
		setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				onExit();
			}
			
		});
	}

	private void initTooltip(){
		del = new Tooltip("Delete?");
		del.setStyle("-fx-background: rgba(30,30,30);-fx-text-fill: white;-fx-background-color: rgba(30,30,30,0.8);-fx-background-radius: 6px;-fx-background-insets: 0;-fx-padding: 0.667em 0.75em 0.667em 0.75em; /* 10px */-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.5) , 10, 0.0 , 0 , 3 );-fx-font-size: 0.85em;"); //https://stackoverflow.com/questions/25336796/tooltip-background-with-javafx-css
		del.setShowDelay(Duration.seconds(0));
	}

	public void setNode(Node node){
		this.thisNode = node;
	}

	private void onHover(){
		setUnderline(true);
		Tooltip.install(thisNode,del);
		Main.getScene().setCursor(Cursor.HAND);
	}

	private void onExit(){
		setUnderline(false);
		Tooltip.uninstall(thisNode, del);
		Main.getScene().setCursor(Cursor.DEFAULT);
	}

	public String getIdx(){
		return id;
	}


}
