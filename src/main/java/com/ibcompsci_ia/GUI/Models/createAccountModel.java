package com.ibcompsci_ia.GUI.Models;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import java.util.Properties;
import org.apache.commons.codec.binary.Base64;


public class createAccountModel {
	
	private Preferences prefs;

	public createAccountModel(){
		prefs = Preferences.userRoot().node(this.getClass().getName());
	}

	public static String decryptor(byte[] encBytes){
		return new String(Base64.decodeBase64(encBytes));
	}	

	private byte[] encryptor(String input){
		return Base64.encodeBase64(input.getBytes());
	}

	public void createAccount(String user, String pwd) throws IOException{
		String encrypted = new String(encryptor(user+"_"+pwd));
		//prefs.put(encrypted,encrypted+".properties");
		File props = new File(this.getClass().getResource("/com/ibcompsci_ia/Accounts").getPath() + "/" + encrypted +".properties");
		props.createNewFile();
		System.out.println("Created account");


	}
	public static void main(String[] args) throws IOException {
		createAccountModel cam = new createAccountModel();
		cam.createAccount("amc","sda");
		System.out.println(cam.getClass().getResource("/com/ibcompsci_ia/Accounts"));
		//File props = new File(cam.getClass().getResource("/com/ibcompsci_ia/Accounts").getPath()+"/test.properties");
		//props.createNewFile();

	}
}
