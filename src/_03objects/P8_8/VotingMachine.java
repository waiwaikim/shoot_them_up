package _03objects.P8_8;

public class VotingMachine {

    private int demVoteCount, repVoteCount;

    public void clearMachine(){
        demVoteCount = 0;
        repVoteCount = 0;
    }

    public void voteDemocrat(){
        demVoteCount++;
    }
    public void voteRepublican(){
        repVoteCount++;
    }

    public void printTallies(){
        System.out.println("Vote count for the Democrat is " + demVoteCount + ", and vote count for the Republican is " + repVoteCount);
    }

    public static void main(String[] args) {
        VotingMachine myMachine = new VotingMachine();

        for(int i=0; i<100; i++){
            myMachine.voteDemocrat();
        }

        for(int j=0; j<101; j++){
            myMachine.voteRepublican();
        }

        myMachine.printTallies();
    }

}
