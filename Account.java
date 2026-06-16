import java.util.ArrayList;

public class Account {
    private String accountNumber;
    private String holderName;
    private String accountType;
    private double balance;
    private ArrayList<Transaction> transactions;
    private static int transactionCounter = 1;

    public Account(String accountNumber,
                   String holderName,
                   String accountType) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.accountType = accountType;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() { 
        return accountNumber; 
    }
    public String getHolderName() { 
        return holderName; 
    }
    public String getAccountType() { 
        return accountType; 
    }
    public double getBalance() { return balance; }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println(
                "❌ Invalid amount!");
            return;
        }
        balance += amount;
        transactions.add(new Transaction(
            transactionCounter++,
            "DEPOSIT", amount, balance));
        System.out.printf(
            "✅ $%.2f deposited successfully!%n", 
            amount);
        System.out.printf(
            "   Current Balance: $%.2f%n", balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println(
                "❌ Invalid amount!");
            return;
        }
        if (amount > balance) {
            System.out.println(
                "❌ Insufficient balance!");
            System.out.printf(
                "   Available Balance: $%.2f%n", 
                balance);
            return;
        }
        balance -= amount;
        transactions.add(new Transaction(
            transactionCounter++,
            "WITHDRAWAL", amount, balance));
        System.out.printf(
            "✅ $%.2f withdrawn successfully!%n",
            amount);
        System.out.printf(
            "   Remaining Balance: $%.2f%n", balance);
    }

    public void addTransferTransaction(
            String type, double amount) {
        transactions.add(new Transaction(
            transactionCounter++,
            type, amount, balance));
    }

    public void getTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println(
                "⚠️ No transactions found!");
            return;
        }
        System.out.println(
            "\n===== TRANSACTION HISTORY =====");
        System.out.println(
            "Account: " + accountNumber + 
            " | " + holderName);
        System.out.println(
            "+---------+--------------+-----------" +
            "+-----------------+------------+");
        System.out.println(
            "| TXN ID  | Type         | Amount    " +
            "| Date             | Balance    |");
        System.out.println(
            "+---------+--------------+-----------" +
            "+-----------------+------------+");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
        System.out.println(
            "+---------+--------------+-----------" +
            "+-----------------+------------+");
        System.out.println(
            "Total Transactions: " + 
            transactions.size());
    }

    public void display() {
        System.out.println(
            "+------------------------------------+");
        System.out.println(
            "  Account No : " + accountNumber);
        System.out.println(
            "  Name       : " + holderName);
        System.out.println(
            "  Type       : " + accountType);
        System.out.printf(
            "  Balance    : $%.2f%n", balance);
        System.out.println(
            "  Total TXNs : " + transactions.size());
        System.out.println(
            "+------------------------------------+");
    }

    @Override
    public String toString() {
        return String.format(
            "| %-7s | %-20s | %-8s | $%-12.2f |",
            accountNumber, holderName,
            accountType, balance);
    }
}