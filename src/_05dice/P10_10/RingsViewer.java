package _05dice.P10_10;

import _05dice.P10_9.AnyFlagComponent;

import javax.swing.*;
import java.awt.*;

public class RingsViewer {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent ring4 = new Rings(300, 300, Color.yellow);
        frame.add(ring4);
        frame.setVisible(true);

        JComponent ring5 = new Rings(500, 300, Color.green.darker());
        frame.add(ring5);
        frame.setVisible(true);

        JComponent ring1 = new Rings(200, 200, Color.blue);
        frame.add(ring1);
        frame.setVisible(true);

        JComponent ring2 = new Rings(400, 200, Color.black);
        frame.add(ring2);
        frame.setVisible(true);

        JComponent ring3 = new Rings(600, 200, Color.red);
        frame.add(ring3);
        frame.setVisible(true);


    }
}
