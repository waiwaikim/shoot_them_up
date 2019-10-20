package _02arrays;
import java.util.Random;

public class P6_1 {

    private static int[] dieRoll(){
        int[] die = new int[20];
        Random rand = new Random();

        for(int i=0; i<20; i++){
            int rand_int = rand.nextInt(6);
            die[i]=(rand_int+1);
            //random number generated from 0-5. thus 1 is added to change the range to 1-6
        }

        return die;
    }

    private static void printRun(int[] die){
        boolean inRun = false;

        for(int i=0 ; i<20; i++){
            if(inRun){
                if(i>0 && die[i] != die[i-1]){
                    System.out.print(")");
                    inRun =false;
                }
            }
            else {
                if(i<19 && die[i]==die[i+1]){
                    System.out.print("(");
                    inRun=true;
                }
            }
            System.out.print(" "+die[i]+" ");
        }
        if(inRun){
            System.out.print(")");
        }
        System.out.println('\n');
    }
    private static void printDie(int[] die){
        for (int a : die ){
            System.out.print(" "+a+" ");
        }
        System.out.print('\n');
    }
    private static void test(){
        int[] test1 = {1, 1, 1, 1, 1,1, 1, 1, 1, 1,1, 1, 1, 1, 1,1, 1, 1, 1, 1};
        printDie(test1);
        printRun(test1);

        int[] test2 = {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0};
        printDie(test2);
        printRun(test2);

        int[] test3 = {1, 2, 5, 5, 3, 1, 2, 4, 3, 2, 2, 2, 2, 3, 6, 5, 5, 6, 3, 1};
        printDie(test3);
        printRun(test3);

        int[] test4 = {1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,  0, 1, 0, 1};
        printDie(test4);
        printRun(test4);
    }
    public static void main(String[] args) {
        //test();
        int[] die;
        die = dieRoll();
        printRun(die);
    }
}
