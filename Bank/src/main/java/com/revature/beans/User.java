package com.revature.beans;



public class User{



	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**

	 * 

	 */
	private int id;

	private String username;

	private String password;

	private String firstName;

	private String lastName;

	private int age;

	private String checkingAccountBalance;
	
	private String savingsAccountBalance;
	
	private Transaction t = new Transaction();

	public User(int id, String username, String password, String firstName, String lastName, int age,
			String checkingAccountBalance, String savingsAccountBalance, Transaction t) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.checkingAccountBalance = checkingAccountBalance;
		this.savingsAccountBalance = savingsAccountBalance;
		this.t = t;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCheckingAccountBalance() {
		return checkingAccountBalance;
	}

	public void setCheckingAccountBalance(String checkingAccountBalance) {
		this.checkingAccountBalance = checkingAccountBalance;
	}

	public String getSavingsAccountBalance() {
		return savingsAccountBalance;
	}

	public void setSavingsAccountBalance(String savingsAccountBalance) {
		this.savingsAccountBalance = savingsAccountBalance;
	}

	public Transaction getT() {
		return t;
	}

	public void setT(Transaction t) {
		this.t = t;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((checkingAccountBalance == null) ? 0 : checkingAccountBalance.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((savingsAccountBalance == null) ? 0 : savingsAccountBalance.hashCode());
		result = prime * result + ((t == null) ? 0 : t.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (age != other.age)
			return false;
		if (checkingAccountBalance == null) {
			if (other.checkingAccountBalance != null)
				return false;
		} else if (!checkingAccountBalance.equals(other.checkingAccountBalance))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (savingsAccountBalance == null) {
			if (other.savingsAccountBalance != null)
				return false;
		} else if (!savingsAccountBalance.equals(other.savingsAccountBalance))
			return false;
		if (t == null) {
			if (other.t != null)
				return false;
		} else if (!t.equals(other.t))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", age=" + age + ", checkingAccountBalance=" + checkingAccountBalance
				+ ", savingsAccountBalance=" + savingsAccountBalance + ", t=" + t + "]";
	}
	
	

}