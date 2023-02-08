package com.ibcompsci_ia.Bible;

import java.io.IOException;

import com.ibcompsci_ia.launch;

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
			e.printStackTrace();
		}
	}
}