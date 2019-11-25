package _08final_raster.mvc.model;



import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Water extends Sprite{

    private int waterWidth = 100;
    private int gapAdjust = 0;
    private Image imgWater1 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_1.png").getImage(),waterWidth,waterWidth);
    private Image imgWater2 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_2.png").getImage(),waterWidth,waterWidth);
    private Image imgWater3 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_3.png").getImage(),waterWidth,waterWidth);
    private Image imgWater4 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_4.png").getImage(),waterWidth,waterWidth);
    private Image imgWater5 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_5.png").getImage(),waterWidth,waterWidth);
    private Image imgWater6 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_6.png").getImage(),waterWidth,waterWidth);
    private Image imgWater7 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_7.png").getImage(),waterWidth,waterWidth);
    private Image imgWater8 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_8.png").getImage(),waterWidth,waterWidth);

    private Image imgWater9 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_9.png").getImage(),waterWidth,waterWidth);
    private Image imgWater10 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_10.png").getImage(),waterWidth,waterWidth);
    private Image imgWater11 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_11.png").getImage(),waterWidth,waterWidth);
    private Image imgWater12 = getScaledImage(new ImageIcon(Sprite.strImageDir + "water_12.png").getImage(),waterWidth,waterWidth);

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
        this.nCenterX = nCenterX;
        this.nCenterY = nCenterY;
        setTeam(Team.BACKGROUND);
        setCenter(new Point(nCenterX, nCenterY));
        setHeight(100);
        setWidth(100);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        //first row
        g2d.drawImage(imgWater1, getCenter().x, getCenter().y,null);
        g2d.drawImage(imgWater2, getCenter().x + waterWidth , getCenter().y,null);
        g2d.drawImage(imgWater3, getCenter().x + (2*waterWidth), getCenter().y,null);
        g2d.drawImage(imgWater4, getCenter().x + (3*waterWidth),  getCenter().y,null);
        g2d.drawImage(imgWater1, getCenter().x + (4*waterWidth), getCenter().y,null);
        g2d.drawImage(imgWater2, getCenter().x + (5*waterWidth),  getCenter().y,null);
        g2d.drawImage(imgWater3, getCenter().x + (6*waterWidth),  getCenter().y,null);
        //second row
        g2d.drawImage(imgWater5, getCenter().x, getCenter().y+ waterWidth,null);
        g2d.drawImage(imgWater6, getCenter().x + waterWidth, getCenter().y + waterWidth,null);
        g2d.drawImage(imgWater7, getCenter().x + (2*waterWidth), getCenter().y + waterWidth,null);
        g2d.drawImage(imgWater8, getCenter().x + (3*waterWidth), getCenter().y+ waterWidth,null);
        g2d.drawImage(imgWater5, getCenter().x + (4*waterWidth), getCenter().y + waterWidth,null);
        g2d.drawImage(imgWater6, getCenter().x + (5*waterWidth), getCenter().y+ waterWidth,null);
        g2d.drawImage(imgWater7, getCenter().x + (6*waterWidth), getCenter().y+ waterWidth,null);
        //third row
        g2d.drawImage(imgWater2, getCenter().x  , getCenter().y+ 2*waterWidth,null);
        g2d.drawImage(imgWater3, getCenter().x + (1*waterWidth), getCenter().y+ 2*waterWidth,null);
        g2d.drawImage(imgWater4, getCenter().x + (2*waterWidth),  getCenter().y+ 2*waterWidth,null);
        g2d.drawImage(imgWater1, getCenter().x + (3*waterWidth),  getCenter().y+ 2*waterWidth,null);
        g2d.drawImage(imgWater2, getCenter().x + (4*waterWidth),  getCenter().y+ 2*waterWidth,null);
        g2d.drawImage(imgWater3, getCenter().x + (5*waterWidth),  getCenter().y+ 2*waterWidth,null);
        g2d.drawImage(imgWater1, getCenter().x+ (6*waterWidth), getCenter().y+ 2*waterWidth,null);
        //fourth row
        g2d.drawImage(imgWater6, getCenter().x , getCenter().y + 3*waterWidth,null);
        g2d.drawImage(imgWater7, getCenter().x + (1*waterWidth), getCenter().y + 3*waterWidth,null);
        g2d.drawImage(imgWater8, getCenter().x + (2*waterWidth), getCenter().y+ 3*waterWidth,null);
        g2d.drawImage(imgWater5, getCenter().x + (3*waterWidth), getCenter().y + 3*waterWidth,null);
        g2d.drawImage(imgWater6, getCenter().x + (4*waterWidth), getCenter().y+ 3*waterWidth,null);
        g2d.drawImage(imgWater7, getCenter().x + (5*waterWidth), getCenter().y+ 3*waterWidth,null);
        g2d.drawImage(imgWater5, getCenter().x+ (6*waterWidth), getCenter().y+ 3*waterWidth,null);


    }


}
