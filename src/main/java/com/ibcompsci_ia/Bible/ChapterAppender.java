//package com.ibcompsci_ia.Bible;

//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;

//import com.ibcompsci_ia.launch;
//import com.ibcompsci_ia.parser.CSVParser;
//import com.ibcompsci_ia.parser.findChapter;

//public class ChapterAppender implements Runnable{
	//private int bookidx;
	//private String bookName;
	//private int chapNo;
	//private HashMap<String, ArrayList<String>> versesLang;
	//private ArrayList<String> enVerses;
	//private ArrayList<String> idVerses;


	//public ChapterAppender(int bookidx,int chapNo, findChapter fc) throws IOException{ //instantiated first
		//this.bookidx = bookidx;
		//this.bookName = CSVParser.books.get(bookidx);
		//this.chapNo = chapNo;
		//enVerses = new ArrayList<>();
		//idVerses = new ArrayList<>();
		////System.out.println(bookName + " " + chapNo + " size: " + enVerseObjs.size());

		////cannot do this?
		////for(int i = 0;i < enVerses.size();i++){
			////VerseObject enVerse = new VerseObject(bookidx, chapNo, i,enVerses.get(i),1);
			////enVerseObjs.add(enVerse);
		////}
		////for(int i = 0; i < idVerses.size();i++){
			////VerseObject idVerse = new VerseObject(bookidx, chapNo, i,idVerses.get(i),0);
			////idVerseObjs.add(idVerse);
		////}
		//versesLang.put("id",idVerses); //indo
		//versesLang.put("en",enVerses); //eng
	//}
	//@Override
	//public void run() {
		//// TODO Auto-generated method stub
		//Chapter chap = new Chapter(bookName,chapNo,versesLang);
		//launch.bible.books[bookidx].chapter.add(chap); 
	//}
//}

////Need to load both languages and store somehow.