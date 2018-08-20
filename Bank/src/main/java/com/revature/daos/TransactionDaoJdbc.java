package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.util.ConnectionUtil;

public class TransactionDaoJdbc implements TransactionDao {
	
	private ConnectionUtil cu = ConnectionUtil.cu;
	private Logger log = Logger.getRootLogger();
	
	@Override
	public int updateTransactionHistory(User u) {
		try (Connection conn = cu.getConnection()){
			//don't do this
			//Statement s = conn.createStatement();				
			//ResultSet rs = s.executeQuery("SELECT * FROM app_users WHERE username = '" + username + "'and pass = '" + password + "';");
			//do this, can't do sql injection,i.e. bypass password with username entry "name' OR 1=1 ORDER BY u_id" or "name' --"
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO transactions (receipt, u_id) VALUES (?,?);");
			ps.setString(1, u.getT().getTransactionHistory());
			ps.setInt(2, u.getId());
		
			int recordsCreated = ps.executeUpdate();
				
			log.trace(recordsCreated + " records created");
				

		} catch (SQLException e) {

			log.error(e.getMessage());

			log.warn("failed to create new user");
		}
			
		return 0;
			
	}
	
	@Override
	public void retrieveTransactionHistory(User u) {
		try (Connection conn = cu.getConnection()){
			//don't do this
			//Statement s = conn.createStatement();				
			//ResultSet rs = s.executeQuery("SELECT * FROM app_users WHERE username = '" + username + "'and pass = '" + password + "';");
			//do this, can't do sql injection,i.e. bypass password with username entry "name' OR 1=1 ORDER BY u_id" or "name' --"
			List<String> transactionHistory = new ArrayList<String>();
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM transactions WHERE u_id = ?");
			ps.setInt(1, u.getId());
		
			ResultSet rs = ps.executeQuery();
				
			while(rs.next()) {
				transactionHistory.add(rs.getString("receipt"));
				transactionHistory.add(String.valueOf(rs.getTimestamp("t_date")) + " ");
			}
			
			u.getT().setTransactionList(transactionHistory);
		} catch (SQLException e) {

			log.error(e.getMessage());

			log.warn("failed to create new user");
		}
			
	}
}
