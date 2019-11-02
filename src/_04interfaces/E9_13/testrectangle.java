package _04interfaces.E9_13;

public class testrectangle {

    public static void main(String[] args) {

        BetterRectangle r1 = new BetterRectangle(0,0, 10, 15);
        System.out.println("perimeter is " + r1.getPerimeter());
        System.out.println("area is " + r1.getArea());
    }
}
