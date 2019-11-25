package _08final_raster.mvc.model;

import javax.swing.*;
import java.awt.*;

public class Enemy1 extends Sprite {


    private final int HORIZONTAL_SPEED = 0;
    private final int VERTICAL_SPEED = 4;
    private int adjustWidth = 50;
    private boolean bDead;

    private int nDeadTimeLeft = 0;
    public static final int WORTH = +100;
    private int nWorthDeltaY = -5;
    private int nWorthY = 0;


    private Image imgEnemy = getScaledImage(new ImageIcon(Sprite.strImageDir + "foe1.png").getImage(), adjustWidth, adjustWidth);
    private Image imgEnemyExploded = getScaledImage(new ImageIcon(Sprite.strImageDir + "explode.gif").getImage(), adjustWidth, adjustWidth);

    public Enemy1(int nCenterX, int nCenterY) {
        super(nCenterX, nCenterY);
        setTeam(Team.FOE);
        setCenter(new Point(nCenterX, nCenterY));
        setHeight(32);
        setWidth(32);
        bDead = false;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(imgEnemy, getCenter().x,getCenter().y,null);

        /*if (bDead) {
            g2d.drawString(String.format("%03d",WORTH), getCenter().x,nWorthY);
        }*/
    }

    @Override
    public void move() {
        setImage();
        if (!bDead) {
            super.move();
            setCenter(new Point(getCenter().x + getDeltaX(), getCenter().y));
        } else {
            nWorthY+= nWorthDeltaY;
        }
    }
    @Override
    public void setLeftDirection() { setDeltaX(-HORIZONTAL_SPEED);}

    @Override
    public void setRightDirection() { setDeltaX(HORIZONTAL_SPEED);}

    @Override
    public void setUpDirection() { setDeltaY(-VERTICAL_SPEED);}

    @Override
    public void setDownDirection() {setDeltaY(VERTICAL_SPEED); }

    @Override
    public void setDead() {
        bDead = true;
        nDeadTimeLeft = 10;
        nWorthY = getCenter().y;
    }

    @Override
    public boolean isDead() { return bDead; }

    private void setImage() {
        if (bDead) {
            imgEnemy= imgEnemyExploded;
            if (nDeadTimeLeft == 10) {
                //what is nDeadTimeLeft do?
                setCenter(new Point(getCenter().x, getCenter().y + 16));
            }
            nDeadTimeLeft--;
        }
    }

    @Override
    public int getDeadTimeLeft() {
        return nDeadTimeLeft;
    }

    public int getWorth() {
        return WORTH;
    }

}
