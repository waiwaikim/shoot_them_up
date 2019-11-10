package _05dice.P11_9;

import _01control.P3_14;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CircleFrame extends JFrame {

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 1000;

    private boolean first= true;
    //first = is it the first mouse click?
    private boolean second = false;
    //second = has the second mouse click been clicked?

    private int centerX, centerY, peripheryX, peripheryY;
    private CircleComponent myCircle;

    class MouseClickListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent event) {
            if (first){

                centerX = event.getX();
                centerY = event.getY();
                System.out.println("CenterX: " + centerX + " centerY: " + centerY);
                first = false;
                second = false;
            }
            else {

                peripheryX = event.getX();
                peripheryY = event.getY();
                System.out.println("PeripheryX: "+ peripheryX + " PerpheryY: " +peripheryY);
                first= true;
                second = true;
                myCircle.moveCircleTo(centerX, centerY, peripheryX, peripheryY);
            }
        }
        public void mouseReleased(MouseEvent event) {}
        public void mousePressed(MouseEvent event) {}
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
    }

    public CircleFrame(){
        myCircle = new CircleComponent();
        add(myCircle);

        MouseListener myListener = new MouseClickListener();
        myCircle.addMouseListener(myListener);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
}
