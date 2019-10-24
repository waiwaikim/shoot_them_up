package _03objects.P8_7;

import java.util.ArrayList;

public class ComboLock {

    private int secret1, secret2, secret3;
    // secret1-3 are the password set by the constructor

    private int curLoc;
    private ArrayList<Character> movements = new ArrayList<Character>();
    private ArrayList<Integer> combos = new ArrayList<Integer>();
    // combo1-3 are input by the user trying to open the lock

    public ComboLock(int num1, int num2, int num3) throws Exception {

        if (num1<0 || num1>39 || num2<0 || num2>39 || num3<0 || num3>39 ){
            throw new Exception("at least one of the password is not found on the dial");
        }
        else{
            secret1 = num1;
            secret2 = num2;
            secret3 = num3;
            curLoc = 0;
        }
    }

    public void reset(){
        combos.clear();
        movements.clear();
        curLoc = 0;
    }

    public void turnLeft(int ticks){

        while(ticks>39){
            movements.add('L');
            ticks -= 39;
            // because it's lock with a dial, you could get to the same number in a indifinitely different way
            // while loop keeps track of how many rounds the dial makes.
            // Dial has to stop at the first time it reaches the secret code.
            // We consider it's incorrect to reach the code once it has passed the secret code.
        }
        movements.add('L');
        int temp = curLoc - ticks;
        if (temp>=0) curLoc = temp;
        else{
            curLoc = 40 + temp;
        }
        //System.out.println("turn:" + curLoc);
        combos.add(curLoc);

    }
/*    public void turnRight(int ticks){
        movements.add('R');
        int temp = curLoc + ticks;
        if (temp<=39) curLoc=temp;
        else{
            curLoc = 40 - temp;
        }
        //System.out.println("turn: "+curLoc);
        combos.add(curLoc);
    }*/
    public void turnRight(int ticks){

        while(ticks>39){
            movements.add('R');
            ticks -= 39;
        }
        movements.add('R');
        int temp = curLoc + ticks;
        if (temp<=39) curLoc=temp;
        else{
            curLoc = 40 - temp;
        }
        //System.out.println("turn: "+curLoc);
        combos.add(curLoc);

    }

    public boolean open(){

        if(movements.size() != 3 ) {
            return false;
        }
        //when the user has more than three movements
        else{
            if(movements.get(0) !='R' || movements.get(1)!='L' || movements.get(2)!='R') {
                return false;
            }
                //when the user rotates in an incorrect sequence
            else {
                if(secret1!=combos.get(0) || secret2!=combos.get(1) || secret3!=combos.get(2)) {
                    return false;
                }
                else{
                    return true;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ComboLock myLock = new ComboLock(20, 3, 11);

        myLock.turnRight(20);
        myLock.turnLeft(17);
        myLock.turnRight(8);

        /*System.out.println("secret: " + myLock.secret1 + " " + myLock.secret2 + " " + myLock.secret3);
        System.out.println("size: " + myLock.movements.size());
        System.out.println("turn sequence: " + myLock.movements.get(0)+ " " + myLock.movements.get(1) + " " + myLock.movements.get(2));
        System.out.println("password input: " + myLock.combos.get(0)+ " " + myLock.combos.get(1) + " " + myLock.combos.get(2));
*/
        System.out.println("Is myLock open? " + myLock.open());
        myLock.reset();

        myLock.turnRight(98);
        myLock.turnLeft(17);
        myLock.turnRight(8);
        System.out.println("password input: " + myLock.combos.get(0)+ " " + myLock.combos.get(1) + " " + myLock.combos.get(2));
        System.out.println("Is myLock open? " + myLock.open());
    }
}
