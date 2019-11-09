package _05dice.P10_19;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import  java.awt.event.MouseEvent;

public class InvestmentFrame3 extends JFrame {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 400;

    private static final int AREA_ROWS = 10;
    private static final int AREA_COLUMNS = 30;

    private static final double DEFAULT_RATE = 5;
    private static final double INITIAL_BALANCE = 1000;

    private JLabel rateLabel;
    private JTextField rateField;
    private JButton button;
    private JTextArea resultArea;
    private JComponent resultArea2;
    private double balance;

    public InvestmentFrame3()
    {
        balance = INITIAL_BALANCE;
        resultArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        resultArea.setText(balance + "\n");
        resultArea.setEditable(false);

        resultArea2 = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        //resultArea2.add()

        createTextField();
        createButton();
        createPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void createTextField()
    {
        rateLabel = new JLabel("Interest Rate: ");

        final int FIELD_WIDTH = 10;
        rateField = new JTextField(FIELD_WIDTH);
        rateField.setText("" + DEFAULT_RATE);
    }


    class AddInterestListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            double rate = Double.parseDouble(rateField.getText());
            double interest = balance * rate / 100;

            balance = balance + interest;
            resultArea.append(balance + "\n");
        }
    }
    private void createButton()
    {
        button = new JButton("Add Interest");

        ActionListener listener = new AddInterestListener();
        button.addActionListener(listener);
    }

    private void createPanel()
    {
        JPanel panel = new JPanel();
        panel.add(rateLabel);
        panel.add(rateField);
        panel.add(button);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);
        add(panel);
    }
}
