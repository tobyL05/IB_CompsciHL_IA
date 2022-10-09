package com.ibcompsci_ia.Bible;

import java.util.ArrayList;

import com.ibcompsci_ia.parser.CSVParser;

public class Book {
	Book prev;
	Book next;
	String bookName; //each node is a book
	ArrayList<Chapter> chapters; //each book has an arrlist of chapters
	int numOfChap;
	int LLidx;

	public Book(String book){
		bookName = book;
		chapters = new ArrayList<Chapter>();
		//LLidx = biblePageModel.bookIdx.indexOf(book);
		numOfChap = CSVParser.bookMap.get(book);
		addChapters(); // add the book's chapters
	}

	private void addChapters(){
		for(int i = 0;i < numOfChap;i++){
			chapters.add(new Chapter(bookName,i));
		}
	}	

	public int getChapters(){
		return chapters.size();
	}

	//public static void main(String[] args) {
		//Book b = new Book("Genesis");
	//}

}
