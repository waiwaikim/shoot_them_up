package _08final_raster.mvc.model;

import _08final_raster.mvc.model.CommandCenter;
import _08final_raster.mvc.model.Movable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class Sprite implements Movable {

	//the center-point of this sprite
	private Point pntCenter;
    private int nInitCenterX, nInitCenterY;

	//this causes movement; change in x and change in y
	private int nDeltaX, nDeltaY;

	//we need to know what team we're on
	private Team mTeam;

    // Default worth is zero.
    private final int WORTH = 0;

    private int nExpiry; //natural mortality (short-living objects)

    // Radius, Height and width of the sprite
    private int nRadius;
	private int nHeight;
	private int nWidth;


    // Expiry team for this sprite
    private int nDeadTimeLeft = 0;
    private  boolean bDead;
    private boolean bOutFrame;

    // project directory with images
    public static String strImageDir  = System.getProperty("user.dir") + File.separator + "src"
                                        + File.separator + "_08final_raster" + File.separator + "images" + File.separator;

    // project directory with fonts
    public static String strFontDir  = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "_08final_raster" + File.separator + "fonts" + File.separator;



    public Sprite(int nCenterX, int nCenterY) {
        nInitCenterX = nCenterX;
        nInitCenterY = nCenterY;
        setCenter(new Point(nCenterX,nCenterY));
        bDead = false;
    }
    public void setExpire(int n) { nExpiry = n; }


	@Override
	public Team getTeam() {
	  return mTeam;
	}

	public void setTeam(Team team){
		mTeam = team;
	}

    // This move method is primarily used by non-moving objects like platform when the screen moves
    // Other moving objects override to implement their own logic but also call this method
    public void move(){
        if (CommandCenter.getInstance().getMoveCountY() != 0) {
            pntCenter.y+= CommandCenter.getInstance().getDeltaY();
        }
        if (CommandCenter.getInstance().getMoveCountX() != 0) {
            pntCenter.x+= CommandCenter.getInstance().getDeltaX();
        }

    }

    // Set initial position for this sprite to be used when Mario is respawned in the current level
    public void initPos() {
        pntCenter.x = nInitCenterX;
        pntCenter.y = nInitCenterY;
    }


    //setter methods
    public void setCenter(Point pntParam) {
        pntCenter = pntParam;
    }
    public void setDeltaX(int nSet) {
		nDeltaX = nSet;
	}
	public void setDeltaY(int nSet) { nDeltaY = nSet; }
	public void setRadius(int nRadius){this.nRadius = nRadius;}
    public void setHeight(int nHeight) {
        this.nHeight = nHeight;
    }
    public void setWidth(int nWidth) {
        this.nWidth = nWidth;
    }

    public void setLeftDirection() { };
    public void setRightDirection() { };
    public void setUpDirection(){  };
    public void setDownDirection(){};
    public void setDead() {
        this.bDead = true;
    };

    //getter methods
    public Point getCenter() {
        return pntCenter;
    }
	public int getDeltaY() {
		return nDeltaY;
	}
	public int getDeltaX() {
		return nDeltaX;
	}
	public int getRadius(){ return nRadius;}
    public int getHeight() {
        return nHeight;
    }
    public int getWidth() {
        return nWidth;
    }

    public int getWorth() {
        return WORTH;
    }
    public int getExpire() {
        return nExpiry;
    }
    public int getDeadTimeLeft() {
        return nDeadTimeLeft;
    }

	@Override
    public void draw(Graphics g) {}

    public boolean isDead() { return bDead; }

    public boolean isOutFrame(){return bOutFrame;}

    public void setOutFrame(){
        Point pnt = getCenter();
        if(pnt.x <= 0 || pnt.x>=700 || pnt.y <=0 || pnt.y >= 1000)
            bOutFrame = true;
        else
            bOutFrame = false;
    }




    Image getScaledImage(Image srcImg, int w, int h){
        //to resize background image.
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

}
