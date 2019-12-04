package _08final_raster.mvc.model;

import javax.swing.*;
import java.awt.*;

public class Enemy1 extends Sprite {


    private int HORIZONTAL_SPEED = 3;
    private int VERTICAL_SPEED = 5;
    private int adjustWidth = 50;
    private boolean bDead;

    private int nDeadTimeLeft = 0;
    public static final int WORTH = +100;
    private int nWorthDeltaY = -5;
    private int nWorthY = 0;
    private int nEnemyDirection;

    private Image imgEnemy;
    private Image imgEnemyLive= getScaledImage(new ImageIcon(Sprite.strImageDir + "foe1.png").getImage(), adjustWidth, adjustWidth);

    private Image imgSmoke01 = getScaledImage(new ImageIcon(Sprite.strImageDir + "enemy_smoke_01.png").getImage(), adjustWidth, adjustWidth);
    private Image imgSmoke02 = getScaledImage(new ImageIcon(Sprite.strImageDir + "enemy_smoke_02.png").getImage(), adjustWidth, adjustWidth);
    private Image imgSmoke03 = getScaledImage(new ImageIcon(Sprite.strImageDir + "enemy_smoke_03.png").getImage(), adjustWidth, adjustWidth);
    private Image imgSmoke04 = getScaledImage(new ImageIcon(Sprite.strImageDir + "enemy_smoke_04.png").getImage(), adjustWidth, adjustWidth);
    private Image imgSmoke05 = getScaledImage(new ImageIcon(Sprite.strImageDir + "enemy_smoke_05.png").getImage(), adjustWidth, adjustWidth);

    public Enemy1(int nCenterX, int nCenterY, int nEnemyDirection) {
        super(nCenterX, nCenterY);
        setTeam(Team.FOE);
        setCenter(new Point(nCenterX, nCenterY));

        setRadius(16);
        setHeight(32);
        setWidth(32);
        bDead = false;
        imgEnemy = imgEnemyLive;

        switch(nEnemyDirection){
            case(1):
                VERTICAL_SPEED = 4;
                HORIZONTAL_SPEED = 0;
                break;
            case(2):
                VERTICAL_SPEED = 5;
                HORIZONTAL_SPEED = -1;
                break;
            case(3):
                VERTICAL_SPEED = 5 ;
                HORIZONTAL_SPEED = 1;
                break;

            default:
                VERTICAL_SPEED = 3;
                HORIZONTAL_SPEED = 0;
        }
        setDeltaX(HORIZONTAL_SPEED);
        setDeltaY(VERTICAL_SPEED);
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
            //setCenter(new Point(getCenter().x , getCenter().y ));
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
        setRadius(0);
        nDeadTimeLeft = 25;
        nWorthY = getCenter().y;
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
            else if(nDeadTimeLeft>0 && nDeadTimeLeft<=5)
                imgEnemy = imgSmoke05;

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
