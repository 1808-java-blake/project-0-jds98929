package com.revature.screens;

import java.util.Scanner;
import com.revature.beans.User;
import com.revature.daos.UserDao;

public class RegisterUserScreen implements Screen {

	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;

	@Override

	public Screen start() {

		User u = new User();

		System.out.println("Enter new username");

		u.setUsername(scan.nextLine());

		System.out.println("Enter password");

		u.setPassword(scan.nextLine());

		System.out.println("Enter first name");

		u.setFirstName(scan.nextLine());

		System.out.println("Enter last name");

		u.setLastName(scan.nextLine());
		
		u.setAccountBalance("0.00");

		System.out.println("Enter age");

		String age = scan.nextLine();

		

		try {

			u.setAge(Integer.valueOf(age));

			ud.createUser(u);

			

		} catch (NumberFormatException e) {

			System.out.println("Invalid number");

		}
		
		System.out.println("Registration Successful");
		
		System.out.println("Enter 1 to log in");
		System.out.println("Enter 2 to exit");
		
		String selection = scan.nextLine();
		
		switch (selection) {
		
			case "1":
				Screen ls = new LoginScreen();
				ls.start();
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