package _04interfaces.E9_11;

public class Instructor extends Person {

    private int salary;

    public Instructor(){

    }

    public Instructor(String n, int y, int s){
        super(n, y);
        salary = s;
    }

    @Override
    public String toString() {
        return super.toString() + "I am a baller cuz I make " + salary + " a year.";
    }
}
