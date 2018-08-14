package com.revature.screens;

import java.text.DecimalFormat;
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
		
		DecimalFormat df2 = new DecimalFormat("#.##");
		
		switch (selection) {
		
		case "1":
			String checkingString = currentUser.getCheckingAccountBalance();
			double checkingDouble = Double.valueOf(checkingString);
			System.out.println("Current checking account balance is $" + 
					df2.format(checkingDouble));
			break;

		case "2":
			String savingsString = currentUser.getSavingsAccountBalance();
			double savingsDouble = Double.valueOf(savingsString);
			System.out.println("Current savings account balance is $" + 
					df2.format(savingsDouble));
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
