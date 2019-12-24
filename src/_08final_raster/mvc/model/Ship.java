package _08final_raster.mvc.model;

import javax.swing.*;
import java.awt.*;

public class Ship extends Sprite {

    private final int VERTICAL_SPEED = 1;
    private boolean bDead;
    private boolean bTurretDestroyed;
    private int nEnergy = 0;

    private int nDeadTimeLeft = 0;
    public static final int WORTH = +500;
    private int nWorthDeltaY = -5;
    private int nWorthY = 0;

    private Turret turret1, turret2, turret3, turret4, turret5;

    private Image imgEnemy;
    private Image imgEnemyLive= getScaledImage(new ImageIcon(Sprite.strImageDir + "ship1.png").getImage(), 50, 245);

    private int adjustWidth = 90;
    private Image imgSmoke01 = getScaledImage(new ImageIcon(Sprite.strImageDir + "enemy_smoke_01.png").getImage(), adjustWidth, adjustWidth);
    private Image imgSmoke02 = getScaledImage(new ImageIcon(Sprite.strImageDir + "enemy_smoke_02.png").getImage(), adjustWidth, adjustWidth);
    private Image imgSmoke03 = getScaledImage(new ImageIcon(Sprite.strImageDir + "enemy_smoke_03.png").getImage(), adjustWidth, adjustWidth);
    private Image imgSmoke04 = getScaledImage(new ImageIcon(Sprite.strImageDir + "enemy_smoke_04.png").getImage(), adjustWidth, adjustWidth);
    private Image imgSmoke05 = getScaledImage(new ImageIcon(Sprite.strImageDir + "enemy_smoke_05.png").getImage(), adjustWidth, adjustWidth);



    public Ship(int nCenterX, int nCenterY, Turret turret1, Turret turret2, Turret turret3, Turret turret4, Turret turret5) {

        super(nCenterX, nCenterY);
        setTeam(Team.FOE);
        setCenter(new Point(nCenterX, nCenterY));
        setDeltaY(VERTICAL_SPEED);
        setRadius(25);
        setHeight(245);
        setWidth(50);
        bDead = false;
        bTurretDestroyed = false;
        imgEnemy = imgEnemyLive;
        nEnergy= 1000;
        this.turret1 = turret1;
        this.turret2 = turret2;
        this.turret3 = turret3;
        this.turret4 = turret4;
        this.turret5 = turret5;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(imgEnemy, getCenter().x, getCenter().y,null);
        if (bDead) {
            //g2d.drawString(String.format("%03d",WORTH), getCenter().x,nWorthY);
        }
    }

    @Override
    public void move() {
        setImage();
        setDestroyed();
        super.move();
        if (!bDead) {
            setCenter(new Point(getCenter().x , getCenter().y + getDeltaY()));
        }
        //else {
        //    nWorthY+= nWorthDeltaY;
        //}
    }

    public void setDead() {
        bDead = true;
        setRadius(0);
        nDeadTimeLeft = 25;
        //nWorthY = getCenter().y;
    }
    public void setDestroyed(){
        if(turret1.isDead() && turret2.isDead() && turret3.isDead() && turret4.isDead() && turret5.isDead()){
            bTurretDestroyed = true;
        }
    }
    public void setEnergy(int shot){
        //decrement the energy by the fire power of shot
        if(bTurretDestroyed) {
            nEnergy -= shot;
        }
    }

    private void setImage() {
        if (this.isDead()) {
            if(nDeadTimeLeft>20)
                imgEnemy = imgSmoke01;
            else if(nDeadTimeLeft>15 && nDeadTimeLeft<=20)
                imgEnemy = imgSmoke02;
            else if(nDeadTimeLeft>10 && nDeadTimeLeft<=15)
                imgEnemy = imgSmoke03;
            else if(nDeadTimeLeft>5 && nDeadTimeLeft<=10)
                imgEnemy = imgSmoke04;
            else if(nDeadTimeLeft>1 && nDeadTimeLeft<=5)
                imgEnemy = imgSmoke05;
            else if(nDeadTimeLeft ==0)
                CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
            nDeadTimeLeft--;
        }
        else {

            imgEnemy = imgEnemyLive;
        }
    }


    public int getEnergy(){
        return nEnergy;
    }

    public boolean isDead() { return bDead; }

    public boolean isTurretDestroyed() {
        return bTurretDestroyed;
    }

    @Override
    public int getDeadTimeLeft() {
        return nDeadTimeLeft;
    }

    public int getWorth() {
        return WORTH;
    }

	public int getnWorthDeltaY() {
		return nWorthDeltaY;
	}

	public void setnWorthDeltaY(int nWorthDeltaY) {
		this.nWorthDeltaY = nWorthDeltaY;
	}

	public int getnWorthY() {
		return nWorthY;
	}

	public void setnWorthY(int nWorthY) {
		this.nWorthY = nWorthY;
	}

}
