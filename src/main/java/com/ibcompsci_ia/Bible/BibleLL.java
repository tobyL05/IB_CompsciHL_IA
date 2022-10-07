package com.ibcompsci_ia.Bible;

import java.io.IOException;
import java.util.ArrayList;

import com.ibcompsci_ia.GUI.Models.biblePageModel;
import com.ibcompsci_ia.parser.CSVParser;
import com.ibcompsci_ia.users.Session;

public class BibleLL {
	
	Book head;
	Book LLparser;
	private ArrayList<String> contents; //arrlist of book names (Genesis, exodus, etc.)
	String bookNameandRef;
	public int currChapidx = 0;//0 = first chap
	private int currBookidx = 0; //0 = genesis

	public BibleLL(){
		head = new Book("Genesis");
		head.prev = null;
		contents = new ArrayList<>();
		contents.add("Genesis");
		LLparser = head;
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

	public ArrayList<String> getVerse(String bookName, String chapNo){ //searches using LLparser
		do{
			//System.out.println(LLparser.bookName + " " + LLparser.chapters.size());
			System.out.println("retrieving " + bookName + " " + chapNo);
			if(LLparser.bookName.equals(bookName) && LLparser.chapters.size() > Integer.parseInt(chapNo)){
				currChapidx = Integer.parseInt(chapNo);
				System.out.println(LLparser.chapters.get(currChapidx).getVerse());
				return LLparser.chapters.get(currChapidx).getVerse();
				//method to add verses to chapter obj
			}
		}while(LLparser.next != null);

		return null;
	}

	public ArrayList<String> getNextChap(){
		currChapidx++; //next chap
		if(LLparser.getChapters() > currChapidx){
			return LLparser.chapters.get(currChapidx).getVerse(); //get next chapter in same book
		}else{
			if(LLparser.next != null){
				LLparser = LLparser.next; //get next chapter in diff book if exist
			}else{ 
				currBookidx++;
				append(CSVParser.books.get(currBookidx)); //if does not exist add to LL (ITS BOOKINDEX NOT CHAPINDEX)
				LLparser = LLparser.next;
			}//make sure next page loads the next book/chapter. 
			currChapidx = 0;
			return LLparser.chapters.get(currChapidx).getVerse();
		}
		//on next page (iterate forwards)
	}

	public ArrayList<String> getPrevChap(){
		//on prev page (iterate backwards)
		if(currChapidx - 1 != 0){
			return LLparser.chapters.get(currChapidx - 1).getVerse();
		}else{
			LLparser = LLparser.prev; 
			//need the last chapter of prev book
			currChapidx = CSVParser.bookMap.get(CSVParser.books.get(CSVParser.books.indexOf(LLparser.bookName) - 1));
			return LLparser.chapters.get(currChapidx).getVerse();
		}
	}

	public static void main(String[] args) throws IOException{
		biblePageModel bpm = new biblePageModel();
		BibleLL bib = new BibleLL();
		System.out.println(bib.getVerse("Genesis","0"));
	}
}
