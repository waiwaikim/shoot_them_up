package _07streams;

import com.sun.javafx.image.IntPixelGetter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class E13_20 {
    //Given an integer price, list all possible ways of paying for it with $100, $20, $5, and $1 bills, using recursion. Don’t list duplicates

    public static void main(String[] args) {

        System.out.println("dkfjldafnlkdanfl");
        ArrayList<Integer> bills = new ArrayList<Integer>();
        bills.add(1);
        bills.add(5);
        bills.add(20);
        bills.add(100);
        int targetPrice = 1230;

        ArrayList<ArrayList<Integer>> answer = find_changes(targetPrice, bills);
        for(ArrayList<Integer> option :answer ){
            System.out.println(option.toString());
        }

        //show answer
    }

    static ArrayList<ArrayList<Integer>> find_changes(int price, ArrayList<Integer> bills ){

        if(price ==  0) {

            ArrayList<ArrayList<Integer>> return_list = new ArrayList<ArrayList<Integer>>();
            return_list.add(new ArrayList<Integer>());
            return return_list;
        }
        ArrayList<ArrayList<Integer>> myAnswer = new ArrayList<ArrayList<Integer>>();

        for(int bill : bills){
            if(price-bill>= 0){
                ArrayList<ArrayList<Integer>> combos = find_changes(price - bill, bills);
                for (ArrayList<Integer> combo : combos){
                    combo.add(bill);
                    Collections.sort(combo);
                    if(!myAnswer.contains(combo)) myAnswer.add(combo);
                }
            }
        }
        return myAnswer;
    }

    /*
    static void change_recursive(ArrayList<Integer> bills, int price, ArrayList<Integer> partial){
        int s = 0;
        for(int x : partial){
            s += x ;
        }

        if(s == price) System.out.println("a solution: " + partial.toString());

        if(s >= price) return;

        for(int i=0 ; i<bills.size() ; i++){

            int n = bills.get(i);
            ArrayList<Integer> partial_rec = new ArrayList<Integer>(partial);
            partial_rec.add(n);
            change_recursive(bills, price, partial_rec);
        }
    }
    *
     */

}
