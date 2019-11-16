package _06design.E12_4;

import java.util.Random;

public class Lv2Problem extends Problem {

    public Lv2Problem(){
        Random r = new Random();
        int tempA = r.nextInt(10);
        int tempB = r.nextInt(10);

        setOperands(tempA, tempB);
        setOperator("+");
        setAnswer();
    }
}
