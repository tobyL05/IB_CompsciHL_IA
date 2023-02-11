package com.ibcompsci_ia.API;

import java.io.BufferedReader; //Request from API
import java.net.HttpURLConnection; //Request from API
import java.net.MalformedURLException; //Request from API
import java.net.URL; //Request from API
import org.json.JSONArray; //Read and parse JSON response
import org.json.JSONObject;  //Read and parse JSON response
import java.io.InputStream; //Read and parse JSON response
import java.io.InputStreamReader; //Read and parse JSON response

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class recovery_versionAPI{

	private String recverURL = "https://api.lsm.org/recver.php?String='%s'&Out=json";
	private boolean multi = false;
	private ArrayList<String> refs = new ArrayList<String>();
	private String ref;
	private JSONObject response;
	public String verse;

	/**
	 * Check the user's internet connection 
	 * @return Boolean
	 */
	public Boolean connected(){
		URL apiURL;
		try {
			apiURL = new URL(recverURL);
			HttpURLConnection con = (HttpURLConnection) apiURL.openConnection();
			con.setRequestMethod("GET");
			return true;	
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Constructor that checks for internet connection and handles API
	 */
	public recovery_versionAPI(){
		if(connected()){
			ref = getRandomRef();
			try {
				getReq();
				verse = getMainText();
			} catch (Exception e) {
				// If fails for some reason. Just show welcome text or something
				System.out.println("No internet Connection");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Get a random verse reference from verse.txt to query the API
	 * @return String 
	 */
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
	/**
	 * Perform a GET request to the API with the verse reference
	 * @throws Exception
	 */
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
	
	/**
	 * Parses JSON response to extract verse
	 * @return String
	 */
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

	/**
	 * Parses the JSON response to extract reference
	 * @return String
	 */
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
	
	/**
	 * Returns the final formatted String containing reference and verse
	 * @return String
	 */
	public String getMainText(){
		return getVerse() + " - " + getRef();
	}

	//public static void main(String[] args) {
		//recovery_versionAPI r = new recovery_versionAPI();
		//System.out.println(r.verse);
		
	//}
	
	// DEBUG
	//private void printRefs(){
		//for(String s:refs){
			//System.out.println(s);
		//}
	//}
}
