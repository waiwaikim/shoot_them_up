package _02arrays;

public class E5_8 {

    public static int countWords(String str){

        int prev =0;
        int count =1;
        String sub = str.trim();

        for(int i=0; i<sub.length(); i++){
            if(sub.charAt(i) == ' ' && prev != (i-1)){
                count++;
                prev = i;
            }
        }

        return count;
    }
    public static float avgLengthWords(String str){

        int wordCount = countWords(str);
        int space_count =0;
        String sub = str.trim();

        for(int i=0; i<sub.length(); i++){
            if(sub.charAt(i) == ' '){
                space_count++;
            }
        }

        return ((sub.length() - space_count)/(float)wordCount);
    }
    public static void main(String[] args) {
        String words= "     Mary  had  a  little lamb    ";

        System.out.println("# of words: " + countWords(words));
        System.out.println("avg length of words: " + avgLengthWords(words));
    }
}
