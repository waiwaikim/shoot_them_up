package _05dice.P10_10;


import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Rings extends JComponent {
    private Color c1;
    private int centerX, centerY;

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D)g;

        drawRings(gr);
    }

    public Rings(int x, int y, Color c1){
        this.c1 = c1;
        this.centerX= x;
        this.centerY = y;
    }

    void drawRings(Graphics2D g){

        Shape ring = createRing(g, centerX, centerY, 100, 15);
        g.setColor(c1);
        g.fill(ring);
        g.draw(ring);
    }

    private static Shape createRing(Graphics2D g, int centerX, int centerY, int radius, int thickness)
    {
        Ellipse2D outer = new Ellipse2D.Double(
                centerX - radius,
                centerY - radius,
                radius + radius,
                radius + radius);
        Ellipse2D inner = new Ellipse2D.Double(
                centerX - radius + thickness,
                centerY - radius + thickness,
                radius + radius - thickness - thickness,
                radius + radius - thickness - thickness);
        Area area = new Area(outer);
        area.subtract(new Area(inner));
        return area;
    }
}


