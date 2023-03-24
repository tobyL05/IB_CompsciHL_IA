package com.ibcompsci_ia.users;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.ibcompsci_ia.GUI.Models.createAccountModel;


public class User implements Serializable{

	private String username;
	private String pwd;
	private int currBookidx;
	private int currChapidx;
	private int currVerse;
	private String currLang; //en or id
	private LinkedHashMap<String,String> savedVerses; //sort by id. by book then chapter then verse
	private int ver;

	/**
	 * Instantiate new user and their data
	 * @param username
	 * @param pwd
		*/
		public User(String username, String pwd){
		System.out.println("Created user");
		this.username = username;
		this.pwd = pwd;
		this.savedVerses = new LinkedHashMap<>();
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

	public HashMap<String, String> getBookmarks(){
		return savedVerses;
	}

	public void saveVerse(String verseId, String verse){
		savedVerses.put(verseId,verse);
	}

	public void removeBookmark(String verseId){
		savedVerses.remove(verseId);
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
