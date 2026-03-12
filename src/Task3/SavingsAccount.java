package Task3;

public class SavingsAccount extends Account {

    private double interestRate = 0.05;

    public SavingsAccount(double initialBalance) {
        super(initialBalance);
    }

    public void applyInterest() {

        double interest = getBalance() * interestRate;

        deposit(interest);

        System.out.println("Interest added: " + interest);
    }
}