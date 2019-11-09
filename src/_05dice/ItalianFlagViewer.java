package _05dice;


import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;

public class ItalianFlagViewer {
    public static void main(String[] args)
    {

        JFrame frame = new JFrame();
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent component = new ItalianFlagComponent();
        frame.add(component);
        frame.setVisible(true);

    }
}
