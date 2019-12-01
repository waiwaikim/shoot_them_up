package _08final_raster.mvc.controller;

import _08final_raster.mvc.model.*;
import _08final_raster.mvc.view.GamePanel;
import _08final_raster.sounds.Sound;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EnumMap;
import java.util.Random;

import static java.awt.event.KeyEvent.VK_N;

/**
 *
 *  Date        Author      Description
 *  ----        ------      -----------
 *  06/05/16    Moison      Extended code to implement first two levels of Mario
 */
public class Game implements Runnable, KeyListener {

	// FIELDS
	public static final Dimension DIM = new Dimension(700, 1000); //the dimension of the game.
	private GamePanel gmpPanel;
	public static Random R = new Random();
	public final static int ANI_DELAY = 45; // milliseconds between screen
											// updates (animation)

    public static final int GAME_MAX_LEVEL = 2; // Define max number of levels to determine if game is over.
	private Thread thrAnim;
	private int nTick = 0;
    private final int FOE_LEVEL_MULTIPLIER = 4; //Number of foes active based on level.

    private final int GOOMBA_INTRO_INTERVAL = 4500; // Milliseconds
    private final int SHIP_INTRO_INTERVAL = 8000;
    private final int KOOPA_INTRO_INTERVAL = 12000; // Milliseconds
    private final int PARATROOPA_INTRO_INTERVAL = 12000; // Milliseconds
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
			MUTE = 77; // m-key mute

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

		while (Thread.currentThread() == thrAnim) {

			tick();

            // Check items in order of priority
            checkGameOver();
            checkLevelClear();
            checkHorizontalCollision();
            checkExpiringCoins();
            checkP38Dead();
			gmpPanel.update(gmpPanel.getGraphics());
            checkCollision();
            checkClouds();
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
        if (CommandCenter.getInstance().isLevelClear() && CommandCenter.getInstance().getFlag().getCenter().y > 650) {
            setNextLevel();
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

        Ground ground;
        Water water;
        Score score;

        //Add first water
        water = new Water(0, -600);
        CommandCenter.getInstance().getOpsList().enqueue(water, CollisionOp.Operation.ADD);
        CommandCenter.getInstance().setWaterFirst(water);

        //decrement water placement by 1600
        for(int i=0; i<10; i++){
            CommandCenter.getInstance().getOpsList().enqueue(new Water(0, -2200-(1600*i)), CollisionOp.Operation.ADD);
        }

        //last water
        //water = new Water(0, -8600);
        //CommandCenter.getInstance().getOpsList().enqueue(water, CollisionOp.Operation.ADD);
        //CommandCenter.getInstance().setWaterLast(water);

        //Add Island
        CommandCenter.getInstance().getOpsList().enqueue(new Island(DIM.width- 400,-400), CollisionOp.Operation.ADD);
        CommandCenter.getInstance().getOpsList().enqueue(new Island(DIM.width- 600,-4000), CollisionOp.Operation.ADD);

        score = new Score(0, 0);
        CommandCenter.getInstance().getOpsList().enqueue(score, CollisionOp.Operation.ADD);

    }


    // Method to draw the game level components
    private void drawLevelGame() {
        Flag flag;
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
                if(checkWithinRange(pntFriendCenter, nFriendRadius, pntFoeCenter,nFoeRadius)){

                    //P38
                    if((movFriend instanceof  P38)){
                        P38 myPlane = (P38) movFriend;

                        //when P38 and enemy plane1 collides or when P38 and enemy bullets hits
                        if(movFoe instanceof Enemy1 || movFoe instanceof EnemyBullet){
                            if (!myPlane.isDead()){
                                stopLoopingSounds(clpMusicBackground);
                                Sound.playSound("Mario_die.wav");
                                movFriend.setDead();
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
                if(pntP38Center.distance(pntFloaterCenter) < (nFloaterRadius + nP38Radius)){

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
    // Check for auto-expiring coins from the question block
    private void checkExpiringCoins() {
        if (CommandCenter.getInstance().getMario() != null) {
            for (Movable movFriend : CommandCenter.getInstance().getMovFriends()) {
                if (movFriend instanceof ExpiringCoin) {
                    if (((ExpiringCoin) movFriend).getExpiryCounter() > 0) {
                        ((ExpiringCoin) movFriend).decrExpiryCounter();
                    } else {
                        CommandCenter.getInstance().getOpsList().enqueue(movFriend, CollisionOp.Operation.REMOVE);
                    }
                }
            }
        }
    }

    // Check for horizontal collision with pipe or block
    private void checkHorizontalCollision() {

        if (CommandCenter.getInstance().getMario() != null) {
            Mario mario = CommandCenter.getInstance().getMario();

            int nMarioWidth = mario.getWidth();
            int nMarioCenterX = mario.getCenter().x;
            int nMarioCenterY = mario.getCenter().y;

            for (Movable movPlatform : CommandCenter.getInstance().getMovPlatform()) {
                if (((movPlatform instanceof Pipe || movPlatform instanceof Block)
                        && nMarioCenterY > movPlatform.getCenter().y
                        && ((movPlatform.getCenter().x + movPlatform.getWidth() < nMarioCenterX
                        && movPlatform.getCenter().x + movPlatform.getWidth() > nMarioCenterX + mario.getDeltaMoveLeftX()
                        && mario.getMoveLeftCount() > 0 )
                        || (nMarioCenterX + nMarioWidth < movPlatform.getCenter().x
                        && nMarioCenterX + nMarioWidth + mario.getDeltaMoveRightX() > movPlatform.getCenter().x
                        && mario.getMoveRightCount() > 0))
                        && mario.checkMarioMoving())) {
                    mario.stopMarioHorizontalMove();
                }
            }
        }
    }

    // Method to check if cloud is out of screen, then remove and add a replacement for the same type
    private void checkClouds() {
        for (Movable movBackground : CommandCenter.getInstance().getMovBackground()) {
            if (movBackground instanceof Cloud1 && (movBackground.getCenter().x + movBackground.getWidth() + 100 < 0)) {
                CommandCenter.getInstance().getOpsList().enqueue(movBackground, CollisionOp.Operation.REMOVE);
                CommandCenter.getInstance().getOpsList().enqueue(new Cloud1(DIM.width + 20,200), CollisionOp.Operation.ADD);
            }
            if (movBackground instanceof Cloud2 && (movBackground.getCenter().x + movBackground.getWidth() + 100 < 0)) {
                CommandCenter.getInstance().getOpsList().enqueue(movBackground, CollisionOp.Operation.REMOVE);
                CommandCenter.getInstance().getOpsList().enqueue(new Cloud2(DIM.width + 20,150), CollisionOp.Operation.ADD);
            }
            if (movBackground instanceof Cloud3 && (movBackground.getCenter().x + movBackground.getWidth() + 200 < 0)) {
                CommandCenter.getInstance().getOpsList().enqueue(movBackground, CollisionOp.Operation.REMOVE);
                CommandCenter.getInstance().getOpsList().enqueue(new Cloud3(DIM.width + 20,100), CollisionOp.Operation.ADD);
            }
        }
    }


    //Method for FOES to shoot bullets
    private void checkFoes() {

        for(Movable movFoe : CommandCenter.getInstance().getMovFoes()){
            if(movFoe instanceof Enemy1 ){
                Enemy1 myEnemy = (Enemy1) movFoe;
                if(getTick()%10==0){
                    CommandCenter.getInstance().getOpsList().enqueue(new EnemyBullet(myEnemy), CollisionOp.Operation.ADD);
                }
            }
        }
    }


    // Spawn a foes based on their intro interval but limit based on level multiplier
    // Ensure number of foes are proportionate to game level
    private void addFoes() {
        if (CommandCenter.getInstance().getLevel() != 0) {
            if (getTick() % (GOOMBA_INTRO_INTERVAL /ANI_DELAY/ CommandCenter.getInstance().getLevel()) == 0) {
                CommandCenter.getInstance().spawnEnemy1();
                CommandCenter.getInstance().spawnPowerUp();
            }

            if(getTick()%300 == 0){
                CommandCenter.getInstance().spawnShip();
            }
        }
    }

    private void killFoe(Movable myFriend, Movable movFoe) {
        //when a bullet hits a foe
        Bullet1 thisBullet = (Bullet1) myFriend;

		if (movFoe instanceof Enemy1 || movFoe instanceof Turret){
		    //when the foe is a baby fighter jet
            Sound.playSound("kapow.wav");
            movFoe.setDead();
            CommandCenter.getInstance().addScore(movFoe.getWorth());
            CommandCenter.getInstance().getOpsList().enqueue(myFriend, CollisionOp.Operation.REMOVE);
            CommandCenter.getInstance().getOpsList().enqueue(movFoe, CollisionOp.Operation.REMOVE);

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
        drawLevelGame();
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

    /*public boolean checkWithinRange(int nObj1Center, int nObj1Length, int nObj2Center, int nObj2Length) {
        if ((nObj1Center + nObj1Length >= nObj2Center && nObj1Center <= nObj2Center)
                || (nObj2Center + nObj2Length >= nObj1Center && nObj2Center <= nObj1Center)) {
            return true;
        } else {
            return false;
        }
    }*/

    // Method to move right when the right arrow key is pressed. If Mario is beyond his screen limit, move everything else to left
    private void moveRight() {
        if (CommandCenter.getInstance().getP38().getCenter().getX() > P38.SCREEN_RIGHT_LIMIT) {
            CommandCenter.getInstance().getP38().moveNotRight();
        } else {
            CommandCenter.getInstance().getP38().moveRight();
        }
    }

    // Similar to moveRight method but this is for moving left.
    private void moveLeft() {
        if (CommandCenter.getInstance().getP38().getCenter().getX() < P38.SCREEN_LEFT_LIMIT) {
            CommandCenter.getInstance().getP38().moveNotLeft();
        } else {
            CommandCenter.getInstance().getP38().moveLeft();
        }
    }
    private void moveUp() {
        if (CommandCenter.getInstance().getP38().getCenter().getX() < P38.SCREEN_LEFT_LIMIT) {
            /*----------------------- NEED TO UPDATE -----------------------*/
            moveEverythingRight();
        } else {
            CommandCenter.getInstance().getP38().moveUp();
        }
    }
    private void moveDown() {
        if (CommandCenter.getInstance().getP38().getCenter().getX() < P38.SCREEN_LEFT_LIMIT) {
            /*----------------------- NEED TO UPDATE -----------------------*/
            moveEverythingRight();
        } else {
            CommandCenter.getInstance().getP38().moveDown();
        }
    }

    private void moveEverythingLeft() {
        CommandCenter.getInstance().setMoveCountX(P38.DEFAULT_HORIZONTAL_STEPS);
        CommandCenter.getInstance().setDeltaX(-CommandCenter.getInstance().getP38().getDeltaMoveRightX());
    }

    private void moveEverythingRight() {
        CommandCenter.getInstance().setMoveCountX(P38.DEFAULT_HORIZONTAL_STEPS);
        CommandCenter.getInstance().setDeltaX(-CommandCenter.getInstance().getP38().getDeltaMoveLeftX());
    }


    // Method to increment to next level of the game.
    private void setNextLevel() {
            CommandCenter.getInstance().setLevelClear(false);
            CommandCenter.getInstance().setNextLevel();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (CommandCenter.getInstance().getLevel()) {
                case 2:
                    clpMusicBackground = Sound.clipForLoopFactory("Mario_Birabuto_Kingdom.wav");
                    break;
                default:
                    clpMusicBackground = Sound.clipForLoopFactory("Mario_background.wav");
                    break;
            }
            clpMusicBackground.loop(Clip.LOOP_CONTINUOUSLY);
            CommandCenter.getInstance().clearAll();
            CommandCenter.getInstance().setNumMarios(5);
            CommandCenter.getInstance().setCoins(0);
            drawBackGround();
            drawLevelGame();

            CommandCenter.getInstance().spawnMario(true);
            CommandCenter.getInstance().setTimeLeft(300);

        }

}

