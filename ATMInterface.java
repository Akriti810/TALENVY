import java.util.Scanner;

// Bank Account class
class BankAccount {
    private double balance;

    // Constructor
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(" Successfully deposited ₹" + amount);
        } else {
            System.out.println(" Invalid deposit amount!");
        }
    }

    // Withdraw method
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(" Successfully withdrawn ₹" + amount);
            return true;
        } else if (amount > balance) {
            System.out.println(" Insufficient balance!");
            return false;
        } else {
            System.out.println(" Invalid withdrawal amount!");
            return false;
        }
    }

    // Check Balance method
    public double getBalance() {
        return balance;
    }
}

// ATM class
class ATM {
    private BankAccount account;
    private Scanner scanner;

    // Constructor
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Display ATM Menu
    public void showMenu() {
        while (true) {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleWithdrawal();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    System.out.println(" Current Balance: ₹" + account.getBalance());
                    break;
                case 4:
                    System.out.println(" Exiting... Thank you for using the ATM!");
                    return;
                default:
                    System.out.println(" Invalid option! Please choose again.");
            }
        }
    }

    // Handle Withdrawal
    private void handleWithdrawal() {
        System.out.print("Enter withdrawal amount: ₹");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    // Handle Deposit
    private void handleDeposit() {
        System.out.print("Enter deposit amount: ₹");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }
}

// Main class
public class ATMInterface {
    public static void main(String[] args) {
        // Initialize account with ₹5000 balance
        BankAccount userAccount = new BankAccount(5000);
        ATM atmMachine = new ATM(userAccount);
        
        // Display ATM Menu
        atmMachine.showMenu();
    }
}
