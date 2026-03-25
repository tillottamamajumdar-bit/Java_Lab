public class LoanAccount extends Account {
    double interestRate;
    int tenure;
    String loanType;

    public LoanAccount(String accNo, Customer cust, double loanAmount,
                       double rate, int tenure, String type) {
        super(accNo, cust, loanAmount);
        this.interestRate = rate;
        this.tenure = tenure;
        this.loanType = type;
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Loan - Invalid repayment amount!");
            return;
        }

        if (amount > balance) {
            System.out.println("Loan - Payment exceeds loan amount!");
        } else {
            balance -= amount;
            System.out.println("Loan - Repayment of: " + amount);
            System.out.println("Remaining Loan: " + balance);
        }
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Loan - Withdrawal not allowed!");
    }

    @Override
    public void displayDetails() {
        System.out.println("Loan Account: " + accountNumber);
        System.out.println("Loan Type: " + loanType);
        System.out.println("Outstanding Loan: " + balance);
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Tenure: " + tenure + " months");
    }
}