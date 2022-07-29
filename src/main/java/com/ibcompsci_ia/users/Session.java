package com.ibcompsci_ia.users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ibcompsci_ia.Enums.paths;
import com.ibcompsci_ia.GUI.Models.createAccountModel;

public class Session {
	
	private FileOutputStream fOut;
	private static ObjectOutputStream objOut;
	private String fileName;
	private static User user;
	private static boolean loggedIn;

	public Session(User user, File f){
		try(ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(f))){//if there is a file, read it
			Session.user = (User) objIn.readObject();
			fileName = new String(createAccountModel.encryptor(user.getCreds() + "_" + Integer.toString(user.getVer())));
			objOut = new ObjectOutputStream(new FileOutputStream(paths.accountsPath.path() + fileName + ".ser")); //have to copy everything to new object and serialize
			//System.out.println(Session.user.getCreds());

		}catch(Exception e){ //if no file, make new one
			Session.user = user;
			fileName = new String(createAccountModel.encryptor(user.getCreds() + "_" + Integer.toString(user.getVer())));
			
			try {
				fOut = new FileOutputStream(paths.accountsPath.path() + fileName + ".ser");
			} catch (FileNotFoundException fnfe) {
				// TODO Auto-generated catch block
				System.out.println("file not found");
				fnfe.printStackTrace();
			}
	
			try {
				objOut = new ObjectOutputStream(fOut);
			} catch (IOException ioe) {
				// TODO Auto-generated catch block
				System.out.println("Error outputtig obj");
				ioe.printStackTrace();
			}

		}

		System.out.println("Created session");
		Session.loggedIn = true;
	}

	public static Boolean loginStatus(){
		return Session.loggedIn;
	}

	public static void saveObj(){
		try {
			objOut.writeObject(user);
			objOut.flush();
			objOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error saving account");
			e.printStackTrace();
		}
		//objOut.close()
	}

	public static void loadObj(){

	}
}
