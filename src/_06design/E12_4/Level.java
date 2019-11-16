package _06design.E12_4;

import java.util.Scanner;

public class Level {

    private int currPoints;
    private int currLevel;

    public Level(){
        currPoints = 0;
    }
    public Level(int myLevel){
        currLevel = myLevel;
        currPoints = 0;
    }

    public boolean askQuestion(Problem myProblem){
        //ask the question until the user has the correct answer or until the user runs out of tries
        int numTries = 0 ;

        while(numTries <2){

            Scanner in = new Scanner(System.in);
            myProblem.display();
            int userAnswer = in.nextInt();

            if(myProblem.checkAnswer(userAnswer)){
                //user inputs the correct answer
                System.out.println("correct!");
                return true;
            }
            else {
                System.out.println("Incorrect answer. Let's try again.");
                numTries++;
            }
        }
        System.out.println("Incorrect answer again. Let's try a similar but different question.");
        return false;
    }

    public void startLevel(){

        while(currPoints < 5){
            //each wile loop is a question
            //question will be asked when currPoints is 4.
            Problem myQuestion = new Problem();
            switch(currLevel){
                //create a question based on currLevel
                case 1:
                    myQuestion = new Lv1Problem();
                    break;
                case 2:
                    myQuestion = new Lv2Problem();
                    break;
                case 3:
                    myQuestion = new Lv3Problem();
                    break;
            }
            if(askQuestion(myQuestion)){
                currPoints++;
            }
            else{
                //do nothing
            }
        }
    }
    public boolean isCompleted(){
        if(this.currPoints >=5){
            return true;
        }
        else return false;
    }

}
