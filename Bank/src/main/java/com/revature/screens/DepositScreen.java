package com.revature.screens;

import java.text.DecimalFormat;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.TransactionDao;
import com.revature.daos.UserDao;
import com.revature.util.AppState;

public class DepositScreen implements Screen{
	
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao; 
	private TransactionDao td = TransactionDao.currentTransactionDao;
	private AppState state = AppState.state;
	
	@Override
	
	public Screen start() {
		
		System.out.println("Enter 1 to deposit into checking account");
		System.out.println("Enter 2 to deposit into savings account");
		String selection = scan.nextLine();
		
		return deposit(selection);
		
	}
	
	public Screen deposit(String selection) {
		
		User currentUser = state.getCurrentUser();
		double amount = 0.0;
		double doubleBalance;
		DecimalFormat df2 = new DecimalFormat("#.##");
		String amountString;
		int length;
		
		switch (selection) {
		
		case "1":
			System.out.println("Enter amount to deposit in the form dollars.cents: ");
			amountString = scan.nextLine();
			length = amountString.length();
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
			doubleBalance = getCheckingBalance(currentUser, amount);
			currentUser.setCheckingAccountBalance(df2.format(doubleBalance));
			currentUser.getT().setTransactionHistory("Deposited $" + amountString + " into checking");
			td.updateTransactionHistory(currentUser);
			ud.updateUser(currentUser);
			break;

		case "2":
			System.out.println("Enter amount to deposit in the form dollars.cents: ");
			amountString = scan.nextLine();
			length = amountString.length();
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
			doubleBalance = getSavingsBalance(currentUser, amount);
			currentUser.setSavingsAccountBalance(df2.format(doubleBalance));
			currentUser.getT().setTransactionHistory("Deposited $" + amountString + " into savings");
			td.updateTransactionHistory(currentUser);
			ud.updateUser(currentUser);
			break;
			
		default:
			break;
		
		}
		
		System.out.println("Deposit Successful");
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
		System.out.println("here");
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
