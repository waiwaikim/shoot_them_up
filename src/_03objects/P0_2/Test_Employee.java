package _03objects.P0_2;

public class Test_Employee {
    // this instance variable is visible for any child class.
    public String name;

    // salary  variable is visible in Employee class only.
    private double salary;

    public Test_Employee () {
    }

    // The name variable is assigned in the constructor.
    public Test_Employee (String empName) {
        name = empName;
    }

    // The salary variable is assigned a value.
    public void setSalary(double empSal) {
        salary = empSal;
    }

    // This method prints the employee details.
    public void printEmp() {
        System.out.println("name  : " + name );
        System.out.println("salary :" + salary);
    }

    public static void main(String args[]) {
        Test_Employee empOne = new Test_Employee();
        empOne.printEmp();
    }
}
