package _01control;

import java.util.Scanner;

public class P3_14 {


    private boolean gap_year(int year){
        boolean gap;

        if(year%4 ==0){ gap = true;
            if(year%100==0){ gap = false;
                if(year%400==0){ gap=true;
                }
            }
        }
        else{
            gap = false;
        }

        return gap;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a year: ");
        int year = in.nextInt();

        P3_14 ob = new P3_14();

        boolean val = ob.gap_year(year);

        if(val){
            System.out.println(year+ " is a leap year");
        }
        else{
            System.out.println(year + " is not a leap year");
        }


    }
}
