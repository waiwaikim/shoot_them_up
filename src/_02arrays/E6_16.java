package _02arrays;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class E6_16 {

    private static int[] normalize(int max, ArrayList<Integer> list){
        int[] returnList = new int[list.size()];

        for(int i=0; i<list.size(); i++){
            returnList[i] = list.get(i) *20/max;
        }
        return returnList;
    }

    private static int findMax(ArrayList<Integer> list){
        int idx;
        int max = list.get(0);

        for(int i=0; i <list.size(); i++){
            if(list.get(i)> max) {
                max = list.get(i);
                idx = i;
            }
        }
        return max;
    }
    private static void printHist(int[] list){
        char[][] matrix = new char[20][list.length];

        for(int i=19; i >= 0; i--){
            for(int j=0; j<list.length; j++){
                if(list[j] >= 0){
                    matrix[i][j] = '*';
                    list[j]--;
                }
                else {
                    matrix[i][j] = ' ';
                }
            }
        }

        for(int i=0; i<19; i++){
            for(int j=0; j<list.length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.print('\n');
        }

    }


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> intValues = new ArrayList<Integer>();

        while(true){
            try {
                System.out.print("Type integer value or \"quit\" to exit:");
                //any value that is not an integer (including "quit") will throw an exception, which breaks out of the loop
                intValues.add(scan.nextInt());
            } catch ( Exception e) {
                break;
            }
        }
        if (intValues.size() == 0){
            System.out.println("Not enough data");
            return;
        }


        int max = findMax(intValues);
        int[] normalList = normalize(max, intValues);
        printHist(normalList);

    }
}
