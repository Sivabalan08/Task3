package Task3;

import java.util.*;
import java.io.*;

public class BankingSystem {

    static List<Customer> customers = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        loadData();

        int choice;

        do {

            System.out.println("\n---- Banking Menu ----");
            System.out.println("1 Create Customer");
            System.out.println("2 Create Account");
            System.out.println("3 Deposit");
            System.out.println("4 Withdraw");
            System.out.println("5 Show Customers");
            System.out.println("6 Search Customer");
            System.out.println("0 Exit");
            System.out.println("Enter the choice: ");

            choice = sc.nextInt();

            switch(choice){

                case 1:
                    createCustomer();
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
                    showCustomers();
                    break;

                case 6:
                    searchCustomer();
                    break;

                case 0:
                    saveData();
                    System.out.println("Data saved. Exiting...");
                    break;
            }

        }while(choice != 0);
    }

    static void createCustomer(){

        System.out.print("Enter name: ");
        String name = sc.next();

        System.out.print("Enter address: ");
        String address = sc.next();

        Customer customer = new Customer(name, address);

        customers.add(customer);

        System.out.println("Customer created successfully");
    }

    static Customer findCustomer(String name){

        for(Customer c : customers){

            if(c.getName().equalsIgnoreCase(name)){
                return c;
            }
        }

        return null;
    }

    static void createAccount(){

        System.out.print("Enter customer name: ");
        String name = sc.next();

        Customer customer = findCustomer(name);

        if(customer == null){
            System.out.println("Customer not found");
            return;
        }

        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();

        Account acc = new SavingsAccount(balance);

        customer.addAccount(acc);

        System.out.println("Account created. Number: " + acc.getAccountNumber());
    }

    static void deposit(){

        System.out.print("Enter customer name: ");
        String name = sc.next();

        Customer customer = findCustomer(name);

        if(customer == null){
            System.out.println("Customer not found");
            return;
        }

        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();

        Account acc = customer.findAccount(accNo);

        if(acc == null){
            System.out.println("Account not found");
            return;
        }

        System.out.print("Enter amount: ");
        acc.deposit(sc.nextDouble());
    }

    static void withdraw(){

        try{

            System.out.print("Enter customer name: ");
            String name = sc.next();

            Customer customer = findCustomer(name);

            if(customer == null){
                System.out.println("Customer not found");
                return;
            }

            System.out.print("Enter account number: ");
            int accNo = sc.nextInt();

            Account acc = customer.findAccount(accNo);

            if(acc == null){
                System.out.println("Account not found");
                return;
            }

            System.out.print("Enter amount: ");
            acc.withdraw(sc.nextDouble());

        }catch(InsufficientFundsException e){

            System.out.println(e.getMessage());
        }
    }

    static void showCustomers(){

        for(Customer c : customers){

            System.out.println("\nCustomer: " + c.getName());

            c.showAccounts();
        }
    }

    static void searchCustomer(){

        System.out.print("Enter customer name: ");

        String name = sc.next();

        Customer c = findCustomer(name);

        if(c == null){

            System.out.println("Customer not found");

        }else{

            System.out.println("Customer: " + c.getName());

            c.showAccounts();
        }
    }

    static void saveData(){

        try{

            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream("bankdata.dat"));

            out.writeObject(customers);

            out.close();

        }catch(Exception e){

            System.out.println("Error saving data");
        }
    }

    static void loadData(){

        try{

            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream("bankdata.dat"));

            customers = (List<Customer>) in.readObject();

            in.close();

        }catch(Exception e){

            customers = new ArrayList<>();
        }
    }
}