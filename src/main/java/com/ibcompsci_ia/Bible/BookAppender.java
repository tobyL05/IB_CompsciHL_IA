package com.ibcompsci_ia.Bible;

import java.io.IOException;

import com.ibcompsci_ia.launch;
import com.ibcompsci_ia.parser.CSVParser;

public class BookAppender implements Runnable{
	private int idx;
	private int size;

	public BookAppender(int idx,int size){
		this.idx = idx;
		this.size = size;
	}

	@Override
	public void run(){
		//System.out.println("Launching new thread");
		for(int i = idx;i < size ;i++){
			System.out.println("Appending: " + CSVParser.books.get(i) + " " + i);
			try {
				launch.bible.books[i] = new Book(i);
				launch.bible.books[i].addChapters();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}