package com.ibcompsci_ia.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibcompsci_ia.Main;


public class CSVParser {
	
	private final InputStream bookDataStream = Main.class.getResourceAsStream("books.csv");
	public static ArrayList<String> books = new ArrayList<String>();
	public static ArrayList<String> idBooks = new ArrayList<String>();
	public static ArrayList<String> files = new ArrayList<String>();
	public static HashMap<String, Integer> bookMap = new HashMap<>(); //(bookName, no of chaps)

	public CSVParser(){
		try{
			String line = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(bookDataStream));
			while((line = br.readLine()) != null){
				String[] bookdata = line.split(",");
				CSVParser.books.add(bookdata[0]); //eng
				CSVParser.bookMap.put(bookdata[0], Integer.parseInt(bookdata[1]));// (book name, size)
				CSVParser.idBooks.add(bookdata[2]); //id
				CSVParser.files.add(bookdata[3]);
			}
			br.close();
		}catch(FileNotFoundException fofe){
			System.out.println("Books csv not found");
		}catch(IOException ioe){
			System.out.println("io exception");
		}
	}
	public static void main(String[] args) {
		new CSVParser();
		System.out.println(CSVParser.books);
		
	}
}
