package com.ibcompsci_ia.GUI.Models;

import java.util.ArrayList;

import com.ibcompsci_ia.users.Session;

public class bookmarkModel {
	
	private ArrayList<String> bookNames;
	private ArrayList<String> verses;

	
	public bookmarkModel(){
		this.bookNames = Session.user.getSavedBooks();	
		this.verses = Session.user.getSavedVerses();	
	}
	
	public ArrayList<String> getBookNames() {
		return bookNames;
	}

	public void setBookNames(ArrayList<String> bookNames) {
		this.bookNames = bookNames;
	}

	public ArrayList<String> getVerses() {
		return verses;
	}

	public void setVerses(ArrayList<String> verses) {
		this.verses = verses;
	}


	//handle delete bookmarks
}
