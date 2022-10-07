package com.ibcompsci_ia.Bible;

import java.util.ArrayList;

import com.ibcompsci_ia.parser.findChapter;

public class Chapter {
	//has an arraylist of verses
	private ArrayList<String> verses;
	public String bookName;
	public String chapNo;
	
	public Chapter(String bookName, int chapNo){
		this.bookName = bookName;
		this.chapNo = Integer.toString(chapNo);
		verses = findChapter.staticgetVerseinChapter(bookName, this.chapNo);
		System.out.println("adding " + this.bookName + this.chapNo);
	}

	public ArrayList<String> getVerse(){
		return verses;
	}
	
	public static void main(String[] args) {
		Chapter gen = new Chapter("Genesis",1);
		System.out.println(gen.getVerse());
	}
}
