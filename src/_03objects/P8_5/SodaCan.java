package _03objects.P8_5;

public class SodaCan {

    private int height;
    private int radius;
    public SodaCan(int h, int r){
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
        SodaCan sodaCan = new SodaCan(10, 2);
        System.out.println("Surface area: "+sodaCan.getSurfaceArea());
        System.out.println("Volume: " + sodaCan.getVolume());
    }
}
