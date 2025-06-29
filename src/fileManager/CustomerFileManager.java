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

public class CustomerFileManager {

	 private static final String FILE_PATH = "src/data/customers.csv";

		private static String customerToCSV(Customer customer) {
		    String accountsStr = customer.getAccounts().stream()
		        .map(a -> String.valueOf(a.getAccountNum()))
		        .reduce((a, b) -> a + ";" + b)
		        .orElse(""); // convert the accounts to a single String
		    
		    return customer.getName() + "," + customer.getCustomerId() + "," + customer.getContactInfo() + "," + accountsStr;
		}
		
		private static Customer csvToCustomer(String line) {
		    String[] parts = line.split(",", -1);
		    String name = parts[0];
		    int customerId = Integer.parseInt(parts[1]);
		    String contactInfo = parts[2];

		    List<Account> accounts = new ArrayList<>();
		    if (parts.length > 3 && !parts[3].isEmpty()) {
		        for (String accId : parts[3].split(";")) {
		            Account a = new Account();
		            a.setAccountNum(Integer.parseInt(accId));
		            accounts.add(a);
		        }
		    }

		    Customer customer = new Customer();
		    customer.setCustomerId(customerId);
		    customer.setName(name);
		    customer.setContactInfo(contactInfo);
		    customer.setAccounts(accounts);
		    return customer;
		}
	 
		public static void writeCustomers(List<Customer> customers)
		{
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
	            for (Customer customer : customers) {
	                bw.write(customerToCSV(customer));
	                bw.newLine();
	            }
	        } catch (IOException e) {
	            System.out.println("Error writing customers: " + e.getMessage());
	        }
		}
		public static List<Customer> readCustomers() throws FileNotFoundException, IOException
		{
			List<Customer> customers=new ArrayList<>();
			try(BufferedReader br= new BufferedReader(new FileReader(FILE_PATH)))
			{
				String line;
				while((line=br.readLine()) != null)
				{
					customers.add(csvToCustomer(line));
				}
			}
			catch (IOException e)
			{
				System.out.println("Error reading customers: "+e.getMessage());
		}
			return customers;
		}	
		
}
