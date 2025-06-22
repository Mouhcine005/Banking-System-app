package service;

import java.util.ArrayList;
import java.util.List;

import exceptions.*;
import model.*;
public class BankServiceImpl implements BankService{
	 
	private static int nextAccountNumber = 1; //you can change it with whatever value you want !
	private static int nextTransactionId = 1; //you can change it with whatever value you want !
	
	
    private List<Customer> customers = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    
	@Override
	public void addCustomer(Customer c)
	{
		customers.add(c);
	}
	
	
	@Override
	public void createAcc(int customerId,String accType)
	{
		Customer c = getCustomerById(customerId);
		if(c != null)
		{
			int accNum=nextAccountNumber++;
			Account acc = new Account(accNum,c,accType);
			System.out.println("Account created with Account Number: " + accNum);
			c.addAcc(acc);
		}
		else {
	        System.out.println("Customer not found.");
	    }
	}
	
	@Override
	public Customer getCustomerById(int id)
	{
		for(Customer c : customers)
		{
			if(c.getCustomerId()==id)
			{
				return c;
			}
		}
		return null;
	}
	
	@Override
	public boolean deposit(int accountNum, float amount) throws InvalidAmountException {
	    for (Customer c : customers) {
	        for (Account acc : c.getAccounts()) {
	            if (acc.getAccountNum() == accountNum) {
	                acc.deposit(amount); 
	                Transaction t = new Transaction(nextTransactionId++, accountNum, amount, "Deposit");
	                acc.getTransactions().add(t);
	                transactions.add(t);
	                System.out.println(amount + " added to balance.");
	                return true;
	            }
	        }
	    }
	    System.out.println("Account with number " + accountNum + " not found!");
	    return false;
	}
	
	@Override
	public boolean withdraw(int accountNum, float amount) throws InsufficientBalanceException, InvalidAmountException {
		
	    for (Customer c : customers) {
	        for (Account acc : c.getAccounts()) {
	            if (acc.getAccountNum() == accountNum) {
	            	acc.withdraw(amount);
	            	Transaction t = new Transaction(nextTransactionId++, accountNum, amount, "Withdraw");
	            	transactions.add(t);
	            	acc.getTransactions().add(t);
	                System.out.println(amount + " withdrawn.");
	                return true;
	            }
	        }
	    }
	    System.out.println("Account with number " + accountNum + " not found!");
	    return false;
	}
	
	@Override 
	public List<Transaction> getTransactions()
	{
		return transactions;
	}
	
	@Override
	public List<Transaction> getTransactions(int accountNum) {
	    for (Customer c : customers) {
	        for (Account acc : c.getAccounts()) {
	            if (acc.getAccountNum() == accountNum) {
	                return acc.getTransactions();
	            }
	        }
	    }
	    System.out.println("Account with number " + accountNum + " not found!");
	    return new ArrayList<>();
	}
	
	@Override
	public List<Account> getAccounts(int customerId)
	{
	    for (Customer c : customers) {
	    	if(c.getCustomerId()==customerId)
	    	{
	    		return c.getAccounts();
	    	}
	    }
	    System.out.println("Customer with ID " + customerId + " not found!");
	    return new ArrayList<>();
	}
}
