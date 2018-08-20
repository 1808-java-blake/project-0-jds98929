	package com.revature.screens;

	import java.util.Scanner;


	public class HomeScreen implements Screen {

		private Scanner scan = new Scanner(System.in);

		public Screen start() {

			System.out.println("Please choose from following options:");

			System.out.println("Enter 1 to deposit money");

			System.out.println("Enter 2 to withdrawal money");
			
			System.out.println("Enter 3 to view transaction history");
			
			System.out.println("Enter 4 to view account balances");
			
			System.out.println("Enter 5 to wire money");
			
			System.out.println("Enter 6 to log out");

			String selection = scan.nextLine();

			switch (selection) {

			case "1":

				return new DepositScreen();	


			case "2":

				return new WithdrawalScreen();
			
			
			case "3":
				
				return new TransactionHistoryScreen();
				

			case "4":
				
				return new AccountBalanceScreen();
				
				
			case "5":
				
				return new WireScreen();
				
			case "6":
				
				return new LoginScreen();
				
			
			default:

				break;

			}



			return this;

		}



	}

