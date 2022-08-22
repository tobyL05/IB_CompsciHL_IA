package com.ibcompsci_ia.GUI.Models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibcompsci_ia.Enums.paths;
import com.ibcompsci_ia.parser.findChapter;
import com.ibcompsci_ia.users.Session;

public class biblePageModel {

	private String currBook;
	private String currChap;
	private int currVerse;
	private String currLang;
	private final String bookCSV = getClass().getResource(paths.resourcePath.toString() + "books.csv").getPath();
	public ArrayList<String> books = new ArrayList<String>();
	public HashMap<String, Integer> bookMap = new HashMap<>();
	private ArrayList<String> verses;

	
	public biblePageModel() throws IOException{
		//read in cbox input
		this.currBook = Session.user.getCurrBook();
		this.currChap = Session.user.getCurrChap();
		this.currVerse = Session.user.getCurrVerse();
		this.currLang = Session.user.getCurrLang();

		findChapter fc = new findChapter(this.currBook);
		verses = fc.getVersesinChapter(currChap, currLang);
		
		readCSV();
	}

	private HashMap<String,Integer> readCSV(){
		//read csv
		try{
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader(bookCSV));
			while((line = br.readLine()) != null){
				String[] bookdata = line.split(",");
				books.add(bookdata[0]);
				bookMap.put(bookdata[0],Integer.parseInt(bookdata[1]));
				//chaps.add(Integer.parseInt(bookdata[1]));
			}
			br.close();

		}catch(FileNotFoundException e){
			System.out.println("Book data not found");
		}catch(IOException io){
			System.out.println("IO Exception");
		}
		return bookMap;
	}


	public void findChapFromCbox(String book, String string, int verse){
		//get and parse input from cbox
		//call findchapter to find search
		//update scene
		System.out.println(book + " " + string + " " + verse);
	}

	public String getCurrBook(){
		return currBook;
	}

	public String getCurrChap(){
		return currChap;
	}

	public ArrayList<String> getVerses(){
		return verses;
	}

}
