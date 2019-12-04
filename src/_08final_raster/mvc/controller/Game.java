package _08final_raster.mvc.controller;

import _08final_raster.mvc.model.*;
import _08final_raster.mvc.view.GamePanel;
import _08final_raster.sounds.Sound;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.Random;

import static java.awt.event.KeyEvent.VK_N;

/**
 *
 *  Date        Author      Description
 *  ----        ------      -----------
 *  12/05/2019    Waiwai      Extended code to implement 1941 Counter Attack
 */
public class Game implements Runnable, KeyListener {

	// FIELDS
	public static final Dimension DIM = new Dimension(700, 1000); //the dimension of the game.
	private GamePanel gmpPanel;
	public static Random R = new Random();
	public final static int ANI_DELAY = 45; // milliseconds between screen
											// updates (animation)

    public static final int GAME_MAX_LEVEL = 1; // Define max number of levels to determine if game is over.
	private Thread thrAnim;
	private int nTick = 0;

    private long lStartTime = System.currentTimeMillis();

	private boolean bMuted = false;
	private final int

            PAUSE = 80, // p key
			QUIT = 81, // q key
			LEFT = 37, // rotate left; left arrow
            UP = 38, // thrust; up arrow
			RIGHT = 39, // rotate right; right arrow
            DOWN = 40,
			START = 83, // s key
			FIRE = 32, // space key
			MUTE = 77, // m-key mute
            SPEED = 65; // a key to speed up

	private Clip clpMusicBackground;

	public Game() {
		gmpPanel = new GamePanel(DIM);
		gmpPanel.addKeyListener(this);
		clpMusicBackground = Sound.clipForLoopFactory("game_background.wav");
	}

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Game game = new Game();
							game.fireUpAnimThread();

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
	}

	private void fireUpAnimThread() {
		if (thrAnim == null) {
			thrAnim = new Thread(this);
			thrAnim.start();
		}
	}

	public void run() {

		thrAnim.setPriority(Thread.MIN_PRIORITY);

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");

		while (Thread.currentThread() == thrAnim) {

			tick();

            // Check items in order of priority
            checkGameOver();
            checkLevelClear();
            checkP38Dead();
			gmpPanel.update(gmpPanel.getGraphics());
            checkCollision();
            checkFoes();
            addFoes();
            processQueue();
			try {
				lStartTime += ANI_DELAY;
				Thread.sleep(Math.max(0,lStartTime - System.currentTimeMillis()));
			} catch (InterruptedException e) {
				continue;
			}
		} // end while
	} // end run

    // Check if level is clear after the completion of fireworks
    private void checkLevelClear() {
        if (CommandCenter.getInstance().isLevelClear()) {
            CommandCenter.getInstance().getOpsList().enqueue(new Firework(Game.R.nextInt(_08final.mvc.controller.Game.DIM.width), Game.R.nextInt(Game.DIM.height) - 300),
                                                            CollisionOp.Operation.ADD);
            for (Movable movFriend : CommandCenter.getInstance().getMovFriends()) {
                if (movFriend instanceof Firework) {
                    if (((Firework) movFriend).getExpiryCounter() > 0) {
                        ((Firework) movFriend).decrExpiryCounter();
                    } else {
                        CommandCenter.getInstance().getOpsList().enqueue(movFriend, CollisionOp.Operation.REMOVE);
                    }
                }
            }
        } else  {
            // Remove any left over fireworks from previous level
            for (Movable movFriend : CommandCenter.getInstance().getMovFriends()) {
                if (movFriend instanceof Firework) {
                    CommandCenter.getInstance().getOpsList().enqueue(movFriend, CollisionOp.Operation.REMOVE);
                }
            }
        }
    }

    // Check if Game is over and stop background music
    private void checkGameOver() {
        if (CommandCenter.getInstance().isGameOver()) {
            stopLoopingSounds(clpMusicBackground);
        }
    }

    // Method to draw background based on current level of game.
	private void drawBackGround() {
        Water water;
        Score score;

        //Add first water
        water = new Water(0, -600);
        CommandCenter.getInstance().getOpsList().enqueue(water, CollisionOp.Operation.ADD);
        CommandCenter.getInstance().setWaterFirst(water);

        //decrement water placement by 1600
        for(int i=0; i<10; i++){
            CommandCenter.getInstance().getOpsList().enqueue(new Water(0, -2100-(1600*i)), CollisionOp.Operation.ADD);
        }

        //Add Island
        CommandCenter.getInstance().getOpsList().enqueue(new Island(550,-400), CollisionOp.Operation.ADD);
        CommandCenter.getInstance().getOpsList().enqueue(new Island(DIM.width- 600,-4000), CollisionOp.Operation.ADD);
        CommandCenter.getInstance().getOpsList().enqueue(new Island(400,-6000), CollisionOp.Operation.ADD);
        CommandCenter.getInstance().getOpsList().enqueue(new Island(0,-8000), CollisionOp.Operation.ADD);
        CommandCenter.getInstance().getOpsList().enqueue(new Island(100,-14000), CollisionOp.Operation.ADD);
        CommandCenter.getInstance().getOpsList().enqueue(new Island(30,-15000), CollisionOp.Operation.ADD);

        CommandCenter.getInstance().getOpsList().enqueue(score = new Score(0, 0), CollisionOp.Operation.ADD);
    }


    // Method to check gravity of Mario
	private void checkP38Dead() {

        if (CommandCenter.getInstance().getP38() != null){
            P38 p38 = CommandCenter.getInstance().getP38();
            if (p38.isDead()) {

                if (p38.getP38DeadTimeLeft() <=0) {
                    //p38's dead time is decremented in P38.setImage() which checks if P38 is deade or not.
                    CommandCenter.getInstance().getOpsList().enqueue(p38, CollisionOp.Operation.REMOVE);
                    CommandCenter.getInstance().spawnP38(false);
                    if (!CommandCenter.getInstance().isGameOver()) {
                        clpMusicBackground.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                }
            }
        }
	}//end gravity check


    // Method to check collision with friends and foes
    private void checkCollision(){
        Point pntFriendCenter, pntFoeCenter;
        int nFriendRadius, nFoeRadius;

        for(Movable movFriend : CommandCenter.getInstance().getMovFriends()){
            for(Movable movFoe : CommandCenter.getInstance().getMovFoes()){

                pntFriendCenter = movFriend.getCenter();
                pntFoeCenter = movFoe.getCenter();
                nFriendRadius = movFriend.getRadius();
                nFoeRadius = movFoe.getRadius();

                //detect collision between Friend and Foe
                if(checkWithinRange(pntFriendCenter, nFriendRadius, pntFoeCenter,nFoeRadius)
                        && !((Sprite) movFoe).isOutFrame()
                        && !((Sprite) movFriend).isOutFrame()){

                    //P38
                    if((movFriend instanceof  P38)){
                        P38 myPlane = (P38) movFriend;

                        //when P38 and enemy plane1 collides or when P38 and enemy bullets hits
                        if(movFoe instanceof Enemy1 || movFoe instanceof Enemy2 || movFoe instanceof EnemyBullet || movFoe instanceof SmartBullet){
                            if (!myPlane.isDead()){
                                movFriend.setDead();
                                stopLoopingSounds(clpMusicBackground);
                                Sound.playSound("Mario_die.wav");

                                myPlane.setSpawnLocation(pntFriendCenter);
                                CommandCenter.getInstance().getOpsList().enqueue(movFoe, CollisionOp.Operation.REMOVE);
                            }
                        }
                        //when P38 and enemy ship colides --> nothing
                    }

                    //bullet hits a foe
                    else{
                        killFoe(movFriend, movFoe);
                    }
                }
            }
        }
        //when P38 collides with FLOATER
        if(CommandCenter.getInstance().getP38() != null){
            Point pntP38Center = CommandCenter.getInstance().getP38().getCenter();
            int nP38Radius =CommandCenter.getInstance().getP38().getRadius();

            Point pntFloaterCenter;
            int nFloaterRadius;

            for(Movable movFloater : CommandCenter.getInstance().getMovFloater()){
                pntFloaterCenter = movFloater.getCenter();
                nFloaterRadius = movFloater.getRadius();
                if(pntP38Center.distance(pntFloaterCenter) < (nFloaterRadius + nP38Radius) && !((Sprite) movFloater).isOutFrame()){

                    if(movFloater instanceof PowerUp){

                        if(CommandCenter.getInstance().getNumP38s() < 5){
                            CommandCenter.getInstance().incrementNumP38s();
                        }
                        else{
                            System.out.println("You can't have more than 5 lives.");
                        }
                        Sound.playSound("Mario_Coin.wav");
                        CommandCenter.getInstance().getOpsList().enqueue(movFloater, CollisionOp.Operation.REMOVE);
                    }
                }
            }
        }
    }

    //Method for FOE to shoot bullets
    //This is where shooting happens
    private void checkFoes() {

        for(Movable movFoe : CommandCenter.getInstance().getMovFoes()){
            if(movFoe instanceof Enemy1  && !((Enemy1) movFoe).isOutFrame()){
                Enemy1 myEnemy = (Enemy1) movFoe;
                if(getTick()%33==0){
                    CommandCenter.getInstance().getOpsList().enqueue(new EnemyBullet(myEnemy, 2), CollisionOp.Operation.ADD);
                  }
            }
            if(movFoe instanceof  Enemy2 && !((Enemy2) movFoe).isOutFrame()){
                Enemy2 myEnemy = (Enemy2) movFoe;
                if(getTick()%33 ==0){
                    CommandCenter.getInstance().getOpsList().enqueue(new SmartBullet(myEnemy, CommandCenter.getInstance().getP38()), CollisionOp.Operation.ADD);
                }
            }
            if(movFoe instanceof Turret && !((Turret) movFoe).isOutFrame()){
                Turret myTurret = (Turret)movFoe;
                switch (myTurret.getImgIndex()){
                    case(1):
                        if(getTick()%30==0)
                            CommandCenter.getInstance().getOpsList().enqueue(new EnemyBullet(myTurret, 1), CollisionOp.Operation.ADD);
                        break;
                    case(2):
                        if((getTick()+5)%30==0)
                            CommandCenter.getInstance().getOpsList().enqueue(new EnemyBullet(myTurret, 2), CollisionOp.Operation.ADD);
                        break;
                    case(3):
                        if((getTick()+10)%30==0)
                            CommandCenter.getInstance().getOpsList().enqueue(new EnemyBullet(myTurret, 5), CollisionOp.Operation.ADD);
                        break;
                    case(4):
                        if((getTick()+15)%30==0)
                            CommandCenter.getInstance().getOpsList().enqueue(new EnemyBullet(myTurret, 4), CollisionOp.Operation.ADD);
                        break;
                }
            }
        }
    }


    // Spawn a foes based on their intro interval but limit based on level multiplier
    // Ensure number of foes are proportionate to game level
    private void addFoes() {
        if (CommandCenter.getInstance().getLevel() != 0) {
            if (getTick() == 5 * 22) {
                CommandCenter.getInstance().spawnEnemy1_1(200, -100);
            } else if (getTick() == 10 * 22) {
                CommandCenter.getInstance().spawnEnemy1_1(600, -100);
                //CommandCenter.getInstance().spawnEnemy2(300, -100);
            } else if (getTick() == 15 * 22) {
                CommandCenter.getInstance().spawnEnemy1_1(300, -100);
                CommandCenter.getInstance().spawnPowerUp();
            } else if (getTick() == 20 * 22) {
                CommandCenter.getInstance().spawnEnemy1_2(300, -100);
            } else if (getTick() == 25 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            } else if (getTick() == 30 * 22) {
                CommandCenter.getInstance().spawnEnemy1_1(75, -100);
            } else if (getTick() == 35 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(600, -100);
            } else if (getTick() == 40 * 22) {
                CommandCenter.getInstance().spawnShip(300, -100);
                CommandCenter.getInstance().spawnPowerUp();
            } else if (getTick() == 45 * 22) {
                CommandCenter.getInstance().spawnEnemy2(300, -100);
            } else if (getTick() == 50 * 22) {
                CommandCenter.getInstance().spawnEnemy1_1(75, -100);
                CommandCenter.getInstance().spawnEnemy2(300, -100);
            }else if (getTick() == 55 * 22) {
                CommandCenter.getInstance().spawnEnemy1_1(500, -100);
            }else if (getTick() == 57 * 22) {
                CommandCenter.getInstance().spawnEnemy1_2(75, -100);
            }else if (getTick() == 60 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }else if (getTick() == 65 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
                CommandCenter.getInstance().spawnPowerUp();
                CommandCenter.getInstance().spawnShip(170, -100);
            }else if (getTick() == 70 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }else if (getTick() == 75 * 22) {
                CommandCenter.getInstance().spawnEnemy1_1(600, -100);
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }else if (getTick() == 80 * 22) {
                CommandCenter.getInstance().spawnEnemy1_1(450, -100);
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }else if (getTick() == 85 * 22) {
                CommandCenter.getInstance().spawnEnemy1_1(300, -100);
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }else if (getTick() == 90 * 22) {
                CommandCenter.getInstance().spawnEnemy1_1(150, -100);
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
                CommandCenter.getInstance().spawnPowerUp();
                CommandCenter.getInstance().spawnShip(430, -100);
            }else if (getTick() == 95 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }else if (getTick() == 100 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }else if (getTick() == 105 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }else if (getTick() == 110 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }else if (getTick() == 115 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }else if (getTick() == 120 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
                CommandCenter.getInstance().spawnPowerUp();
            }else if (getTick() == 125 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }else if (getTick() == 130 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
                CommandCenter.getInstance().spawnShip(550, -100);
            }else if (getTick() == 135 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }else if (getTick() == 140 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
                CommandCenter.getInstance().spawnPowerUp();
            }else if (getTick() == 145 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }else if (getTick() == 150 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
                CommandCenter.getInstance().spawnShip(550, -100);
            }else if (getTick() == 155 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }else if (getTick() == 160 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
                CommandCenter.getInstance().spawnPowerUp();
            }else if (getTick() == 165 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }else if (getTick() == 170 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
                CommandCenter.getInstance().spawnShip(350, -100);
            }else if (getTick() == 175 * 22) {
                CommandCenter.getInstance().spawnEnemy1_3(0, -100);
            }
        }
    }

    private void killFoe(Movable myFriend, Movable movFoe) {
        //when a bullet hits a foe
        Bullet1 thisBullet = (Bullet1) myFriend;

		if (movFoe instanceof Enemy1 || movFoe instanceof Enemy2 || movFoe instanceof Turret){
		    //when the foe is a baby fighter jet
            Sound.playSound("kapow.wav");
            movFoe.setDead();
            CommandCenter.getInstance().addScore(movFoe.getWorth());
            CommandCenter.getInstance().getOpsList().enqueue(myFriend, CollisionOp.Operation.REMOVE);
            if(movFoe.getDeadTimeLeft() <=0){
                CommandCenter.getInstance().getOpsList().enqueue(movFoe, CollisionOp.Operation.REMOVE);
            }
		}

		else if(movFoe instanceof Ship){
		    //when the foe is a boss ship
		    Ship thisShip = (Ship) movFoe;
		    if(thisShip.isTurretDestroyed()){
                if(thisShip.getEnergy() ==0){
                    //when the ship is destroyed
                    //Sound.playSound("big explosion/destruction sound" );
                    thisShip.setDead();
                    CommandCenter.getInstance().addScore(thisShip.getWorth());
                    CommandCenter.getInstance().getOpsList().enqueue(myFriend, CollisionOp.Operation.REMOVE);
                    CommandCenter.getInstance().getOpsList().enqueue(movFoe, CollisionOp.Operation.REMOVE);
                }
                else {
                    Sound.playSound("Mario_Block.wav");
                    CommandCenter.getInstance().getOpsList().enqueue(myFriend, CollisionOp.Operation.REMOVE);
                    thisShip.setEnergy(thisBullet.getFirePower());
                }
            }
		    //the ship is not destroyed yet.
		    else{
                System.out.println("not yet");
            }
        }

	}

    private void processQueue() {

        //we are dequeuing the opsList and performing operations in serial to avoid mutating the movable arraylists while iterating them above
        while(!CommandCenter.getInstance().getOpsList().isEmpty()){
            CollisionOp cop =  CommandCenter.getInstance().getOpsList().dequeue();
            Movable mov = cop.getMovable();
            CollisionOp.Operation operation = cop.getOperation();

            switch (mov.getTeam()){
                case FOE:
                    if (operation == CollisionOp.Operation.ADD){
                        CommandCenter.getInstance().getMovFoes().add(mov);
                    } else {
                        CommandCenter.getInstance().getMovFoes().remove(mov);
                    }
                    break;
                case FRIEND:
                    if (operation == CollisionOp.Operation.ADD){
                        CommandCenter.getInstance().getMovFriends().add(mov);
                        //System.out.println("BUlLEt!");
                    } else {
                        CommandCenter.getInstance().getMovFriends().remove(mov);
                    }
                    break;
                case BACKGROUND:
                    if (operation == CollisionOp.Operation.ADD){
                        CommandCenter.getInstance().getMovBackground().add(mov);
                    } else {
                        CommandCenter.getInstance().getMovBackground().remove(mov);
                    }
                    break;
                case PLATFORM:
                    if (operation == CollisionOp.Operation.ADD){
                        CommandCenter.getInstance().getMovPlatform().add(mov);
                    } else {
                        CommandCenter.getInstance().getMovPlatform().remove(mov);
                    }
                    break;
                case FLOATER:
                    if(operation == CollisionOp.Operation.ADD){
                        CommandCenter.getInstance().getMovFloater().add(mov);
                    } else {
                        CommandCenter.getInstance().getMovFloater().remove(mov);
                    }
                    break;
            }
        }
        System.gc();
    }//end meth

	//some methods for timing events in the game, such as the appearance of UFOs, floaters (power-ups), etc.
	public void tick() {
		if (nTick == Integer.MAX_VALUE)
			nTick = 0;
		else
			nTick++;
	}

	public int getTick() {
		return nTick;
	}

	// Called when user presses 's'
	private void startGame() {

		clpMusicBackground.loop(Clip.LOOP_CONTINUOUSLY);
		CommandCenter.getInstance().clearAll();
		CommandCenter.getInstance().initGame();
		CommandCenter.getInstance().setPlaying(true);
		CommandCenter.getInstance().setPaused(false);
		drawBackGround();
        CommandCenter.getInstance().spawnP38(true);
        //CommandCenter.getInstance().spawnMario(true);
    }


    // Varargs for stopping looping-music-clips
	private static void stopLoopingSounds(Clip... clpClips) {
		for (Clip clp : clpClips) {
			clp.stop();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Mario mario = CommandCenter.getInstance().getMario();
        P38 p38 =  CommandCenter.getInstance().getP38();

        int nKey = e.getKeyCode();

		if (nKey == START && !CommandCenter.getInstance().isPlaying())
			startGame();

		if (p38 != null) {
			switch (nKey) {
			case PAUSE:
				CommandCenter.getInstance().setPaused(!CommandCenter.getInstance().isPaused());
				if (CommandCenter.getInstance().isPaused())
					stopLoopingSounds(clpMusicBackground);
				else
					clpMusicBackground.loop(Clip.LOOP_CONTINUOUSLY);
				break;
			case QUIT:
				System.exit(0);
				break;
            case RIGHT:
                if (!CommandCenter.getInstance().getP38().isDead()) {
                    moveRight();
                }
                break;
            case LEFT:
                if (!CommandCenter.getInstance().getP38().isDead()) {
                    moveLeft();
                }
                break;
            case UP:
                if (!CommandCenter.getInstance().getP38().isDead()) {
                    moveUp();
                }
                break;
            case DOWN:
                if (!CommandCenter.getInstance().getP38().isDead()) {
                    moveDown();
                }
                break;
            case SPEED:
                if (!CommandCenter.getInstance().getP38().isDead()) {
                    speedUp();
                }
                break;

			default:
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Mario mario = CommandCenter.getInstance().getMario();
		P38 p38 = CommandCenter.getInstance().getP38();
		int nKey = e.getKeyCode();

		if (p38 != null) {
			switch (nKey) {
            case FIRE:
                if (!CommandCenter.getInstance().getP38().isDead()){
                    CommandCenter.getInstance().getOpsList().enqueue(new Bullet1(p38), CollisionOp.Operation.ADD);
                    Sound.playSound("laser.wav");
                    break;
                }

			case MUTE:
				if (!bMuted){
					stopLoopingSounds(clpMusicBackground);
					bMuted = !bMuted;
				} 
				else {
					clpMusicBackground.loop(Clip.LOOP_CONTINUOUSLY);
					bMuted = !bMuted;
				}
				break;
            case SPEED:
                if (!CommandCenter.getInstance().getP38().isDead()) {
                    speedDown();
                }
                break;
			default:
				break;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

    public boolean checkWithinRange(Point pntObj1Center,  int nObj1Radius, Point pntObj2Center, int nObj2Raidus) {
        if(pntObj1Center.distance(pntObj2Center) < (nObj1Radius+nObj2Raidus)){
            return true;
        }
        else return false;
    }


    // Method to move right when the right arrow key is pressed. If Mario is beyond his screen limit, move everything else to left
    private void moveRight() {
        CommandCenter.getInstance().getP38().moveRight();
    }
    // Similar to moveRight method but this is for moving left.
    private void moveLeft() {
        CommandCenter.getInstance().getP38().moveLeft();
    }
    private void moveUp() {
        CommandCenter.getInstance().getP38().moveUp();
    }
    private void moveDown() {
        CommandCenter.getInstance().getP38().moveDown();
    }
    private void speedUp(){
	    CommandCenter.getInstance().getP38().speedUp();
    }
    private void speedDown(){
        CommandCenter.getInstance().getP38().speedDown();
    }
    private void moveEverythingLeft() {
        CommandCenter.getInstance().setMoveCountX(P38.DEFAULT_HORIZONTAL_STEPS);
        CommandCenter.getInstance().setDeltaX(-CommandCenter.getInstance().getP38().getDeltaMoveRightX());
    }

    private void moveEverythingRight() {
        CommandCenter.getInstance().setMoveCountX(P38.DEFAULT_HORIZONTAL_STEPS);
        CommandCenter.getInstance().setDeltaX(-CommandCenter.getInstance().getP38().getDeltaMoveLeftX());
    }
}

