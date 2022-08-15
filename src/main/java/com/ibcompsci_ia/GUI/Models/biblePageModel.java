package com.ibcompsci_ia.GUI.Models;

import java.io.IOException;
import java.util.ArrayList;

import com.ibcompsci_ia.parser.findChapter;
import com.ibcompsci_ia.users.Session;

public class biblePageModel {

	private String currBook;
	private int currChap;
	private int currVerse;
	private String currLang;
	ArrayList<String> verses;

	
	public biblePageModel() throws IOException{
		//read in cbox input
		this.currBook = Session.user.getCurrBook();
		this.currChap = Session.user.getCurrChap();
		this.currVerse = Session.user.getCurrVerse();
		this.currLang = Session.user.getCurrLang();

		findChapter fc = new findChapter(this.currBook);
		verses = fc.getVersesinChapter(currChap, currLang);
	}

	public void findChapFromCbox(String book, int chap, int verse){
		//get and parse input from cbox
		//call findchapter to find search
		//update scene
		System.out.println(book + " " + chap + " " + verse);
	}

	public String getCurrBook(){
		return currBook;
	}

	public int getCurrChap(){
		return currChap;
	}

	public ArrayList<String> getVerses(){
		return verses;
	}

}
