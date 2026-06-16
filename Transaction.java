import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private int transactionId;
    private String type;
    private double amount;
    private String date;
    private double balanceAfter;

    public Transaction(int transactionId, String type,
                       double amount, double balanceAfter) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.date = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern(
                "dd-MM-yyyy HH:mm"));
    }

    public int getTransactionId() { 
        return transactionId; 
    }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
    public double getBalanceAfter() { 
        return balanceAfter; 
    }

    public void display() {
        System.out.println(
            "+------------------------------------+");
        System.out.printf(
            "  TXN ID  : TXN%03d%n", transactionId);
        System.out.println(
            "  Type     : " + type);
        System.out.printf(
            "  Amount   : $%.2f%n", amount);
        System.out.println(
            "  Date     : " + date);
        System.out.printf(
            "  Balance  : $%.2f%n", balanceAfter);
        System.out.println(
            "+------------------------------------+");
    }

    @Override
    public String toString() {
        return String.format(
            "| TXN%03d | %-12s | $%-9.2f | %-16s |" +
            " $%-10.2f |",
            transactionId, type, amount, 
            date, balanceAfter);
    }
}