import java.util.HashMap;

public class Bank {
    private HashMap<String, Account> accounts
        = new HashMap<>();
    private int nextAccountNumber = 1;

    public String createAccount(String holderName,
                                 String accountType,
                                 double initialDeposit){
        String accountNumber = String.format(
            "ACC%03d", nextAccountNumber++);
        Account account = new Account(
            accountNumber, holderName, accountType);
        accounts.put(accountNumber, account);

        System.out.println(
            "\n✅ Account created successfully!");
        System.out.println(
            "   Account Number: " + accountNumber);

        if (initialDeposit > 0) {
            account.deposit(initialDeposit);
        }
        return accountNumber;
    }

    public void deposit(String accountNumber,
                        double amount) {
        Account account = findAccount(accountNumber);
        if (account == null) return;
        System.out.println(
            "\n--- DEPOSIT ---");
        account.deposit(amount);
    }

    public void withdraw(String accountNumber,
                         double amount) {
        Account account = findAccount(accountNumber);
        if (account == null) return;
        System.out.println(
            "\n--- WITHDRAWAL ---");
        account.withdraw(amount);
    }

    public void transfer(String fromNumber,
                         String toNumber,
                         double amount) {
        Account fromAccount = findAccount(fromNumber);
        Account toAccount = findAccount(toNumber);

        if (fromAccount == null || 
            toAccount == null) return;

        if (amount <= 0) {
            System.out.println(
                "❌ Invalid transfer amount!");
            return;
        }

        if (fromAccount.getBalance() < amount) {
            System.out.println(
                "❌ Insufficient balance for transfer!");
            System.out.printf(
                "   Available: $%.2f%n",
                fromAccount.getBalance());
            return;
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        fromAccount.addTransferTransaction(
            "TRANSFER OUT", amount);
        toAccount.addTransferTransaction(
            "TRANSFER IN", amount);

        System.out.println(
            "\n✅ Transfer successful!");
        System.out.printf(
            "   From : %s (%s)%n",
            fromNumber,
            fromAccount.getHolderName());
        System.out.printf(
            "   To   : %s (%s)%n",
            toNumber,
            toAccount.getHolderName());
        System.out.printf(
            "   Amount: $%.2f%n", amount);
    }

    public void checkBalance(String accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) return;
        System.out.println(
            "\n--- BALANCE ENQUIRY ---");
        System.out.println(
            "Account  : " + accountNumber);
        System.out.println(
            "Name     : " + account.getHolderName());
        System.out.printf(
            "Balance  : $%.2f%n",
            account.getBalance());
    }

    public void viewTransactionHistory(
            String accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) return;
        account.getTransactionHistory();
    }

    public void viewAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println(
                "\n⚠️ No accounts found!");
            return;
        }
        System.out.println(
            "\n========= ALL ACCOUNTS =========");
        System.out.println(
            "+---------+----------------------+" +
            "----------+---------------+");
        System.out.println(
            "| Acc No  | Name                 " +
            "| Type     | Balance       |");
        System.out.println(
            "+---------+----------------------+" +
            "----------+---------------+");
        for (Account acc : accounts.values()) {
            System.out.println(acc);
        }
        System.out.println(
            "+---------+----------------------+" +
            "----------+---------------+");
        System.out.println(
            "Total Accounts: " + accounts.size());
    }

    public void searchAccount(String accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) return;
        System.out.println(
            "\n✅ Account Found:");
        account.display();
    }

    private Account findAccount(
            String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            System.out.println(
                "❌ Account " + accountNumber + 
                " not found!");
        }
        return account;
    }
}