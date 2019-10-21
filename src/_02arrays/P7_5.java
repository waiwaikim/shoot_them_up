package _02arrays;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class P7_5 {
    public static void main(String[] args) throws Exception  {

        String strPath = "C:\\Users\\kjd13\\java\\projava\\src\\_02arrays\\Book1.csv";
        File fileInput = new File(strPath);

        System.out.println(numberOfRows(fileInput));
        System.out.println(numberOfFields(fileInput , 1));
        System.out.println(field(fileInput, 2, 3));

    }

    private static int numberOfRows(File fileInput) throws FileNotFoundException {
        Scanner scan = new Scanner(fileInput);
        int n=0 ;
        while(scan.hasNext()){
            n++;
            scan.nextLine();
        }
        return n;
    }
    private static int numberOfFields(File fileInput, int row) throws FileNotFoundException {
        Scanner scan = new Scanner(fileInput);
        int n=0;
        while(n<row){
            n++;
            scan.nextLine();
        }

        String curLine = scan.nextLine();
        //System.out.println(curLine);
        ArrayList<String> parsedList = parseLine(curLine);

        //System.out.println(parsedList.toString());
        return parsedList.size();
    }

    private static String field(File fileInput, int row, int column) throws FileNotFoundException{
        Scanner scan = new Scanner(fileInput);
        int n=0;
        while(n<row){
            n++;
            scan.nextLine();
        }

        String curLine = scan.nextLine();
        //System.out.println(curLine);
        ArrayList<String> parsedList = parseLine(curLine);
        return parsedList.get(column);
    }
    private static ArrayList parseLine(String curLine){

        ArrayList<String> result = new ArrayList<>();
        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false; //this indicates whether I'm in quotes or not
        boolean startCollector = false;
        boolean doubleQuotes = false;
        char[] chars = curLine.toCharArray();


        for (char ch : chars){
            if(inQuotes){
                startCollector = true;
                if(ch == '"'){
                    inQuotes=false;
                    doubleQuotes=false;
                }
                else{
                    if(ch=='\"'){
                        if(!doubleQuotes){
                            curVal.append(ch);
                            doubleQuotes = true;
                        }
                    }
                    else{
                        curVal.append(ch);
                    }
                }
            }
            else{
                if(ch=='"'){
                    inQuotes=true;
                    if(startCollector){
                        curVal.append('"');
                    }
                }
                else if(ch==','){
                    result.add(curVal.toString());
                    //result.add();
                    curVal = new StringBuffer();
                    startCollector = false;
                }
                else if(ch =='\n'){
                    break;
                }
                else{
                    curVal.append(ch);
                }
            }
        }
        result.add(curVal.toString());
        return result;
    }


}
