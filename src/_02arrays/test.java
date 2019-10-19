package _02arrays;

import _01control.P3_14;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Arrays;

public class test {

    public static int f(int i){
        int n=0;
        while(n*n <= i){
            n++;
        }
        return n-1;
    }


    public static void main(String[] args) {
       /* double values[] = {1.1 , 2.1, 3.1 , 4.1 , 5.1 };

        for (double item: values) {

            if(item == values[0]) continue;
            else System.out.println(item);
        }*/

       int rows = 4;
       int columns =5;

       int[][] values = new int[rows][columns];


/*       for (int i=0; i<rows; i++){
           for(int j=0; j<columns; j++){
               values[i][j]=0;
           }
       }
        for(int i=0; i<values.length; i++){
            System.out.println(Arrays.toString(values[i]));
        }
        System.out.println('\n');*/



/*       boolean flag = true;
        for (int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                if(flag){
                    values[i][j]=0;
                }
                else {
                    values[i][j]=1;
                }
                flag = !flag;
            }
        }*/



        for (int i=0; i<rows; i++) {
            if (i == 0 || i == (rows - 1)) {
                for (int j = 0; j < columns; j++) {
                    values[i][j]=0;
                }
            }
            else{
                values[i]=null;
            }

        }

        for(int i=0; i<values.length; i++){
            System.out.println(Arrays.toString(values[i]));
        }

    }
}
