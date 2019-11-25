package _09codingInterview;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

public class _16_4 {
    public static void main(String[] args) {
        int[][] myList =  {{211,4}, {262,3}, {211,5}, {216,6}};
        int[][] resultList= sumSales(myList);
        for (int[] a : resultList){
            System.out.println(Arrays.toString(a));
        }
    }

    static int[][] sumSales(int[][] myInput){
        HashMap<Integer, Integer> mySales = new HashMap<Integer, Integer>();

        for(int i=0; i<myInput.length; i++){
            if(!mySales.containsKey(myInput[i][0])){
                mySales.put(myInput[i][0], myInput[i][1]);
            }
            else{
                mySales.put(myInput[i][0], mySales.get(myInput[i][0])+ myInput[i][1]);
            }
        }
        int[][] total = new int[mySales.size()][2];
        int j=0;
        for(int key: mySales.keySet()){
            total[j][0] = key;
            total[j][1] = mySales.get(key);
            j++;
        }
        return total;
    }
}
