package com.ibcompsci_ia.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibcompsci_ia.Enums.paths;

public class CSVParser {
	
	private final String bookData = getClass().getResource(paths.resourcePath.toString() + "books.csv").getPath();
	public static ArrayList<String> books = new ArrayList<String>();
	public static HashMap<String, Integer> bookMap = new HashMap<>(); //(bookName, no of chaps)

	public CSVParser(){
		try{
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader(bookData));
			while((line = br.readLine()) != null){
				String[] bookdata = line.split(",");
				CSVParser.books.add(bookdata[0]);
				CSVParser.bookMap.put(bookdata[0], Integer.parseInt(bookdata[1]));// (book name, size)
				System.out.println(bookdata[0] + " " + bookdata[1]);
			}
			System.out.println(CSVParser.books);
			br.close();
		}catch(FileNotFoundException fofe){
			System.out.println("Books csv not found");
		}catch(IOException ioe){
			System.out.println("io exception");
		}
	}
}
