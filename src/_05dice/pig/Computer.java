package _05dice.pig;

import java.util.ArrayList;
import java.util.Random;

public class Computer {

    private int score;
    private boolean[] rollDecision = new boolean[]{true, false};

    public Computer(){
        score = 0 ;

    }

    public int getScore(){
        return this.score;
    }

    public int addScore(ArrayList<Integer> thisRound){
        //add scores from the current round to the total score and returns the result of the round
        int scoreRound = 0 ;
        for(Integer i : thisRound){
            score += i;
            scoreRound += i;
        }
        return scoreRound;
    }
    public void reset(){
        score = 0;
    }

    public boolean shouldComRoll(){
        //randomly decides whether computer should roll or hold
        //true: computer rolls
        //false: computer holds

        Random r = new Random();
        int random_pick = r.nextInt(2);
        return rollDecision[random_pick];
    }

    public ArrayList<Integer>  computerPlay(Die myDie){
        //calling computerPlay() equates to one round of computer's play
        //computer rolls until it rolls 1 or decides to hold
        boolean oneRolled = false;

        ArrayList<Integer> thisRound = new ArrayList<Integer>();
        int thisRoll = myDie.rollDie();

        if(thisRoll==1){
            oneRolled = true;
            thisRound = new ArrayList<Integer>();
        }
        else{
            thisRound.add(thisRoll);
        }

        while(shouldComRoll() && !oneRolled){
            thisRoll = myDie.rollDie();
            if(thisRoll==1){
                oneRolled=true;
                thisRound = new ArrayList<Integer>();
            }
            else{
                thisRound.add(thisRoll);
            }
        }
        return thisRound;
    }
}
