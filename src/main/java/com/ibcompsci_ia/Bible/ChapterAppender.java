package com.ibcompsci_ia.Bible;

import java.io.IOException;
import java.util.ArrayList;

import com.ibcompsci_ia.launch;
import com.ibcompsci_ia.parser.CSVParser;
import com.ibcompsci_ia.parser.findChapter;

public class ChapterAppender implements Runnable{
	private int bookidx;
	private String bookName;
	private int chapNo;
	private findChapter fc;

	public ChapterAppender(int bookidx,int chapNo) throws IOException{
		this.bookidx = bookidx;
		this.bookName = CSVParser.books.get(bookidx);
		this.chapNo = chapNo;
		fc = new findChapter(bookName);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		launch.bible.books[bookidx].chapter.add(new Chapter(bookName,chapNo,fc.getVersesinChapter(chapNo, "English")));
	}
}
