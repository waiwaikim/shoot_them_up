package _01control;

import java.util.Scanner;
public class E4_17 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int a = in.nextInt();

        int tmp = 0 ;
        while(a>0){
            System.out.println(a%2);
            a /= 2;
        }
    }
}
