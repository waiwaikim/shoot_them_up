package _04interfaces.P9_6;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Daily extends Appointment {

    public Daily(String desc, int year, int month, int day) throws ParseException {
        super(desc, year, month, day);
    }

    //user input date is compared to the Appointment's date.
    //returns true if the object's date is before or equal to the user input date.
    @Override
    public boolean occursOn(int year, int month, int day) throws ParseException {

        SimpleDateFormat compFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date compDate = compFormat.parse(String.format("%d-%d-%d", year, month, day));
        return (this.date.equals(compDate) || this.date.before(compDate));
    }
}
