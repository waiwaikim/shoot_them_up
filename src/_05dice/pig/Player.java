package _05dice.pig;

public class Player {
    private int score;
    private int scoreRound;

    public Player(){
        score = 0 ;
        scoreRound = 0;
    }

    public int getScore(){
        return this.score;
    }
    public int getRoundScore(){
        return this.scoreRound;
    }

    public void addScoreRound(int current){
        scoreRound += current;
    }
    public void addScore(int scoreRound){
        score += scoreRound;
    }

    public void setZero(){
        scoreRound = 0;
    }

    public void reset(){
        score = 0;
        scoreRound = 0;
    }

}
