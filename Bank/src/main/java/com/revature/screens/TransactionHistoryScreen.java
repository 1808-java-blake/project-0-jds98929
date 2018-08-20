package com.revature.screens;

import java.util.List;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.TransactionDao;
import com.revature.util.AppState;

public class TransactionHistoryScreen implements Screen{
	
	private AppState state = AppState.state;
	private TransactionDao td = TransactionDao.currentTransactionDao;
	private Scanner scan = new Scanner(System.in);
	
	public Screen start() {
		User currentUser = state.getCurrentUser();
		td.retrieveTransactionHistory(currentUser);
		List<String> al = currentUser.getT().getTransactionList();
		for (int i = al.size() - 1; i >= 0; i--) {
			System.out.println(al.get(i));
		}
		System.out.println();
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
