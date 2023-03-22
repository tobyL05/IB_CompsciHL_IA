package com.ibcompsci_ia.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class htmtojson {

	//convert each book to json file
	public htmtojson() throws IOException{
		for(String s : CSVParser.books){
			saveFile(s,convert(s));
		}
		//saveFile(CSVParser.books.get(0),convert(CSVParser.books.get(0)));
	}
	
	public JSONObject convert(String bookName) throws IOException{
		JSONObject book = new JSONObject(); //new book jsonobject
		JSONObject lang;
		JSONObject chap;
		JSONArray chaps = new JSONArray(); //array of chapters
		ArrayList<String> verseseng;
		ArrayList<String> versesid;
		for(int i = 0;i < CSVParser.bookMap.get(bookName);i++){ //for each chapter
			chap = new JSONObject();
			lang = new JSONObject();
			verseseng = new findChapter(bookName).getVersesinChapter(i, 1);
			versesid = new findChapter(bookName).getVersesinChapter(i, 0);
			lang.put("en",verseseng);
			lang.put("id",versesid);
			chap.put(Integer.toString(i+1),lang); //put chap in chaps
			chaps.put(chap);
		}
		book.put(bookName,chaps);
		return book;
	}

	public void saveFile(String name,JSONObject obj){
		try {
			FileWriter fw = new FileWriter("src/main/resources/com/ibcompsci_ia/jsons/" + name +".json");
			fw.write(obj.toString());
			fw.close();
			System.out.println("saved " + name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) throws IOException {
		new CSVParser();
		new htmtojson();
		//File[] folder = new File("src/main/resources/com/ibcompsci_ia").listFiles();
		//for(File f:folder){
			//System.out.println(f.getName());
		//}
		//System.out.println(System.getProperty("user.dir"));

		
	}
}
