package model;

import exceptions.*;

public class Account {
	
	private int accountNum;
	private float balance;
	private Customer customer;
	private String accType;
	
	public int getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public Account(int accountNum, Customer customer, String accType) {
		this.accountNum = accountNum;
		this.balance = 0;
		this.customer = customer;
		this.accType = accType;
	}
	public Account(Account a) {
		this.accountNum = a.accountNum;
		this.balance = a.balance;
		this.customer = a.customer;
		this.accType = a.accType;
	}

	public float getBalance()
	{
		return balance;
	}
	
	public boolean withdraw(float amount) throws InvalidAmountException,InsufficientBalanceException
	{
		if(amount<=0)
		{
			throw new InvalidAmountException("amount should be more than 0 !");
		}
		else if(amount>balance)
		{
			throw new InsufficientBalanceException("amount must be less than balance !");
		}
		else
		{
			balance-=amount;
			return true;
		}
		
	}

	public void deposit(float amount) throws InvalidAmountException
	{
		if(amount <= 0)
		{
			throw new InvalidAmountException("amount should be more than 0 !");
		}
		balance += amount;
	}
	
	@Override
	public String toString() {
	    return "Account{" +
	           "account Number=" + accountNum +
	           ", balance=" + balance +
	           ", customer='" + customer + '\'' +
	           ", account Type='" + accType + '\'' +
	           '}';
	}
}
