package _03objects.P8_19;
import _03objects.P8_16.Mailbox;

import java.lang.Math;

public class Canonball {

    private double x_pos, y_pos, x_speed, y_speed;

    public Canonball(int x){
        this.x_pos = (double) x;
        this.y_pos = 0;
        this.x_speed = 0;
        this.y_speed = 0;
    }
    public double getX(){ return this.x_pos; }
    public double getY(){ return this.y_pos; }

    public void move(double sec){
        //x_speed is unchanged
        //y_speed changes as soon as the ball is shot
        y_speed -= (9.81)*sec;
        x_pos += x_speed * sec;
        y_pos += y_speed * sec ;
        //y_pos = y_speed * sec - ((0.5)*(9.81)*Math.pow(sec,2));


    }
    public void shoot(double angle, double initV){

        angle = Math.toRadians(angle);
        //System.out.println("angle: " + angle);
        this.x_speed = initV*Math.cos(angle);
        this.y_speed = initV*Math.sin(angle);

        //System.out.println("Initial x speed: "+x_speed);
        //System.out.println("Initial y speed: "+y_speed);
        double time= 0.0;
        System.out.println("time: "+ Math.round(time *100)/100.0d+ " sec @ ("+Math.round(this.getX()*100)/100.0d+"m , " +
                Math.round(this.getY()*100)/100.0d+"m)");
        //the purpose of Math.round(double variable * 100) / 100.0d:
        //to display a decimal number with a precision of 2
        //without it, a double can be seen as 4.99999999999 - not user friendly

        while(this.getY()>=0){
            this.move(0.1);
            time += 0.1;
            System.out.println("time: "+ Math.round(time *100)/100.0d+ " sec @ ("+Math.round(this.getX()*100)/100.0d+"m , " +
                    Math.round(this.getY()*100)/100.0d+"m)");

        }

    }
}
