package _08final_raster.mvc.view;

import _08final_raster.mvc.controller.Game;
import _08final_raster.mvc.model.Coin;
import _08final_raster.mvc.model.CommandCenter;
import _08final_raster.mvc.model.Movable;
import _08final_raster.mvc.model.Sprite;
import _08final_raster.mvc.view.GameFrame;
import _08final_raster.sounds.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;


public class GamePanel extends Panel {

	// FIELDS
    // The following "off" vars are used for the off-screen double-bufferred image.
	private Dimension dimOff;
	private Image imgOff;
	private Graphics grpOff;
	
	private GameFrame gmf;
	private Font fnt = new Font("SansSerif", Font.BOLD, 12);
	private Font fntBig = new Font("SansSerif", Font.BOLD, 28);
    private Font customFont;
    private Coin coinScore = new Coin(220,22);
    private Image imgP38Life = new ImageIcon(Sprite.strImageDir + "P38_Lives.png").getImage();
	private FontMetrics fmt;
	private int nFontWidth;
	private int nFontHeight;
	private String strDisplay = "";
    private boolean bPlayGameOverSound = true;


	// CONSTRUCTOR
	public GamePanel(Dimension dim){
	    gmf = new GameFrame();
		gmf.getContentPane().add(this);
		gmf.pack();
		initView();

		gmf.setSize(dim);
		gmf.setTitle("1941 Counter Attack");
		gmf.setResizable(false);
		gmf.setVisible(true);
		this.setFocusable(true);
	}

	// METHODS
    public Graphics getGrpOff(){
	    return grpOff;
    }
	public void drawScore() {

        Graphics2D g2D = (Graphics2D) grpOff;
		g2D.setColor(Color.white);
        g2D.setFont(customFont);

        // Player's current score
		g2D.drawString("SCORE", nFontWidth + 30, nFontHeight + 20);
        strDisplay = String.format("%05d", CommandCenter.getInstance().getScore());
		g2D.drawString(strDisplay, nFontWidth + 33, nFontHeight + 45);

        // Highest score achieved
        //g2D.drawString("HI-SCORE", 230, nFontHeight + 20);
        //strDisplay = String.format("%01d", CommandCenter.getInstance().getLevel());
        //g2D.drawString(strDisplay, 295, nFontHeight + 45);

        //lives left - energy
        //feature to implement later: show lives as images / energy bar per vital
        g2D.drawString("VITAL", 490, nFontHeight + 20);
        g2D.drawImage(imgP38Life,490,nFontHeight + 30,null);
        strDisplay = "x" + String.format("%02d", CommandCenter.getInstance().getNumMarios());
        g2D.drawString(strDisplay, 520,nFontHeight + 45);

        // Draw coin score
        //coinScore.draw(g2D);
        //strDisplay = "x" + String.format("%03d", CommandCenter.getInstance().getCoins());
        //g2D.drawString(strDisplay, 245, 47);

        // Draw time left
	}

	@SuppressWarnings("unchecked")
	public void update(Graphics g) {
		if (grpOff == null || Game.DIM.width != dimOff.width
				|| Game.DIM.height != dimOff.height) {
			dimOff = Game.DIM;
			imgOff = createImage(Game.DIM.width, Game.DIM.height);
			grpOff = imgOff.getGraphics();
		}

		// Fill in background with Mario blue.
		//.setColor(new Color(0, 98, 157));
        grpOff.setColor(Color.black);
		grpOff.fillRect(0, 0, Game.DIM.width, Game.DIM.height);

		if (!CommandCenter.getInstance().isPlaying() && !CommandCenter.getInstance().isGameOver()) {
            displayTextOnScreen(grpOff);
        } else if (CommandCenter.getInstance().isGameOver()) {

            grpOff.setColor(Color.white);
            grpOff.setFont(customFont);
            strDisplay = "GAME OVER"; // Manual alignment of 40 pixels to keep text in center
            grpOff.drawString(strDisplay,(Game.DIM.width - fmt.stringWidth(strDisplay))/2 - 50, Game.DIM.height / 2);
            strDisplay = "YOUR SCORE : " + String.format("%05d", CommandCenter.getInstance().getScore());
            grpOff.drawString(strDisplay,(Game.DIM.width - fmt.stringWidth(strDisplay))/2 - 100, Game.DIM.height / 2 + 50);
            if (bPlayGameOverSound) {
                Sound.playSound("Game_over.wav");
                bPlayGameOverSound = false;
            }

		} else if (CommandCenter.getInstance().isPaused()) {

            Image rawPause = new ImageIcon(Sprite.strImageDir + "game_pause.png").getImage();
            Image resizedPause = getScaledImage(rawPause, dimOff.width, dimOff.height);
            grpOff.drawImage(resizedPause,0,0,null);
			strDisplay = "GAME PAUSED";
            grpOff.setColor(Color.white);
            grpOff.setFont(customFont); // Manual alignment of 40 pixels to keep text in center
            grpOff.drawString(strDisplay,(Game.DIM.width - fmt.stringWidth(strDisplay))/2 - 50, Game.DIM.height / 2);
		}
		else {
		    //plyaing and not paushed!
            // Update game timer
            if (CommandCenter.getInstance().getMario() != null && !CommandCenter.getInstance().getMario().isDead()) {
                CommandCenter.getInstance().updateTimeLeft();
            }

            //drawScore();

            // Control frame movement. Check if movement will cause first or last ground block to go out of screen bounds
            if (CommandCenter.getInstance().getMoveCountX() != 0
                && (CommandCenter.getInstance().getGroundFirst().getCenter().getX() + CommandCenter.getInstance().getDeltaX() > 0
                    || (CommandCenter.getInstance().getGroundLast().getCenter().getX()
                        +   CommandCenter.getInstance().getGroundLast().getWidth()
                        +   CommandCenter.getInstance().getDeltaX()) < Game.DIM.width)) {
                CommandCenter.getInstance().setMoveCountX(0);
            }

			iterateMovables(grpOff,
					(ArrayList<Movable>)  CommandCenter.getInstance().getMovBackground(),
                    (ArrayList<Movable>)  CommandCenter.getInstance().getMovFriends(),
					(ArrayList<Movable>)  CommandCenter.getInstance().getMovPlatform(),
                    (ArrayList<Movable>)  CommandCenter.getInstance().getMovFloater(),
					(ArrayList<Movable>)  CommandCenter.getInstance().getMovFoes());
		}
		//draw the double-Buffered Image to the graphics context of the panel
		g.drawImage(imgOff, 0, 0, this);

        // Decrement global move counter
        if (CommandCenter.getInstance().getMoveCountX() != 0) {
            CommandCenter.getInstance().decrMoveCountX();
        }
	}

	//for each movable array, process it.
	private void iterateMovables(Graphics g, ArrayList<Movable>...movMovz){

		for (ArrayList<Movable> movMovs : movMovz) {
			for (Movable mov : movMovs) {
                if (!CommandCenter.getInstance().getInitPosFlag()) {
                    mov.move();
                } else {
                    mov.initPos();
                }
				mov.draw(g);
			}
		}

        if (CommandCenter.getInstance().getInitPosFlag()) {
            CommandCenter.getInstance().setInitPosFlag(false);
        }
	}

	private void initView() {
		Graphics g = getGraphics();			// get the graphics context for the panel
        loadGameFont();
		g.setFont(fnt);						// take care of some simple font stuff
		fmt = g.getFontMetrics();
		nFontWidth = fmt.getMaxAdvance();
		nFontHeight = fmt.getHeight();
		g.setFont(fntBig);					// set font info
	}
    private Image getScaledImage(Image srcImg, int w, int h){
	    //to resize background image.
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
	// This method draws some text to the middle of the screen before/after a game
	private void displayTextOnScreen(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Image rawBackground = new ImageIcon(Sprite.strImageDir + "Front_Background.png").getImage();
        Image resizedBackground = getScaledImage(rawBackground, 700, 1000);
        g2d.drawImage(resizedBackground,0,0,null);

        //Image imgBanner = new ImageIcon(Sprite.strImageDir + "mario_banner.jpg").getImage();
        //g2d.drawImage(imgBanner,320,40,null);

        g.setColor(Color.white);
        g.setFont(customFont);

        strDisplay = "KEY CONTROLS";
        grpOff.drawString(strDisplay,
                (Game.DIM.width)/2 - fmt.stringWidth(strDisplay), Game.DIM.height / 2
                        + nFontHeight + 160);

		strDisplay = "S : START";
		grpOff.drawString(strDisplay,
				(Game.DIM.width)/2 - fmt.stringWidth(strDisplay), Game.DIM.height / 2
						+ nFontHeight + 200);

		strDisplay = "P : PAUSE";
		grpOff.drawString(strDisplay,
				(Game.DIM.width)/2 - fmt.stringWidth(strDisplay), Game.DIM.height / 2
						+ nFontHeight + 230);

        strDisplay = "Q : QUIT    ";
        grpOff.drawString(strDisplay,
                (Game.DIM.width)/2 - fmt.stringWidth(strDisplay) + 2, Game.DIM.height / 2 // Manual offset to ensure the semi-colons align
                        + nFontHeight + 260);

        strDisplay = "ARROW KEYS TO MOVE";
        grpOff.drawString(strDisplay,
                (Game.DIM.width)/2 - fmt.stringWidth(strDisplay), Game.DIM.height / 2
                        + nFontHeight + 290);

	}


    // Custom font for Mario
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