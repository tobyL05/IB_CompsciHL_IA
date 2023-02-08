package com.ibcompsci_ia.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.ibcompsci_ia.Main;

public class findChapter {
	private Document doc;
    private static Document staticDoc;
    private String bookName;
    private int bookidx;
    private ArrayList<String> verses;

    public findChapter(String bookName) throws IOException{
        bookidx = CSVParser.books.indexOf(bookName);
        String filePath = "htmls/" + CSVParser.files.get(bookidx);
        InputStream file = Main.class.getResourceAsStream(filePath);
        doc = Jsoup.parse(file,"UTF-8",""); //works
        removeSpaces();
    }

    //somehow return current book and chapter

    private void removeSpaces() throws IOException{
        Elements sup = doc.select("sup");
        for(Element sups:sup){
            sups.replaceWith(new TextNode(" "));
        }
    }

    //index 0 for bahasa, index 1 for english
    public ArrayList<String> getVersesinChapter(int chapNo,int lang){
        verses = new ArrayList<>();
        Element table;
        try{
            table = doc.select("table").get(1); //get the table
        }catch(IndexOutOfBoundsException e){
           table = doc.select("table").get(0); //get the table
        }
        Elements rows = table.select("tr"); //get all rows
        for(Element row:rows){ // for each row
            Elements col = row.select("td"); //get table data
            if(col.get(lang).text().equalsIgnoreCase(bookName + " " + chapNo+1)){//if end of chapter
                break;
            }
            if(isVerse(col.get(lang).text()) && col.get(lang).text().startsWith((Integer.toString(chapNo + 1) + ":"))){
                //if(col.get(lang).text().contains()){

                //}
                verses.add(col.get(lang).text() + "\n");
                //verses.add(col.get(lang).text() + "\n");
            }
        }
        return verses;

    }

    private boolean isVerse(String text){
        int ints = 0;
        int letters = 0;
        char[] textArr = text.toCharArray();
        for(Character c:textArr){
            if(Character.isDigit(c)){
                ints++;
            }else{
                letters++;
            }
            if(letters - ints == 5){
                return true;
            }
        }
        return false;
    }

    public static int getChapSize(String bookName, int chapNo) throws IOException{
        System.out.println("Getting size for " + bookName + " " + chapNo);
        int booknum = CSVParser.books.indexOf(bookName);
        staticDoc = Jsoup.parse(Main.class.getResourceAsStream("htmls/" + CSVParser.files.get(booknum)),"UTF-8","");

        Elements sup = staticDoc.select("sup");
        for(Element sups:sup){
            sups.replaceWith(new TextNode(" "));
        }

        int chapSize = 0;
        Element table;
        try{
            table = staticDoc.select("table").get(1); //get the table
        }catch(IndexOutOfBoundsException e){
           table = staticDoc.select("table").get(0); //get the table
        }
        Elements rows = table.select("tr"); //get all rows
        for(Element row:rows){ // for each row
            Elements col = row.select("td"); //get table data
            if(col.get(0).text().equalsIgnoreCase(bookName + " " + chapNo+1)){//if end of chapter
                break;
            }
            if(staticisVerse(col.get(0).text()) && col.get(0).text().startsWith((Integer.toString(chapNo + 1) + ":"))){
                chapSize++;
            }
        }
        System.out.println(chapSize);
        //System.out.println("size: " + chapSize);
        return chapSize;
    }


    private static boolean staticisVerse(String text){
        int ints = 0;
        int letters = 0;
        char[] textArr = text.toCharArray();
        for(Character c:textArr){
            if(Character.isDigit(c)){
                ints++;
            }else{
                letters++;
            }
            if(letters - ints == 5){
                return true;
            }
        }
        return false;
    }

    //private static void testAll(){
        //new CSVParser();
        //for(int i = 0;i < CSVParser.books.size();i++){ //for each book
            //System.out.println(CSVParser.books.get(i));
            //for(int j = 0;j < CSVParser.bookMap.get(CSVParser.books.get(i));j++){ //for each chapter in the book
                ////System.out.println(CSVParser.books.get(i) + " " + (j+1) + " size: " + findChapter.getChapSize(CSVParser.books.get(i), Integer.toString(j+1)));
                //assert findChapter.getChapSize(CSVParser.books.get(i), Integer.toString(j+1)) != 0;
            //}
        //}
    //}

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new CSVParser();
        System.out.println(findChapter.getChapSize("Genesis", 0));
    }
}
