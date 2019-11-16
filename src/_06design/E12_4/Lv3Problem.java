package _06design.E12_4;

import java.util.Random;

public class Lv3Problem extends Problem{

    public Lv3Problem(){
        Random r = new Random();
        int tempA = r.nextInt(10);
        int tempB = r.nextInt(tempA+1);

        setOperands(tempA, tempB);
        setOperator("-");
        setAnswer();
    }
}
