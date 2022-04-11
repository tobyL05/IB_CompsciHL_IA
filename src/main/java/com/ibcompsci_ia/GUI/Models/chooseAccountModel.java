package com.ibcompsci_ia.GUI.Models;

import java.io.File;

public class chooseAccountModel {

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

	public File[] getAccountDir(){
		return accountDir;
	}

	public static void main(String[] args){
		chooseAccountModel cam = new chooseAccountModel();
	}
}
