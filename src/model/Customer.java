package model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;
	private int customerId;
	private String contactInfo;
	private List<Account> accounts;
	public Customer(String name, int customerId, String contactInfo) {
		this.name = name;
		this.customerId = customerId;
		this.contactInfo = contactInfo;
		this.accounts = new ArrayList<>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public void addAcc(Account a)
	{
		accounts.add(a);
	}
	public void removeAcc(Account a)
	{
		accounts.remove(a);
	}
	public List<Account> getAccounts()
	{
		return accounts;
	}
	public String toString()
	{
		return "Customer{" +
		           "Customer Name=" + name +
		           ", Customer id=" + customerId +
		           ", contact Informations ='" + contactInfo + '\'' +
		           ", list of accounts=" + getAccounts() +
		           '}';
	}
}
