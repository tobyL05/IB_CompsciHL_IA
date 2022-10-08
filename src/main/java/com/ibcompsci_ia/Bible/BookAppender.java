package com.ibcompsci_ia.Bible;

import com.ibcompsci_ia.launch;
import com.ibcompsci_ia.parser.CSVParser;

public class BookAppender implements Runnable{
	private int bookIdx;
	public BookAppender(int bookIdx){
		this.bookIdx = bookIdx;
	}

	public void run(){
		System.out.println("Launching new thread");
		launch.bible.append(CSVParser.books.get(bookIdx));
	}
	
}
