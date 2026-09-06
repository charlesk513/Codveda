import java.lang.NumberFormatException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BankManager {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BankAccount account = new BankAccount();

        while (true) {
            System.out.print("deposit/withdraw/check?: ");
            String operation = reader.readLine().toLowerCase();

            if (operation.equals("deposit")) {
                account.deposit(reader);
            } else if (operation.equals("withdraw")) {
                account.withdraw(reader);
            } else if (operation.equals("check")) {
                double balance = account.checkBalance();
                System.out.println("You're account balance is UGX " + balance);
                System.out.println();
            } else {
                break;
            }
        }

    }

}

class BankAccount {
    double amount, balance;

    public BankAccount() {

    }

    public void deposit(BufferedReader read) throws IOException {

        System.out.print("Enter the amount: ");
        try {
            amount = Double.parseDouble(read.readLine());
            balance = balance + amount;
            System.out.println("Successfully deposited UGX " + amount + " available balance is UGX " + balance);
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("Amount must be a double");
        }

    }

    public double checkBalance() {
        return this.balance;
    }

    public void withdraw(BufferedReader read) throws IOException {

        System.out.print("Enter the withdrawal amount: ");
        try {
            amount = Double.parseDouble(read.readLine());
            if (amount > balance) {
                System.out.println("Unable to complete the transaction, insufficient funds!");
                System.out.println();
                return;
            }
            balance = balance - amount;
            System.out.println("Successfully withdrawn UGX " + amount + " available balance is UGX " + balance);
            System.out.println();

        } catch (NumberFormatException e) {
            System.out.println("Amount must be a double");
        }

    }

}
