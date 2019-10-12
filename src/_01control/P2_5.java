package _01control;

import java.util.Scanner;
public class P2_5 {

    // extract the dollars and cents from a price given as a floating-point value
    // ex) a price of 2.95 yields values 2 and 95 for the dolars and cents
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in );
        System.out.print("Enter a price:  ");

        double  price = in.nextDouble();
        int dollars = (int)price;
        int cents = (int)(100*(price-dollars) + 0.5);

        System.out.println(dollars);
        System.out.println(cents);
    }
}
