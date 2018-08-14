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
		
		System.out.println("______ _           _        ______             _    \r\n" + 
				"| ___ (_)         | |       | ___ \\           | |   \r\n" + 
				"| |_/ /_ _ __ __ _| |_ ___  | |_/ / __ _ _ __ | | __\r\n" + 
				"|  __/| | '__/ _` | __/ _ \\ | ___ \\/ _` | '_ \\| |/ /\r\n" + 
				"| |   | | | | (_| | ||  __/ | |_/ / (_| | | | |   < \r\n" + 
				"\\_|   |_|_|  \\__,_|\\__\\___| \\____/ \\__,_|_| |_|_|\\_\\\n               "
				+ ".--\"\"\"\"''-.\r\n" + 
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
				"        jgs   '---' \\             |\r\n" + 
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

			Screen rs = new RegisterUserScreen();
			rs.start();
			return this;

		}

		System.out.println("Enter Password: ");

		String password = scan.nextLine();

		if ("admin".equals(password) && "admin".equals(username)) {
			
			Screen as = new AdminScreen();
			as.start();
			return this;
		}
		
		if("blackbeard".equals(username) && "ocracoke".equals(password)) {
			
			Screen bs = new BlackbeardScreen();
			bs.start();
			return this;
		}
		
		currentUser = ud.findByUsernameAndPassword(username, password);

		if (currentUser == null && !("admin".equals(username) && "admin".equals(password))) {

			System.out.println("unable to login");
			return this;
		}
		
	
		Screen hs = new HomeScreen();
		hs.start();

		return this;
	}


}
