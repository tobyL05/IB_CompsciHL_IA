package com.ibcompsci_ia.GUI.Models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.ibcompsci_ia.users.Session;
import com.ibcompsci_ia.parser.CSVParser;

public class biblePageModel {

	private int currBookidx;
	private int currChapidx;
	//private int currVerse;
	private String currLang;
	private ArrayList<String> verses;
	Queue<String> scenes;
	private static biblePageModel instance = null; //this class is a singleton
	
	private biblePageModel(){
		//read in cbox input
		//this.currBookidx = Session.user.getCurrBook(); //get index of the current book
		//this.currChapidx = Session.user.getCurrChap();
		////this.currVerse = Session.user.getCurrVerse();
		//this.currLang = Session.user.getCurrLang();

		String biblefxml = "biblePage";
		String duolang = "duolangPage";
		scenes = new LinkedList<String>();
		scenes.add(biblefxml);
		scenes.add(duolang);
	}

	public void updateidx(){

	}

	public static biblePageModel getInstance() throws IOException{
		if(instance == null){
			instance = new biblePageModel();
		}
		instance.currBookidx = Session.user.getCurrBook();
		instance.currChapidx = Session.user.getCurrChap();
		instance.currLang = Session.user.getCurrLang();
		return instance;
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
		Session.user.setCurrBook(idx);
	}

	public int getCurrChapidx(){
		return currChapidx;
	}

	public void setCurrChapidx(int idx){
		currChapidx = idx;
		Session.user.setCurrChap(idx);
	}

	public void incCurrChap(){
		currChapidx++;
		if(currChapidx == CSVParser.bookMap.get(CSVParser.books.get(currBookidx)) && currBookidx < 65){//if next chap
			currBookidx++;
			currChapidx = 0;
		}else if(currBookidx == 65 && currChapidx == 22){
			currChapidx--;;
		}
		Session.user.setCurrBook(currBookidx);
		Session.user.setCurrChap(currChapidx);
	}

	public void decCurrChap(){
		currChapidx--;
		if(currChapidx < 0 && currBookidx != 0){ //if prev chap
			currBookidx--;
			currChapidx = CSVParser.bookMap.get(CSVParser.books.get(currBookidx))-1;
		}else if(currChapidx < 0 && currBookidx == 0){ //if no prev chap (genesis)
			currChapidx = 0;
		}
		Session.user.setCurrBook(currBookidx);
		Session.user.setCurrChap(currChapidx);
	}

	public ArrayList<String> getVerses(){
		return verses;
	}

	public String getCurrLang(){
		return currLang;
	}

	public void setCurrLang(String lang){
		Session.user.setCurrLang(lang);
		currLang = lang;
	}

	public String getNextScene(){
		String nextScene = scenes.remove();
		scenes.add(nextScene);
		System.out.println("Next scene: " + nextScene);
		return nextScene;
	}

	//change scenes from here
	//save last index of biblePage for next press
	//Scene 1: all eng	fxml:biblePage,fc idx 1
	//scene 2: duo lang fxml:duolangPage
	//scene 3: all id fxml:biblePage,fc idx 0. SAME AS SCENE 1 BUT CHANGE THE IDX BEFORE REQUEING
	//use a queue that requeues whatever is dequeued

}
