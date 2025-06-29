package fileManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Customer;
import model.Transaction;

public class AccountFileManager {
	
	 private static final String FILE_PATH = "src/data/accounts.csv";
	
	public static List<Account> readAccounts() throws FileNotFoundException, IOException
	{
		List<Account> accounts=new ArrayList<>();
		try(BufferedReader br= new BufferedReader(new FileReader(FILE_PATH)))
		{
			String line;
			while((line=br.readLine()) != null)
			{
				accounts.add(csvToAccount(line));
			}
		}
		catch (IOException e)
		{
			System.out.println("Error reading accounts: "+e.getMessage());
	}
		return accounts;
	}	
	
	public static void writeAccounts(List<Account> accounts)
	{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Account account : accounts) {
                bw.write(accountToCSV(account));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing accounts: " + e.getMessage());
        }
	}
	
    private static String accountToCSV(Account account) {
        String transactionsStr = account.getTransactions().stream()
            .map(t -> String.valueOf(t.getTransactionId()))
            .reduce((a, b) -> a + ";" + b)
            .orElse(""); // convert the transactions to a single String

        return account.getAccountNum() + "," +
               account.getBalance() + "," +
               account.getCustomer().getCustomerId() + "," +
               account.getAccType() + "," +
               transactionsStr;
    }
	
    private static Account csvToAccount(String line) {
        String[] parts = line.split(",", -1);
        int accountNum = Integer.parseInt(parts[0]);
        float balance = Float.parseFloat(parts[1]);
        int customerId = Integer.parseInt(parts[2]);
        String accType = parts[3];

        List<Transaction> transactions = new ArrayList<>();
        if (!parts[4].isEmpty()) {
            for (String id : parts[4].split(";")) {
                Transaction t = new Transaction();
                t.setTransactionId(Integer.parseInt(id));
                transactions.add(t);
            }
        }

        Customer customer = new Customer();
        customer.setCustomerId(customerId);

        Account account = new Account(accountNum,balance, customer, accType,new ArrayList<>(transactions));

        return account;
    }
}
