package service;

import java.util.List;

	import exceptions.InsufficientBalanceException;
	import exceptions.InvalidAmountException;
	import model.Account;
	import model.Customer;
	import model.Transaction;
	
	public interface BankService{
		void addCustomer(Customer c);
		void createAcc(int customerId,String accType);
		Customer getCustomerById(int id);
		boolean deposit(int accountNum, float amount) throws InvalidAmountException;
		boolean withdraw(int accountNum,float amount) throws InsufficientBalanceException, InvalidAmountException;
		List<Transaction> getTransactions();
		List<Transaction> getTransactions(int accNum);
		List<Account> getAccounts(int customerId);
		
		
		public void linkData(List<Customer> customers, List<Account> accounts, List<Transaction> transactions);
}
