package com.ibcompsci_ia.parser;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;

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
	private final String dirPath = getClass().getResource(paths.htmlPath.toString()).getPath();
    private final static String staticDirPath = findChapter.class.getResource(paths.htmlPath.toString()).getPath();
    private ArrayList<String> verses = new ArrayList<>();
    private int chapSize;

    public findChapter(String bookName) throws IOException{
        this.bookName = bookName;
        removeSpaces();
    }

    //somehow return current book and chapter

    private void removeSpaces() throws IOException{
        File dir = new File(dirPath);
        File[] book = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file){
                return file.getName().contains(bookName.replace(" ",""));
            }
        });
        //System.out.println(book[0].getName());
        doc = Jsoup.parse(new File(dirPath + book[0].getName()),"windows-1252",dirPath + book[0].getName());
        Elements sup = doc.select("sup");
        for(Element sups:sup){
            sups.replaceWith(new TextNode(" "));
        }
    }

    //index 0 for bahasa, index 1 for english
    public ArrayList<String> getVersesinChapter(String chapNo,String lang){
        int idx;
        if(lang.equalsIgnoreCase("bahasa indonesia")){
            idx = 0;
        }else{
            idx = 1;
        }
        chapSize = 1;
        Element table = doc.select("table").get(1); //get the table
        Elements rows = table.select("tr"); //get all rows
        //  System.out.println(table);
        for(Element row:rows){ // for each row
            //System.out.println(row);
            Elements col = row.select("td"); //get table data
            if(col.get(idx).text().equalsIgnoreCase(bookName + " " + chapNo+1)){
                break;
            }
            if(isVerse(col.get(idx).text()) && col.get(idx).text().startsWith(chapNo + ":")){
                chapSize++;
                verses.add(col.get(idx).text());
                System.out.println(col.get(idx).text());
            }
        }
        return verses;
        //return size;
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

    private static boolean staticIsVerse(String text){
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

    public static ArrayList<String> staticgetVerseinChapter(String bookName, String chapNo){
        ArrayList<String> verses = new ArrayList<>();
        File dir = new File(staticDirPath);
        File[] book = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file){
                return file.getName().contains(bookName.replace(" ","")) && !file.isDirectory();
            }
        });
        //for(File f:book){
            //System.out.println(f.getName());
        //}
        //System.out.println(book[0].getName());
        try{
            staticDoc = Jsoup.parse(new File(staticDirPath + book[0].getName()),"UTF-8");
        }catch(IOException io){
            System.out.println("Cannot access book html");
        }
        Elements sup = staticDoc.select("sup");
        for(Element sups:sup){
            sups.replaceWith(new TextNode(" "));
        }
        Element table;
        try{
           table = staticDoc.select("table").get(1); //get the table
        }catch(IndexOutOfBoundsException e){
           table = staticDoc.select("table").get(0); //get the table
        }
        Elements rows = table.select("tr"); //get all rows
        //  System.out.println(table);
        for(Element row:rows){ // for each row
            //System.out.println(row);
            Elements col = row.select("td"); //get table data
            if(col.get(1).text().equalsIgnoreCase(bookName + " " + chapNo+1)){//if end of chapter
                break;
            }
            if(staticIsVerse(col.get(1).text()) && col.get(1).text().startsWith((Integer.toString(Integer.parseInt(chapNo) + 1) + ":"))){
                verses.add(col.get(1).text() + "\n");
            }
        }
        return verses;

    }
    
    public static int getChapSize(String bookName, String chapNo){
        int chapSize = 1;
        File dir = new File(staticDirPath);
        //System.out.println(bookName);
        File[] book = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file){
                return file.getName().contains(bookName.replace(" ","")) && !file.isDirectory();
            }
        });
        //for(File f:book){
            //System.out.println(f.getName());
        //}
        //System.out.println(book[0].getName());
        try{
            staticDoc = Jsoup.parse(new File(staticDirPath + book[0].getName()),"UTF-8");
        }catch(IOException io){
            System.out.println("Cannot access book html");
        }
        Elements sup = staticDoc.select("sup");
        for(Element sups:sup){
            sups.replaceWith(new TextNode(" "));
        }
        Element table;
        try{
            table = staticDoc.select("table").get(1); //get the table
        }catch(IndexOutOfBoundsException e){
            table = staticDoc.select("table").get(0); //if it doesnt work get the other table
        }
        Elements rows = table.select("tr"); //get all rows
        //  System.out.println(table);
        for(Element row:rows){ // for each row
            //System.out.println(row);
            Elements col = row.select("td"); //get table data
            if(col.get(1).text().equalsIgnoreCase(bookName + " " + chapNo+1)){
                break;
            }
            if(staticIsVerse(col.get(1).text()) && col.get(1).text().startsWith(chapNo + ":")){
                //System.out.println(col.get(1).text());
                //System.out.println(chapSize);
                chapSize++;
            }
        }
        return chapSize-1;
    }


    private static void testAll(){
        new CSVParser();
        for(int i = 0;i < CSVParser.books.size();i++){ //for each book
            System.out.println(CSVParser.books.get(i));
            for(int j = 0;j < CSVParser.bookMap.get(CSVParser.books.get(i));j++){ //for each chapter in the book
                //System.out.println(CSVParser.books.get(i) + " " + (j+1) + " size: " + findChapter.getChapSize(CSVParser.books.get(i), Integer.toString(j+1)));
                assert findChapter.getChapSize(CSVParser.books.get(i), Integer.toString(j+1)) != 0;
            }
        }
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //findChapter a = new findChapter("Genesis");
        //System.out.println(a.getVersesinChapter("2","English"));
        //System.out.println(findChapter.getChapSize("Song of songs", "1"));
        //System.out.println(findChapter.staticgetVerseinChapter("Genesis", "49"));
        testAll();

    }
}
