package _04interfaces.P9_6;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class testAppointment  {
    public static void main(String[] args) throws ParseException {

        ArrayList<Appointment> myCalendar = new ArrayList<Appointment>();
        Appointment ex1 = new Onetime("Facebook recruiting",2019, 10, 30);
        Appointment ex2 = new Daily("workout", 2019, 10, 02);
        Appointment ex3 = new Monthly("economics class", 2019, 10, 30);
        Appointment ex4 = new Onetime("coffee chat", 2019, 11, 30);
        myCalendar.add(ex1);
        myCalendar.add(ex2);
        myCalendar.add(ex3);
        myCalendar.add(ex4);

        Scanner in = new Scanner(System.in);
        System.out.print("enter a year (yyyy): ");
        int year = in.nextInt();
        System.out.print("enter a month (mm): ");
        int month = in.nextInt();
        System.out.print("enter a day (dd): ");
        int day = in.nextInt();
        // input 2019 10 03 to test daily - output: workout
        // test 2019 10 30 - output: Facebook, workout,  economics class
        // input 2019 11 30 to test monthly - output: workout,  economics class, coffee chat

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = myFormat.parse(String.format("%d-%d-%d", year, month, day));

        for (Appointment app : myCalendar) {
            if(app.occursOn(year,month,day)){
                System.out.println(app.toString());
            }
        }
    }
}
