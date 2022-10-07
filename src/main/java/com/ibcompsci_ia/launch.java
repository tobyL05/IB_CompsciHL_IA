package com.ibcompsci_ia;

import com.ibcompsci_ia.Bible.BibleLL;
import com.ibcompsci_ia.parser.CSVParser;

public class launch {

	public static BibleLL bible;
	public static void main(String[] args) throws InterruptedException {
		new Thread(){
			@Override
			public void run(){
				javafx.application.Application.launch(Main.class);
			}
		}.start();
		//run navigator here
		new CSVParser();
		bible = new BibleLL();
		System.out.println("initialized bibleLL");
		//for(int i = 0;i < 100000;i++){
			//System.out.println(i);
		//}
	}
}	