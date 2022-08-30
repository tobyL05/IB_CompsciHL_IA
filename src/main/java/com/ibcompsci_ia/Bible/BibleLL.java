package com.ibcompsci_ia.Bible;

import java.util.ArrayList;

public class BibleLL {
	
	Book head;
	private ArrayList<String> contents;
	String bookNameandRef;

	public BibleLL(){
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

	public boolean contains(String s){
		if(contents.contains(s)){
			return true;
		}
		return false;
	}

	public int getSize(){
		return contents.size();
	}

	public ArrayList<String> getContents(){
		return contents;
	}

}
