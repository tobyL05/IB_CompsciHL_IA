package com.ibcompsci_ia.Bible;

import java.util.ArrayList;

public class chapLinkedList {
	
	Book head;
	private ArrayList<String> contents;
	String bookNameandRef;

	public chapLinkedList(){
		head = new Book("Genesis");
		contents.add("Genesis");
	}

	public void append(String book){
		Book n = new Book(book);
		contents.add(n.bookName);

		Book curr = head;
		while(curr.next != null){
			curr = curr.next;
		}
		n.next = null;
		n.prev = curr;
		curr.next = n;
	}

	public int getSize(){
		return contents.size();
	}

	public ArrayList<String> getContents(){
		return contents;
	}

}
