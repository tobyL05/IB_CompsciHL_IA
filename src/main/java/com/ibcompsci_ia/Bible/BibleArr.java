package com.ibcompsci_ia.Bible;

import java.io.IOException;
import java.util.ArrayList;

public class BibleArr {
	
	public Book[] books;
	public ArrayList<String> contents; //arrlist of book names (Genesis, exodus, etc.)
	String bookNameandRef;
	public int currChapidx = 0;//0 = first chap
	public int currBookidx = 0; //0 = genesis, take from CSVParser

	public BibleArr(){
		books = new Book[66];
	}
	
	public void addBooks() throws InterruptedException, IOException{
		//BookAppender ba = new BookAppender(0,66);
		//Thread t = new Thread(ba);
		//t.setDaemon(true);	
		//t.start();
		BookAppender ot = new BookAppender(0,39); 
		BookAppender nt = new BookAppender(39,66);
		Thread otThread = new Thread(ot);
		Thread ntThread = new Thread(nt);
		otThread.setDaemon(true);
		ntThread.setDaemon(true);
		otThread.start();
		ntThread.start();
		//otThread.join();
		//ntThread.join();
		//addChaps();
	}


	public void addBooks(int start, int end){//append books from user settings
		//BookAppender ba = new BookAppender(0,66);
		//Thread t = new Thread(ba);
		//t.setDaemon(true);	
		//t.start();
		BookAppender ot = new BookAppender(0,39); //start appending books from model
		BookAppender nt = new BookAppender(39,66);
		Thread otThread = new Thread(ot);
		Thread ntThread = new Thread(nt);
		otThread.setDaemon(true);
		ntThread.setDaemon(true);
		otThread.start();
		ntThread.start();
	}

}
