package com.ibcompsci_ia.GUI.Models;

import java.io.IOException;
import org.apache.commons.codec.binary.Base64;

import com.ibcompsci_ia.users.Session;
import com.ibcompsci_ia.users.User;

import com.ibcompsci_ia.Main;


public class createAccountModel {
	
	public static String decryptor(byte[] encBytes){
		return new String(Base64.decodeBase64(encBytes));
	}	

	public static byte[] encryptor(String input){
		return Base64.encodeBase64(input.getBytes());
	}

	public void createAccount(String username, String pwd) throws IOException{
		User user = new User(username, pwd);
		new Session(user,null);

		System.out.println("Created account");
		Main.setRoot("loadingScreen"); //try to include a loading screen
		Main.setRoot("mainMenu");
		//load properties for new account


	}
	public static void main(String[] args) throws IOException {
		System.out.println(new String(encryptor("toby_123")) + ".txt"); //not returning expected output. Should return "dG9ieV8xMjM="
	}
}
