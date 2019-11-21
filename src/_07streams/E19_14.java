package _07streams;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class E19_14 {

    public static void main(String[] args) throws IOException {

        //Read all words from a file into an ArrayList<String>, then turn it into a parallel stream. Use the dictionary
        //file words.txt provided with the book’s companion code. Use filters and the findAny method to find any
        //palindrome that has at least five letters, then print the word. What happens when you run the program multiple times?


        File file = new File("C:/Users/kjd13/java/projava/src/_07streams/warandpeace.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        ArrayList<String> myWords = new ArrayList<String>();

        String line = null;
        while( (line = br.readLine())!= null ) {
            // \\s+ means any number of whitespaces between tokens
            String[] tokens = line.split("\\s+");
            for(String w : tokens){
                myWords.add(w);
            }
        }
        myWords.parallelStream().filter(s-> isPalindrome(s) && isLongEnough(s)).forEach(s -> System.out.println(s));


    }

    public static boolean isPalindrome(String input) {
        String temp =input.replaceAll("\\s+", "").toLowerCase();
        return IntStream.range(0, input.length() / 2)
                .allMatch(i -> temp.charAt(i) == temp.charAt(temp.length() - i - 1));
    }
    public static boolean isLongEnough(String input){
        if(input.length() >=5 ){
            return true;
        }
        else{
            return false;
        }
    }
}
