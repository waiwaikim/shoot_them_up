package _08final_raster.mvc.model;

import _08final_raster.mvc.controller.Game;

import javax.swing.*;
import java.awt.*;

public class P38 extends Sprite {


    private int p38Width = 60;

    private Image P38_1 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_1.png").getImage(),p38Width,p38Width);
    private Image P38_3 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_3.png").getImage(),p38Width,p38Width);
    private Image P38_4 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_4.png").getImage(),p38Width,p38Width);
    private Image P38_5 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_5.png").getImage(),p38Width,p38Width);
    private Image P38_9 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_9.png").getImage(),p38Width,p38Width);
    private Image P38_10 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_10.png").getImage(),p38Width,p38Width);
    private Image P38_11 = getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_11.png").getImage(),p38Width,p38Width);
 
    private Image P38Dead1  = getScaledImage(new ImageIcon(Sprite.strImageDir + "explode_01.png").getImage(),p38Width,p38Width);
    private Image P38Dead2  = getScaledImage(new ImageIcon(Sprite.strImageDir + "explode_02.png").getImage(),p38Width,p38Width);
    private Image P38Dead3  = getScaledImage(new ImageIcon(Sprite.strImageDir + "explode_03.png").getImage(),p38Width,p38Width);
    private Image P38Dead4  = getScaledImage(new ImageIcon(Sprite.strImageDir + "explode_04.png").getImage(),p38Width,p38Width);
    private Image P38Dead5  = getScaledImage(new ImageIcon(Sprite.strImageDir + "explode_05.png").getImage(),p38Width,p38Width);


    private Image imgP38;
    private int nP38DeadTimeLeft = 0;

    private int nMoveRightCount = 0;
    private int nMoveLeftCount = 0;
    private int nMoveUpCount = 0;
    private int nMoveDownCount = 0;
    private static int nSpawnLoctionX ;
    private static int nSpawnLoctionY ;



    public static int DEFAULT_HORIZONTAL_SPEED = 7;
    public static int DEFAULT_VERTICAL_SPEED = 7;
    public static int DEFAULT_HORIZONTAL_STEPS = 7;
    public static int DEFAULT_VERTICAL_STEPS= 7;

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

        if(isDead()){
            setCenter(new Point(pnt.x, pnt.y));

        }
        else if (nMoveRightCount>0) {
            //moving right
            if(pnt.x+this.p38Width+1 > Game.DIM.width){
                setCenter(new Point(Game.DIM.width-p38Width, pnt.y));
            }
            else{
                setCenter(new Point(getCenter().x + DEFAULT_HORIZONTAL_SPEED, getCenter().y));
            }
            nMoveRightCount--;
        } else if (nMoveLeftCount > 0){
            //moving left
            if(pnt.x - 3 < 0){
                setCenter(new Point(1, pnt.y));
            }
            else{
                setCenter(new Point(getCenter().x - DEFAULT_HORIZONTAL_SPEED, getCenter().y));
            }
            nMoveLeftCount--;
        }
        else if(nMoveUpCount>0){
            //moving up
            if(pnt.y - 3< 0){
                setCenter(new Point(pnt.x, 1));
            }
            else{
                setCenter(new Point(getCenter().x, getCenter().y - DEFAULT_VERTICAL_SPEED));
            }
            nMoveUpCount--;
        }
        else if(nMoveDownCount>0 ){
            //moving down
            if ((pnt.y + p38Width+1) > Game.DIM.height){
                setCenter(new Point(pnt.x, Game.DIM.height - p38Width));
            }
            else{
                setCenter(new Point(getCenter().x, getCenter().y + DEFAULT_VERTICAL_SPEED));
            }
            nMoveDownCount--;
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
        nP38DeadTimeLeft = 40;
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

    public void speedUp(){
        DEFAULT_HORIZONTAL_SPEED = 15;
        DEFAULT_VERTICAL_SPEED = 15;
        DEFAULT_HORIZONTAL_STEPS = 15;
        DEFAULT_VERTICAL_STEPS= 15;
    }
    public void speedDown(){
        DEFAULT_HORIZONTAL_SPEED = 7;
        DEFAULT_VERTICAL_SPEED = 7;
        DEFAULT_HORIZONTAL_STEPS = 7;
        DEFAULT_VERTICAL_STEPS= 7;
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
            //imgP38 = P38Dead;
            if(nP38DeadTimeLeft > 34)
                imgP38 = P38Dead1;
            else if(nP38DeadTimeLeft>28 && nP38DeadTimeLeft<=34)
                imgP38 = P38Dead2;
            else if(nP38DeadTimeLeft>22 && nP38DeadTimeLeft<=28)
                imgP38 = P38Dead3;
            else if(nP38DeadTimeLeft>16 && nP38DeadTimeLeft<=22)
                imgP38 = P38Dead4;
            else if(nP38DeadTimeLeft>10 && nP38DeadTimeLeft<=16)
                imgP38 = P38Dead5;
            else if(nP38DeadTimeLeft>0&& nP38DeadTimeLeft<=10)
                imgP38 = P38Dead5;

            nP38DeadTimeLeft--;
        }
        else if(nMoveRightCount > 0 && nMoveRightCount /3 == 0 || (CommandCenter.getInstance().getMoveCountX()/3 == 1 && CommandCenter.getInstance().getDeltaX() < 0) ){
            imgP38 = P38_3;
        } else if (nMoveRightCount /3 == 1 || (CommandCenter.getInstance().getMoveCountX()/3 == 2 && CommandCenter.getInstance().getDeltaX() < 0)) {
            imgP38 = P38_4;
        } else if (nMoveRightCount /3 == 2 || (CommandCenter.getInstance().getMoveCountX()/3 == 3 && CommandCenter.getInstance().getDeltaX() < 0)) {
            imgP38 = P38_5;
        } else if (nMoveLeftCount > 0 && nMoveLeftCount /3 == 0 || (CommandCenter.getInstance().getMoveCountX()/3 == 1 && CommandCenter.getInstance().getDeltaX() > 0)) {
            imgP38 = P38_11;
        } else if (nMoveLeftCount /3 == 1 || (CommandCenter.getInstance().getMoveCountX()/3 == 2 && CommandCenter.getInstance().getDeltaX() > 0)) {
            imgP38 = P38_10;
        } else if (nMoveLeftCount /3 == 2 || (CommandCenter.getInstance().getMoveCountX()/3 == 3 && CommandCenter.getInstance().getDeltaX() > 0)) {
            imgP38 = P38_9;
        } else {
            imgP38 = P38_1;
        }
    }
}
