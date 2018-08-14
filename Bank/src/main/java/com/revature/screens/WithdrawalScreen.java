package com.revature.screens;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class WithdrawalScreen implements Screen{
	
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao; 
	public static User currentUser;
	
	@Override
	
	public Screen start() {
		
		System.out.println("Enter 1 to withdraw from checking account");
		System.out.println("Enter 2 to withdraw from savings account");
		String selection = scan.nextLine();
		
		withdraw(selection);
		return this;
	}
	
	public Screen withdraw(String selection) {
		switch (selection) {
		
		case "1":
			DecimalFormat df2 = new DecimalFormat("#.##");
			double amount = 0;
			System.out.println("Enter amount to withdraw in the form dollars.cents: ");
			String amountString = scan.nextLine();
			int length = amountString.length();
			try {
				if (amountString.charAt(length - 3) == '.') {
					try {
						amount = Double.valueOf(amountString);
					} catch (NumberFormatException e) {
						System.out.println("Invalid amount");
						this.returnOrQuit();
					}
				} else {
					System.out.println("Invalid amount");
					this.returnOrQuit();
				}
			} catch (StringIndexOutOfBoundsException e) {
				System.out.println("Invalid amount");
				this.returnOrQuit();
			}
			currentUser = LoginScreen.currentUser;
			String stringBalance = currentUser.getCheckingAccountBalance();
			Double doubleBalance = Double.valueOf(stringBalance);
			doubleBalance -= amount;
		
			if (doubleBalance < 0.0) {
				System.out.println("Not enough funds");
				this.returnOrQuit();
				return this;
			}
			currentUser.setCheckingAccountBalance(df2.format(doubleBalance));
			List<String> newTransactionHistory = currentUser.getTransactionHistory();
			newTransactionHistory.add("Withdrew $" + amountString + " from checking");
			currentUser.setTransactionHistory(newTransactionHistory);
			ud.updateUser(currentUser);
			break;

		case "2":
			DecimalFormat df22 = new DecimalFormat("#.##");
			double amount2 = 0;
			System.out.println("Enter amount to withdraw in the form dollars.cents: ");
			String amountString2 = scan.nextLine();
			int length2 = amountString2.length();
			try {
				if (amountString2.charAt(length2 - 3) == '.') {
					try {
						amount2 = Double.valueOf(amountString2);
					} catch (NumberFormatException e) {
						System.out.println("Invalid amount");
						this.returnOrQuit();
						return this;
					}
				} else {
					System.out.println("Invalid amount");
					this.returnOrQuit();
					return this;
				}
			} catch (StringIndexOutOfBoundsException e) {
				System.out.println("Invalid amount");
				this.returnOrQuit();
				return this;
			}
			currentUser = LoginScreen.currentUser;
			String stringBalance2 = currentUser.getSavingsAccountBalance();
			Double doubleBalance2 = Double.valueOf(stringBalance2);
			doubleBalance2 -= amount2;
			if (doubleBalance2 < 0.0) {
				System.out.println("Not enough funds");
				this.returnOrQuit();
				return this;
			}
			currentUser.setSavingsAccountBalance(df22.format(doubleBalance2));
			List<String> newTransactionHistory2 = currentUser.getTransactionHistory();
			newTransactionHistory2.add("Withdrew $" + amountString2 + " from savings");
			currentUser.setTransactionHistory(newTransactionHistory2);
			ud.updateUser(currentUser);
			break;
			
		default:
			break;
		
		}
		
		
		System.out.println("Withdrawal Successful");
		this.returnOrQuit();
		return this;
		
	}
	
	public Screen returnOrQuit() {
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


