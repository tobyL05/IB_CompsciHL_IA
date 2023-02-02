package com.ibcompsci_ia.Bible;

import java.io.IOException;
import java.util.ArrayList;

import com.ibcompsci_ia.parser.CSVParser;
import com.ibcompsci_ia.parser.findChapter;

public class Book {
	String bookName; //each node is a book
	public ArrayList<Chapter> chapter; //each book has an arrlist of chapters
	int numOfChap;
	int bookIdx;
	findChapter fc;

	public Book(int idx) throws IOException{
		bookIdx = idx;
		bookName = CSVParser.books.get(idx);
		chapter = new ArrayList<Chapter>(); //to access a chapter Book.chapters.get()
		numOfChap = CSVParser.bookMap.get(bookName);
		fc = new findChapter(bookName);
	}
	//fix get chapters
	public void addChapters() throws IOException{
		for(int i = 0;i < numOfChap;i++){ //create a new thread to append each chapter
			ChapterAppender ca = new ChapterAppender(bookIdx, i,fc);
			Thread t = new Thread(ca);
			t.setDaemon(true);
			t.start();
			//chapter.add(new Chapter(bookName,i,fc.getVersesinChapter(i, "English")));
		}
		System.out.println("Appended all chapters for " + bookName);
	}	

	public int getNumChapters(){
		return chapter.size();
	}

	public String getBookName(){
		return bookName;
	}
	//public static void main(String[] args) {
		//Book b = new Book("Genesis");
	//}

}
