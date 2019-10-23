package _03objects.P0_2;

public class P8_1 {

    private int cookTime;
    private int cookPower;

    public P8_1 (){
        cookTime = 0;
        cookPower = 1;
    }

    public P8_1(int t, int p) throws Exception{
        cookTime = t;
        if(p<1 || p>2) throw new Exception("Invalid Power Level");
        cookPower = p;
    }

    public void plus30(){
        cookTime+=30;
    }

    public void switchPower(){
        if(cookPower == 1) cookPower = 2;
        else cookPower = 1;
    }

    public void reset(){
        cookTime = 0;
        cookPower = 1;
    }
    public void start(){
        System.out.println("Cooking for " + cookTime + " seconds at level " + cookPower);
    }

    public static void main(String[] args) throws Exception {
        //This driver is to test the class above

        P8_1 micro1 = new P8_1();
        micro1.plus30();
        micro1.plus30();
        micro1.start();

        micro1.reset();
        micro1.start();

        P8_1 micro2 = new P8_1(240, 2);
        micro2.start();

        P8_1 micro3 = new P8_1(240, 3);
    }
}
