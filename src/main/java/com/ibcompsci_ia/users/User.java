package com.ibcompsci_ia.users;

import java.io.Serializable;
import java.util.ArrayList;

import com.ibcompsci_ia.GUI.Models.createAccountModel;


public class User implements Serializable{

	private String username;
	private String pwd;
	private String notesPath;
	private ArrayList<String> savedRefs;
	private int ver;

	public User(String username, String pwd){
		System.out.println("Created user");
		this.username = username;
		this.pwd = pwd;
		this.notesPath = "";
		this.savedRefs = new ArrayList<String>();
		this.ver = 0;
	}

	//getters and setters 
	public String getCreds(){
		return username + "_" + pwd;
	}

	public int getVer(){
		return ver;
	}

	public byte[] getPwd(){
		return createAccountModel.encryptor(pwd);
	}

	public void setNotesPath(){
		//create a new txt file for notes
	}

	public void addRef(){
		//add a ref to savedRefs
	}

}
