package service;

import java.util.List;

	import exceptions.InsufficientBalanceException;
	import exceptions.InvalidAmountException;
	import model.Account;
	import model.Customer;
	import model.Transaction;
	
	public interface BankService{
		
		List<Customer> getAllCustomers();
		void addCustomer(Customer c);
		Customer getCustomerById(int id);
		
		void createAcc(int customerId,String accType);
		List<Account> getAllAccounts();
		List<Account> getAccounts(int customerId);
		
		boolean deposit(int accountNum, float amount) throws InvalidAmountException;
		boolean withdraw(int accountNum,float amount) throws InsufficientBalanceException, InvalidAmountException;
		List<Transaction> getAllTransactions();
		List<Transaction> getTransactions(int accNum);

		
		
		public void linkData(List<Customer> customers, List<Account> accounts, List<Transaction> transactions);
}
