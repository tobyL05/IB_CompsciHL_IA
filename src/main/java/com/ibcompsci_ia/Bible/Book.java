package com.ibcompsci_ia.Bible;

import java.util.ArrayList;

public class Book {
	Book prev;
	Book next;
	String bookName; //each node is a book
	ArrayList<Chapter> chapters; //each book has an arrlist of chapters

	public Book(String book){
		bookName = book;
		chapters = new ArrayList<>();
	}
}
