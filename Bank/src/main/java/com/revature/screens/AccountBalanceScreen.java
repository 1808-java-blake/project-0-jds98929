package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;

public class AccountBalanceScreen implements Screen{
	
	public static User currentUser = LoginScreen.currentUser;
	private Scanner scan = new Scanner(System.in);

	public Screen start() {
		
		System.out.println("Current account balance is $" + currentUser.getAccountBalance());
		System.out.println("Enter 1 to return to home screen");
		System.out.println("Enter 2 to log out");
		
		String selection = scan.nextLine();
		
		switch (selection) {
		
		case "1":
			Screen hs = new HomeScreen();
			hs.start();
			break;

		case "2":
			System.out.println("Session ended");
			break;
			
		default:
			break;
		
		}
		return this;
	}

}
