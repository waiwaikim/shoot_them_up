package _04interfaces.E9_11;



public class testDrive {

    public static void main(String[] args) {

        Person waiwai = new Person("Waiwai", 1989);
        System.out.println(waiwai);
        Student waiwai2 = new Student("waiwai", 1989, "MBA & Computer Science");
        System.out.println(waiwai2);

        Instructor adam = new Instructor("adam", 1776, 1000000);
        System.out.println(adam);
    }
}
