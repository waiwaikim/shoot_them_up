package _08final_raster.mvc.model;

import _08final_raster.mvc.controller.Game;
import _08final_raster.mvc.model.CollisionOp;
import _08final_raster.mvc.model.GameOpsList;
import _08final_raster.mvc.model.Movable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class CommandCenter {

    private int nNumP38s;
    private P38 p38;
	private  int nNumMario;
	private  int nLevel;
	private  long lScore;
	private  int nCoins;

	private  Mario mario;
    private  Flag flag;
	private  boolean bPlaying;
	private  boolean bPaused;
    private int nSecondsLeft;
    private long lSysTimeSeconds;
    private boolean bInitPosFlag = false;
    private boolean bLevelClear = false;
    private Point pntFlagCenterTracker;

    private Ground groundFirst, groundLast;

    private Water waterFirst, waterLast;
	
	// These ArrayLists with capacities set
	private List<Movable> movPlatform = new ArrayList<Movable>(300);
	private List<Movable> movFriends = new ArrayList<Movable>(100);
	private List<Movable> movFoes = new ArrayList<>(200);
	private List<Movable> movBackground = new ArrayList<>(100);
	private List<Movable> movFloater = new ArrayList<>(100);

	private GameOpsList opsList = new GameOpsList();

	private static CommandCenter instance = null;

    private int nMoveCountX = 0;
    private int nMoveCountY = 0;
    private int nDeltaX = 0;
    private int nDeltaY = 0;

	// Constructor made private - static Utility class only
	private CommandCenter() {}

	public static CommandCenter getInstance(){
		if (instance == null){
			instance = new CommandCenter();
		}
		return instance;
	}

	public  void initGame(){
		setLevel(1);
		setScore(0);
        //setCoins(0);
		//setNumMarios(5);
		setNumP38s(5);
        nSecondsLeft = 9000;
        lSysTimeSeconds = System.currentTimeMillis()/1000;
	}

    public void setTimeLeft(int nSecondsLeft) {
        this.nSecondsLeft = nSecondsLeft;
    }


	// The parameter is true if this is for the beginning of the game, otherwise false
	public  void spawnMario(boolean bFirst) {
		if (getNumMarios() != 0) {
			mario = new Mario(220,680);
			opsList.enqueue(mario, CollisionOp.Operation.ADD);
			if (!bFirst) {
                setInitPosFlag(true);
                setNumMarios(getNumMarios() - 1);
            }
		}
	}
    // The parameter is true if this is for the beginning of the game, otherwise false
    public  void spawnP38(boolean bFirst) {
        if (getNumP38s() != 0) {

            if(bFirst){
                p38 = new P38(350,700);
                //the spawning location needs to be udpated for respawning; \
                opsList.enqueue(p38, CollisionOp.Operation.ADD);
            }
            else{
                //System.out.println(P38.nSpawnLoctionX +" "  + P38.nSpawnLoctionY);
                p38 = new P38(P38.getSpawnLocationX(), P38.getnSpawnLoctionY()-150);
                opsList.enqueue(p38, CollisionOp.Operation.ADD);
                setInitPosFlag(true);
                setNumP38s(getNumP38s() - 1);
            }

            if (!bFirst) {
            }
        }
    }

	public void spawnGoombas()  {
        switch (nLevel) {
            case 1:
                opsList.enqueue(new Goomba(1040,680), CollisionOp.Operation.ADD);
                break;
            case 2:
                opsList.enqueue(new Goomba(1040,680), CollisionOp.Operation.ADD);
                break;
        }
    }
    public void spawnEnemy1()  {
        opsList.enqueue(new Enemy1(200,100), CollisionOp.Operation.ADD);
    }
    public void spawnEnemy2()  {
        opsList.enqueue(new Enemy2(200,100), CollisionOp.Operation.ADD);
    }
    public void spawnShip()  {

	    int x = 400;
	    int y = -200;

	    //turrets bottom to top
	    Turret turret1 = new Turret(x+4, y+183,2);
	    Turret turret2 = new Turret(x+4, y+160,4);
	    Turret turret3 = new Turret(x+4, y+135,3);
	    Turret turret4 = new Turret(x+4, y+95, 2);
	    Turret turret5 = new Turret(x+4, y+20,1);

        opsList.enqueue(new Ship(x,y, turret1, turret2, turret3, turret4, turret5), CollisionOp.Operation.ADD);
        opsList.enqueue(turret1, CollisionOp.Operation.ADD);
        opsList.enqueue(turret2, CollisionOp.Operation.ADD);
        opsList.enqueue(turret3, CollisionOp.Operation.ADD);
        opsList.enqueue(turret4, CollisionOp.Operation.ADD);
        opsList.enqueue(turret5, CollisionOp.Operation.ADD);


    }
    public void spawnPowerUp(){
	    opsList.enqueue(new PowerUp(300, 500), CollisionOp.Operation.ADD);
    }
    /*public void spawnTurret(){

    }*/
    public void spawnKoopas()  {
        switch (nLevel) {
            case 1:
                opsList.enqueue(new Koopa(1040,664), CollisionOp.Operation.ADD);
                break;
        }
    }

    public void spawnParatroopas()  {
        switch (nLevel) {
            case 2:
                opsList.enqueue(new Paratroopa(1040,664), CollisionOp.Operation.ADD);
                break;
        }
    }

	public GameOpsList getOpsList() {
		return opsList;
	}

	public void setOpsList(GameOpsList opsList) {
		this.opsList = opsList;
	}

	public  void clearAll(){
		movPlatform.clear();
		movFriends.clear();
		movFoes.clear();
		movBackground.clear();
		movFloater.clear();
	}

	public  boolean isPlaying() {
		return bPlaying;
	}

	public  void setPlaying(boolean bPlaying) {
		this.bPlaying = bPlaying;
	}

	public  boolean isPaused() {
		return bPaused;
	}

	public  void setPaused(boolean bPaused) {
		this.bPaused = bPaused;
	}

	public  boolean isGameOver() {		//if the number of Marios is zero or seconds left is zero, then game over
		if ((getNumP38s() == 0  || nLevel > Game.GAME_MAX_LEVEL) && nLevel != 0) {
			return true;
		}
		return false;
	}

	public  int getLevel() {
		return nLevel;
	}

    public void setNextLevel() {
        nLevel++;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public Flag getFlag() {
        return flag;
    }

    public boolean isLevelClear() {
        return bLevelClear;
    }

    public void setLevelClear(boolean bLevelClearStatus) {
        bLevelClear = bLevelClearStatus;
    }

	public   long getScore() {
		return lScore;
	}

    public void setCoins(int nCoins) {
        this.nCoins = nCoins;
    }

    public int getCoins() {
        return nCoins;
    }

    public void incrCoinScore() {
        nCoins++;
    }

	public  void setScore(long lParam) {
		lScore = lParam;
	}

    public  void addScore(long lParam) {
        lScore += lParam;
    }

	public  void setLevel(int n) {
		nLevel = n;
	}

	public  int getNumMarios() {
		return nNumMario;
	}
    public  int getNumP38s() {
        //System.out.println("I'm in CommandCenter.getNumP38s()");
        return nNumP38s;
    }
	public  void setNumMarios(int nParam) {
		nNumMario = nParam;
	}
    public  void setNumP38s(int nParam) {
        nNumP38s = nParam;
    }
    public void incrementNumP38s(){
	    nNumP38s++;
    }
	public  Mario getMario(){
		return mario;
	}
    public P38 getP38(){return p38;}

	public  void setMario(Mario marioParam){
		mario = marioParam;
	}

	public  List<Movable> getMovFriends() {
		return movFriends;
	}

	public  List<Movable> getMovFoes() {
		return movFoes;
	}

	public  List<Movable> getMovBackground() {
		return movBackground;
	}

	public  List<Movable> getMovPlatform() {
		return movPlatform;
	}

	public List<Movable> getMovFloater(){
	    return movFloater;
    }

    public int getMoveCountX() { return nMoveCountX; }
    public int getMoveCountY(){ return nMoveCountY;}

    public void setMoveCountX(int nMoveCount) {
        this.nMoveCountX = nMoveCount;
    }
    public void setMoveCountY(int nMoveCount) {
        this.nMoveCountY = nMoveCount;
    }

    public void decrMoveCountX() {
        nMoveCountX--;
    }
    public void decrMoveCountY() {
        nMoveCountY++;
    }

    public int getDeltaX() {
        return nDeltaX;
    }
    public int getDeltaY() { return nDeltaY; }

    public void setDeltaX(int nDeltaX) {
        this.nDeltaX = nDeltaX;
    }
    public void setDeltaY(int nDeltaY) { this.nDeltaY = nDeltaY; }

    public void setGroundFirst(Ground ground) { groundFirst = ground;}
    public void setGroundLast (Ground ground) {
        groundLast = ground;
    }
    public Ground getGroundFirst() {
        return groundFirst;
    }
    public Ground getGroundLast() {
        return groundLast;
    }

    //water
    public void setWaterFirst(Water water) { waterFirst = water;}
    public void setWaterLast (Water water) {
        waterLast = water;
    }
    public Water getWaterFirst() {
        return waterFirst;
    }
    public Water getWaterLast() {
        return waterLast;
    }

    public int getGameTimeLeft() {
        return nSecondsLeft;
    }

    public void updateTimeLeft() {
        if (lSysTimeSeconds != System.currentTimeMillis()/1000) {
            nSecondsLeft--;
            lSysTimeSeconds = System.currentTimeMillis()/1000;
        }
    }

    public boolean getInitPosFlag() {
        return bInitPosFlag;
    }

    public void setInitPosFlag(boolean bInitPosFlag) {
        this.bInitPosFlag = bInitPosFlag;
    }

    public void setPntFlagCenterTracker(int nCenterX, int nCenterY) {
        pntFlagCenterTracker = new Point(nCenterX,nCenterY);
    }

    public Point getPntFlagCenterTracker() {
        return pntFlagCenterTracker;
    }


}
