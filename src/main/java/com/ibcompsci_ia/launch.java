package com.ibcompsci_ia;

public class launch {

	public static void main(String[] args) throws InterruptedException {
		new Thread(){
			@Override
			public void run(){
				javafx.application.Application.launch(Main.class);
			}
		}.start();
		//run navigator here
	}
}	