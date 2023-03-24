package com.ibcompsci_ia.GUI.Models;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ibcompsci_ia.users.Session;
import com.ibcompsci_ia.Main;
import com.ibcompsci_ia.parser.CSVParser;

public class biblePageModel {

	private int currBookidx;
	private int currChapidx;
	//private int currVerse;
	private String currLang;
	private ArrayList<String> verses;
	Queue<String> scenes;
	private static biblePageModel instance = null; //this class is a singleton
	
	//put findChapter here?
	private biblePageModel(){
		
		String biblefxml = "biblePage";
		String duolang = "duolangPage";
		scenes = new LinkedList<String>();
		scenes.add(biblefxml);
		scenes.add(duolang);
	}

	public static biblePageModel getInstance() throws IOException{
		if(instance == null){
			instance = new biblePageModel();
		}
		instance.currBookidx = Session.user.getCurrBook();
		instance.currChapidx = Session.user.getCurrChap();
		instance.currLang = Session.user.getCurrLang();
		return instance;
	}

    public int getChapSize(int bookNo, int chapNo) throws IOException{
        InputStream file = Main.class.getResourceAsStream("jsons/" + CSVParser.files.get(bookNo));
        String jsontxt = IOUtils.toString(file,"UTF-8");
        JSONObject book = new JSONObject(jsontxt);
        JSONArray jsonarr = book.getJSONObject(CSVParser.books.get(bookNo)).getJSONObject(Integer.toString(chapNo + 1)).getJSONArray("en");
        file.close();
		file = null;
        jsontxt=null;
        book = null;
        return jsonarr.length();
    }

    public ArrayList<String> getVersesinChapter(int bookNo,int chapNo,String lang) throws IOException{
        InputStream file = Main.class.getResourceAsStream("jsons/" + CSVParser.files.get(bookNo));
        String jsontxt = IOUtils.toString(file,"UTF-8");
        JSONObject book = new JSONObject(jsontxt);
        JSONArray jsonarr = book.getJSONObject(CSVParser.books.get(bookNo)).getJSONObject(Integer.toString(chapNo + 1)).getJSONArray(lang);
        ArrayList<String> verses = new ArrayList<>(jsonarr.length());
        for(int i = 0;i<jsonarr.length();i++){
            verses.add(jsonarr.get(i).toString());
        }
        file.close();
        jsontxt=null;
        book = null;
        jsonarr = null;
        return verses;
    }

	public int getCurrBookidx(){
		return currBookidx;
	}

	public void setCurrBookidx(int idx){
		currBookidx = idx;
		Session.user.setCurrBook(idx);
	}

	public int getCurrChapidx(){
		return currChapidx;
	}

	public void setCurrChapidx(int idx){
		currChapidx = idx;
		Session.user.setCurrChap(idx);
	}

	public void incCurrChap(){
		currChapidx++;
		if(currChapidx == CSVParser.bookMap.get(CSVParser.books.get(currBookidx)) && currBookidx < 65){//if next chap
			currBookidx++;
			currChapidx = 0;
		}else if(currBookidx == 65 && currChapidx == 22){
			currChapidx--;;
		}
		Session.user.setCurrBook(currBookidx);
		Session.user.setCurrChap(currChapidx);
	}

	public void decCurrChap(){
		currChapidx--;
		if(currChapidx < 0 && currBookidx != 0){ //if prev chap
			currBookidx--;
			currChapidx = CSVParser.bookMap.get(CSVParser.books.get(currBookidx))-1;
		}else if(currChapidx < 0 && currBookidx == 0){ //if no prev chap (genesis)
			currChapidx = 0;
		}
		Session.user.setCurrBook(currBookidx);
		Session.user.setCurrChap(currChapidx);
	}

	public ArrayList<String> getVerses(){
		return verses;
	}

	public String getCurrLang(){
		return currLang;
	}

	public void setCurrLang(String lang){
		Session.user.setCurrLang(lang);
		currLang = lang;
	}

	public String getNextScene(){
		String nextScene = scenes.remove();
		scenes.add(nextScene);
		System.out.println("Next scene: " + nextScene);
		return nextScene;
	}

}
