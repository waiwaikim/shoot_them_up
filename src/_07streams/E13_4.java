package _07streams;

public class E13_4 {
    public static void main(String[] args) {

        //to test binaryConvert
        System.out.println("binary form of 10: " + binaryConvert(10));
        //expect 1010
        System.out.println("binary form of 47: " + binaryConvert(47));
        //expect 101111
        System.out.println("binary form of 29: " + binaryConvert(29));
        //expect 11101
        System.out.println("binary form of 1024: " + binaryConvert(1024));
        //expect 10000000000
    }

    public static String binaryConvert(int myNum){
        //a recursive method for computing a string with the binary digits of a number.
        //If n is even, then the last digit is 0. If n is odd, then the last digit is 1. Recursively obtain the remaining digits.

        if (myNum == 0) return "0";
        else if(myNum == 1) return "1";
        else{
            if(myNum%2 == 0 ){
                //myNum is even
                return binaryConvert(myNum/2) + "0";
            }
            else{
                //myNum is odd
                return binaryConvert(myNum/2) +"1";
            }
        }
    }
}
