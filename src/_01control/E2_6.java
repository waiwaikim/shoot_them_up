package _01control;

import java.util.Scanner;
public class E2_6 {

    //program that prompts the user for a measurement in meters and
    //converts it to miles, feet, and inches
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter a measurement in meters:  ");
        double  meter = in.nextDouble();

        double inches = 39.37 * meter ;
        double feet = inches / 12;
        double miles =  feet / 5280 ;
        System.out.println("miles: " + miles + " feet: "+ feet + " inches: "+ inches);
    }
}
