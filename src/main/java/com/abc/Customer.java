package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * 
 * @author Sandeep Srivastava
 *
 */
public class Customer {
    private String name;
    private List<Account> accounts;

    /**
     * 
     * @param name, not null
     */
    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    /**
     * 
     * @param account, not null
     */
    public void openAccount(Account account) {
        if(!this.getAccounts().contains(account)){
        	this.getAccounts().add(account);
        }
    }

    public int getNumberOfAccounts() {
        return this.getAccounts().size();
    }

    /**
     * 
     * @return double
     */
    public double totalInterestEarned() {
        double total = 0;
        for (Account a : this.getAccounts())
            total += a.interestEarned();
        return total;
    }

    /**
     * 
     * @return String
     */
    public String getStatement() {
        StringBuilder statement = new StringBuilder("Statement for " + this.getName() + "\n");
        double total = 0.0;
        for (Account a : this.getAccounts()) {
            statement.append("\n" + statementForAccount(a) + "\n");
            total += a.sumTransactions();
        }
        statement.append("\nTotal In All Accounts " + toDollars(total));
        return statement.toString();
    }

    private String statementForAccount(Account a) {
        StringBuilder s = new StringBuilder();

       //Translate to pretty account type
        switch(a.getAccountType()){
            case Account.CHECKING:
                s.append("Checking Account\n");
                break;
            case Account.SAVINGS:
                s.append("Savings Account\n");
                break;
            case Account.MAXI_SAVINGS:
                s.append("Maxi Savings Account\n");
                break;
            default:
            	break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.getTransactions()) {
            s.append("  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n");
            total += t.amount;
        }
        s.append("Total " + toDollars(total));
        return s.toString();
    }
    
   /**
    * This method will do transaction from one account to another account of same customer
    * @param payee
    * @param beneficiary
    * @param amount
    * @return String
    */
    public String trasferFundToOtherAccount(Account payee, Account beneficiary, double amount){
    if(this.getAccounts().contains(payee)&& this.getAccounts().contains(beneficiary)){
    if(payee.checkIfTransactionsExist()<amount){
    	return "INSUFFICIENT FUNDS";
    }else{
    
    	payee.withdraw(amount);
    	beneficiary.deposit(amount);
    	 return "SUCCESS";
    	}
    }else{
		return "ERROR";
	}
   }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
