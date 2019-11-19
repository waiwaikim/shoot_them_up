package _07streams;

import java.util.Scanner;

public class YodaSpeak {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("write a sentence: ");
        String userSentence = in.nextLine();

        System.out.println("Yoda says the following:  " + YodaIterative(userSentence));

    }

    public static String YodaIterative(String mySentence){
        String[] words = mySentence.split(" ");
        String yodaSentence = "";
        for(int i=words.length-1 ; i>-1; i--){
            yodaSentence += words[i]+" ";
        }
        return yodaSentence;
    }
}
