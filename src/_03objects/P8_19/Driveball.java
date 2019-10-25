package _03objects.P8_19;

import java.util.Scanner;

public class Driveball {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        //System.out.print("enter a starting position: ");
        //int start = in.nextInt();
        System.out.print("enter an angle in dgree: ");
        double angle = (double)in.nextInt();
        System.out.print("enter an inital velocity in m/s: ");
        double speed = (double)in.nextInt();

        Canonball myCanon = new Canonball(0);
        myCanon.shoot(angle, speed);
    }
}
