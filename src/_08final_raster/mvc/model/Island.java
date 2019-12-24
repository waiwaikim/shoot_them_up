package _08final_raster.mvc.model;

import javax.swing.*;
import java.awt.*;

public class Island extends Sprite {


    private int imgAdjust = 100;
    private final int VERTICAL_SPEED = 2;
    private int nCenterX;
    private int nCenterY;

    private Image island_1 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_01.png").getImage(),imgAdjust,imgAdjust);
    private Image island_2 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_02.png").getImage(),imgAdjust,imgAdjust);
    private Image island_3 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_03.png").getImage(),imgAdjust,imgAdjust);
    private Image island_4 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_04.png").getImage(),imgAdjust,imgAdjust);
    private Image island_5 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_05.png").getImage(),imgAdjust,imgAdjust);
    private Image island_6 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_06.png").getImage(),imgAdjust,imgAdjust);
    private Image island_7 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_07.png").getImage(),imgAdjust,imgAdjust);
    private Image island_8 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_08.png").getImage(),imgAdjust,imgAdjust);
    private Image island_9 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_09.png").getImage(),imgAdjust,imgAdjust);
    private Image island_10 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_10.png").getImage(),imgAdjust,imgAdjust);
    private Image island_11 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_11.png").getImage(),imgAdjust,imgAdjust);
    private Image island_12 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_12.png").getImage(),imgAdjust,imgAdjust);
    private Image island_13 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_13.png").getImage(),imgAdjust,imgAdjust);
    private Image island_14 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_14.png").getImage(),imgAdjust,imgAdjust);
    private Image island_15 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_15.png").getImage(),imgAdjust,imgAdjust);
    private Image island_16 = getScaledImage(new ImageIcon(Sprite.strImageDir + "island_16.png").getImage(),imgAdjust,imgAdjust);




    public Island(int nCenterX, int nCenterY) {
        super(nCenterX, nCenterY);
        this.setnCenterX(nCenterX);
        this.setnCenterY(nCenterY);
        //setExpire(1000);
        setDeltaY(VERTICAL_SPEED);
        setTeam(Team.BACKGROUND);
        setCenter(new Point(nCenterX, nCenterY));
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        //first row
        g2d.drawImage(island_1, getCenter().x, getCenter().y,null);
        g2d.drawImage(island_2, getCenter().x + imgAdjust , getCenter().y,null);
        g2d.drawImage(island_3, getCenter().x + (2*imgAdjust), getCenter().y,null);
        g2d.drawImage(island_4, getCenter().x + (3*imgAdjust),  getCenter().y,null);

        //second row
        g2d.drawImage(island_5, getCenter().x, getCenter().y+ imgAdjust,null);
        g2d.drawImage(island_6, getCenter().x + imgAdjust, getCenter().y + imgAdjust,null);
        g2d.drawImage(island_7, getCenter().x + (2*imgAdjust), getCenter().y + imgAdjust,null);
        g2d.drawImage(island_8, getCenter().x + (3*imgAdjust), getCenter().y+ imgAdjust,null);

        //third row
        g2d.drawImage(island_9, getCenter().x  , getCenter().y+ 2*imgAdjust,null);
        g2d.drawImage(island_10, getCenter().x + (1*imgAdjust), getCenter().y+ 2*imgAdjust,null);
        g2d.drawImage(island_11, getCenter().x + (2*imgAdjust),  getCenter().y+ 2*imgAdjust,null);
        g2d.drawImage(island_12, getCenter().x + (3*imgAdjust),  getCenter().y+ 2*imgAdjust,null);

        //fourth row
        g2d.drawImage(island_13, getCenter().x , getCenter().y + 3*imgAdjust,null);
        g2d.drawImage(island_14, getCenter().x + (1*imgAdjust), getCenter().y + 3*imgAdjust,null);
        g2d.drawImage(island_15, getCenter().x + (2*imgAdjust), getCenter().y+ 3*imgAdjust,null);
        g2d.drawImage(island_16, getCenter().x + (3*imgAdjust), getCenter().y + 3*imgAdjust,null);

    }

    @Override
    public void move() {
        super.move();
        setCenter(new Point(getCenter().x , getCenter().y + getDeltaY()));

        /*if (getExpire() == 0)
            CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
        else{
            setExpire(getExpire() - 1);
            setCenter(new Point(getCenter().x , getCenter().y + getDeltaY()));
        }*/
    }

	public int getnCenterY() {
		return nCenterY;
	}

	public void setnCenterY(int nCenterY) {
		this.nCenterY = nCenterY;
	}

	public int getnCenterX() {
		return nCenterX;
	}

	public void setnCenterX(int nCenterX) {
		this.nCenterX = nCenterX;
	}

}
