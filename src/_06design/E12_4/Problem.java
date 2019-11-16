package _06design.E12_4;

public class Problem {
    private int operand1, operand2;
    private String operator;
    private int answer;

    public Problem() {

    }


    public void setOperands(int a, int b){
        operand1 = a;
        operand2 = b;
    }

    public void setOperator(String myOperator){
        operator = myOperator;
    }

    public void setAnswer(){
        if (operator.equals("+")){
            answer = operand1 + operand2;
        }
        else if(operator.equals("-")){
            answer = operand1 - operand2;
        }
        else {
            System.out.println("Error: Opertor is other than + and -. ");
        }
    }

    public boolean checkAnswer(int myAnswer){
        return (answer == myAnswer);
    }

    public void display(){
        //display the question
        System.out.print("What is the answer to this equation? " + operand1 + " " + operator +"  " + operand2 + " = ");
    }

}
