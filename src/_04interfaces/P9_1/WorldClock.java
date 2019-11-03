package _04interfaces.P9_1;

public class WorldClock extends Clock {

    private int offset;

    public WorldClock(int i){
        super();
        offset = i;
    }

    @Override
    public String getHours() {
        String localHour = super.getHours();
        int newTime = (Integer.parseInt(localHour)+ offset+ 24)%24;
        //when the offset results in a hour larger than 24 or smaller than 0
        return Integer.toString(newTime);
    }

    public static void main(String[] args) {

        WorldClock c1 = new WorldClock(-16);
        System.out.println(c1.getTime());

    }
}
