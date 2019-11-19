package _07streams;


import com.sun.xml.internal.bind.api.impl.NameConverter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class P13_3 {

    static ArrayList<String> dictionary = new ArrayList<String>();

    public static void main(String[] args) {
        int myNum = 5282;
        StringBuilder dictionaryBuilder = new StringBuilder();

        String filePath = "C:/Users/kjd13/java/projava/src/_07streams/words.txt";
        try(Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)){
            String[] result = stream.toArray(String[]::new );
            Collections.addAll(dictionary, result);

        }
        catch (IOException e){
            e.printStackTrace();
        }
        ArrayList<String> testAnswer= printMnemonics(myNum);

        System.out.println(testAnswer.toString());

    }

    public static ArrayList<String> printMnemonics(int phoneNumber){
        //helper method

        ArrayList<Integer> myNumArray = convert(phoneNumber);
        //sSystem.out.println(myNumArray.toString());

        ArrayList<String> myAnswers = printMnemonics(myNumArray);

        for(int i=0; i<myAnswers.size(); i++){
            if(dictionary.contains(myAnswers.get(i))){

            }
            else{
                myAnswers.remove(i);
            }
        }

        return myAnswers;

    }

    public static ArrayList<String> printMnemonics(ArrayList<Integer> number){
        if(number.size() == 0){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        int firstNum = number.get(0);
        number.remove(0);

        ArrayList<String> prev = printMnemonics(number);
        ArrayList<String> newArray = new ArrayList<>();

        String[] currentKeys = intToString(firstNum);

        for(String val :prev){
            for(String currentChar : currentKeys){
                newArray.add(currentChar+val);
            }
        }
        return newArray;

    }

    public static ArrayList<Integer> convert(int phoneNum){
        //conver integer phone number to ArrayList of integer
        //the recursive method printMnemonics takes an ArrayList of integer as an input for a phone number

        String temp = Integer.toString(phoneNum);
        //System.out.println(temp);
        ArrayList<Integer> returnArray = new ArrayList<Integer>();

        for(int i=0 ; i < temp.length(); i++){
            returnArray.add(temp.charAt(i) - '0');
        }
        return returnArray;
    }

    public static String[] intToString(int n){
        String[] a1 = {"1"};
        String[] a2 = {"A","B","C"};
        String[] a3 = {"D","E","F"};
        String[] a4 = {"G","H","I"};
        String[] a5 = {"J","K","L"};
        String[] a6 = {"M","N","O"};
        String[] a7 = {"P","Q","R","S"};
        String[] a8 = {"T","U","V"};
        String[] a9 = {"W","X","Y","Z"};
        String[] defaultArray = {""};
        switch(n){
            case 2: return a2;
            case 3: return a3;
            case 4: return a4;
            case 5: return a5;
            case 6: return a6;
            case 7: return a7;
            case 8: return a8;
            case 9: return a9;

        }
        return defaultArray;
    }
}
