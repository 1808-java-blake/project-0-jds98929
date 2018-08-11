package com.revature.screens;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class DepositScreen implements Screen{
	
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao; 
	public static User currentUser;
	
	@Override
	
	public Screen start() {
		double amount;
		DecimalFormat df2 = new DecimalFormat("#.##");
		System.out.println("Enter amount to deposit in the form dollars.cents: ");
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
		currentUser = LoginScreen.currentUser;
		String stringBalance = currentUser.getAccountBalance();
		double doubleBalance = Double.valueOf(stringBalance);
		doubleBalance += amount;
		currentUser.setAccountBalance(df2.format(doubleBalance));
		List<String> newTransactionHistory = currentUser.getTransactionHistory();
		newTransactionHistory.add("Deposited $" + amountString);
		currentUser.setTransactionHistory(newTransactionHistory);
		ud.updateUser(currentUser);
		
		System.out.println("Deposit Successful");
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