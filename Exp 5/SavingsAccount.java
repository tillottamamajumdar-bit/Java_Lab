public class SavingsAccount extends Account {
    double interestRate;
    double minimumBalance;

    public SavingsAccount(String accNo, Customer cust, double balance, double rate, double minBal) {
        super(accNo, cust, balance);
        this.interestRate = rate;
        this.minimumBalance = minBal;
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Savings - Invalid deposit amount!");
            return;
        }
        balance += amount;
        System.out.println("Savings - Deposited: " + amount);
        System.out.println("New Balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Savings - Invalid withdrawal amount!");
        } else if (amount > balance) {
            System.out.println("Savings - Insufficient balance!");
        } else if (balance - amount < minimumBalance) {
            System.out.println("Savings - Cannot go below minimum balance!");
        } else {
            balance -= amount;
            System.out.println("Savings - Withdrawn: " + amount);
            System.out.println("New Balance: " + balance);
        }
    }

    public void applyInterest() {
        double interest = (balance * interestRate) / 100;
        balance += interest;
        System.out.println("Interest applied: " + interest);
        System.out.println("New Balance: " + balance);
    }

    @Override
    public void displayDetails() {
        System.out.println("Savings Account: " + accountNumber);
        System.out.println("Balance: " + balance);
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Minimum Balance: " + minimumBalance);
    }
}