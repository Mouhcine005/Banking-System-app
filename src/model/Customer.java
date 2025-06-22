package model;

import java.util.List;

public class Customer {
	String name;
	int customerId;
	List<Account> accounts;
	String contactInfo;
	void addAcc(Account a);
	void removeAcc(Account a);
	List<Account> getAccounts();
	String toString();
}
