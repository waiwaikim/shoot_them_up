package _02arrays;
import java.util.Random;


public class E6_1 {

    private static void print(int[] list){
        for(int a : list){
            System.out.print(a+ " ");
        }
        System.out.print('\n');
    }
    private static void print_at_even(int[] list){
        for(int i=0; i<10; i+=2){
            System.out.print(list[i]+" ");
        }
        System.out.print('\n');
    }

    private static void print_even(int[] list){
        for(int i=0; i<10; i++){
            if(list[i]%2 ==0){
                System.out.print(list[i]+ " ");
            }
        }
        System.out.print('\n');
    }
    private static void print_reserve(int[] list){
        for(int i=9; i>=0; i--){
            System.out.print(list[i]+ " ");
        }
        System.out.print('\n');
    }
    private static void print_first_last(int[] list){
        System.out.print(list[0]+" ");
        System.out.print(list[9]);
        System.out.print('\n');
    }
    public static void main(String[] args) {

        int[] rand_list = new int[10];
        Random rand = new Random();

        for(int i=0; i<10; i++){
            int rand_int = rand.nextInt(100);
            rand_list[i]=(rand_int+1);
        }
        //print(rand_list);
        print_at_even(rand_list);
        print_even(rand_list);
        print_reserve(rand_list);
        print_first_last(rand_list);

    }
}
