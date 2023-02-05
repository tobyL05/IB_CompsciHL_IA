package com.ibcompsci_ia.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.ibcompsci_ia.Enums.paths;

public class findChapter {
	private Document doc;
    private static Document staticDoc;
    private String bookName;
	private final String dirPathStr = paths.htmlPath.toString();
	private final InputStream dirIs = getClass().getResourceAsStream(paths.htmlPath.toString());
    private ArrayList<String> verses = new ArrayList<>();
    private int chapSize;

    //public findChapter(String bookName) throws IOException{
        //this.bookName = bookName;
        //BufferedReader br = new BufferedReader(new InputStreamReader(dirIs));
        //String line="";
        //while((line = br.readLine()) != null){
            //System.out.println(line);
        //}
        ////removeSpaces();
    //}

    public findChapter(String bookName) throws IOException{
        int bookNum = CSVParser.books.indexOf(bookName) + 1;
        String bookNostr = "";
        if(bookNum < 10){
            bookNostr = "0" + Integer.toString(bookNum);
        }else{
            bookNostr = Integer.toString(bookNum);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(dirIs));
        String line="";
        String filePath = dirPathStr;
        InputStream file;
        System.out.println(bookNostr);
        while((line = br.readLine()) != null){
            //System.out.println(line);
            if(!line.contains("files") && line.startsWith(bookNostr)){
                filePath += line;
                System.out.println(filePath);
                file = getClass().getResourceAsStream(filePath);
                doc = Jsoup.parse(file,"UTF-8",""); //works
            }
        }
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
        ArrayList<String> verses = new ArrayList<>();
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

    public int getChapSize(){
        return chapSize;
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
        findChapter fc = new findChapter("1 John");
        //System.out.println(fc.getVersesinChapter(1, 0));
    }
}
