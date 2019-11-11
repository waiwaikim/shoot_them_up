package _05dice.pig;

import java.util.Random;

public class Die {

    private int[] numbers = new int[]{1, 2, 3, 4, 5, 6};

    public Die() {
    }

    public int rollDie(){
        Random r = new Random();
        int random_pick = r.nextInt(6);
        return this.numbers[random_pick];
    }

    public int comRollDie(){

        boolean isNotOne = true;
        int comScore = 0 ;
        while(isNotOne){

            int tempRoll = this.rollDie();
            //if (tempRoll )
        }
        return 0 ;

    }
}

