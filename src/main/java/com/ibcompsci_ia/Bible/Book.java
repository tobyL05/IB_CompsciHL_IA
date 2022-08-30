package com.ibcompsci_ia.Bible;

import java.util.ArrayList;

import com.ibcompsci_ia.GUI.Models.biblePageModel;

public class Book {
	Book prev;
	Book next;
	String bookName; //each node is a book
	ArrayList<Chapter> chapters; //each book has an arrlist of chapters
	int idx;

	public Book(String book){
		bookName = book;
		chapters = new ArrayList<>();
		idx = biblePageModel.chaps.indexOf(book);
	}

	public int getChapters(){
		return chapters.size();
	}

}
