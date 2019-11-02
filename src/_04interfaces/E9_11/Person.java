package _04interfaces.E9_11;


public class Person {

    protected String name;
    protected int birthYear;

    public Person(){

    }
    public Person(String n, int y){
        name= n;
        birthYear = y;
    }


    @Override
    public String toString() {
        return "I am " + name + " born in " + birthYear + ".";
    }
}
