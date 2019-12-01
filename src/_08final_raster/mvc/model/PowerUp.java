package _08final_raster.mvc.model;


import _08final_raster.mvc.controller.Game;
import _08final_raster.mvc.model.CollisionOp;
import _08final_raster.mvc.model.CommandCenter;

import javax.swing.*;
import java.awt.*;

public class PowerUp extends Sprite  {

    private int adjustWidth = 35;
    private Image imgPower= getScaledImage(new ImageIcon(Sprite.strImageDir + "powerUp.png").getImage(), adjustWidth, adjustWidth);


    public PowerUp(int nCenterX, int nCenterY) {
        super(nCenterX, nCenterY);
        setTeam(Team.FLOATER);

        setExpire(100);
        setRadius(10);

        int nx = Game.R.nextInt(6);
        int ny = Game.R.nextInt(6);

        if(nx%2 == 0)
            setDeltaX(nx);
        else
            setDeltaX(-nx);

        if(ny%2 ==0)
            setDeltaY(ny);
        else
            setDeltaY(-ny);

        setCenter(new Point(Game.R.nextInt(Game.DIM.width), Game.R.nextInt(Game.DIM.height)));
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(imgPower,getCenter().x,getCenter().y,null);
    }

    @Override
    public void move() {
        super.move();

        if (getExpire() == 0)
            CommandCenter.getInstance().getOpsList().enqueue(this,  CollisionOp.Operation.REMOVE);
        else
            setExpire(getExpire() - 1);

        Point pnt = getCenter();
        double dX = pnt.x + getDeltaX();
        double dY = pnt.y + getDeltaY();

        //this just keeps the sprite inside the bounds of the frame
        if (pnt.x > Game.DIM.width) {
            setCenter(new Point(1, pnt.y));

        } else if (pnt.x < 0) {
            setCenter(new Point(Game.DIM.width - 1, pnt.y));
        } else if (pnt.y > Game.DIM.height) {
            setCenter(new Point(pnt.x, 1));

        } else if (pnt.y < 0) {
            setCenter(new Point(pnt.x, Game.DIM.height - 1));
        } else {
            setCenter(new Point((int) dX, (int) dY));
        }
    }


    @Override
    public void setLeftDirection() {
        int deltaX = this.getDeltaX();
        setDeltaX(-deltaX);
    }

    @Override
    public void setRightDirection() {
        int deltaX = this.getDeltaX();
        setDeltaX(-deltaX);
    }

    @Override
    public void setUpDirection() {
        int deltaY = this.getDeltaY();
        setDeltaY(-deltaY);
    }

    @Override
    public void setDownDirection() {
        int deltaY = this.getDeltaY();
        setDeltaY(-deltaY);
    }
}
