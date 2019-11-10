package _05dice.P11_9;

import javax.swing.*;
import java.awt.*;

public class CircleComponent extends JComponent
{

    int centerX, centerY;
    double radius;
    public CircleComponent(){
        centerX=0;
        centerY=0;
        radius=0;
    }

 /*   public CircleComponent(int x1, int y1, int x2, int y2){
        this.centerX = x1;
        this.centerY = x2;
        this.radius = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2,2));
    }*/

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D)g;
        drawCircle(gr);
    }

    public void drawCircle(Graphics2D gr) {

        int x = (int)(centerX-radius);
        int y = (int)(centerY-radius);
        int int_r = (int)radius;
        gr.fillOval(x,y, 2*int_r, 2*int_r);
    }
    public void moveCircleTo(int x1, int y1, int x2, int y2){
        centerX = x1;
        centerY = y2;
        radius = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2,2));
        repaint();
    }




}
