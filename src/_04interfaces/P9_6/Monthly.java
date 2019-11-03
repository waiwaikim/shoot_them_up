package _04interfaces.P9_6;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Monthly extends Appointment {

    public Monthly(String desc, int year, int month, int day) throws ParseException {
        super(desc, year, month, day);
    }

    //user input date is compared to the Appointment's date.
    //returns true if 1) the object's date is before or equal to the user input date and
    // 2) the object's day is the same as the user input day
    @Override
    public boolean occursOn(int year, int month, int day) throws ParseException {
        SimpleDateFormat compFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date compDate = compFormat.parse(String.format("%d-%d-%d", year, month, day));

        if (this.date.equals(compDate) || this.date.before(compDate)){
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.date);
            int appointDay = cal.get(Calendar.DAY_OF_MONTH);
            //System.out.println(this.toString() + " " + appointDay);
            if (appointDay== day){
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }

}
