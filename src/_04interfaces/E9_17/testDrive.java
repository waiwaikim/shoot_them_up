package _04interfaces.E9_17;


import java.util.ArrayList;

public class testDrive {
    public static void main(String[] args) {

        SodaCan[] myList = new SodaCan[3];
        SodaCan s1 =
        myList[0] = new SodaCan(2, 5);
        //System.out.println(myList[0].getMeasure());
        myList[1] = new SodaCan(3, 5);
        myList[2] = new SodaCan(5, 2);

        System.out.println(SodaCan.average(myList));
    }
}
