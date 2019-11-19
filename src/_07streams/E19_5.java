package _07streams;


import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E19_5 {

    public static void main(String[] args) {
        Stream<String> words = Arrays.asList("A", "B", "C", "D").stream();

        System.out.println(toString(words, 4));

    }

    public static <T> String toString(Stream<T> stream, int n){
        String result = stream.map( t -> t.toString()).limit(n).collect(Collectors.joining(", "));
        //String myResult = stream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        return result;
    }

    public static String toStringTest(Stream<Rectangle> stream, int n){
        String result = stream
                .map(rectangle -> rectangle.toString())
                .limit(n)
                .collect(Collectors.joining(", "));
        return result;
    }
}
