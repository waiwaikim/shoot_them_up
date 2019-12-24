package _08final_raster.mvc.model;

import javax.swing.*;
import java.awt.*;

public class Enemy2 extends Sprite {


    private final int HORIZONTAL_SPEED = 0;
    private final int VERTICAL_SPEED = 1;
    private int adjustWidth = 80;
    private boolean bDead;
    private int nDeadTimeLeft = 0;
    public static final int WORTH = +200;
    private Image imgEnemy;
    private Image imgEnemyLive= getScaledImage(new ImageIcon(Sprite.strImageDir + "foe2.png").getImage(), adjustWidth, adjustWidth);


    private Image imgSmoke01 = getScaledImage(new ImageIcon(Sprite.strImageDir + "enemy_smoke_01.png").getImage(), adjustWidth, adjustWidth);
    private Image imgSmoke02 = getScaledImage(new ImageIcon(Sprite.strImageDir + "enemy_smoke_02.png").getImage(), adjustWidth, adjustWidth);
    private Image imgSmoke03 = getScaledImage(new ImageIcon(Sprite.strImageDir + "enemy_smoke_03.png").getImage(), adjustWidth, adjustWidth);
    private Image imgSmoke04 = getScaledImage(new ImageIcon(Sprite.strImageDir + "enemy_smoke_04.png").getImage(), adjustWidth, adjustWidth);
    private Image imgSmoke05 = getScaledImage(new ImageIcon(Sprite.strImageDir + "enemy_smoke_05.png").getImage(), adjustWidth, adjustWidth);


    public Enemy2(int nCenterX, int nCenterY) {
        super(nCenterX, nCenterY);
        setTeam(Team.FOE);
        setCenter(new Point(nCenterX, nCenterY));
        setDeltaY(VERTICAL_SPEED);
        setDeltaX(HORIZONTAL_SPEED);
        setRadius(16);
        setHeight(32);
        setWidth(32);
        bDead = false;
        imgEnemy = imgEnemyLive;
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
        super.move();
        if (!bDead) {
            setCenter(new Point(getCenter().x + getDeltaX() , getCenter().y + getDeltaY()));
            //setDownDirection();
        } else {
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
        setRadius(0);
        nDeadTimeLeft = 25;
       
    }

    @Override
    public boolean isDead() { return bDead; }

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

    @Override
    public int getDeadTimeLeft() {
        return nDeadTimeLeft;
    }

    public int getWorth() {
        return WORTH;
    }

}
