package _04interfaces.P9_6;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Appointment {

    private String description;
    Date date;

    //default constructor
    public Appointment(){
    }

    //constructor
    public Appointment(String desc, int year, int month, int day) throws ParseException {
        description = desc;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.date= format.parse(String.format("%d-%d-%d", year, month, day));
    }

    public String getDate(){
        return this.date.toString();
    }

    @Override
    public String toString() {
        return description + " " + getDate();
    }

    public boolean occursOn(int year, int month, int day) throws ParseException {
        SimpleDateFormat compFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date compDate = compFormat.parse(String.format("%d-%d-%d", year, month, day));
        return this.date.equals(compDate);
    }
}
