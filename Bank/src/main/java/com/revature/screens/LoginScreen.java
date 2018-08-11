package com.revature.screens;

import java.util.Scanner;
import com.revature.beans.User;
import com.revature.daos.UserDao;

public class LoginScreen implements Screen {

	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	public static User currentUser; 

	@Override

	public Screen start() {

		System.out.println("Enter Username or type Register to sign up: ");

		String username = scan.nextLine();

		if ("register".equalsIgnoreCase(username)) {

			Screen rs = new RegisterUserScreen();
			rs.start();
			return this;

		}

		System.out.println("Enter Password: ");

		String password = scan.nextLine();

		if ("admin".equals(password) && "admin".equals(username)) {
			
			Screen as = new AdminScreen();
			as.start();
		}
		
		currentUser = ud.findByUsernameAndPassword(username, password);

		if (currentUser == null && !("admin".equals(username) && "admin".equals(password))) {

			System.out.println("unable to login");
			return this;
		}
		
		if ("admin".equals(username) && "admin".equals(password)) {
			return this;
		}
	
		Screen hs = new HomeScreen();
		hs.start();

		return this;
	}


}
