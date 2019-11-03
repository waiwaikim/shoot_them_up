package _04interfaces.P9_6;

import java.text.ParseException;

public class Onetime extends Appointment {

    public Onetime(String desc, int year, int month, int day) throws ParseException {
        super(desc, year, month, day);
    }


    @Override
    public boolean occursOn(int year, int month, int day) throws ParseException {
        return super.occursOn(year, month, day);
    }
}
