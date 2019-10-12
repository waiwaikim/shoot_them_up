package _01control;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;
import com.sun.org.apache.xpath.internal.objects.XString;

import java.util.Scanner;

public class P3_13 {

    private int convert(char r){
        switch(r){
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return -1;
    }

    private int roman_to_number(String str){
        int num =0 ;

        for(int i=str.length()-1; i>=0; i--){
            int a1 = convert(str.charAt(i));
            //System.out.println(a1);
            if(i-1>=0){
                int a2 = convert(str.charAt(i-1));
                //System.out.println(a2);
                if(a1 > a2){
                    num += (a1-a2);
                    i--;
                }
                else{
                    num += a1;
                }
            }
            else {
                num += a1;
                i--;
            }
        }

        return num;
    }


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("enter a roman number: ");
        String roman = in.next();

        P3_13 ob = new P3_13();

        int result = ob.roman_to_number(roman);
        System.out.println("Integer form of " + roman + " is " + result);

    }
}
