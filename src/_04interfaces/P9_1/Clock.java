package _04interfaces.P9_1;
import java.time.LocalTime;

public class Clock {

    public LocalTime now;
    public String nowString;


    public Clock(){
        now = LocalTime.now();
        nowString = now.toString();
    }

    public String getHours(){
        String[] timeArray = nowString.split(":");
        return timeArray[0];
    }

    public String getMinutes(){
        String[] timeArray = nowString.split(":");
        return timeArray[1];
    }
    public String getTime(){
        String time = "";

        /*time += Integer.toString(now.getHour()) + ":";
        time += Integer.toString(now.getMinute());
        */

        time += this.getHours() + ":"+ this.getMinutes();
        return time;
    }

//    public static void main(String[] args) {
//        Clock c1 = new Clock();
//        System.out.println(c1.now);
//        System.out.println(c1.getTime());
//    }
}
