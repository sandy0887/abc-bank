package com.abc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * 
 * @author Sandeep Srivastava
 *
 */
public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    
    private List<Transaction> transactions;

    /**
     * public constructor to initialize account type and creates new Transaction List
     * @param accountType
     */
    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    /**
     * 
     * @param amount, double
     */
    public void deposit(double amount) {
        if (amount > 0) {
        	getTransactions().add(new Transaction(amount));
        	} else {
        		throw new IllegalArgumentException("amount must be greater than zero");
        }
    }

    /**
     * 
     * @param amount, double
     */
    public void withdraw(double amount) {
    if (amount > 0) {
    	getTransactions().add(new Transaction(-amount));
    	} else {
    		throw new IllegalArgumentException("amount must be greater than zero");
    }
}
    /**
     * public method to calculate interest earned
     * @return double
     */
    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
            case MAXI_SAVINGS:
			return getIntersEarnForMaxiSavings(amount);
            default:
                return amount * 0.001;
        }
    }

	private double getIntersEarnForMaxiSavings(double amount) {
		
		if(this.getTransactions() != null && !this.getTransactions().isEmpty()){
			Transaction lastTransaction  = this.getTransactions().get(this.getTransactions().size()-1);
			if(lastTransaction != null && lastTransaction.getTransactionDate() != null && daysBetween(new Date(),lastTransaction.getTransactionDate())<10){
				return amount * 0.01;
			}else{
				return getLastTransactionInterest(amount, lastTransaction);
				
			}
		}else{
			return simpleInterest(amount, 5.00, 0);
		}
	}

	private double getLastTransactionInterest(double amount, Transaction lastTransaction) {
		if(lastTransaction.getTransactionDate() != null){
			return simpleInterest(amount, 5.00, daysBetween(new Date(),lastTransaction.getTransactionDate()));
		}else{
			return simpleInterest(amount, 5.00, 0);
		}
	}
	
	private int daysBetween(Date one, Date two){
		// Creates two calendars instances
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(one);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(two);
        
        // Get the represented date in milliseconds
        long milis1 = cal1.getTimeInMillis();
        long milis2 = cal2.getTimeInMillis();
        
        // Calculate difference in milliseconds
        long diff = milis2 - milis1;
        
        // Calculate difference in days
        long diffDays = diff / (24 * 60 * 60 * 1000);
        Integer i = (int) diffDays;
        return i.intValue();
	}

    /**
     * 
     * @return double
     */
	public double sumTransactions() {
       return checkIfTransactionsExist();
    }

    /**
     * 
     * @return double
     */
	public double checkIfTransactionsExist() {
        double amount = 0.0;
        for (Transaction t: getTransactions())
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
    
	private static double simpleInterest(double principle, double rate, int time){
        return (principle*rate*time)/100;
    }

}
