package com.ibcompsci_ia.Bible;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BibleArr {
	
	public Book[] books;
	public ArrayList<String> contents; //arrlist of book names (Genesis, exodus, etc.)
	String bookNameandRef;
	public int currChapidx = 0;//0 = first chap
	public int currBookidx = 0; //0 = genesis, take from CSVParser
	ExecutorService threads = Executors.newFixedThreadPool(30);

	/**
	 * Instantiate books[] array to store all 66 books of the Bible
	 * @throws IOException
	 */
	public BibleArr() throws IOException{
		books = new Book[66];
	}
	
	/**
	 * Launches a Thread to add each Book in the Bible to books[]
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void addBooks() throws InterruptedException, IOException{
		System.out.println("Adding books");
		for(int i = 0; i < 66;i++){ //add all the books
			BookAppender ba = new BookAppender(i);
			Thread b = new Thread(ba);
			b.setDaemon(true);
			b.start();
		}
	}

	//public static void main(String[] args) {
		//BibleArr b = new BibleArr();
	//}

}
