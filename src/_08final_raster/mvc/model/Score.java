package _08final_raster.mvc.model;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Score extends Sprite {

    private int nCenterX;
    private int nCenterY;
    private int nFontWidth= 100;
    private int nFontHeight= 100;
    private String strDisplay = "";
    private Image imgP38Life =  getScaledImage(new ImageIcon(Sprite.strImageDir + "P38_Lives.png").getImage(),40,40);

    private Font customFont;

    public Score(int nCenterX, int nCenterY) {
        super(nCenterX, nCenterY);
        this.setnCenterX(nCenterX);
        this.setnCenterY(nCenterY);
        setTeam(Team.BACKGROUND);
        setCenter(new Point(nCenterX, nCenterY));
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        loadGameFont();
        g2D.setColor(Color.white);
        g2D.setFont(customFont);

        // Player's current score
        g2D.drawString("SCORE", 50 , 25 );
        strDisplay = String.format("%05d", CommandCenter.getInstance().getScore());
        g2D.drawString(strDisplay, 55, 50);

        // Game time left
        g2D.drawString("TIME LEFT", 230, 25);
        strDisplay = String.format("%01d", CommandCenter.getInstance().getGameTimeLeft());
        g2D.drawString(strDisplay, 295, 45);

        //lives left - energy
        //feature to implement later: show lives as images / energy bar per vital
        g2D.drawString("VITAL",520, 25);
        g2D.drawImage(imgP38Life,520,30,null);
        strDisplay = "x" + String.format("%02d", CommandCenter.getInstance().getNumP38s());
        g2D.drawString(strDisplay, 560,55);



    }
    private void loadGameFont() {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(Sprite.strFontDir + "mario2.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);

        } catch (java.io.IOException | FontFormatException e)  {
            e.printStackTrace();
            System.out.println("Invalid font or font file not found");
        }
    }

	public int getnFontWidth() {
		return nFontWidth;
	}

	public void setnFontWidth(int nFontWidth) {
		this.nFontWidth = nFontWidth;
	}

	public int getnFontHeight() {
		return nFontHeight;
	}

	public void setnFontHeight(int nFontHeight) {
		this.nFontHeight = nFontHeight;
	}

	public int getnCenterX() {
		return nCenterX;
	}

	public void setnCenterX(int nCenterX) {
		this.nCenterX = nCenterX;
	}

	public int getnCenterY() {
		return nCenterY;
	}

	public void setnCenterY(int nCenterY) {
		this.nCenterY = nCenterY;
	}
}
