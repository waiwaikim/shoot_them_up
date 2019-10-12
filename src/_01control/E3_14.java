package _01control;


import java.util.Scanner;
public class E3_14 {

    //
    public static void main(String[] args) {

        String[] seasons = {"Winter", "Spring", "Summer", "Fall"};

        Scanner in = new Scanner(System.in);
        System.out.print("enter a month: ");
        int month = in.nextInt();
        System.out.print("enter a day: ");
        int day = in.nextInt();

        int season =-1 ;
        switch(month){
            case 1: case 2: case 3:
                season = 0;
                break;
            case 4: case 5: case 6:
                season = 1;
                break;
            case 7: case 8: case 9:
                season = 2;
                break;
            case 10: case 11: case 12:
                season = 3;
                break;
        }
        if (month%3==0 && day>=21){
            season++;
        }
        System.out.println(seasons[season]);
    }
}
