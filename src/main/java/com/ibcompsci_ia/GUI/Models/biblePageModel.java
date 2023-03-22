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
		
		String biblefxml = "biblePage";
		String duolang = "duolangPage";
		scenes = new LinkedList<String>();
		scenes.add(biblefxml);
		scenes.add(duolang);
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

}
