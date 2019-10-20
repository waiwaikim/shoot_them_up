package _02arrays;
import java.util.Random;
import java.util.Arrays;

public class E6_12 {
    public static void main(String[] args) {

        int[] rand_list = new int[20];
        Random rand = new Random();

        for(int i=0; i<20; i++){
            int rand_int = rand.nextInt(100);
            rand_list[i]=(rand_int);
        }

        System.out.println(Arrays.toString(rand_list));

        Arrays.sort(rand_list);

        System.out.println(Arrays.toString(rand_list));
    }
}
