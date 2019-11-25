package _07streams;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class E19_7 {

    /* Write a lambda expression for a function that turns a string into a string made of the first letter, three periods,
    and the last letter, such as "W...d". (Assume the string has at least two letters.) Then write a program that reads
    words into a stream, applies the lambda expression to each element, and prints the result. Filter out any words
    with fewer than two letters.*/

    public static void main(String[] args) {


        String testString = "worrddds";
        String myAnswer ="";

        for(int i=0; i < testString.length(); i++){
            if(i==0){
                myAnswer += testString.charAt(i);
            }
            else if(i==(testString.length()-1)){
                myAnswer += "..." + testString.charAt(i);
            }
        }
        System.out.println("answer: " + myAnswer);

        //String test = testString.codePoints().map(c ->)
        StringBuilder sb = new StringBuilder();
        testString.codePoints().filter(x-> x == testString.charAt(0) || x == testString.charAt(testString.length()-1)).forEachOrdered(sb::appendCodePoint);

        System.out.println(sb.toString());

        //String --> Stream: to print exactly the same

        //testString.codePoints().mapToObj(i -> new Character((char) i)).forEach(System.out::print);

        //testString.codePoints().mapToObj(i -> new Character((char) i)).forEach(System.out::print);
        //Stream<String> yes = IntStream.range(0, n).mapToObj(i -> testString.substring(0,i));
        //System.out.println( IntStream.range(0,n).map(x-> "Hello".charAt(x)).sum());
        //System.out.println(yes);
        //System.out.println("Mapping ints to objects: " + Arrays.toString(yes.toArray(String[]::new)));
    }


}
