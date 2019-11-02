package _04interfaces.E9_8;

public class BasicAccount extends BankAccount {

    private double minBal ;
    public BasicAccount(){
        minBal = 0;
    }
    @Override
    public void withdraw(double amount) {

        double balance = this.getBalance();
        if(balance < amount){
            System.out.println("Amount exceeds account's balance");
        }
        else{
            super.withdraw(amount);
        }
    }
}
