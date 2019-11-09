package _05dice;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JComponent;


public class ItalianFlagComponent extends JComponent {

    private Color c1 = Color.green;
    private Color c2 = Color.red;

    public void paintComponent(Graphics g)
    {

        drawItalianFlag(g, 10, 10, 100, random_color(), random_color());
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


    void drawItalianFlag(Graphics g, int xLeft, int yTop, int width, Color c1, Color c2 )
    {
        g.setColor(c1);
        g.fillRect(xLeft, yTop, width / 3, width * 2 / 3);

        g.setColor(c2);
        g.fillRect(xLeft + 2 * width / 3, yTop, width / 3, width * 2 / 3);
        g.setColor(Color.BLACK);
        g.drawLine(xLeft + width / 3, yTop, xLeft + width * 2 / 3, yTop);
        g.drawLine(xLeft + width / 3, yTop + width * 2 / 3,
                    xLeft + width * 2 / 3, yTop + width * 2 / 3);
    }
}
