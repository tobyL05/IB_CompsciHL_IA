package com.ibcompsci_ia;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.ibcompsci_ia.Bible.BibleArr;
import com.ibcompsci_ia.parser.CSVParser;

public class launch {

	private static final String appdataPath = System.getenv("APPDATA");
	private static final String appFolder = "BilingualBible";
	public static BibleArr bible;

	/**
	 * Check AppData for the application directory. Create one if it doesn't exist
	 */
	private static void checkAppData(){
		File[] appdata = new File(appdataPath).listFiles();
		if(!Arrays.asList(appdata).contains(new File(appFolder))){
			new File(appdataPath + "/" + appFolder).mkdir();
			new File(appdataPath + "/" + appFolder + "/" + "notes").mkdir(); //create notes dir
			new File(appdataPath + "/" + appFolder + "/" + "Accounts").mkdir(); //create accounts dir
		}
	}
	public static void main(String[] args) throws InterruptedException, IOException {
		new Thread(){
			@Override
			public void run(){
				//javafx.application.Application.launch(Main.class);
				Main.main(args);
			}
		}.start();
		//run navigator here
		//check if directory exist in appdata
		checkAppData();
		new CSVParser();
		System.out.println("Initialized CSVParser");
		launch.bible = new BibleArr(); //appending books and chapters still quite slow but works.
		launch.bible.addBooks();

	}
}	
