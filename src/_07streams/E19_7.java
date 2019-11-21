package _07streams;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class E19_7 {

    /* Write a lambda expression for a function that turns a string into a string made of the first letter, three periods,
    and the last letter, such as "W...d". (Assume the string has at least two letters.) Then write a program that reads
    words into a stream, applies the lambda expression to each element, and prints the result. Filter out any words
    with fewer than two letters.*/

    public static void main(String[] args) {


        String testString = "words";
        int n = testString.length();
        //testString.codePoints().mapToObj(i -> new StringBuilder().appendCodePoint(i)).forEach(System.out::print);
		testString.codePoints().mapToObj(i -> new Character((char) i)).forEach(System.out::print);

        //Stream<String> yes = IntStream.range(0, n).mapToObj(i -> testString.substring(0,i));
        //System.out.println( IntStream.range(0,n).map(x-> "Hello".charAt(x)).sum());
        //System.out.println(yes);

        //System.out.println("Mapping ints to objects: " + Arrays.toString(yes.toArray(String[]::new)));

        //codePoints

    }


}
