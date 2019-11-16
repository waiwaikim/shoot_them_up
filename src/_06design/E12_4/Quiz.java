package _06design.E12_4;

public class Quiz {

    public static void main(String[] args) {
        //This is a driver for Quiz. Please run this java code.

        Level myLevel1 = new Level(1);
        Level myLevel2 = new Level(2);
        Level myLevel3 = new Level(3);

        System.out.println("Let's begin our arithmetic quiz!");
        myLevel1.startLevel();

        if(myLevel1.isCompleted()){
            System.out.println("You've completed the level 1.");
            myLevel2.startLevel();
        }

        if(myLevel2.isCompleted()){
            System.out.println("You've completed the level 2.");
            myLevel3.startLevel();
        }

        if(myLevel3.isCompleted()){
            System.out.println("Congratulation! You have completed the quiz");
        }


    }
}
