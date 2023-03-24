package com.ibcompsci_ia.Bible;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.Enums.fxmlStyles;
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


/**
 * Extends javafx.scene.text to create my own custom clickable text
 */
public class VerseObject extends Text{
	
	private int bookIdx;
	private int chapIdx;
	private int verseIdx;
	public String verse;
	private Tooltip save;
	private String id;
	private Node thisNode;
	

	/**
	 * Instantiate a verse object
	 * @param bookIdx
	 * @param chapIdx
	 * @param verseIdx
	 * @param verse
	 * @param lang
	 */
	public VerseObject(int bookIdx, int chapIdx, int verseIdx,String verse,String lang){
		super(verse);
		this.verse = verse;
		this.bookIdx = bookIdx;
		this.chapIdx = chapIdx;
		this.verseIdx = verseIdx;
		this.id = Integer.toString(bookIdx) + "." + Integer.toString(chapIdx) + "." + Integer.toString(verseIdx) + "." + lang;
		initTooltip();
		setFont(new Font("Verdana",14));
		setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				setUnderline(true);
				Tooltip.install(thisNode,save);
				Main.getScene().setCursor(Cursor.HAND);
			}
				
		});
		setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				setUnderline(false);
				Tooltip.uninstall(thisNode, save);
				Main.getScene().setCursor(Cursor.DEFAULT);
			}
			
		});
		setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if(lang.equals("id")){
					Session.user.saveVerse(id, CSVParser.idBooks.get(bookIdx) + " " + verse);
				}else{
					Session.user.saveVerse(id, CSVParser.books.get(bookIdx) + " " + verse);
				}
			}
		});
	}

	/**
	 * Initialize a JavaFX tooltip for this VerseObject 
	 */
	private void initTooltip(){
		save = new Tooltip("Click to Bookmark");
		save.setStyle(fxmlStyles.tooltip_style.toString());
		save.setShowDelay(Duration.seconds(0));
	}

	/**
	 * Assign VerseObject to a node in FXML
	 * @param node
	 */
	public void setNode(Node node){
		this.thisNode = node;
	}

	
	/**
	 * Get index of the book this verse is located in
	 * @return
	 */
	public int getBookIdx() {
		return bookIdx;
	}

	/**
	 * Get index of the chapter this verse is located in
	 * @return
	 */
	public int getChapIdx() {
		return chapIdx;
	}

	/**
	 * Get index of the verse in its chapter
	 * @return
	 */
	public int getVerseIdx() {
		return verseIdx;
	}
}
