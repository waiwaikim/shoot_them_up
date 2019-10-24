package _03objects;

public class P8_5 {

    private int height;
    private int radius;
    public P8_5(int h, int r){
        this.height = h;
        this.radius = r;

    }

    public double getSurfaceArea(){
        return ((Math.PI * Math.pow(radius, 2) *2)+(2*Math.PI*radius*height));
    }

    public double getVolume(){
        return Math.PI * Math.pow(radius, 2)*height;
    }

    public static void main(String[] args) {
        P8_5 sodaCan = new P8_5(10, 2);
        System.out.println("Surface area: "+sodaCan.getSurfaceArea());
        System.out.println("Volume: " + sodaCan.getVolume());
    }
}
