package com.ibcompsci_ia.users;

import java.io.Serializable;
import java.util.ArrayList;

import com.ibcompsci_ia.Bible.VerseObject;
import com.ibcompsci_ia.GUI.Models.createAccountModel;


public class User implements Serializable{

	private String username;
	private String pwd;
	private String notesPath;
	private int currBookidx;
	private int currChapidx;
	private int currVerse;
	private String currLang; //en or id
	private ArrayList<String> bookName;
	private ArrayList<String> savedRefs; //saved verses
	private int ver;

	public User(String username, String pwd){
		System.out.println("Created user");
		this.username = username;
		this.pwd = pwd;
		this.notesPath = "";
		this.bookName = new ArrayList<String>();
		this.savedRefs = new ArrayList<String>();
		this.ver = 0;
		this.currBookidx = 0;
		this.currChapidx = 0;//first chapter
		this.currVerse = 1;
		this.currLang = "en";
	}

	//getters and setters 
	public String getCreds(){
		return username + "_" + pwd;
	}

	public int getVer(){
		return ver;
	}

	public byte[] getPwd(){
		return createAccountModel.encryptor(pwd);
	}

	public void setNotesPath(){
		//create a new txt file for notes
	}

	public void setBookName(String name){
		bookName.add(name);
	}

	public void addRef(String verse){
		savedRefs.add(verse);
		//add a ref to savedRefs
	}

	public void removeSavedVerse(int i){
		bookName.remove(i);
		savedRefs.remove(i);
	}

	public ArrayList<String> getSavedBooks(){
		return bookName;
	}

	public ArrayList<String> getSavedVerses(){
		return savedRefs;
	}

	public int getCurrBook(){
		return currBookidx;
	}

	public void setCurrBook(int currBook){
		this.currBookidx = currBook;	
	}

	public int getCurrChap(){
		return currChapidx;
	}

	public void setCurrChap(int currChap){
		this.currChapidx = currChap;
	}

	public int getCurrVerse(){
		return currVerse;
	}

	public void setCurrVerse(int currVerse){
		this.currVerse = currVerse;
	}

	public String getCurrLang(){
		return currLang;
	}

	public void setCurrLang(String currLang){
		this.currLang = currLang;	
	}


}
