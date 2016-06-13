package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Sandeep Srivastava
 *
 */
public class Bank {
	Logger logger = Logger.getLogger(Bank.class.getName());
    private List<Customer> customers;

    /**
     * Constructor to initialize customers ArrayList
     */
    public Bank() {
        customers = new ArrayList<Customer>();
    }

    /**
     * 
     * @param customer, not null
     */
    public void addCustomer(Customer customer) {
        this.getCustomers().add(customer);
    }

    /**
     * 
     * @return String
     */
    public String customerSummary() {
        StringBuilder summary = new StringBuilder("Customer Summary");
        for (Customer c : this.getCustomers())
            summary.append("\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")");
        return summary.toString();
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }
    /**
     * 
     * @return double
     */
    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: this.getCustomers())
            total += c.totalInterestEarned();
        return total;
    }

    public String getFirstCustomer() {
        try {
            return this.getCustomers().get(0).getName();
        } catch (NullPointerException e){
        	logger.log(Level.SEVERE, "NullPointerException", e);
        	return "Error"; 
        }
    }
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
    
    
}
