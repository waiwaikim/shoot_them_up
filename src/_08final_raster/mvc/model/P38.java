package _08final_raster.mvc.model;

import _08final_raster.mvc.controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class P38 extends Sprite {


    private int p38Width = 60;

    private Image P38_1 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_1.png").getImage(),p38Width,p38Width);
    private Image P38_2 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_2.png").getImage(),p38Width,p38Width);
    private Image P38_3 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_3.png").getImage(),p38Width,p38Width);
    private Image P38_4 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_4.png").getImage(),p38Width,p38Width);
    private Image P38_5 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_5.png").getImage(),p38Width,p38Width);
    private Image P38_6 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_6.png").getImage(),p38Width,p38Width);
    private Image P38_7 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_7.png").getImage(),p38Width,p38Width);
    private Image P38_8 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_8.png").getImage(),p38Width,p38Width);
    private Image P38_9 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_9.png").getImage(),p38Width,p38Width);
    private Image P38_10 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_10.png").getImage(),p38Width,p38Width);
    private Image P38_11 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_11.png").getImage(),p38Width,p38Width);
    private Image P38Dead  = getScaledImage(new ImageIcon(Sprite.strImageDir + "p38_dead.png").getImage(),p38Width,p38Width);

    private Image imgP38;
    private boolean bInit = true;
    private int nEnergy = 100;
    private int nP38DeadTimeLeft = 0;

    private int nMoveRightCount = 0;
    private int nMoveLeftCount = 0;
    private int nMoveUpCount = 0;
    private int nMoveDownCount = 0;
    private static int nSpawnLoctionX ;
    private static int nSpawnLoctionY ;



    private int nDeltaY = -4;
    public static final int DEFAULT_HORIZONTAL_SPEED = 9;
    public static final int DEFAULT_VERTICAL_SPEED = 9;
    public static final int DEFAULT_HORIZONTAL_STEPS = 9;
    public static final int DEFAULT_VERTICAL_STEPS= 9;

    //need to change later for vertical movement
    public static final int SCREEN_LEFT_LIMIT = 10;
    public static final int SCREEN_RIGHT_LIMIT = 650;


    public P38(int nCenterX, int nCenterY) {
        super(nCenterX, nCenterY);
        setTeam(Team.FRIEND);
        setCenter(new Point(nCenterX, nCenterY));
        setRadius(25);
        setHeight(50);
        setWidth(50);
        imgP38 = P38_1;
        //setDeltaY(nDeltaY);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(imgP38,getCenter().x,getCenter().y,null);
    }

    @Override
    public void move() {

        setP38Image();

        Point pnt = getCenter();

        if (nMoveRightCount > 0) {
            setCenter(new Point(getCenter().x + DEFAULT_HORIZONTAL_SPEED, getCenter().y));
            nMoveRightCount--;
        } else if (nMoveLeftCount > 0){
            setCenter(new Point(getCenter().x - DEFAULT_HORIZONTAL_SPEED, getCenter().y));
            nMoveLeftCount--;
        }
        else if(nMoveUpCount>0){
            setCenter(new Point(getCenter().x, getCenter().y - DEFAULT_VERTICAL_SPEED));
            nMoveUpCount--;
        }
        else if(nMoveDownCount>0){
            setCenter(new Point(getCenter().x, getCenter().y + DEFAULT_VERTICAL_SPEED));
            nMoveDownCount--;
        }

        if(pnt.x > Game.DIM.width){
            //going over to the right
            setCenter(new Point(Game.DIM.width, pnt.y));
        } else if (pnt.x < 0) {
            //going ove to the left
            setCenter(new Point(1, pnt.y));
        } else if (pnt.y > Game.DIM.height) {
            //going over to the bottom
            setCenter(new Point(pnt.x, Game.DIM.height));
        } else if (pnt.y < 0) {
            //going over to the top
            setCenter(new Point(pnt.x, 1));
        }



    }

    public void stopHorizontalMove(){
        nMoveLeftCount = 0;
        nMoveRightCount = 0;
    }
    public void stopVerticalMove(){
        nMoveUpCount = 0;
        nMoveDownCount = 0;
    }
    public int getMoveRightCount(){return nMoveRightCount;}
    public int getMoveLeftCount(){return nMoveLeftCount; }
    public int getMoveUpCount(){return nMoveUpCount;}
    public int getMoveDownCount(){return nMoveDownCount;}

    public void moveRight(){ nMoveRightCount = DEFAULT_HORIZONTAL_STEPS; }
    public void moveLeft(){ nMoveLeftCount = DEFAULT_HORIZONTAL_STEPS; }
    public void moveNotLeft(){ nMoveLeftCount = 0; }
    public void moveNotRight(){ nMoveRightCount = 0; }
    public void moveUp(){ nMoveUpCount = DEFAULT_VERTICAL_STEPS; }
    public void moveDown(){ nMoveDownCount = DEFAULT_VERTICAL_STEPS; }

    @Override
    public void setDead() {
        //System.out.println("I'm in P38.setDead()");
        super.setDead();
        nP38DeadTimeLeft = 50;
    }

    public void setSpawnLocation(Point lastPoint){
        nSpawnLoctionX = lastPoint.x;
        nSpawnLoctionY = lastPoint.y;
        //System.out.println(nSpawnLoctionX + " " + nSpawnLoctionY);
    }
    public static int getSpawnLocationX(){
        return nSpawnLoctionX;
    }
    public static int getnSpawnLoctionY(){
        return nSpawnLoctionY;
    }

    public int getP38DeadTimeLeft() {
        return nP38DeadTimeLeft;
    }
    public int getDeltaMoveRightX() {
        return DEFAULT_HORIZONTAL_STEPS;
    }
    public int getDeltaMoveLeftX() {
        return -DEFAULT_HORIZONTAL_STEPS;
    }
    public int getDeltaMoveUpX() {
        return -DEFAULT_VERTICAL_STEPS;
    }
    public int getDeltaMoveDownX() {
        return DEFAULT_VERTICAL_STEPS;
    }

    public boolean checkMoving() {
        if (nMoveRightCount > 0 || nMoveLeftCount > 0 || nMoveUpCount>0 || nMoveDownCount>0) {
            return true;
        } else {
            return false;
        }
    }

    private void setP38Image(){
        //System.out.println("is it dead? in setImage: " + this.isDead());

        if(this.isDead()){
            //image of explosion
            imgP38 = P38Dead;
            nP38DeadTimeLeft--;
        }
        else{
            //different images of P38 moving.
        }
    }
}
