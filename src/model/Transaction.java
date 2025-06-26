package model;

import java.time.LocalDateTime;

public class Transaction {
	private int transactionId;
	private int accNum;
	private LocalDateTime date;
	private float amount;
	private String transactionType;

	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getAccNum() {
		return accNum;
	}
	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Transaction() {
	    this.transactionId = 0;
	    this.accNum = 0;
	    this.amount = 0.0f;
	    this.transactionType = "";
	    this.date = java.time.LocalDateTime.now();
	}
	public Transaction(int transactionId, int accNum, float amount, String transactionType) {
		this.transactionId = transactionId;
		this.accNum = accNum;
		this.date = LocalDateTime.now();
		this.amount = amount;
		this.transactionType = transactionType;
	}
	public Transaction(int transactionId, int accNum, LocalDateTime date, float amount, String transactionType) {
		this.transactionId = transactionId;
		this.accNum = accNum;
		this.date = date;
		this.amount = amount;
		this.transactionType = transactionType;
	}
	public String toString()
	{
		return "Transaction{" +
		           "Transaction id=" + transactionId +
		           ", Account number=" + accNum +
		           ", Amount=" + amount +
		           ", Date='" + date + '\'' +
		           ", Transaction Type='" + transactionType + '\'' +
		           '}';
	}
	
}
