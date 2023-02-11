package com.ibcompsci_ia.Bible;

import java.io.IOException;
import java.util.ArrayList;

import com.ibcompsci_ia.parser.CSVParser;
import com.ibcompsci_ia.parser.findChapter;

public class Book {
	String bookName; //each node is a book
	public ArrayList<Chapter> chapter = new ArrayList<>(); //each book has an arrlist of chapters
	int numOfChap;
	int bookIdx;
	findChapter fc;

	public Book(int idx) throws IOException{
		bookIdx = idx;
		bookName = CSVParser.books.get(idx);
		numOfChap = CSVParser.bookMap.get(bookName);
		fc = new findChapter(bookName);
		addChapters();
	}

	/**
	 * Adds all the chapters in the current Book.
	 * @throws IOException
	 */
	public void addChapters() throws IOException{
		for(int i = 0;i < numOfChap;i++){//for each 
			chapter.add(new Chapter(i,fc));
		}	
	}	

	/**
	 * Returns the name of the book
	 * @return
	 */
	public String getBookName(){
		return bookName;
	}

	//public static void main(String[] args) {
		//try {
			//Book b = new Book(1);
		//} catch (IOException e) {
			//e.printStackTrace();
		//
	//
}

