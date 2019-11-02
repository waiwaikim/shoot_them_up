package _04interfaces.E9_11;

public class Student extends Person {
    private String major;

    public Student(){

    }

    public Student(String m){
        super();
        major = m;
    }

    public Student(String n, int y, String m){
        super(n,y);
        major = m ;
    }

    @Override
    public String toString() {
        return super.toString() + "I major in " + major + ".";
    }
}
