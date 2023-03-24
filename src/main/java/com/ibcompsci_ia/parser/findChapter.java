package com.ibcompsci_ia.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.ibcompsci_ia.Main;

public class findChapter {

    public int getChapSize(int bookNo, int chapNo) throws IOException{
        InputStream file = Main.class.getResourceAsStream("jsons/" + CSVParser.files.get(bookNo));
        String jsontxt = IOUtils.toString(file,"UTF-8");
        JSONObject book = new JSONObject(jsontxt);
        JSONArray jsonarr = book.getJSONObject(CSVParser.books.get(bookNo)).getJSONObject(Integer.toString(chapNo + 1)).getJSONArray("en");
        file.close();
        jsontxt=null;
        book = null;
        return jsonarr.length();
    }

    public ArrayList<String> getVersesinChapter(int bookNo,int chapNo,String lang) throws IOException{
        InputStream file = Main.class.getResourceAsStream("jsons/" + CSVParser.files.get(bookNo));
        String jsontxt = IOUtils.toString(file,"UTF-8");
        JSONObject book = new JSONObject(jsontxt);
        JSONArray jsonarr;
        jsonarr = book.getJSONObject(CSVParser.books.get(bookNo)).getJSONObject(Integer.toString(chapNo + 1)).getJSONArray(lang);
        ArrayList<String> verses = new ArrayList<>(jsonarr.length());
        for(int i = 0;i<jsonarr.length();i++){
            verses.add(jsonarr.get(i).toString());
        }
        file.close();
        jsontxt=null;
        book = null;
        jsonarr = null;
        return verses;
    }
    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new CSVParser();
        System.out.println(new findChapter().getVersesinChapter(0, 1, "en"));
    }
}
