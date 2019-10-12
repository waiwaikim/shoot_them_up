package _01control;

import java.util.Scanner;
import java.lang.Math;
public class P3_7 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("enter an income amount: ");
        int income = in.nextInt();
        double tax = 0;

        while(income != 0 ){
            if(income > 500000){
                tax += (income-500000)*0.06;
                income = 500000;
            }
            else if(income>250000 && income <= 500000){
                tax += (income-250000)*0.05;
                income = 250000;
            }
            else if(income>100000 && income<=250000){
                tax += (income-100000)* 0.04;
                income = 100000;
            }
            else if(income>75000 && income<=100000){
                tax += (income-75000)*0.03;
                income = 75000;
            }
            else if(income>50000 && income <= 75000){
                tax += (income-50000)*0.02;
                income = 50000;
            }
            else if(income<=50000){
                tax += income*0.01;
                income =0 ;
            }
        }

        System.out.println(tax);

    }
}
