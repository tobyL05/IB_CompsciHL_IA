package com.ibcompsci_ia.Bible;

import java.util.ArrayList;

import com.ibcompsci_ia.parser.findChapter;

public class Chapter {
	//has an arraylist of verses
	private ArrayList<String> verses;
	private String bookName;
	private String chapNo;
	
	public Chapter(String bookName, int chapNo, ArrayList<String> verses){
		this.bookName = bookName;
		this.chapNo = Integer.toString(chapNo);
		this.verses = verses;
	}

	public ArrayList<String> getVerse(){
		return verses;
	}

	public static void main(String[] args) {
		//Chapter gen = new Chapter("Genesis",1);
		//System.out.println(gen.getVerse());
	}
}
