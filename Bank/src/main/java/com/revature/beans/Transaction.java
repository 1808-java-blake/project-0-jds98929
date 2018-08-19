package com.revature.beans;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
	private String transactionHistory;
	private List<String> transactionList = new ArrayList<String>();
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(String transactionHistory, List<String> transactionList) {
		super();
		this.transactionHistory = transactionHistory;
		this.transactionList = transactionList;
	}
	public String getTransactionHistory() {
		return transactionHistory;
	}
	public void setTransactionHistory(String transactionHistory) {
		this.transactionHistory = transactionHistory;
	}
	public List<String> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<String> transactionList) {
		this.transactionList = transactionList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transactionHistory == null) ? 0 : transactionHistory.hashCode());
		result = prime * result + ((transactionList == null) ? 0 : transactionList.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (transactionHistory == null) {
			if (other.transactionHistory != null)
				return false;
		} else if (!transactionHistory.equals(other.transactionHistory))
			return false;
		if (transactionList == null) {
			if (other.transactionList != null)
				return false;
		} else if (!transactionList.equals(other.transactionList))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Transaction [transactionHistory=" + transactionHistory + ", transactionList=" + transactionList + "]";
	}

}