package com.ibcompsci_ia.Bible;

import java.util.ArrayList;

import com.ibcompsci_ia.parser.findChapter;

public class Chapter {
	//has an arraylist of verses
	private String bookName;
	private int chapNo;
	private ArrayList<String> enVerses = new ArrayList<>();
	private ArrayList<String> idVerses = new ArrayList<>();
	findChapter fc;
	
	public Chapter(String bookName, int chapNo, findChapter fc){
		this.bookName = bookName;
		this.chapNo = chapNo;
		this.fc = fc;
		getVersesfromHTML();
		//System.out.println(bookName + " " + Arrays.toString(verses.entrySet().toArray()));
		//System.out.println();
	}

	private void getVersesfromHTML(){
		enVerses = fc.getVersesinChapter(chapNo, 1);
		idVerses = fc.getVersesinChapter(chapNo, 0);
		//System.out.println("Added " + bookName + " " + chapNo);
	}

	public ArrayList<String> getEnVerses() {
		return enVerses;
	}

	public ArrayList<String> getIdVerses() {
		return idVerses;
	}
	

	public static void main(String[] args) {
		//Chapter gen = new Chapter("Genesis",1);
		//System.out.println(gen.getVerse());
	}
}

//TEST LANGUAGE FEATURE
