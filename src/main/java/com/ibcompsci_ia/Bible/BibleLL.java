package com.ibcompsci_ia.Bible;

import java.util.ArrayList;
import com.ibcompsci_ia.parser.CSVParser;

public class BibleLL {
	
	Book head;
	Book LLparser;
	private ArrayList<String> contents; //arrlist of book names (Genesis, exodus, etc.)
	String bookNameandRef;
	public int currChapidx = 0;//0 = first chap
	public int currBookidx = 0; //0 = genesis, take from CSVParser

	public BibleLL(){
		contents = new ArrayList<>();
		head = new Book("Genesis");
		head.prev = null;
		contents.add("Genesis");
		LLparser = head;
		//for(int i = 1;i<CSVParser.books.size();i++){ //append everything
			//append(CSVParser.books.get(i));
		//}
	}

	public void add(String book){
		Book n = new Book(book);
		contents.add(n.bookName);

		Book curr = head;
		System.out.println(curr.bookName + " " + curr.LLidx);
		while(curr.LLidx < CSVParser.books.indexOf(book) && curr.next != null){ //navigate to index in LL based on csvparser.books
			curr = curr.next;
			System.out.println(curr.bookName + " " + curr.LLidx);
		}
		n.next = curr.next;
		n.prev = curr;
		curr.next = n;

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

	public int getSize(){ //how many books
		return contents.size();
	}

	public ArrayList<String> getContents(){ //get list of books
		return contents;
	}

	public String getHeader(){
		return LLparser.bookName + " " + Integer.toString(currChapidx + 1);
	}

	public ArrayList<String> findChap(String bookName, String chapNo){ //searches using LLparser
		LLparser = head;
		while(LLparser.next != null){ //traverse until right book is found
			if(LLparser.bookName.equals(bookName)){
				break;
			}
			LLparser = LLparser.next;
		}
		currChapidx = Integer.parseInt(chapNo);
		return LLparser.chapters.get(currChapidx).getVerse();
		
	}

	public ArrayList<String> getFirstVerse(String bookName, String chapNo){
		
		do{
			//System.out.println(LLparser.bookName + " " + LLparser.chapters.size());
			System.out.println("retrieving " + bookName + " " + chapNo);
			if(LLparser.bookName.equals(bookName) && LLparser.chapters.size() > Integer.parseInt(chapNo)){//make sure bookName is correct
				System.out.println(LLparser.bookName);
				currChapidx = Integer.parseInt(chapNo);
				System.out.println(LLparser.chapters.get(currChapidx).getVerse());
				return LLparser.chapters.get(currChapidx).getVerse();
				//method to add verses to chapter obj
			}
			LLparser = LLparser.next;
		}while(LLparser.next != null);

		return null;
	}

	public ArrayList<String> getNextChap(){
		currChapidx++; //next chap
		//System.out.println("LLparser get chap: " + LLparser.getChapters());
		//System.out.println("LL parser next: " + LLparser.next);
		if(LLparser.getChapters() - 10 == currChapidx && LLparser.next == null){//load next chap
			BookAppender appender = new BookAppender(currBookidx + 1);
			new Thread(appender).start();
		}else if(currChapidx >= LLparser.getChapters() && LLparser.next != null){//go to next chap after preloading it when chapidx greater
			System.out.println("Loading next chap");
			//currBookidx++;
			currChapidx = 0;
			LLparser = LLparser.next;
			currBookidx = LLparser.LLidx;
		}else if(currChapidx >= LLparser.getChapters() && LLparser.next == null){
			return null;
		}
		return LLparser.chapters.get(currChapidx).getVerse(); //on next page (iterate forwards)
	}

	public ArrayList<String> getPrevChap(){ //cannot go to prev chapter in a new book
		//on prev page (iterate backwards)
		currChapidx--;
		if(currBookidx == 0 && currChapidx < 0){ //first chapter in the bible (no prev)
			currChapidx = 0;
			System.out.println("1 current chap idx: " + currChapidx);
			return null;
		}else if(currChapidx < 0){//prev chap diff book
			LLparser = LLparser.prev; //DO THIS FIRST
			currChapidx = CSVParser.bookMap.get(CSVParser.books.get((CSVParser.books.indexOf(LLparser.bookName)))) - 1	;	//need the last chapter of prev book
			currBookidx = LLparser.LLidx;
			System.out.println("Curr book idx: " + currBookidx);
			System.out.println("2 current chap idx: " + currChapidx);
			//return LLparser.chapters.get(currChapidx).getVerse();
		}
		//}else{ //prev chap same book
			//System.out.println("3 current chap idx: " + currChapidx);
			//return LLparser.chapters.get(currChapidx).getVerse();
		//}
		return LLparser.chapters.get(currChapidx).getVerse();
	}

	//public static void main(String[] args) throws IOException{
		//biblePageModel bpm = new biblePageModel();
		//BibleLL bib = new BibleLL();
		//System.out.println(bib.getVerse("Genesis","0"));
	//}
}
