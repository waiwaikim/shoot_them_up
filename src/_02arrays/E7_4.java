package _02arrays;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class E7_4 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //C:\dev\java\pros\proArrays\_instructions.txt as test input
        System.out.print("Add line numbers. What is the absolute path to your file?:");
        String strPath = scan.next();
        File fileInput = new File(strPath);
        try {
            scan = new Scanner(fileInput);
        } catch (FileNotFoundException e) {
            System.out.println("There's been an error: " + e.getMessage());
            return;
        }

        int nC = 1;
        while(scan.hasNextLine()){
            System.out.print(toLineNumberString(nC++));
            System.out.println(scan.nextLine());
        }
    }
    private static String toLineNumberString(int nLine){
        if (nLine < 1){
            return "00:  ";
        }
        else {
            return "/* "+nLine + " */ ";
        }
    }

}
