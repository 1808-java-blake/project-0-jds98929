package com.revature.screens;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class DepositScreen implements Screen{
	
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao; 
	public static User currentUser = LoginScreen.currentUser;
	
	@Override
	
	public Screen start() {
		
		System.out.println("Enter 1 to deposit into checking account");
		System.out.println("Enter 2 to deposit into savings account");
		String selection = scan.nextLine();
		
		deposit(selection);
		
		return this;
	}
	
	public Screen deposit(String selection) {
		
		double amount, doubleBalance;
		DecimalFormat df2 = new DecimalFormat("#.##");
		String amountString, stringBalance;
		int length;
		List<String> newTransactionHistory;
		
		switch (selection) {
		
		case "1":
			System.out.println("Enter amount to deposit in the form dollars.cents: ");
			amountString = scan.nextLine();
			length = amountString.length();
			if (amountString.charAt(length - 3) == '.') {
				try {
					amount = Double.valueOf(amountString);
				} catch (NumberFormatException e) {
					System.out.println("Invalid amount");
					return this;
				}
			} else {
				System.out.println("Invalid amount");
				return this;
			}
			doubleBalance = getCheckingBalance(currentUser, amount);
			currentUser.setCheckingAccountBalance(df2.format(doubleBalance));
			newTransactionHistory = currentUser.getTransactionHistory();
			newTransactionHistory.add("Deposited $" + amountString + " into checking");
			currentUser.setTransactionHistory(newTransactionHistory);	
			ud.updateUser(currentUser);
			break;

		case "2":
			System.out.println("Enter amount to deposit in the form dollars.cents: ");
			amountString = scan.nextLine();
			length = amountString.length();
			if (amountString.charAt(length - 3) == '.') {
				try {
					amount = Double.valueOf(amountString);
				} catch (NumberFormatException e) {
					System.out.println("Invalid amount");
					return this;
				}
			} else {
				System.out.println("Invalid amount");
				return this;
			}
			doubleBalance = getSavingsBalance(currentUser, amount);
			currentUser.setSavingsAccountBalance(df2.format(doubleBalance));
			newTransactionHistory = currentUser.getTransactionHistory();
			newTransactionHistory.add("Deposited $" + amountString + " into savings");
			currentUser.setTransactionHistory(newTransactionHistory);
			ud.updateUser(currentUser);
			break;
			
		default:
			break;
		
		}
		
		System.out.println("Deposit Successful");
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
	
	public double getSavingsBalance(User u, double amount) {
		String stringBalance = u.getSavingsAccountBalance();
		Double doubleBalance = Double.valueOf(stringBalance);
		doubleBalance += amount;
		return doubleBalance;
	}
	
	public double getCheckingBalance(User u, double amount) {
		String stringBalance = u.getCheckingAccountBalance();
		Double doubleBalance = Double.valueOf(stringBalance);
		doubleBalance += amount;
		return doubleBalance;
	}
	

}
