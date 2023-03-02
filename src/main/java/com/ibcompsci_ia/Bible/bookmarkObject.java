package com.ibcompsci_ia.Bible;

import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.Enums.fxmlStyles;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Extends javafx.scene.text to create my own implementation of a custom clickable text.
 */

public class bookmarkObject extends Text{
	
	private String id;
	private Tooltip del;
	private Node thisNode;
	
	/**
	 * Instantiate a bookmark with its id and verse
	 * @param id
	 * @param verse
	 */
	public bookmarkObject(String id, String verse){
		super(verse);
		this.id = id;
		initTooltip();
		setFont(new Font("Verdana",14));
		setOnMouseEntered(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				setUnderline(true);
				Tooltip.install(thisNode,del);
				Main.getScene().setCursor(Cursor.HAND);
			}
		});

		setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				setUnderline(false);
				Tooltip.uninstall(thisNode, del);
				Main.getScene().setCursor(Cursor.DEFAULT);
			}
		});
	}

	
	/**
	 * Assign bookmark object to a node in FXML
	 * @param node
	 */
	public void setNode(Node node){
		this.thisNode = node;
	}

	public String getIdx(){
		return id;
	}

	/**
	 * Initialize JavaFX tooltip for this object 
	 */
	private void initTooltip(){
		del = new Tooltip("Delete?");
		del.setStyle(fxmlStyles.tooltip_style.toString());
		del.setShowDelay(Duration.seconds(0));
	}

}
