import java.util.ArrayList;

public class BankingApp {
    public static void main(String[] args) {

        // Customers
        Customer c1 = new Customer(1, "Raven", "raven@mail.com", "9876545400");
        Customer c2 = new Customer(2, "Luka", "luka@mail.com", "9123400775");

        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(c1);
        customers.add(c2);

        // Accounts
        SavingsAccount sa1 = new SavingsAccount("SA101", c1, 50000, 4.5, 1000);
        LoanAccount la1 = new LoanAccount("LA201", c1, 200000, 8.5, 60, "Home");

        SavingsAccount sa2 = new SavingsAccount("SA102", c2, 30000, 4.0, 1000);

        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(sa1);
        accounts.add(la1);
        accounts.add(sa2);

        // Transactions
        sa1.deposit(5000);
        sa1.withdraw(2000);
        sa1.applyInterest();

        la1.deposit(10000);
        la1.withdraw(500); // not allowed

        System.out.println("\n===== EDGE CASE TESTS =====");

        // Negative deposit
        sa1.deposit(-500);

        // Withdraw more than balance
        sa1.withdraw(999999);

        // Below minimum balance
        sa1.withdraw(49000);

        // Loan overpayment
        la1.deposit(999999);

// Withdraw from loan
la1.withdraw(1000);

        // Display Report
        System.out.println("\n===== CUSTOMER REPORT =====");

        for (Customer c : customers) {
            System.out.println("\n--- Customer Details ---");
            c.displayDetails();

            System.out.println("--- Accounts ---");
            boolean found = false;

            for (Account a : accounts) {
                if (a.customer.customerId == c.customerId) {
                    a.displayDetails();
                    System.out.println();
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No accounts found.");
            }
        }
    }
}