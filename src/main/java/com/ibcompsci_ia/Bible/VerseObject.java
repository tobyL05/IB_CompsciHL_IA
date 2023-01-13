package com.ibcompsci_ia.Bible;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VerseObject extends Text{
	
	private String verse;
	private int bookIdx;
	private int chapIdx;
	private int verseIdx;
	
	public VerseObject(int bookIdx, int chapIdx, int verseIdx,String verse){
		this.bookIdx = bookIdx;
		this.chapIdx = chapIdx;
		this.verseIdx = verseIdx;
		this.verse = verse;
		setText(verse);
		setFont(new Font("Verdana",14));
		setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				System.out.println("Saved: " + verse);
			}
			
		});
	}

}
