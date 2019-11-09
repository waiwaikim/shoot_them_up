package _05dice.P10_19;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.PrimitiveIterator;

public class MainGUI {

    private ActionListener resetActionListener;
    private ActionListener printActionListener;
    private ActionListener customAcctionListener;
    private ActionListener menuActionListener;
    private JPanel ReceiptCreator;
    private JButton friesButton;
    private JButton phillyCheesesteakButton;
    private JButton chickenSandwichButton;
    private JButton burgerButton;
    private JButton donutButton;
    private JButton softDrinkButton;
    private JButton beerButton;
    private JButton milkShakeButton;
    private JButton liquorButton;
    private JButton todaySpecialButton;
    private JButton addButton;
    private JTextField customPrice;
    private JTextField customItem;
    private JButton resetButton;
    private JButton printButton;
    private JTextArea itemArea;
    private JTextArea priceArea;

    private double balance = 0 ;
    private final static Map<String, Double> prices = new HashMap<String, Double>();
    static{
        prices.put("Fries", 5.00);
        prices.put("Burger", 7.00);
        prices.put("Chicken Sandwich", 6.00);
        prices.put("Donut", 2.00);
        prices.put("Philly Cheesesteak", 10.00);
        prices.put("Soft Drink", 1.50);
        prices.put("Beer", 5.00);
        prices.put("Milk Shake", 4.50);
        prices.put("Liquor", 5.00);
        prices.put("Today Special", 9.50);
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("MainGUI");
        frame.setContentPane(new MainGUI().ReceiptCreator);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1500, 1000);
        frame.setVisible(true);
    }

    public MainGUI() {

        menuActionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String strMenuText = ((JButton)e.getSource()).getText();
                itemArea.append(strMenuText + "\n");

                Double strMenuPrice = getPrice(strMenuText);
                balance += strMenuPrice;
                priceArea.append(String.valueOf(strMenuPrice) + "\n");
            }
        };

        customAcctionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    String strCustomText = customItem.getText();
                    double doubleCustomPrice = Double.parseDouble(customPrice.getText());

                    itemArea.append(strCustomText + "\n");
                    balance += doubleCustomPrice;
                    priceArea.append(String.valueOf(doubleCustomPrice) +"\n");
                }
                catch(NumberFormatException e1){
                    JOptionPane.showMessageDialog(null, "Please input valid information." );
                }

            }
        };

        resetActionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                balance = 0 ;
                priceArea.setText("");
                itemArea.setText("");
                customPrice.setText("");
                customItem.setText("");
            }
        };
        printActionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                itemArea.append("------------------------------" +"\n");
                priceArea.append("------------------------------" + "\n");

                double tax = balance * 0.08;
                itemArea.append("Tax" +"\n");
                priceArea.append(String.valueOf(tax) +"\n");
                balance += tax;

                double tip = balance *0.2;
                itemArea.append("Suggested Tip" +"\n");
                priceArea.append(String.valueOf(tip) +"\n");
                balance += tip;

                itemArea.append("Total " + "\n");
                priceArea.append(String.valueOf(balance) +"\n");
            }
        };
        createButton();
    }

    private void createButton(){
        friesButton.addActionListener(menuActionListener);
        phillyCheesesteakButton.addActionListener(menuActionListener);
        chickenSandwichButton.addActionListener(menuActionListener);
        burgerButton.addActionListener(menuActionListener);
        donutButton.addActionListener(menuActionListener);
        softDrinkButton.addActionListener(menuActionListener);
        beerButton.addActionListener(menuActionListener);
        milkShakeButton.addActionListener(menuActionListener);
        liquorButton.addActionListener(menuActionListener);
        todaySpecialButton.addActionListener(menuActionListener);
        addButton.addActionListener(customAcctionListener);
        printButton.addActionListener(printActionListener);
        resetButton.addActionListener(resetActionListener);
    }

    private static double getPrice(String item){
        return prices.get(item);
    }



}
