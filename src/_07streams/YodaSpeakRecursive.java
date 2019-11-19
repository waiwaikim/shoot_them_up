package _07streams;

import java.util.Arrays;
import java.util.Scanner;

public class YodaSpeakRecursive {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("write a sentence: ");
        String userSentence = in.nextLine();

        String yodaSays = YodaRecursive(userSentence );
        System.out.println("Yoda says the following:  " + yodaSays);

    }
    public static String YodaRecursive(String mySentence){
        String[] words = mySentence.split(" ");
        String empty ="" ;
        return YodaRecursive(words,  empty);
    }

    public static String YodaRecursive(String[] mySentence, String yodaSentence){

        if(mySentence.length == 0 ){
            //System.out.println("base case : " +mySentence);
            //base case
            return yodaSentence;
        }

        else{
            yodaSentence += mySentence[mySentence.length -1] + " ";
            String[] newStringArray = new String[mySentence.length-1];
            for(int i = 0 ; i< newStringArray.length; i++){
                newStringArray[i] = mySentence[i];
            }
            return YodaRecursive(newStringArray, yodaSentence);
        }
    }
}
