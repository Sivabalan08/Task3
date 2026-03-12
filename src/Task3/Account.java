package Task3;

import java.io.Serializable;
import java.util.*;

public class Account implements Serializable {

    private static int nextAccountNumber = 1001;

    private int accountNumber;
    private double balance;
    private List<String> transactions;

    public Account(double initialBalance) {

        this.accountNumber = nextAccountNumber++;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();

        transactions.add("Account created with balance: " + initialBalance);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {

        if(amount <= 0){
            System.out.println("Invalid amount");
            return;
        }

        balance += amount;
        transactions.add("Deposited: " + amount);
    }

    public void withdraw(double amount) throws InsufficientFundsException {

        if(amount > balance){
            throw new InsufficientFundsException("Insufficient balance");
        }

        balance -= amount;
        transactions.add("Withdrawn: " + amount);
    }

    public void showTransactions() {

        for(String t : transactions){
            System.out.println(t);
        }
    }
}