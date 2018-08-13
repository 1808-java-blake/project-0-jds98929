package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;

public class AccountBalanceScreen implements Screen{
	
	public static User currentUser = LoginScreen.currentUser;
	private Scanner scan = new Scanner(System.in);

	public Screen start() {
		
		System.out.println("View checking account or savings account?");
		System.out.println("Enter 1 to view checking account balance");
		System.out.println("Enter 2 to view savings account balance");
		String selection = scan.nextLine();
		
		switch (selection) {
		
		case "1":
			System.out.println("Current checking account balance is $" + 
					currentUser.getCheckingAccountBalance());
			break;

		case "2":
			System.out.println("Current savings account balance is $" + 
					currentUser.getSavingsAccountBalance());
			break;
			
		default:
			break;
		
		}
		
		System.out.println("Enter 1 to return to home screen");
		System.out.println("Enter 2 to log out");
		
		selection = scan.nextLine();
		
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
