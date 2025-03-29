import java.util.Scanner;

class BankAccount {
    private double balance;
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(" Successfully deposited ₹" + amount);
        } else {
            System.out.println(" :( Invalid deposit amount!");
        }
    }

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

    public double getBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

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

    private void handleWithdrawal() {
        System.out.print("Enter withdrawal amount: ₹");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    private void handleDeposit() {
        System.out.print("Enter deposit amount: ₹");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        // Initialize account with ₹5000 balance
        BankAccount userAccount = new BankAccount(5000);
        ATM atmMachine = new ATM(userAccount);

        atmMachine.showMenu();
    }
}
