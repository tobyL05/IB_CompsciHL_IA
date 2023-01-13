package com.ibcompsci_ia.Bible;

import com.ibcompsci_ia.Main;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.PopupWindow;
import javafx.util.Duration;


public class VerseObject extends Text{
	
	private String verse;
	private int bookIdx;
	private int chapIdx;
	private int verseIdx;
	private Tooltip save;
	private Node thisNode;
	
	public VerseObject(int bookIdx, int chapIdx, int verseIdx,String verse){
		super(verse);
		this.bookIdx = bookIdx;
		this.chapIdx = chapIdx;
		this.verseIdx = verseIdx;
		this.verse = verse;
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
		setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				System.out.println("Saved: " + verse);
			}
			
		});
	}

	private void initTooltip(){
		save = new Tooltip("Save");
		save.setStyle("-fx-background: rgba(30,30,30);-fx-text-fill: white;-fx-background-color: rgba(30,30,30,0.8);-fx-background-radius: 6px;-fx-background-insets: 0;-fx-padding: 0.667em 0.75em 0.667em 0.75em; /* 10px */-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.5) , 10, 0.0 , 0 , 3 );-fx-font-size: 0.85em;"); //https://stackoverflow.com/questions/25336796/tooltip-background-with-javafx-css
		save.setShowDelay(Duration.seconds(0));
	}

	public void setNode(Node node){
		this.thisNode = node;
	}

	private void onHover(){
		setUnderline(true);
		Tooltip.install(thisNode,save);
		Main.getScene().setCursor(Cursor.HAND);
	}

	private void onExit(){
		setUnderline(false);
		Tooltip.uninstall(thisNode, save);
		Main.getScene().setCursor(Cursor.DEFAULT);
	}
}
