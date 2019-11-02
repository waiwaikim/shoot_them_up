package _04interfaces.E9_17;

public class SodaCan implements Measurable {

    public int radius;
    public int height;

    public SodaCan(int radius, int height) {
        this.radius = radius;
        this.height = height;
    }

    public double getMeasure(){
        //get surface area
        return (Math.PI *Math.pow(radius,2)*2) + (2*Math.PI*radius*height);
    }

    public double getVolume(){
        return Math.PI *Math.pow(radius,2)*height;
    }

    public static double average(Measurable[] objects){
        if(objects.length == 0){ return 0; }
        double sum = 0 ;
        for(Measurable obj : objects){
            sum += obj.getMeasure();
        }
        return sum/ objects.length;
    }
}

