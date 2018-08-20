package com.revature.screens;

import java.text.DecimalFormat;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.TransactionDao;
import com.revature.daos.UserDao;
import com.revature.util.AppState;

public class WithdrawalScreen implements Screen{
	
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao; 
	private TransactionDao td = TransactionDao.currentTransactionDao;
	private AppState state = AppState.state;
	private User currentUser;
	
	@Override
	
	public Screen start() {
		
		System.out.println("Enter 1 to withdraw from checking account");
		System.out.println("Enter 2 to withdraw from savings account");
		String selection = scan.nextLine();
		
		return withdraw(selection);
		
	}
	
	public Screen withdraw(String selection) {
		currentUser = state.getCurrentUser();
		switch (selection) {
		
		case "1":
			DecimalFormat df2 = new DecimalFormat("0.00");
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
						return returnOrQuit();
					}
				} else {
					System.out.println("Invalid amount");
					return returnOrQuit();
				}
			} catch (StringIndexOutOfBoundsException e) {
				System.out.println("Invalid amount");
				return returnOrQuit();
			}
			double doubleBalance = getCheckingBalance(currentUser, amount);
		
			if (doubleBalance < 0.0) {
				System.out.println("Not enough funds");
				return returnOrQuit();
			}
			currentUser.setCheckingAccountBalance(df2.format(doubleBalance));
			currentUser.getT().setTransactionHistory("Withdrew $" + amountString + " from checking \n");
			td.updateTransactionHistory(currentUser);
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
						return returnOrQuit();
					}
				} else {
					System.out.println("Invalid amount");
					return returnOrQuit();
				}
			} catch (StringIndexOutOfBoundsException e) {
				System.out.println("Invalid amount");
				return returnOrQuit();
			}
			double doubleBalance2 = getSavingsBalance(currentUser, amount2);
			if (doubleBalance2 < 0.0) {
				System.out.println("Not enough funds");
				return returnOrQuit();
			}
			currentUser.setSavingsAccountBalance(df22.format(doubleBalance2));
			currentUser.getT().setTransactionHistory("Withdrew $" + amountString2 + " from savings \n");
			td.updateTransactionHistory(currentUser);
			ud.updateUser(currentUser);
			break;
			
		default:
			break;
		
		}
		
		
		System.out.println("Withdrawal Successful");
		return returnOrQuit();
	}

	public Screen returnOrQuit() {
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
	
	public double getSavingsBalance(User u, double amount) {
		String stringBalance = u.getSavingsAccountBalance();
		Double doubleBalance = Double.valueOf(stringBalance);
		doubleBalance -= amount;
		return doubleBalance;
	}
	
	public double getCheckingBalance(User u, double amount) {
		String stringBalance = u.getCheckingAccountBalance();
		Double doubleBalance = Double.valueOf(stringBalance);
		doubleBalance -= amount;
		return doubleBalance;
	}

}


