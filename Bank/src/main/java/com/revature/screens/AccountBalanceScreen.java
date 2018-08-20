package com.revature.screens;

import java.text.DecimalFormat;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.util.AppState;

public class AccountBalanceScreen implements Screen{
	
	private Scanner scan = new Scanner(System.in);
	private AppState state = AppState.state;

	public Screen start() {
		
		User currentUser = state.getCurrentUser();
		
		System.out.println("View checking account or savings account?");
		System.out.println("Enter 1 to view checking account balance");
		System.out.println("Enter 2 to view savings account balance");
		String selection = scan.nextLine();
		
		DecimalFormat df2 = new DecimalFormat("0.00");
		
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
			return new HomeScreen();

		case "2":
			System.out.println("Session ended");
			return new LoginScreen();
			
		default:
			break;
		
		}
		return this;
	}

}
