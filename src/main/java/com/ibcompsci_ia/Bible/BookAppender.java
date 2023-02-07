package com.ibcompsci_ia.Bible;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.directory.InitialDirContext;

import com.ibcompsci_ia.launch;
import com.ibcompsci_ia.parser.CSVParser;
import com.ibcompsci_ia.parser.findChapter;

public class BookAppender implements Runnable{
	private int idx;

	public BookAppender(int idx) throws IOException{
		this.idx = idx;
	}

	@Override
	public void run(){
		// find chapter and add to hashmap
		// add hashmap to arraylist
		try {
			launch.bible.books[idx] = new Book(idx);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}