package com.ibcompsci_ia.Bible;

import java.util.ArrayList;
import java.util.HashMap;

import com.ibcompsci_ia.parser.findChapter;

public class Chapter {
	//has an arraylist of verses
	private String bookName;
	private int chapNo;
	private HashMap<String,ArrayList<VerseObject>> VersesLangs;
	findChapter fc;
	
	public Chapter(String bookName, int chapNo, HashMap<String, ArrayList<VerseObject>> verses){
		this.bookName = bookName;
		this.chapNo = chapNo;
		//this.verses = verses;
		this.VersesLangs = verses;
	}

	public ArrayList<VerseObject> getVerseinLang(String lang){
		System.out.println(bookName + ": " + chapNo);
		return VersesLangs.get(lang);
	}

	public ArrayList<VerseObject> getVerseinLang(int lang){
		System.out.println(bookName + ": " + chapNo);
		if(lang == 0){
			return VersesLangs.get("id");
		}else{
			return VersesLangs.get("en");
		}
	}

	public static void main(String[] args) {
		//Chapter gen = new Chapter("Genesis",1);
		//System.out.println(gen.getVerse());
	}
}

//TEST LANGUAGE FEATURE
