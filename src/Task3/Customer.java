package Task3;

import java.io.Serializable;
import java.util.*;

public class Customer implements Serializable {

    private String name;
    private String address;
    private List<Account> accounts;

    public Customer(String name, String address) {

        this.name = name;
        this.address = address;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void showAccounts() {

        for(Account acc : accounts){

            System.out.println("Account No: " + acc.getAccountNumber()
                    + " Balance: " + acc.getBalance());
        }
    }

    public Account findAccount(int accountNumber) {

        for(Account acc : accounts){

            if(acc.getAccountNumber() == accountNumber){
                return acc;
            }
        }

        return null;
    }
}