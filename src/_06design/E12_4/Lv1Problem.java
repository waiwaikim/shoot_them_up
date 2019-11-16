package _06design.E12_4;

import java.util.Random;

public class Lv1Problem extends Problem {

    public Lv1Problem(){
        Random r = new Random();
        int tempA = r.nextInt(10);
        int tempB = r.nextInt(10 - tempA);

        this.setOperands(tempA, tempB);
        this.setOperator("+");
        this.setAnswer();
    }

}
