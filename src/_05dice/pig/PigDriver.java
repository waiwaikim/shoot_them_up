package _05dice.pig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class PigDriver {


    private ActionListener holdActionListener;
    private ActionListener rollActionListener;
    private ActionListener endGameActionListener;
    private ActionListener startActionListener;
    private JTextArea gameArea;
    private JButton rollButton;
    private JButton holdButton;
    private JTextArea playerTextArea;
    private JTextArea computerTextArea;
    private JButton endGameButton;
    private JButton startButton;
    private JPanel dieGame;

    private boolean isPlayerCurrent;
    private boolean isComputerCurrent;
    private boolean gameLive;

    Die myDie = new Die();
    Player myPlayer = new Player();
    Computer myComputer = new Computer();

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainGUI");
        frame.setContentPane(new PigDriver().dieGame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1500, 1000);
        frame.setVisible(true);
    }
    
    public PigDriver(){



        rollActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ( gameLive && isPlayerCurrent){
                    //Human player is playing
                    int thisRoll = myDie.rollDie();

                    if(thisRoll ==1 ){
                        //Human rolls 1. Computer's turn to play
                        myPlayer.setZero();
                        gameArea.append("Player rolls 1. Shoot! You lose all the points. It's computer's turn. \n");
                        isPlayerCurrent = false;
                        isComputerCurrent = true;

                        //computer's turn
                        playComputer();
                    }
                    else{
                        //Human doesn't not roll 1.
                        gameArea.append("Player rolls "+thisRoll + ". Would you like to roll or hold? \n");
                        myPlayer.addScoreRound(thisRoll);
                    }
                }
                else if (gameLive && isComputerCurrent){
                    //Computer is playing
                    gameArea.append("It's not your turn \n");

                }
                else{
                    gameArea.append("Please start the game. \n");
                }
            }
        };

        holdActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameLive && isPlayerCurrent){
                    //user presses hold key when it's his turn

                    int currentScore = myPlayer.getRoundScore();
                    myPlayer.addScore(currentScore);
                    myPlayer.setZero();
                    gameArea.append("Player holds. " + currentScore + " has been added. \n It's Computer's turn.\n\n");
                    playerTextArea.append(currentScore + " has been earned." + "Total Score: " + myPlayer.getScore()+"\n");

                    isPlayerCurrent = !isPlayerCurrent;
                    isComputerCurrent = !isComputerCurrent;

                    didPlayerWin();
                    //now it's computer's turn
                    playComputer();
                }
                else if(gameLive && isComputerCurrent){
                    gameArea.append("It's not your turn \n");
                }
                else{
                    gameArea.append("You must start the game before deciding to hold. \n");
                }

            }
        };

        endGameActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanScreen();
                isPlayerCurrent = false;
                isComputerCurrent = false;
                gameLive = false;
            }
        };

        startActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanScreen();
                String startString = "Game has begun! It's your turn. Please roll the dice!";
                gameArea.append(startString + "\n");
                isPlayerCurrent = true;
                isComputerCurrent = false;
                gameLive = true;
            }
        };

        createButton();
    }

    private void cleanScreen(){
        myPlayer.reset();
        myComputer.reset();
        gameArea.setText("");
        playerTextArea.setText("");
        computerTextArea.setText("");
    }

    private void createButton(){
        rollButton.addActionListener(rollActionListener);
        holdButton.addActionListener(holdActionListener);
        endGameButton.addActionListener(endGameActionListener);
        startButton.addActionListener(startActionListener);
    }

    private void playComputer(){

        if(isComputerCurrent){
            ArrayList<Integer> comRound = myComputer.computerPlay((myDie));
            gameArea.append("Computer plays such: " + comRound.toString() +"\n");
            int comResult = myComputer.addScore(comRound);
            gameArea.append( comResult +" has been added to computer's score \n It's player's turn. \n\n");
            computerTextArea.append(comResult +" has been earned." + "Total Score: " + myComputer.getScore() +"\n");

            isPlayerCurrent = !isPlayerCurrent;
            isComputerCurrent = !isComputerCurrent;
            didComputerWin();
        }

    }

    private void didPlayerWin(){
        int currScore = myPlayer.getScore();
        if(currScore >=100){
            gameArea.append("Congratulation. You've won!. \n");
            disableGame();
        }
    }

    private void didComputerWin(){
        int currScore = myComputer.getScore();
        if(currScore >= 100){
            gameArea.append("Computer has won. Do you want to try again?");
            disableGame();
        }
    }

    public void disableGame(){
        isPlayerCurrent = false;
        isComputerCurrent = false;
        gameLive = false;

        gameArea.append("The game has ended. If you want to play more reset and restart the game \n");
    }


}
