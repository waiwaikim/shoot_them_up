package _08final_raster.mvc.model;

import javax.swing.*;
import java.awt.*;

public class SmartBullet extends Sprite {

    private int adjustWidth = 15;
    private P38 p38;
    private static final double PULL = 1.0;
    private final int VERTICAL_SPEED = 4;
    private final int HORIZONTAL_SPEED = 4;

    private Image imgSmartBullet= getScaledImage(new ImageIcon(Sprite.strImageDir + "enemySmartBullet.png").getImage(), adjustWidth, adjustWidth);

    public SmartBullet(Sprite enemy, P38 p38) {
        super(enemy.getCenter().x, enemy.getCenter().y);
        setTeam(Team.FOE);
        this.p38 = p38;
        setExpire(66);
        setRadius(10);
        setDeltaX(enemy.getDeltaX()+HORIZONTAL_SPEED);
        setDeltaY(enemy.getDeltaY()+VERTICAL_SPEED);
    }
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(imgSmartBullet, getCenter().x,getCenter().y,null);
    }
    @Override
    public void move() {
        super.move();

        Point myCenter = getCenter();
        Point p38Center = p38.getCenter();

        if (getExpire() == 0){
            CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
        }
        else {
            setExpire(getExpire() - 1);
            if (myCenter.x > p38Center.x){
                //when p38 is left of the bullet
                setDeltaX((int) (getDeltaX() - PULL));
                setCenter(new Point(getCenter().x +getDeltaX() , getCenter().y + getDeltaY()));
            } else {
                setDeltaX((int) (getDeltaX() +PULL));
                setCenter(new Point(getCenter().x +getDeltaX() , getCenter().y + getDeltaY()));
            }

            if (myCenter.y > p38Center.y){
                setDeltaY((int) (getDeltaY() -PULL));
                setCenter(new Point(getCenter().x +getDeltaX() , getCenter().y + getDeltaY()));
            } else {
                setDeltaY((int) (getDeltaY() +PULL));
                setCenter(new Point(getCenter().x +getDeltaX() , getCenter().y + getDeltaY()));
            }
        }
    }
}
