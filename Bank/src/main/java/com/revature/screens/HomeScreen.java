	package com.revature.screens;

	import java.util.Scanner;

	import com.revature.beans.User;

	public class HomeScreen implements Screen {

		public static User currentUser = LoginScreen.currentUser;
		private Scanner scan = new Scanner(System.in);

		public Screen start() {

			System.out.println("Please choose from following options:");

			System.out.println("Enter 1 to deposit money");

			System.out.println("Enter 2 to withdrawal money");
			
			System.out.println("Enter 3 to view transaction history");
			
			System.out.println("Enter 4 to view account balance");
			
			System.out.println("Enter 5 to wire money");

			String selection = scan.nextLine();

			switch (selection) {

			case "1":

				Screen ds = new DepositScreen();
				ds.start();

				break;

			case "2":

				Screen ws = new WithdrawalScreen();
				ws.start();

				break;
			
			case "3":
				
				Screen ths = new TransactionHistoryScreen();
				ths.start();
				
				break;

			case "4":
				
				Screen abs = new AccountBalanceScreen();
				abs.start();
				
				break;
				
			case "5":
				
				Screen wires = new WireScreen();
				wires.start();
				
				break;
			
			default:

				break;

			}



			return this;

		}



	}

