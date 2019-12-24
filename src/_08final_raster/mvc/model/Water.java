package _08final_raster.mvc.model;



import javax.swing.*;
import java.awt.*;

public class Water extends Sprite{

    private int waterWidth = 100;
    private final int VERTICAL_SPEED = 2;
    private Image imgWater1 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_1.png").getImage(),waterWidth,waterWidth);
    private Image imgWater2 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_2.png").getImage(),waterWidth,waterWidth);
    private Image imgWater3 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_3.png").getImage(),waterWidth,waterWidth);
    private Image imgWater4 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_4.png").getImage(),waterWidth,waterWidth);
    private Image imgWater5 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_5.png").getImage(),waterWidth,waterWidth);
    private Image imgWater6 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_6.png").getImage(),waterWidth,waterWidth);
    private Image imgWater7 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_7.png").getImage(),waterWidth,waterWidth);
    private Image imgWater8 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_8.png").getImage(),waterWidth,waterWidth);

 
    /*private Image imgWater3 = new ImageIcon(Sprite.strImageDir + "water_3.png").getImage();
    private Image imgWater4 = new ImageIcon(Sprite.strImageDir + "water_4.png").getImage();
    private Image imgWater5 = new ImageIcon(Sprite.strImageDir + "water_5.png").getImage();
    private Image imgWater6 = new ImageIcon(Sprite.strImageDir + "water_6.png").getImage();
    private Image imgWater7 = new ImageIcon(Sprite.strImageDir + "water_7.png").getImage();
    private Image imgWater8 = new ImageIcon(Sprite.strImageDir + "water_8.png").getImage();
*/
    private int nCenterX;
    private int nCenterY;



    public Water(int nCenterX, int nCenterY) {
        super(nCenterX, nCenterY);
        this.setnCenterX(nCenterX);
        this.setnCenterY(nCenterY);
        setDeltaY(VERTICAL_SPEED);
        setTeam(Team.BACKGROUND);
        setCenter(new Point(nCenterX, nCenterY));
        //setExpire(100);
        setHeight(100);
        setWidth(100);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        for(int i=0 ; i <4; i++){
            //first row
            g2d.drawImage(imgWater1, getCenter().x, getCenter().y+ (400*i),null);
            g2d.drawImage(imgWater2, getCenter().x + waterWidth , getCenter().y+ (400*i),null);
            g2d.drawImage(imgWater3, getCenter().x + (2*waterWidth), getCenter().y+ (400*i),null);
            g2d.drawImage(imgWater4, getCenter().x + (3*waterWidth),  getCenter().y+ (400*i),null);
            g2d.drawImage(imgWater1, getCenter().x + (4*waterWidth), getCenter().y+ (400*i),null);
            g2d.drawImage(imgWater2, getCenter().x + (5*waterWidth),  getCenter().y+ (400*i),null);
            g2d.drawImage(imgWater3, getCenter().x + (6*waterWidth),  getCenter().y+ (400*i),null);
            //second row
            g2d.drawImage(imgWater5, getCenter().x, getCenter().y+ waterWidth+ (400*i),null);
            g2d.drawImage(imgWater6, getCenter().x + waterWidth, getCenter().y + waterWidth+ (400*i),null);
            g2d.drawImage(imgWater7, getCenter().x + (2*waterWidth), getCenter().y + waterWidth+ (400*i),null);
            g2d.drawImage(imgWater8, getCenter().x + (3*waterWidth), getCenter().y+ waterWidth+ (400*i),null);
            g2d.drawImage(imgWater5, getCenter().x + (4*waterWidth), getCenter().y + waterWidth+ (400*i),null);
            g2d.drawImage(imgWater6, getCenter().x + (5*waterWidth), getCenter().y+ waterWidth+ (400*i),null);
            g2d.drawImage(imgWater7, getCenter().x + (6*waterWidth), getCenter().y+ waterWidth+ (400*i),null);
            //third row
            g2d.drawImage(imgWater2, getCenter().x  , getCenter().y+ 2*waterWidth+ (400*i),null);
            g2d.drawImage(imgWater3, getCenter().x + (1*waterWidth), getCenter().y+ 2*waterWidth+ (400*i),null);
            g2d.drawImage(imgWater4, getCenter().x + (2*waterWidth),  getCenter().y+ 2*waterWidth+ (400*i),null);
            g2d.drawImage(imgWater1, getCenter().x + (3*waterWidth),  getCenter().y+ 2*waterWidth+ (400*i),null);
            g2d.drawImage(imgWater2, getCenter().x + (4*waterWidth),  getCenter().y+ 2*waterWidth+ (400*i),null);
            g2d.drawImage(imgWater3, getCenter().x + (5*waterWidth),  getCenter().y+ 2*waterWidth+ (400*i),null);
            g2d.drawImage(imgWater1, getCenter().x+ (6*waterWidth), getCenter().y+ 2*waterWidth+ (400*i),null);
            //fourth row
            g2d.drawImage(imgWater6, getCenter().x , getCenter().y + 3*waterWidth+ (400*i),null);
            g2d.drawImage(imgWater7, getCenter().x + (1*waterWidth), getCenter().y + 3*waterWidth+ (400*i),null);
            g2d.drawImage(imgWater8, getCenter().x + (2*waterWidth), getCenter().y+ 3*waterWidth+ (400*i),null);
            g2d.drawImage(imgWater5, getCenter().x + (3*waterWidth), getCenter().y + 3*waterWidth+ (400*i),null);
            g2d.drawImage(imgWater6, getCenter().x + (4*waterWidth), getCenter().y+ 3*waterWidth+ (400*i),null);
            g2d.drawImage(imgWater7, getCenter().x + (5*waterWidth), getCenter().y+ 3*waterWidth+ (400*i),null);
            g2d.drawImage(imgWater5, getCenter().x+ (6*waterWidth), getCenter().y+ 3*waterWidth+ (400*i),null);
        }
    }
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
