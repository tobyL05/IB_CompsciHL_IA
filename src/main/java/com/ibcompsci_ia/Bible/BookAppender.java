package com.ibcompsci_ia.Bible;

import java.io.IOException;

import com.ibcompsci_ia.launch;

public class BookAppender implements Runnable{
	private int idx;

	/**
	 * Instantiate a new book at index and add to books[]
	 * @param idx
	 * @throws IOException
	 */
	public BookAppender(int idx) throws IOException{
		this.idx = idx;
	}

	@Override
	public void run(){
		try {
			launch.bible.books[idx] = new Book(idx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}