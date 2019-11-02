package _04interfaces.E9_8;

public class BankAccount {

    private double balance;

    public BankAccount()
    {
        balance = 0;
    }

    public void deposit(double amount)
    {
        balance = balance + amount;
    }

    public void withdraw(double amount)
    {
        balance = balance - amount;
    }

    public void monthEnd()
    {
    }


    public double getBalance()
    {
        return balance;
    }
}
