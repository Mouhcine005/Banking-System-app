package fileManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.Transaction;

public class TransactionFileManager {
	
	 private static final String FILE_PATH = "data/transactions.csv";
	
	public static List<Transaction> readTransactions() throws FileNotFoundException, IOException
	{
		List<Transaction> transactions=new ArrayList<>();
		try(BufferedReader br= new BufferedReader(new FileReader(FILE_PATH)))
		{
			String line;
			while((line=br.readLine()) != null)
			{
				transactions.add(csvToTransaction(line));
			}
		}
		catch (IOException e)
		{
			System.out.println("Error reading transactions: "+e.getMessage());
	}
		return transactions;
	}	
	
	public static void writeTransactions(List<Transaction> transactions)
	{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Transaction transaction : transactions) {
                bw.write(transactionToCSV(transaction));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing transactions: " + e.getMessage());
        }
	}
	
    private static String transactionToCSV(Transaction transaction) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = transaction.getDate().format(formatter);
    	return transaction.getTransactionId()+","+transaction.getAccNum()+","+formattedDate+","+transaction.getAmount()+","+transaction.getTransactionType();
    }
	
    private static Transaction csvToTransaction(String line) {
    	 	String[] parts = line.split(",", -1);
    	 	int transactionId = Integer.parseInt(parts[0]);
		    int accNum = Integer.parseInt(parts[1]);
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    LocalDateTime date = LocalDateTime.parse(parts[2], formatter);
		    float amount = Float.parseFloat(parts[3]);
		    String transactionType = parts[4];
		    return new Transaction(transactionId,accNum,date,amount,transactionType);
    }
}
