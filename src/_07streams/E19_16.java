package _07streams;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E19_16 {
    //Read all words in a file and group them by the first letter (in lowercase).
    // Print the average word length for each initial letter. Use collect and Collectors.groupingBy.

    public static void main(String[] args) throws IOException {

        File file = new File("C:/Users/kjd13/java/projava/src/_07streams/example.txt");
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
        //System.out.println(myWords.toString());


        /*Stream<String> myStream = Stream.of("aaaaa", "bbb", "bb", "ababab","aaa", "aaaaaaa","babababab", "bbbb", "ababab");
        Map<String, List<String>> groups =myStream.collect(
                                                            Collectors.groupingBy(w-> w.substring(0,1)));
        System.out.println(groups.toString() + "\n");*/

        //Stream<String> myStream1 = Stream.of("aaaaa", "bbb", "bb", "ababab","aaa", "aaaaaaa","babababab", "bbbb", "ababab");
        Map<String, Double> groups1 =myWords.stream().collect(
                                                        Collectors.groupingBy(w-> w.substring(0,1).toLowerCase(), // the fucntion for extracting the keys
                                                        Collectors.averagingInt(w -> w.length()))
                                                    );
        System.out.println(groups1.toString());
    }
}
