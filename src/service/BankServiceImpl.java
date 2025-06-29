package service;

import java.util.ArrayList;
import java.util.List;

import exceptions.*;
import fileManager.*;
import model.*;
public class BankServiceImpl implements BankService{
	 
	private static int nextAccountNumber = 1; //you can change it with whatever value you want !
	private static int nextTransactionId = 1; //you can change it with whatever value you want !
	
	
    private List<Customer> customers = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    
    public BankServiceImpl() {
        try {
            this.customers = CustomerFileManager.readCustomers();
            List<Account> accounts = AccountFileManager.readAccounts();
            this.transactions = TransactionFileManager.readTransactions();

            linkData(customers, accounts, transactions);

            // Update ID counters
            for (Account acc : accounts) {
                if (acc.getAccountNum() >= nextAccountNumber) {
                    nextAccountNumber=acc.getAccountNum()+1;
                }
            }
            for (Transaction t : transactions) {
                if (t.getTransactionId() >= nextTransactionId) {
                    nextTransactionId=t.getTransactionId()+1;
                }
            }
        } catch (Exception e) {
            System.out.println("Error initializing BankService: " + e.getMessage());
        }
    }
    @Override
    public List<Account> getAllAccounts() {
        List<Account> all = new ArrayList<>();
        for (Customer c : customers) {
            all.addAll(c.getAccounts());
        }
        return all;
    }
    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }

    
	@Override
	public void addCustomer(Customer c)
	{
		customers.add(c);
		CustomerFileManager.writeCustomers(customers);
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
			CustomerFileManager.writeCustomers(customers);
			AccountFileManager.writeAccounts(getAllAccounts());
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
	    			AccountFileManager.writeAccounts(getAllAccounts());
	    			TransactionFileManager.writeTransactions(getAllTransactions());
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
	    			AccountFileManager.writeAccounts(getAllAccounts());
	    			TransactionFileManager.writeTransactions(getAllTransactions());
	                System.out.println(amount + " withdrawn.");
	                return true;
	            }
	        }
	    }
	    System.out.println("Account with number " + accountNum + " not found!");
	    return false;
	}
	
	@Override 
	public List<Transaction> getAllTransactions()
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
	
	@Override
	 public void linkData(List<Customer> customers, List<Account> accounts, List<Transaction> transactions) {
	        for (Account acc : accounts) {
	            for (Customer cust : customers) {
	                if (acc.getCustomer().getCustomerId() == cust.getCustomerId()) {
	                    acc.setCustomer(cust);
	                    cust.addAcc(acc);
	                    break;
	                }
	            }
	        }

	        for (Transaction tr : transactions) {
	            for (Account acc : accounts) {
	                if (tr.getAccNum() == acc.getAccountNum()) {
	                    acc.getTransactions().add(tr);
	                    break;
	                }
	            }
	        }
	    }

}
