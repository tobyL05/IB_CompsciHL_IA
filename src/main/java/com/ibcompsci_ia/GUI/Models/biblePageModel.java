package com.ibcompsci_ia.GUI.Models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import com.ibcompsci_ia.users.Session;
import com.ibcompsci_ia.parser.CSVParser;

public class biblePageModel {

	private int currBookidx;
	private int currChapidx;
	//private int currVerse;
	private String currLang;
	private ArrayList<String> verses;
	
	public biblePageModel() throws IOException{
		//read in cbox input
		this.currBookidx = CSVParser.books.indexOf(Session.user.getCurrBook()); //get index of the current book
		this.currChapidx = Integer.parseInt(Session.user.getCurrChap());
		//this.currVerse = Session.user.getCurrVerse();
		this.currLang = Session.user.getCurrLang();
		//check LL of chapters
	}

	public void findChapFromCbox(String book, String chapNo, String verseNo){
		//get and parse input from cbox
		//call findchapter to find search
		//if string2 is 0, get whole chapter.
		//if string 2 not 0, get exact verse
		//update scene
		System.out.println(book + " " + chapNo + " " + verseNo);
	}

	public int getCurrBookidx(){
		return currBookidx;
	}

	public void setCurrBookidx(int idx){
		currBookidx = idx;
	}

	public int getCurrChapidx(){
		return currChapidx;
	}

	public void setCurrChapidx(int idx){
		currChapidx = idx;
	}

	public void incCurrChap(){
		currChapidx++;
		if(currChapidx == CSVParser.bookMap.get(CSVParser.books.get(currBookidx))){//if next chap
			currBookidx++;
			currChapidx = 0;
		}
	}

	public void decCurrChap(){
		currChapidx--;
		if(currChapidx < 0 && currBookidx != 0){ //if prev chap
			currBookidx--;
			currChapidx = CSVParser.bookMap.get(CSVParser.books.get(currBookidx))-1;
		}else if(currChapidx < 0 && currBookidx == 0){ //if no prev chap (genesis)
			currChapidx = 0;
		}
	}

	public ArrayList<String> getVerses(){
		return verses;
	}

}
