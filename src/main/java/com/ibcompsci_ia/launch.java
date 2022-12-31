package com.ibcompsci_ia;

import java.io.IOException;

import com.ibcompsci_ia.Bible.BibleArr;
import com.ibcompsci_ia.parser.CSVParser;

public class launch {

	public static BibleArr bible;
	public static void main(String[] args) throws InterruptedException, IOException {
		new Thread(){
			@Override
			public void run(){
				javafx.application.Application.launch(Main.class);
			}
		}.start();
		//run navigator here
		new CSVParser();
		System.out.println("Initialized CSVParser");
		launch.bible = new BibleArr(); //appending books and chapters still quite slow but works.
		launch.bible.addBooks();
	}
}	

//TODO
//SAVE CURRENT BOOK/CHAP
//EN/ID SWITCH