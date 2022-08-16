package com.ibcompsci_ia.GUI.Models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.ibcompsci_ia.Enums.paths;
import com.ibcompsci_ia.parser.findChapter;
import com.ibcompsci_ia.users.Session;

public class biblePageModel {

	private String currBook;
	private int currChap;
	private int currVerse;
	private String currLang;
	private final String bookCSV = getClass().getResource(paths.resourcePath.toString()).getPath();
	public String[] books = new String[66];
	public Integer[] chaps = new Integer[66];
	private ArrayList<String> verses;

	
	public biblePageModel() throws IOException{
		//read in cbox input
		this.currBook = Session.user.getCurrBook();
		this.currChap = Session.user.getCurrChap();
		this.currVerse = Session.user.getCurrVerse();
		this.currLang = Session.user.getCurrLang();

		findChapter fc = new findChapter(this.currBook);
		verses = fc.getVersesinChapter(currChap, currLang);
	}

	private void readCSV(){
		//read csv
		try{
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader(bookCSV));
			int i=0;
			while((line = br.readLine()) != null){
				String[] bookdata = line.split(",");
				books[i] = bookdata[0];
				chaps[i] = Integer.parseInt(bookdata[1]);
				i++;
			}
			br.close();

		}catch(FileNotFoundException e){
			System.out.println("Book data not found");
		}catch(IOException io){
			System.out.println("IO Exception");
		}
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
