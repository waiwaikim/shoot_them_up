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
        this.nCenterX = nCenterX;
        this.nCenterY = nCenterY;
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

        // Highest score achieved
        //g2D.drawString("HI-SCORE", 230, nFontHeight + 20);
        //strDisplay = String.format("%01d", CommandCenter.getInstance().getLevel());
        //g2D.drawString(strDisplay, 295, nFontHeight + 45);

        //lives left - energy
        //feature to implement later: show lives as images / energy bar per vital
        g2D.drawString("VITAL",520, 25);
        g2D.drawImage(imgP38Life,520,30,null);
        strDisplay = "x" + String.format("%02d", CommandCenter.getInstance().getNumMarios());
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
}
