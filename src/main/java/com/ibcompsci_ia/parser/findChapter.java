package com.ibcompsci_ia.parser;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.ibcompsci_ia.Enums.paths;

public class findChapter {
	private Document doc;
    private String bookName;
	private final String dirPath = getClass().getResource(paths.htmlPath.toString()).getPath();

    public findChapter(String bookName) throws IOException{
        this.bookName = bookName;
        File dir = new File(dirPath);
        File[] book = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file){
                return file.getName().contains(bookName.replace(" ",""));
            }
        });
        //System.out.println(book[0].getName());
        doc = Jsoup.parse(new File(dirPath + book[0].getName()),"UTF-8");
        Elements sup = doc.select("sup");
        for(Element sups:sup){
            sups.replaceWith(new TextNode(" "));
        }
    }


    //index 0 for bahasa, index 1 for english
    public int getVersesinChapter(int chapNo,String lang){
        int idx;
        if(lang.equalsIgnoreCase("bahasa indonesia")){
            idx = 0;
        }else{
            idx = 1;
        }
        int size = 0;
        Element table = doc.select("table").get(1); //get the table
        Elements rows = table.select("tr"); //get all rows
        //  System.out.println(table);
        for(Element row:rows){ // for each row
            //System.out.println(row);
            Elements col = row.select("td"); //get table data
            if(col.get(idx).text().equalsIgnoreCase(String.format(bookName + "%s",chapNo+1))){
                break;
            }
            if(isVerse(col.get(idx).text()) && col.get(idx).text().startsWith(String.format("%s:",chapNo))){
                size++;
                System.out.println(col.get(idx).text());
            }
        }
        return size;
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
    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        findChapter a = new findChapter("Genesis");
        System.out.println(a.getVersesinChapter(50,"Bahasa Indonesia"));
    }
}
