package _03objects.P0_2.P8_6;

public class Car {

    private final float fuelEfficiency;
    private float fuelLevel;

    public Car(float f){
        fuelEfficiency = f;
        fuelLevel = 0;
    }
    public void drive(float dist){
        float gasSpent = dist / fuelEfficiency ;
        fuelLevel -= gasSpent;
    }

    public void addGas(float fill){
        fuelLevel += fill;
    }
    public float getGasLevel(){
        return fuelLevel;
    }

    public static void main(String[] args) {
        Car myHybrid = new Car(50);
        myHybrid.addGas(20);
        myHybrid.drive(100);
        System.out.println("amount of gas left in the car is " + myHybrid.getGasLevel());

    }
}
