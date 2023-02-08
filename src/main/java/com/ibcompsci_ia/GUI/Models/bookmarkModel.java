package com.ibcompsci_ia.GUI.Models;

import java.util.HashMap;

import com.ibcompsci_ia.users.Session;

import javafx.scene.text.Text;

public class bookmarkModel {
	
	public HashMap<String, String> verse;
	public HashMap<String, Text> nodes;
	private static bookmarkModel instance = null; //singleton
	
	public static bookmarkModel getInstance(){
		if(instance == null){
			instance = new bookmarkModel();
		}
		return instance;
	}
	
	private bookmarkModel(){
		verse = new HashMap<>();
		nodes = new HashMap<>();
		verse.clear();
	}
	
	public void updateVerses(){
		verse = Session.user.getBookmarks();
	}
	
	public void removeVerse(String id){ //remove from 
		Session.user.removeBookmark(id);
	}
	
}
