package com.ibcompsci_ia.users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ibcompsci_ia.GUI.Models.biblePageModel;
import com.ibcompsci_ia.GUI.Models.createAccountModel;

public class Session {
	
	private FileOutputStream fOut;
	private static ObjectOutputStream objOut;
	private static String fileName;
	public static User user;
	private static boolean loggedIn;
	private final String accountsPath = System.getenv("APPDATA") + "/BilingualBible/Accounts/";

	public Session(User user, File f){
		try(ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(f))){//if there is a file, read it
			Session.user = (User) objIn.readObject();
			fileName = new String(createAccountModel.encryptor(user.getCreds()));
			objOut = new ObjectOutputStream(new FileOutputStream(accountsPath + fileName + ".ser")); //have to copy everything to new object and serialize

		}catch(Exception e){ //if no file, make new one
			Session.user = user;
			fileName = new String(createAccountModel.encryptor(user.getCreds()));
			
			try {
				fOut = new FileOutputStream(accountsPath + fileName + ".ser");
			} catch (FileNotFoundException fnfe) {
				System.out.println("file not found");
				fnfe.printStackTrace();
			}
	
			try {
				objOut = new ObjectOutputStream(fOut);
			} catch (IOException ioe) {
				System.out.println("Error outputting obj");
				ioe.printStackTrace();
			}

		}

		System.out.println("Created session");
		System.out.println("Current account: " + Session.user.getCreds());
		Session.loggedIn = true;
	}

	public static String getFileName() {
		return fileName;
	}

	public static Boolean loginStatus(){
		return Session.loggedIn; 
	}

	public static void saveObj(){
		if(Session.loggedIn){
			try {
				System.out.println("Logging out of: " + Session.user.getCreds());
				Session.user.setCurrLang(biblePageModel.getInstance().getCurrLang());
				Session.user.setCurrBook(biblePageModel.getInstance().getCurrBookidx());
				Session.user.setCurrChap(biblePageModel.getInstance().getCurrChapidx());
				objOut.writeObject(user);
				objOut.flush();
				objOut.close();
			} catch (IOException e) {
				System.out.println("Error saving account");
				e.printStackTrace();
			}
			Session.loggedIn = false;
		}
	}

}
