package _02arrays;

public class P5_24 {
    private int convert(char r){
        switch(r){
            case 'I' :
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return -1;
    }

    private int toNumeric(String str){
        int tot = 0;

        while(str != ""){
            if(str.length()==1 || convert(str.charAt(0)) >= convert(str.charAt(1))){
                tot += convert(str.charAt(0));
                char old = str.charAt(0);
                if(str.length()==1){ str =""; }
                else{ str = str.substring(1);}
            }
            else{
                int diff = convert(str.charAt(1))-convert(str.charAt(0));
                tot += diff;
                str = str.substring(2);
            }
        }
        return tot;
    }

    public static void main(String[] args) {
        String example = "MCMLXXVIII";
        //expected answer = 1978
        P5_24 ob = new P5_24();
        System.out.println(ob.toNumeric(example));
    }
}
