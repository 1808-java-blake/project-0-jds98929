package com.revature.daos;

import com.revature.beans.User;

public interface TransactionDao {
	
	public static final TransactionDao currentTransactionDao = new TransactionDaoJdbc();
	
	int updateTransactionHistory(User U);
	
	void retrieveTransactionHistory(User u);
}
