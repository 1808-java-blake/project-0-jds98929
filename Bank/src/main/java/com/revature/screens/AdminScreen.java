package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class AdminScreen implements Screen {

	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;

	public Screen start() {

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

		System.out.println("Enter 2 to view customer's account balance");

		System.out.println("Enter 3 to view customer's transaction history");

		String selection = scan.nextLine();

		switch (selection) {
			case "1":
				System.out.println(u.toString());
				break;

			case "2":
				System.out.println("Account balance: $" + u.getAccountBalance());
				break;
				
			case "3":
				System.out.println(u.getTransactionHistory());
				break;
				
			default:
				break;
			
		}
		
		System.out.println("Enter 1 to return to admin options");
		System.out.println("Enter 2 to log out");
		
		selection = scan.nextLine();
		
		switch (selection) {
		
		case "1":
			Screen as = new AdminScreen();
			as.start();
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
