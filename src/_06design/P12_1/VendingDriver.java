package _06design.P12_1;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VendingDriver {
    public static void main(String[] args) {

        Coin nickel = new Coin(0.05, "Nickel");
        Coin dime = new Coin(0.10, "Dime");
        Coin quarter = new Coin(0.25, "Quarter");
        Coin dollar = new Coin(1.00, "Dollar");

        VendingMachine myMachine = new VendingMachine();
        //installing a new vending machine

        Product newItem = new Product("Dragon", 1000.0);
        myMachine.addProduct(newItem,1);
        //forgot to add an important item. adding it now!

        System.out.println(myMachine.display());
        //make sure we have the complete product
        Scanner in = new Scanner(System.in);

        do{
            if(areYouOperator()){
                //You're an operator
                int operatorAnswer = 0 ;
                System.out.println("what task would you like to perform? 1) stock or add an item or 2) collect money  (1 / 2) ");
                try {
                    operatorAnswer = in.nextInt();
                }
                catch(InputMismatchException e){
                    System.out.println("You must enter a valid option 1 or 2");
                }

                if(operatorAnswer == 1){
                    //Operator stocking the vending machine
                    String itemName ="";
                    double itemPrice =0.0;
                    Integer itemQuantity = 0 ;

                    System.out.println("Enter the name of an item you'd like to add?");
                    itemName = in.next();

                    System.out.println("What's the price of an item?");
                    itemPrice = in.nextInt();

                    System.out.println("How many would you like to add?");
                    itemQuantity = in.nextInt();

                    Product newProduct = new Product(itemName, itemPrice);
                    myMachine.addProduct(newProduct, itemQuantity);
                    System.out.println("It's been stocked. Please see below");
                    System.out.println(myMachine.display());
                }
                else if (operatorAnswer == 2){
                    System.out.println(myMachine.getTotalMoney() + " has been collected. Coin box has been emptied");
                    myMachine.emptyCoinBox();
                }
            }
            else{
                //consumer

                //First - Inserting Coins
                int[] coinAmounts = askCoinAmount();
                myMachine.insertCoins(nickel, coinAmounts[0]);
                myMachine.insertCoins(dime, coinAmounts[1]);
                myMachine.insertCoins(quarter, coinAmounts[2]);
                myMachine.insertCoins(dollar, coinAmounts[3]);

                //Second - Pick a product
                System.out.println(myMachine.display() + '\n');
                System.out.println("You have so far inserted $"+ myMachine.getPayment());
                System.out.println("Please type a product name:" );
                String productName= in.next();


                if(myMachine.dispenseItem(productName)){
                    System.out.println(productName + " has been dispensed");
                    System.out.println(myMachine.display());
                }
                else{
                    System.out.println(myMachine.returnChange() + " has been returned");

                }



            }
        }
        while(!askUserToQuit());



    }

    public static int[] askCoinAmount(){

        Scanner in = new Scanner(System.in);
        int nickelQuantity = 0;
        int dimeQuantity = 0 ;
        int quarterQuantity = 0;
        int dollarQuantity = 0;

        System.out.println("please enter the number of nickel you'd like to insert. ");
        System.out.println("You can enter 0 if you don't have nickel.");
        try{
            nickelQuantity = in.nextInt();
        }
        catch(Exception e){
            System.out.println("You must enter a valid quantity");
        }
        System.out.println("please enter the number of dime you'd like to insert. ");
        System.out.println("You can enter 0 if you don't have dime.");
        try{
            dimeQuantity = in.nextInt();
        }
        catch(Exception e){
            System.out.println("You must enter a valid quantity");
        }
        System.out.println("please enter the number of quarter you'd like to insert. ");
        System.out.println("You can enter 0 if you don't have quarter.");
        try{
            quarterQuantity = in.nextInt();
        }
        catch(Exception e){
            System.out.println("You must enter a valid quantity");
        }
        System.out.println("please enter the number of dollar you'd like to insert. ");
        System.out.println("You can enter 0 if you don't have dollar.");
        try{
            dollarQuantity = in.nextInt();
        }
        catch(Exception e){
            System.out.println("You must enter a valid quantity");
        }

        return new int[] {nickelQuantity, dimeQuantity, quarterQuantity, dollarQuantity};

    }
    public static boolean areYouOperator(){
        Scanner in = new Scanner(System.in);
        String userAnswer;
        boolean keepAsk = true;
        String[] acceptableAnswer ={"YES","NO","Y","N", "Yes", "No", "yes","no","y","n"};
        String[] yes = {"YES","Y", "Yes", "yes","y"};
        do{
            System.out.println("Are you an operator (Yes/No) ");
            userAnswer = in.nextLine();
            keepAsk = !(Arrays.asList(acceptableAnswer).contains(userAnswer));

            if(keepAsk){
                System.out.print("Please enter a valid response. ");
            }
        }
        while(keepAsk);

        if(Arrays.asList(yes).contains(userAnswer)){
            //user wants to continue
            return true;
        }
        else return false;
    }
    public static boolean askUserToQuit(){
        // return true when the use wants to continue
        // return false when the user wants to quit
        Scanner in = new Scanner(System.in);
        String userAnswer;
        boolean keepAsk = true;
        String[] acceptableAnswer ={"YES","NO","Y","N", "Yes", "No", "yes","no","y","n"};
        String[] yes = {"YES","Y", "Yes", "yes","y"};
        do{
            System.out.println("Are you done with your task? (Yes/No) ");
            userAnswer = in.nextLine();
            keepAsk = !(Arrays.asList(acceptableAnswer).contains(userAnswer));

            if(keepAsk){
                System.out.print("Please enter a valid response. ");
            }
        }
        while(keepAsk);

        if(Arrays.asList(yes).contains(userAnswer)){
            //user wants to continue
            return true;
        }
        else return false;
    }

}
