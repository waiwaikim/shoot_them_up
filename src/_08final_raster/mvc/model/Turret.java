package _08final_raster.mvc.model;

import javax.swing.*;
import java.awt.*;

public class Turret extends Sprite  {

    private final int VERTICAL_SPEED = 1;
    private boolean bDead;

    private int nDeadTimeLeft = 0;
    public static final int WORTH = +200;
    private int nWorthDeltaY = -5;
    private int nWorthY = 0;
    private int nImgIndex;



    private Image imgTurret1= getScaledImage(new ImageIcon(Sprite.strImageDir + "turret_01.png").getImage(), 25, 25);
    private Image imgTurret2= getScaledImage(new ImageIcon(Sprite.strImageDir + "turret_02.png").getImage(), 25, 25);
    private Image imgTurret3= getScaledImage(new ImageIcon(Sprite.strImageDir + "turret_03.png").getImage(), 25, 25);
    private Image imgTurret4= getScaledImage(new ImageIcon(Sprite.strImageDir + "turret_04.png").getImage(), 25, 25);


    public Turret(int nCenterX, int nCenterY, int nImgIndex) {
        super(nCenterX, nCenterY);
        setTeam(Team.FOE);
        setCenter(new Point(nCenterX, nCenterY));
        setDeltaY(VERTICAL_SPEED);
        setRadius(15);
        setHeight(25);
        setWidth(25);
        bDead = false;
        this.nImgIndex = nImgIndex;
    }
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        switch (nImgIndex){
            case(1):
                g2d.drawImage(imgTurret1, getCenter().x, getCenter().y,null);
                break;
            case(2):
                g2d.drawImage(imgTurret2, getCenter().x, getCenter().y,null);
                break;
            case(3):
                g2d.drawImage(imgTurret3, getCenter().x, getCenter().y,null);
                break;
            case(4):
                g2d.drawImage(imgTurret4, getCenter().x, getCenter().y,null);
                break;
            default:
                g2d.drawImage(imgTurret1, getCenter().x, getCenter().y,null);
        }
        if (bDead) {
        }
    }

    @Override
    public void move() {
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
        nDeadTimeLeft = 2;
        //nWorthY = getCenter().y;
    }

    public boolean isDead() { return bDead; }

    @Override
    public int getDeadTimeLeft() {
        return nDeadTimeLeft;
    }

    public int getWorth() {
        return WORTH;
    }

    public int getImgIndex(){return nImgIndex;}

}
