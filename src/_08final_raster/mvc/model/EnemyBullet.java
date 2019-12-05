package _08final_raster.mvc.model;

import javax.swing.*;
import java.awt.*;

public class EnemyBullet extends Sprite {

    private int adjustWidth = 15;
    private Image imgEnemyBullet= getScaledImage(new ImageIcon(Sprite.strImageDir + "enemyBullet.png").getImage(), 7, 20);
    private int VERTICAL_SPEED ;
    private int  HORIZONTAL_SPEED;
    private int nBulletDirection;

    public EnemyBullet(Sprite enemy1, int nBulletDirection) {
        super(enemy1.getCenter().x, enemy1.getCenter().y);
        setTeam(Team.FOE);

        setExpire(88);
        setRadius(10);
        setHeight(5);
        setWidth(14);

        this.nBulletDirection = nBulletDirection;

        switch(nBulletDirection){
            case(1):
                VERTICAL_SPEED = 5;
                HORIZONTAL_SPEED = -5;
                break;
            case(2):
                VERTICAL_SPEED = 15;
                HORIZONTAL_SPEED = 0;
                break;
            case(3):
                VERTICAL_SPEED = 0 ;
                HORIZONTAL_SPEED = 10;
                break;
            case(4):
                VERTICAL_SPEED = 0;
                HORIZONTAL_SPEED = -10;
                break;
            case(5):
                VERTICAL_SPEED =5;
                HORIZONTAL_SPEED = 5;
                break;
            default:
                VERTICAL_SPEED = -5;
                HORIZONTAL_SPEED = -5;
        }
        setDeltaX(enemy1.getDeltaX() + HORIZONTAL_SPEED);
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
            setCenter(new Point(getCenter().x+getDeltaX(), getCenter().y + getDeltaY()));
        }
    }
}
