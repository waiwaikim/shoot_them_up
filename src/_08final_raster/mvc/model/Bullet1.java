package _08final_raster.mvc.model;

import javax.swing.*;
import java.awt.*;

public class Bullet1 extends Sprite {

    private int FIRE_POWER = 0;
    private int adjustWidth = 15;
    private final int VERTICAL_SPEED = -15;
    private Image imgBullet1= getScaledImage(new ImageIcon(Sprite.strImageDir + "weapon_1.png").getImage(), adjustWidth, adjustWidth);

    public Bullet1(P38 p38){
        super(p38.getCenter().x, p38.getCenter().y);
        setTeam(Team.FRIEND);

        FIRE_POWER = 100;
        setExpire(40);
        setRadius(10);
        setHeight(5);
        setWidth(14);

        //everything is relative to P38 that fired the bullet

        setDeltaX(p38.getDeltaX());
        setDeltaY(p38.getDeltaY()+VERTICAL_SPEED);

    }
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(imgBullet1,getCenter().x,getCenter().y,null);
    }


    @Override
    public void move() {
        super.move();

        if (getExpire() == 0)
            CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
        else
            setExpire(getExpire() - 1);
            setCenter(new Point(getCenter().x , getCenter().y + getDeltaY()));
    }

    public void setFirePower(int nPower){ FIRE_POWER = nPower; }
    public int getFirePower(){ return FIRE_POWER;}
}
