package com.ibcompsci_ia.Bible;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.print.attribute.HashAttributeSet;

import com.ibcompsci_ia.launch;
import com.ibcompsci_ia.parser.CSVParser;
import com.ibcompsci_ia.parser.findChapter;

public class Book {
	String bookName; //each node is a book
	public ArrayList<Chapter> chapter = new ArrayList<>(); //each book has an arrlist of chapters
	int numOfChap;
	int bookIdx;
	findChapter fc;

	public Book(int idx) throws IOException{
		bookIdx = idx;
		bookName = CSVParser.books.get(idx);
		numOfChap = CSVParser.bookMap.get(bookName);
		fc = new findChapter(bookName);
		addChapters();
	}

	//fix get chapters
	public void addChapters() throws IOException{
		for(int i = 0;i < numOfChap;i++){//for each 
			//add chapters here
			chapter.add(new Chapter(bookName,i,fc));
			//System.out.println("Appending " + bookName + " " + i);
		}	
		
		//for(int i = 0;i < numOfChap;i++){ //problem is here
			//verses.clear();
			//verses.put("en",fc.getVersesinChapter(i, 1));
			//verses.put("id",fc.getVersesinChapter(i, 0));
			////System.out.println(Arrays.toString(verses.entrySet().toArray()));
			////System.out.println();
			////System.out.println(bookName + " " + i + " : " + fc.getVersesinChapter(i, 0).size());
			//Chapter c = new Chapter(bookName, i ,verses);
			//launch.bible.books[bookIdx].chapter.add(c);
			////System.out.println(c);
			////chapter.add(new Chapter(bookName,i,verses));

			//ChapterAppender ca = new ChapterAppender(bookIdx, i,fc);
			//Thread t = new Thread(ca);
			//t.setDaemon(true);
			//t.start();
			//chapter.add(new Chapter(bookName,i,fc.getVersesinChapter(i, "English")));
			System.out.println("Appended all chapters for " + bookName); //verseslangs 0 here
	}	

	public int getNumChapters(){
		return chapter.size();
	}

	public String getBookName(){
		return bookName;
	}

	public static void main(String[] args) {
		try {
			Book b = new Book(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

