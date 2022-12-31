package com.ibcompsci_ia.Bible;

import java.util.ArrayList;
import java.util.HashMap;

import com.ibcompsci_ia.parser.findChapter;

public class Chapter {
	//has an arraylist of verses
	private String bookName;
	private int chapNo;
	HashMap<String,ArrayList<String>> VersesLangs;
	findChapter fc;
	
	public Chapter(String bookName, int chapNo, HashMap<String, ArrayList<String>> verses){
		this.bookName = bookName;
		this.chapNo = chapNo;
		//this.verses = verses;
		VersesLangs = verses;
	}

	//public void addVerseToChapter(){
		//VersesLangs.put("id",fc.getVersesinChapter(chapNo,"id"));
		//VersesLangs.put("en",fc.getVersesinChapter(chapNo,"en"));
	//}

	public ArrayList<String> getVerseinLang(String lang){
		System.out.println(bookName + ": " + chapNo);
		return VersesLangs.get(lang);
	}

	public static void main(String[] args) {
		//Chapter gen = new Chapter("Genesis",1);
		//System.out.println(gen.getVerse());
	}
}

//TEST LANGUAGE FEATURE
