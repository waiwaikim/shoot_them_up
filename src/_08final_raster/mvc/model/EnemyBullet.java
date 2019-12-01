package _08final_raster.mvc.model;

import javax.swing.*;
import java.awt.*;

public class EnemyBullet extends Sprite {

    private int adjustWidth = 15;
    private Image imgEnemyBullet= getScaledImage(new ImageIcon(Sprite.strImageDir + "enemyBullet.png").getImage(), 7, 20);
    private final int VERTICAL_SPEED = 10;


    public EnemyBullet(Enemy1 enemy1) {
        super(enemy1.getCenter().x, enemy1.getCenter().y);
        setTeam(Team.FOE);

        setExpire(40);
        setRadius(10);
        setHeight(5);
        setWidth(14);
        setDeltaX(enemy1.getDeltaX());
        setDeltaY(enemy1.getDeltaY()+ VERTICAL_SPEED);
    }
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(imgEnemyBullet,getCenter().x,getCenter().y,null);
    }

    @Override
    public void move() {
        super.move();

        if (getExpire() == 0){
            CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
        }
        else {
            setExpire(getExpire() - 1);
            setCenter(new Point(getCenter().x, getCenter().y + getDeltaY()));
        }
    }
}
