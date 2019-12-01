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


    private Image imgEnemy= getScaledImage(new ImageIcon(Sprite.strImageDir + "ship1.png").getImage(), 50, 245);

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
        //setImage();
        setDestroyed();
        super.move();
        if (!bDead) {
            setCenter(new Point(getCenter().x , getCenter().y + getDeltaY()));
            //setDownDirection();
        } else {
            //setCenter(new Point(getCenter().x , getCenter().y ));
            nWorthY+= nWorthDeltaY;
        }
    }

    public void setDead() {
        bDead = true;
        setRadius(0);
        nDeadTimeLeft = 2;
        nWorthY = getCenter().y;
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

}
