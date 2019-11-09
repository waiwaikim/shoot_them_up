package _05dice.P10_9;


import javax.swing.*;
import java.awt.*;

public class AnyFlagViewer {

    public static void main(String[] args)
    {

        JFrame frame = new JFrame();
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent  Hungary = new AnyFlagComponent(10, 10, Color.red, Color.white, Color.green.darker());
        frame.add(Hungary);
        frame.setVisible(true);

        JComponent German = new AnyFlagComponent(10, 180, Color.black, Color.red, Color.yellow);
        frame.add(German);
        frame.setVisible(true);

    }
}
