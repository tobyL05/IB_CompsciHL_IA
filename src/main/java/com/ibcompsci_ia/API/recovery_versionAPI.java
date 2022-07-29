package com.ibcompsci_ia.API;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class recovery_versionAPI{

	private String recverURL = "https://api.lsm.org/recver.php?String='%s'&Out=json";
	private boolean multi = false;
	private ArrayList<String> refs = new ArrayList<String>();
	private String ref;
	private JSONObject response;
	public String verse;

	public Boolean connected(){
		URL apiURL;
		try {
			apiURL = new URL(recverURL);
			HttpURLConnection con = (HttpURLConnection) apiURL.openConnection();
			con.setRequestMethod("GET");
			return true;	
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public recovery_versionAPI(){
		if(connected()){
			ref = getRandomRef();
			try {
				getReq();
				verse = getMainText();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// If fails for some reason. Just show welcome text or something
				System.out.println("No internet Connection");
				e.printStackTrace();
			}
		}
	}

	private String getRandomRef(){

		try{
			InputStream is = getClass().getClassLoader().getResourceAsStream("com/ibcompsci_ia/verses.txt");
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(reader);

			String line;
			while((line = br.readLine()) != null){
				refs.add(line);
			}

		}catch (FileNotFoundException e){
			System.out.println("An error occurred");
			e.printStackTrace();

		}catch(IOException ioe){
			System.out.println("IO Exception, verses file not found");
			ioe.printStackTrace();
		}

		ref = refs.get((int)(Math.random() * refs.size()));
		//ref = "Ephesians.2:8-9";
		if(ref.contains("-")){
			multi = true;
		}
		return ref;
	}

	//GET request to api, gets json object
	private void getReq() throws Exception{
		recverURL = String.format(recverURL,ref);
		URL apiURL = new URL(recverURL);
		HttpURLConnection con = (HttpURLConnection) apiURL.openConnection();
		con.setRequestMethod("GET");
		//System.out.println(con.getResponseCode());
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer resp = new StringBuffer();
		while((inputLine = in.readLine()) != null){
			resp.append(inputLine);
		}
		in.close();
		response = new JSONObject(resp.toString());
	}
	
	//Extracts verse from JSON
	private String getVerse(){
		String verse = "";
		JSONArray verses;
		try{
			verses = response.getJSONArray("verses");
		}catch(NullPointerException e){
			System.out.println("Check internet connection/API down");
			return "";
		}
		if(multi){
			for(int i = 0;i < verses.length();i++){
				verse += verses.getJSONObject(i).getString("text") + " ";
			}
		}else{
			verse = verses.getJSONObject(0).getString("text");
		}
		return verse;
	}

	//Extracts references from JSON	
	private String getRef(){
		String ref = "";
		ArrayList<String> refs = new ArrayList<String>();
		JSONArray verses;
		try{
			verses = response.getJSONArray("verses");
		}catch(Exception e){
			System.out.println("No internet connection/API down");
			return "";
		}
		if(multi){
			for(int i = 0;i < verses.length();i++){
				refs.add(verses.getJSONObject(i).getString("ref"));
			}
			ref = refs.get(0) + " - " + refs.get(refs.size()-1).substring(refs.get(refs.size()-1).indexOf(":") + 1);
		}else{
			ref = verses.getJSONObject(0).getString("ref");
		}
		return ref;
	}
	
	//Get verse + newline + reference
	public String getMainText(){
		return getVerse() + " - " + getRef();
	}

	public static void main(String[] args) {
		recovery_versionAPI r = new recovery_versionAPI();
		System.out.println(r.verse);
		
	}
	
	// DEBUG
	//private void printRefs(){
		//for(String s:refs){
			//System.out.println(s);
		//}
	//}
}
