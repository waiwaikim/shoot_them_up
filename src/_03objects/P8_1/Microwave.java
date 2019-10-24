package _03objects.P8_1;

public class Microwave {

    private int cookTime;
    private int cookPower;

    public Microwave(){
        cookTime = 0;
        cookPower = 1;
    }

    public Microwave(int t, int p) throws Exception{
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

        Microwave micro1 = new Microwave();
        micro1.plus30();
        micro1.plus30();
        micro1.start();

        micro1.reset();
        micro1.start();

        Microwave micro2 = new Microwave(240, 2);
        micro2.start();

        Microwave micro3 = new Microwave(240, 3);
    }
}
