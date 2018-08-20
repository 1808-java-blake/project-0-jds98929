package com.revature.daos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.util.ConnectionUtil;
 
public class UserDaoJdbc implements UserDao{
		
	private ConnectionUtil cu = ConnectionUtil.cu;
	private Logger log = Logger.getRootLogger();
	
	@Override
	public int createUser(User u) {
		try (Connection conn = cu.getConnection()){
			//don't do this
			//Statement s = conn.createStatement();
			//ResultSet rs = s.executeQuery("SELECT * FROM app_users WHERE username = '" + username + "'and pass = '" + password + "';");
			//do this, can't do sql injection, i.e. bypass password with username entry "name' OR 1=1 ORDER BY u_id" or "name' --"
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO users (username, password, firstname, lastname, age, checking_balance, savings_balance) VALUES (?,?,?,?,?,?,?);",
					new String[] {"user_id"});
			ps.setString(1, u.getUsername());
			ps.setString(2,  u.getPassword());
			ps.setString(3,  u.getFirstName());
			ps.setString(4,  u.getLastName());
			ps.setInt(5,  u.getAge());
			ps.setString(6,  u.getCheckingAccountBalance());
			ps.setString(7,  u.getSavingsAccountBalance());
	
			int recordsCreated = ps.executeUpdate();
			
			log.trace(recordsCreated + " records created");
			
			ResultSet rs = ps.getGeneratedKeys();

			if(rs.next()) {
				
				log.trace("generated user id is" + rs.getInt("user_id"));

				return rs.getInt("user_id");

			}

		} catch (SQLException e) {

			log.error(e.getMessage());

			log.warn("failed to create new user");

		}
		
		return 0;
		
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		User u = new User();
		// TODO Auto-generated method stub
		try (Connection conn = cu.getConnection()){
			//don't do this
			//Statement s = conn.createStatement();
			//ResultSet rs = s.executeQuery("SELECT * FROM app_users WHERE username = '" + username + "'and pass = '" + password + "';");
			//do this, can't bypass password with username entry "name' OR 1=1 ORDER BY u_id" or "name' --"
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM users WHERE username=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {

				u.setAge(rs.getInt("age"));
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
			    u.setUsername(rs.getString("username"));
			    u.setPassword(rs.getString("password"));
			    u.setCheckingAccountBalance(rs.getString("checking_balance"));
			    u.setSavingsAccountBalance(rs.getString("savings_balance"));
				u.setId(rs.getInt("user_id"));
			}else {
				log.trace("failed to find user with provided credentials from the db");
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public User findByUsername(String username) {
		User u = new User();
		// TODO Auto-generated method stub
		try (Connection conn = cu.getConnection()){
			//don't do this
			//Statement s = conn.createStatement();
			//ResultSet rs = s.executeQuery("SELECT * FROM app_users WHERE username = '" + username + "'and pass = '" + password + "';");
			//do this, can't bypass password with username entry "name' OR 1=1 ORDER BY u_id" or "name' --"
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM users WHERE username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {

				u.setAge(rs.getInt("age"));
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
			    u.setUsername(rs.getString("username"));
			    u.setPassword(rs.getString("password"));
			    u.setCheckingAccountBalance(rs.getString("checking_balance"));
			    u.setSavingsAccountBalance(rs.getString("savings_balance"));
				u.setId(rs.getInt("user_id"));
			}else {
				log.trace("failed to find user with provided credentials from the db");
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public void updateUser(User u) {
		try (Connection conn = cu.getConnection()){
			//don't do this
			//Statement s = conn.createStatement();
			//ResultSet rs = s.executeQuery("SELECT * FROM app_users WHERE username = '" + username + "'and pass = '" + password + "';");
			//do this, can't do sql injection, i.e. bypass password with username entry "name' OR 1=1 ORDER BY u_id" or "name' --"
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE users SET username = ?, password = ?, age = ?, firstname = ?, lastname = ?, checking_balance = ?, savings_balance = ?" 
					+ "WHERE username = ?");
			ps.setString(1, u.getUsername());
			ps.setString(2,  u.getPassword());
			ps.setInt(3,  u.getAge());
			ps.setString(4,  u.getFirstName());
			ps.setString(5,  u.getLastName());
			ps.setString(6, u.getCheckingAccountBalance());
			ps.setString(7,  u.getSavingsAccountBalance());
			ps.setString(8, u.getUsername());
		
			int recordsCreated = ps.executeUpdate();

			log.trace(recordsCreated + " records created");
			

		} catch (SQLException e) {

			log.error(e.getMessage());

			log.warn("failed to create new user");

		}
		
	}

	@Override
	public void deleteUser(User u) {
		try (Connection conn = cu.getConnection()){
			//don't do this
			//Statement s = conn.createStatement();
			//ResultSet rs = s.executeQuery("SELECT * FROM app_users WHERE username = '" + username + "'and pass = '" + password + "';");
			//do this, can't do sql injection, i.e. bypass password with username entry "name' OR 1=1 ORDER BY u_id" or "name' --"
			PreparedStatement ps = conn.prepareStatement(
					"DELETE FROM users WHERE username = ?");
			ps.setString(1, u.getUsername());
		
			int recordsDeleted = ps.executeUpdate();

			log.trace(recordsDeleted + " record(s) Deleted");
			

		} catch (SQLException e) {

			log.error(e.getMessage());

			log.warn("failed to create new user");
		
		}
	}
	

}
