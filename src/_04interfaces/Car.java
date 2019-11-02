package _04interfaces;

import java.io.OutputStream;

public class Car {
    protected String brand = "Ford";
    public void honk(){
        System.out.println("I'm a car");
    }
}

 class Mustang extends Car{

    private String modelName = "Mustang";

    public void honk(){
        System.out.println("I'm a Mustang!");
    }

     public static void main(String[] args) {

         Car x = new Car();
         Mustang y = new Mustang();

         x.honk();
         y.honk();
         System.out.println(y.modelName);

         x = new Mustang();

     }

}


