package _01control;

import java.util.Scanner;
import java.lang.Math;


public class E2_4 {
    //program that prompts the user for two integers and then prints
    public static void main(String[] args) {
        Scanner in =  new Scanner(System.in);
        System.out.print("Enter first integer: ");
        int a = in.nextInt();
        System.out.print("Enter second integer: ");
        int b = in.nextInt();

        System.out.println("sum: " + (a+ b) );
        System.out.println("difference: " +(a-b));
        System.out.println("product: " + (a*b));
        System.out.println("average: " + (float)(a+b)/2);
        System.out.println("distance: " + Math.abs((a-b)));
        System.out.println("maximum: " + Math.max(a,b));
        System.out.println("minimum: " + Math.min(a,b));
    }
}
