package com.ibcompsci_ia.GUI.Models;

import java.util.ArrayList;
import java.util.HashMap;

import com.ibcompsci_ia.users.Session;

import javafx.scene.text.Text;
import javafx.util.Pair;

public class bookmarkModel {
	
	public ArrayList<Pair<String,String>> versesArrlist;
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
		versesArrlist = new ArrayList<>();
		verse = new HashMap<>();
		nodes = new HashMap<>();
		verse.clear();
	}
	
	public void updateVerses(){
		verse = Session.user.getBookmarks();
		for(String id:verse.keySet()){
			Pair<String,String> p = new Pair<String,String>(id, verse.get(id));
			versesArrlist.add(p);
		}
		
	}
	
	public void removeVerse(String id){ //remove from 
		Session.user.removeBookmark(id);
	}
	
}
