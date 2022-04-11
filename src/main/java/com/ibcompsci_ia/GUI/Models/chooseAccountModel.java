package com.ibcompsci_ia.GUI.Models;

import java.io.File;
import java.util.ArrayList;

public class chooseAccountModel {

	private ArrayList<File> accounts = new ArrayList<File>();
	private File[] accountDir;

	public chooseAccountModel(){
		accountDir = (new File(getClass().getResource("/com/ibcompsci_ia/Accounts").getPath())).listFiles();
	}

	public boolean checkAccountsFound(){
		if(accountDir.length == 1){
			return false;
		}
		return true;
	}

	public static void main(String[] args){
		chooseAccountModel cam = new chooseAccountModel();
	}
}
