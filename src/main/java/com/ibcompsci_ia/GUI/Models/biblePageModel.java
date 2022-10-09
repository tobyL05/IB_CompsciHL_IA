package com.ibcompsci_ia.GUI.Models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import com.ibcompsci_ia.users.Session;

public class biblePageModel {

	private String currBook;
	private String currChap;
	//private int currVerse;
	private String currLang;
	private ArrayList<String> verses;
	
	public biblePageModel() throws IOException{
		//read in cbox input
		this.currBook = Session.user.getCurrBook();
		this.currChap = Session.user.getCurrChap();
		//this.currVerse = Session.user.getCurrVerse();
		this.currLang = Session.user.getCurrLang();
		//check LL of chapters
		//load genesis (default)
		//verses = launch.bible.
	}

	public void findChapFromCbox(String book, String chapNo, String verseNo){
		//get and parse input from cbox
		//call findchapter to find search
		//if string2 is 0, get whole chapter.
		//if string 2 not 0, get exact verse
		//update scene
		System.out.println(book + " " + chapNo + " " + verseNo);
	}

	public String getCurrBook(){
		return currBook;
	}

	public void setCurrBook(String bookName){
		currBook = bookName;
	}

	public String getCurrChap(){
		return currChap;
	}

	public void incCurrChap(){
		currChap = Integer.toString(Integer.parseInt(currChap) + 1);
	}

	public void decCurrChap(){
		currChap = Integer.toString(Integer.parseInt(currChap) - 1);
		if(Integer.parseInt(currChap) - 1 < 0){
			resetChap();
		}
	}

	public void resetChap(){
		currChap = "0";
	}

	public ArrayList<String> getVerses(){
		return verses;
	}

}
