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
	private findChapter fc;
	private HashMap<String, ArrayList<String>> versesLang;

	public ChapterAppender(int bookidx,int chapNo, findChapter fc) throws IOException{
		this.bookidx = bookidx;
		this.bookName = CSVParser.books.get(bookidx);
		this.chapNo = chapNo;
		this.fc = fc;
		versesLang = new HashMap<>();
		versesLang.put("in",fc.getVersesinChapter(chapNo, "in"));
		versesLang.put("en",fc.getVersesinChapter(chapNo, "en"));
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Chapter chap = new Chapter(bookName,chapNo,versesLang);
		launch.bible.books[bookidx].chapter.add(chap); //messing up the order of the chapters
	}
}

//Need to load both languages and store somehow.