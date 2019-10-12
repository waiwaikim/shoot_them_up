package _01control;

import java.lang.Math;
import java.util.Scanner;
public class E4_1 {

    public static void main(String[] args) {

        int even_sum =0;

        for(int i = 2; i<=100; i++){
            if(i%2==0){
                even_sum += i;
            }
        }
        System.out.println("The sum of all even numbers between 2 and 100 (inclusive): "+ even_sum);

        int square_sum=0;
        for(int i=1; i<=100; i++){
            if(Math.sqrt(i) == (int)Math.sqrt(i)){
                square_sum+=i;
            }
        }
        System.out.println("The sum of all square numbers between 1 and 100 (inclusive): "+ square_sum);

        for(int i=0; i<=20; i++){
            double result = Math.pow(2, i);
            System.out.println("2 to the power of " + i + " is " + (int)result);
        }

        System.out.println("E4.1 : The sum of all odd numbers between a and b (inclusive), where a and b are inputs. ");
        Scanner in = new Scanner(System.in);
        System.out.print("Enter first number: ");
        int a = in.nextInt();
        System.out.print("Enter second number: ");
        int b = in.nextInt();
        int odd_sum = 0 ;
        for(int i=a; i<=b; i++){
            if(i%2 == 1){
                odd_sum += i;
            }
        }
        System.out.println("The sum of all odd numbers between "+ a +" and "+b +" is " + odd_sum);

        System.out.println("The sum of all odd digits of an input.");
        Scanner in2 = new Scanner(System.in);
        System.out.print("Enter an integer number: ");
        int c = in2.nextInt();
        int digit_sum = 0;
        int tmp = 0 ;
        while(c>0){
            tmp = c%10;
            if(tmp%2 ==1){
                digit_sum += tmp;
            }
            c /= 10;
        }
        System.out.println("The sum of all odd digits of " +c+ " is "+ digit_sum);
    }
}
