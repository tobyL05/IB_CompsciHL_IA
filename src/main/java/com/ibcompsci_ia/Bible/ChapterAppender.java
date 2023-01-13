package com.ibcompsci_ia.Bible;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibcompsci_ia.launch;
import com.ibcompsci_ia.parser.CSVParser;
import com.ibcompsci_ia.parser.findChapter;

public class ChapterAppender implements Runnable{
	private int bookidx;
	private String bookName;
	private int chapNo;
	private HashMap<String, ArrayList<String>> versesLang;

	public ChapterAppender(int bookidx,int chapNo, findChapter fc) throws IOException{
		this.bookidx = bookidx;
		this.bookName = CSVParser.books.get(bookidx);
		this.chapNo = chapNo;
		versesLang = new HashMap<>();
		versesLang.put("id",fc.getVersesinChapter(chapNo, 0)); //indo
		versesLang.put("en",fc.getVersesinChapter(chapNo, 1)); //eng
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Chapter chap = new Chapter(bookName,chapNo,versesLang);
		launch.bible.books[bookidx].chapter.add(chap); 
	}
}

//Need to load both languages and store somehow.