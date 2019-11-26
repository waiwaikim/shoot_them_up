package _08final_raster.mvc.model;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.*;

public class Ship extends Sprite {

    private final int VERTICAL_SPEED = 2;
    private boolean bDead;
    private int nEnergy = 0;

    private int nDeadTimeLeft = 0;
    public static final int WORTH = +500;
    private int nWorthDeltaY = -5;
    private int nWorthY = 0;


    private Image imgEnemy= getScaledImage(new ImageIcon(Sprite.strImageDir + "ship1.png").getImage(), 50, 245);

    public Ship(int nCenterX, int nCenterY) {

        super(nCenterX, nCenterY);
        setTeam(Team.FOE);
        setCenter(new Point(nCenterX, nCenterY));
        setDeltaY(VERTICAL_SPEED);
        setRadius(25);
        setHeight(245);
        setWidth(50);
        bDead = false;
        nEnergy= 1000;
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
    public void setEnergy(int shot){
        //decrement the energy by the fire power of shot
        System.out.print("energy before: " + nEnergy);
        nEnergy -= shot;
        System.out.print("   energy after: "+ nEnergy + "\n");
    }
    public int getEnergy(){
        return nEnergy;
    }

    public boolean isDead() { return bDead; }

    @Override
    public int getDeadTimeLeft() {
        return nDeadTimeLeft;
    }

    public int getWorth() {
        return WORTH;
    }

}
