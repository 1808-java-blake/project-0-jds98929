package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.util.AppState;

public class LoginScreen implements Screen {
    private AppState state = AppState.state;
	private Scanner scan = new Scanner(System.in);
	private Logger log = Logger.getRootLogger();
	private UserDao ud = UserDao.currentUserDao;
	private User currentUser;
	

	@Override

	public Screen start() {
		
		System.out.println("______ _           _        ______             _    \r\n" + 
				"| ___ (_)         | |       | ___ \\           | |   \r\n" + 
				"| |_/ /_ _ __ __ _| |_ ___  | |_/ / __ _ _ __ | | __\r\n" + 
				"|  __/| | '__/ _` | __/ _ \\ | ___ \\/ _` | '_ \\| |/ /\r\n" + 
				"| |   | | | | (_| | ||  __/ | |_/ / (_| | | | |   < \r\n" + 
				"\\_|   |_|_|  \\__,_|\\__\\___| \\____/ \\__,_|_| |_|_|\\_\\\n               "
				+ "   .--\"\"\"\"''-.\r\n" + 
				"              .-'            '.\r\n" + 
				"            .'                 '.\r\n" + 
				"           /            .        )\r\n" + 
				"          |                   _  (\r\n" + 
				"          |          .       / \\  \\\r\n" + 
				"          \\         .     .  \\_/  |\r\n" + 
				"           \\    .--' .  '         /\r\n" + 
				"            \\  /  .'____ _       /,\r\n" + 
				"             '/   (\\    `)\\       |\r\n" + 
				"             ||\\__||    |;-.-.-,-,|\r\n" + 
				"             \\\\___//|   \\--'-'-'-'|\r\n" + 
				"              '---' \\             |\r\n" + 
				"       .--.          '---------.__)   .-.\r\n" + 
				"      .'   \\                         /  '.\r\n" + 
				"     (      '.                    _.'     )\r\n" + 
				"      '---.   '.              _.-'    .--'\r\n" + 
				"           `.   `-._      _.-'   _.-'`\r\n" + 
				"             `-._   '-.,-'   _.-'\r\n" + 
				"                 `-._   `'.-'\r\n" + 
				"               _.-'` `;.   '-._\r\n" + 
				"        .--.-'`  _.-'`  `'-._  `'-.--.\r\n" + 
				"       (       .'            '.       )\r\n" + 
				"        `,  _.'                '._  ,'\r\n" + 
				"          ``                      ``");

		System.out.println("Enter Username or type Register to sign up: ");

		String username = scan.nextLine();

		if ("register".equalsIgnoreCase(username)) {

             return new RegisterUserScreen();
			

		}

		System.out.println("Enter Password: ");

		String password = scan.nextLine();

		if ("admin".equals(password) && "admin".equals(username)) {
			
			return new AdminScreen();
		}
		
		if("blackbeard".equals(username) && "ocracoke".equals(password)) {
			
			return new BlackbeardScreen();
		}
		
		currentUser = ud.findByUsernameAndPassword(username, password);

		if (currentUser == null && !("admin".equals(username) && "admin".equals(password))) {

			System.out.println("unable to login");
			return this;
		}
		state.setCurrentUser(currentUser);
		
		log.info("user successfully logged in");
		log.info("welcome" + currentUser);
		

		return new HomeScreen();
	
	}


}
