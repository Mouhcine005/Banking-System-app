package app;

import java.util.Scanner;
import exceptions.*;
import model.*;
import service.*;

public class BankApp {
	
	
	private static BankService bankService = new BankServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;
        while (running) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    showCustomerAccounts();
                    break;
                case 6:
                    showAccountTransactions();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Banking System Menu ===");
        System.out.println("1. Add Customer");
        System.out.println("2. Create Account");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Show Customer Accounts");
        System.out.println("6. Show Account Transactions");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }
    
    private static void addCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact info: ");
        String contact = scanner.nextLine();

        Customer c = new Customer();
        c.setName(name);
        c.setContactInfo(contact);

        bankService.addCustomer(c);
        System.out.println("Customer added with ID: " + c.getCustomerId());
    }
    
    private static void createAccount() {
        System.out.print("Enter customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter account type: ");
        String accType = scanner.nextLine();

        bankService.createAcc(customerId, accType);
    }
    
    private static void deposit() {
        try {
            System.out.print("Enter account number: ");
            int accNum = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter amount to deposit: ");
            float amount = Float.parseFloat(scanner.nextLine());

            boolean success = bankService.deposit(accNum, amount);
            if (!success) System.out.println("Deposit failed.");
        } catch (InvalidAmountException e) {
            System.out.println("Invalid amount: " + e.getMessage());
        }
    }
    
    private static void withdraw() {
        try {
            System.out.print("Enter account number: ");
            int accNum = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter amount to withdraw: ");
            float amount = Float.parseFloat(scanner.nextLine());

            boolean success = bankService.withdraw(accNum, amount);
            if (!success) System.out.println("Withdrawal failed.");
        } catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void showCustomerAccounts() {
        System.out.print("Enter customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        var accounts = bankService.getAccounts(customerId);

        if (accounts.isEmpty()) {
            System.out.println("No accounts found for this customer.");
        } else {
            System.out.println("Accounts:");
            for (Account a : accounts) {
                System.out.println(" - Account Number: " + a.getAccountNum() + ", Type: " + a.getAccType() + ", Balance: " + a.getBalance());
            }
        }
    }
    
    private static void showAccountTransactions() {
        System.out.print("Enter account number: ");
        int accNum = Integer.parseInt(scanner.nextLine());
        var transactions = bankService.getTransactions(accNum);

        if (transactions.isEmpty()) {
            System.out.println("No transactions found for this account.");
        } else {
            System.out.println("Transactions:");
            for (Transaction t : transactions) {
                System.out.println(" - [" + t.getDate() + "] " + t.getTransactionType() + ": " + t.getAmount());
            }
        }
    }
}
