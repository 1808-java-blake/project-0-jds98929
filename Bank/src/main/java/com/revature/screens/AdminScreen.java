package com.revature.screens;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.TransactionDao;
import com.revature.daos.UserDao;

public class AdminScreen implements Screen {

	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private TransactionDao td = TransactionDao.currentTransactionDao;

	public Screen start() {
		
		DecimalFormat df2 = new DecimalFormat("0.00");

		System.out.println("Specify a customer to view account");
		System.out.println("Username:");
		String username = scan.nextLine();
		
		User u = ud.findByUsername(username);
		if (u == null) {
			System.out.println("Invalid username");
			return this;
		}

		System.out.println("Please choose from following options:");

		System.out.println("Enter 1 to view customer's personal information");

		System.out.println("Enter 2 to view customer's account balances");

		System.out.println("Enter 3 to view customer's transaction history");

		String selection = scan.nextLine();

		switch (selection) {
			case "1":
				System.out.println(u.toString());
				break;

			case "2":
				System.out.println("Enter 1 to view checking account balance");
				System.out.println("Enter 2 to view savings account balance");
				selection = scan.nextLine();
				
				switch (selection) {
				
				case "1":
					String checkingString = u.getCheckingAccountBalance();
					double checkingDouble = Double.valueOf(checkingString);
					System.out.println("Current checking account balance is $" + 
							df2.format(checkingDouble));
					break;

				case "2":
					String savingsString = u.getSavingsAccountBalance();
					double savingsDouble = Double.valueOf(savingsString);
					System.out.println("Current savings account balance is $" + 
							df2.format(savingsDouble));
					break;
					
				default:
					break;
				
				}
				
				break;
				
			case "3":
				td.retrieveTransactionHistory(u);
				List<String> al = u.getT().getTransactionList();
				for (int i = al.size() - 1; i >= 0; i--) {
					System.out.println(al.get(i));
				}
				break;
				
			default:
				break;
			
		}
		
		System.out.println("Enter 1 to return to admin options");
		System.out.println("Enter 2 to log out");
		
		selection = scan.nextLine();
		
		switch (selection) {
		
		case "1":
			return new AdminScreen();


		case "2":
			System.out.println("Session ended");
			return new LoginScreen();
			
		default:
			break;
		
		}
	
		
		return this;

	}
}
