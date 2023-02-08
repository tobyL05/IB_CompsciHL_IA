package com.ibcompsci_ia.Bible;

import com.ibcompsci_ia.Main;
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


public class VerseObject extends Text{
	
	private int bookIdx;
	private int chapIdx;
	private int verseIdx;
	public String verse;
	private Tooltip save;
	private String id;
	private Node thisNode;
	
	public VerseObject(int bookIdx, int chapIdx, int verseIdx,String verse,int lang){
		super(verse);
		this.verse = verse;
		this.bookIdx = bookIdx;
		this.chapIdx = chapIdx;
		this.verseIdx = verseIdx;
		this.id = Integer.toString(bookIdx) + "." + Integer.toString(chapIdx) + "." + Integer.toString(verseIdx) + "." + Integer.toString(lang);
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
				if(lang == 0){
					Session.user.saveVerse(id, CSVParser.idBooks.get(bookIdx) + " " + verse);
				}else{
					Session.user.saveVerse(id, CSVParser.books.get(bookIdx) + " " + verse);
				}
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

	public int getBookIdx() {
		return bookIdx;
	}

	public int getChapIdx() {
		return chapIdx;
	}

	public int getVerseIdx() {
		return verseIdx;
	}
}
