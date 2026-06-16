import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Bank bank = new Bank();

    public static void main(String[] args) {

        // Sample accounts
        bank.createAccount(
            "John Smith", "Savings", 5000);
        bank.createAccount(
            "Jane Doe", "Current", 10000);
        bank.createAccount(
            "Bob Johnson", "Savings", 2500);

        System.out.println(
            "╔══════════════════════════════════╗");
        System.out.println(
            "║      BANK MANAGEMENT SYSTEM      ║");
        System.out.println(
            "║       Built with Java & OOP      ║");
        System.out.println(
            "╚══════════════════════════════════╝");

        boolean running = true;
        while (running) {
            showMenu();
            int choice = getIntInput(
                "Enter your choice: ");
            switch (choice) {
                case 1: createAccountFlow(); break;
                case 2: depositFlow();       break;
                case 3: withdrawFlow();      break;
                case 4: transferFlow();      break;
                case 5: balanceFlow();       break;
                case 6: historyFlow();       break;
                case 7: 
                    bank.viewAllAccounts();  break;
                case 8: searchFlow();        break;
                case 9:
                    System.out.println(
                        "\n👋 Thank you! " +
                        "Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println(
                        "❌ Invalid! Try again.");
            }
        }
        sc.close();
    }

    static void showMenu() {
        System.out.println(
            "\n╔══════════════════════════════════╗");
        System.out.println(
            "║           MAIN MENU              ║");
        System.out.println(
            "╠══════════════════════════════════╣");
        System.out.println(
            "║  1.  Create New Account          ║");
        System.out.println(
            "║  2.  Deposit Money               ║");
        System.out.println(
            "║  3.  Withdraw Money              ║");
        System.out.println(
            "║  4.  Transfer Money              ║");
        System.out.println(
            "║  5.  Check Balance               ║");
        System.out.println(
            "║  6.  View Transaction History    ║");
        System.out.println(
            "║  7.  View All Accounts           ║");
        System.out.println(
            "║  8.  Search Account              ║");
        System.out.println(
            "║  9.  Exit                        ║");
        System.out.println(
            "╚══════════════════════════════════╝");
    }

    static void createAccountFlow() {
        System.out.println(
            "\n--- CREATE NEW ACCOUNT ---");
        System.out.print("Holder Name    : ");
        String name = sc.nextLine();
        System.out.println(
            "Account Type:");
        System.out.println("  1. Savings");
        System.out.println("  2. Current");
        int typeChoice = getIntInput("Choice: ");
        String type = typeChoice == 1 ? 
                      "Savings" : "Current";
        double deposit = getDoubleInput(
            "Initial Deposit: $");
        bank.createAccount(name, type, deposit);
    }

    static void depositFlow() {
        System.out.println("\n--- DEPOSIT ---");
        bank.viewAllAccounts();
        System.out.print(
            "Enter Account Number: ");
        String accNo = sc.nextLine().toUpperCase();
        double amount = getDoubleInput(
            "Enter Amount: $");
        bank.deposit(accNo, amount);
    }

    static void withdrawFlow() {
        System.out.println("\n--- WITHDRAWAL ---");
        bank.viewAllAccounts();
        System.out.print(
            "Enter Account Number: ");
        String accNo = sc.nextLine().toUpperCase();
        double amount = getDoubleInput(
            "Enter Amount: $");
        bank.withdraw(accNo, amount);
    }

    static void transferFlow() {
        System.out.println("\n--- TRANSFER ---");
        bank.viewAllAccounts();
        System.out.print("From Account Number: ");
        String from = sc.nextLine().toUpperCase();
        System.out.print("To Account Number  : ");
        String to = sc.nextLine().toUpperCase();
        double amount = getDoubleInput(
            "Transfer Amount: $");
        bank.transfer(from, to, amount);
    }

    static void balanceFlow() {
        System.out.println(
            "\n--- CHECK BALANCE ---");
        System.out.print(
            "Enter Account Number: ");
        String accNo = sc.nextLine().toUpperCase();
        bank.checkBalance(accNo);
    }

    static void historyFlow() {
        System.out.println(
            "\n--- TRANSACTION HISTORY ---");
        bank.viewAllAccounts();
        System.out.print(
            "Enter Account Number: ");
        String accNo = sc.nextLine().toUpperCase();
        bank.viewTransactionHistory(accNo);
    }

    static void searchFlow() {
        System.out.println(
            "\n--- SEARCH ACCOUNT ---");
        System.out.print(
            "Enter Account Number: ");
        String accNo = sc.nextLine().toUpperCase();
        bank.searchAccount(accNo);
    }

    static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print(
                "Invalid! Enter a number: ");
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine();
        return val;
    }

    static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextDouble()) {
            System.out.print(
                "Invalid! Enter a number: ");
            sc.next();
        }
        double val = sc.nextDouble();
        sc.nextLine();
        return val;
    }
}