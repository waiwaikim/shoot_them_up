package _05dice.P10_9;


import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AnyFlagComponent extends JComponent {

    private Color c1, c2, c3;
    private int xLeft, yTop;

    // when user doesn't input color, the program randomizes the flag color
    public AnyFlagComponent(){
        this.c1 = random_color();
        this.c2 = random_color();
        this.c3 = random_color();
    }

    //user defined flag colors
    public AnyFlagComponent(int x, int y, Color c1, Color c2, Color c3) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.xLeft = x;
        this.yTop = y;
    }

    public void paintComponent(Graphics g)
    {
        drawItalianFlag(g, xLeft, yTop, 200, c1, c2, c3);
        //drawItalianFlag(g, 10, 125, 150);
    }

    public static Color random_color(){
        int r, g, b;
        Random myRandom = new Random();
        r = myRandom.nextInt(256);
        g = myRandom.nextInt(256);
        b = myRandom.nextInt(256);
        return new Color(r,g,b);
    }


    void drawItalianFlag(Graphics g, int xLeft, int yTop, int width, Color c1, Color c2, Color c3 )
    {
        //top 1/3
        g.setColor(c1);
        g.fillRect(xLeft, yTop, width , width * 2 / 9);

        g.setColor(c2);
        g.fillRect(xLeft, yTop + 2 * width / 9, width, width * 2 / 9 );

        g.setColor(c3);
        g.fillRect(xLeft, yTop + 4* width / 9, width, width * 2 / 9 );


        //Because we don't know which stripe will be white, the program draws line
        //around entire flag.
        g.setColor(Color.BLACK);
        g.drawLine(xLeft, yTop, xLeft+width, yTop);
        g.drawLine(xLeft,yTop, xLeft, yTop+ width*2/3);
        g.drawLine(xLeft+width,yTop, xLeft+width, yTop+width*2/3 );
        g.drawLine(xLeft,yTop+ width*2/3, xLeft+width, yTop+width*2/3);

    }
}
