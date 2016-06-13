package com.abc;

import java.util.Date;

/**
 * 
 * @author Sandeep Srivastava
 *
 */
public class Transaction {
    /**
     * amount for transaction
     */
	public final double amount;
	private Date transactionDate;

    /**
     * 
     * @param amount, not null
     */
    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
    
    

}
