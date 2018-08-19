package com.revature.screens;

import java.text.DecimalFormat;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.TransactionDao;
import com.revature.daos.UserDao;
import com.revature.util.AppState;

public class WireScreen implements Screen{
	
	AppState state = AppState.state;
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao; 		
	private TransactionDao td = TransactionDao.currentTransactionDao;
	public static User currentUser;
	
	@Override		
	public Screen start() {
		double amount;
		DecimalFormat df2 = new DecimalFormat("#.##");
		System.out.println("Enter username of customer to wire money to");
		System.out.println("Username: ");
		String username = scan.nextLine();
		User recipient = ud.findByUsername(username);
		System.out.println("Enter amount to wire in the form dollars.cents: ");
		String amountString = scan.nextLine();
		
		int length = amountString.length();
		if (amountString.charAt(length - 3) == '.') {
			try {
				amount = Double.valueOf(amountString);
			} catch (NumberFormatException e) {
				System.out.println("Invalid amount");
				return new HomeScreen();
			}
		} else {
			System.out.println("Invalid amount");
			return new HomeScreen();
		}
		
		currentUser = state.getCurrentUser();
		String stringSenderBalance = currentUser.getCheckingAccountBalance();
		double doubleSenderBalance = Double.valueOf(stringSenderBalance);
		doubleSenderBalance -= amount;
		
		if (doubleSenderBalance < 0.0) {
			System.out.println("Not enough funds");
			System.out.println("Enter 1 to return to home screen");
			System.out.println("Enter 2 to log out");
			
			String selection = scan.nextLine();
			
			switch (selection) {
			
			case "1":
				return new HomeScreen();

			case "2":
				return new LoginScreen();
				
			default:
				break;
			
			}
			return this;
		}
		
		currentUser.setCheckingAccountBalance(df2.format(doubleSenderBalance));
		currentUser.getT().setTransactionHistory("Sent $" + amountString + " to " + 
				recipient.getFirstName() + " " + recipient.getLastName());
		td.updateTransactionHistory(currentUser);
		ud.updateUser(currentUser);
		
		String stringRecipientBalance = recipient.getCheckingAccountBalance();
		double doubleRecipientBalance = Double.valueOf(stringRecipientBalance);
		doubleRecipientBalance += amount;
		recipient.setCheckingAccountBalance(df2.format(doubleRecipientBalance));
		recipient.getT().setTransactionHistory("Received $" + amountString +
				"from " + currentUser.getFirstName() + " " + currentUser.getLastName());
		td.updateTransactionHistory(recipient);
		ud.updateUser(recipient);
		
		System.out.println("Wire Transfer Successful");
		System.out.println("Enter 1 to return to home screen");
		System.out.println("Enter 2 to log out");
		
		String selection = scan.nextLine();
		
		switch (selection) {
		
			case "1":
				return new HomeScreen();

			case "2":
				return new LoginScreen();
				
			default:
				break;
			
			}
			return this;
			

	}

}
