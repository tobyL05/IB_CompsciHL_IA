package com.ibcompsci_ia.Bible;

import java.util.ArrayList;

import com.ibcompsci_ia.parser.findChapter;

public class Chapter {
	//has an arraylist of verses
	private int chapNo;
	private ArrayList<String> enVerses = new ArrayList<>();
	private ArrayList<String> idVerses = new ArrayList<>();
	findChapter fc;
	
	public Chapter(int chapNo, findChapter fc){
		this.chapNo = chapNo;
		this.fc = fc;
		getVersesfromHTML();
	}


	/**
	 * Parses HTML to add this chapter's verses
	 */
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
}

//TEST LANGUAGE FEATURE
