package _06design.P12_1;


import _06design.E12_4.Problem;

import java.util.HashMap;

public class VendingMachine {

    private HashMap<Product, Integer> products;
    private double payment;
    private double totalMoney;

    public VendingMachine(){
        products = new HashMap<Product, Integer>();
        defaultStock();
    }
    /* ----------------- methods Used by operators ---------------*/

    public void addProduct(Product item, Integer quantity){
        //to stock or restock the vending machine
        products.put(item, quantity);
    }
    public void emptyCoinBox(){
        totalMoney = 0;
    }
    public double getPayment(){
        return payment;
    }
    public double getTotalMoney(){
        return totalMoney;
    }
    public void defaultStock(){
        Product coke = new Product("Coke", 1.00);
        Product sprite = new Product("Sprite", 1.00);
        Product coffee = new Product("Coffee", 1.75);
        Product redbull = new Product("RedBull", 2.25);
        Product HealingPotion = new Product("HealingPotion", 3.75);

        addProduct(coke, 10);
        addProduct(sprite, 10);
        addProduct(coffee, 1);
        addProduct(redbull, 3);
        addProduct(HealingPotion, 1);
    }
    /* -------------- end of methods used by operators ---------------*/

    /*------------------- methods enabling customers to purshase -----------------*/
    public void insertCoins(Coin coinType, int coinCount){
        payment += coinType.getValue() * coinCount;
    }

    public boolean isAvailable(Product myItem){
        //does the vending mahcine have the item in stock?
        if(products.get(myItem)>0){
            return true;
        }
        else return false;
    }

    public boolean isSufficentFund(Product myItem){

        if(payment >= myItem.getPrice()){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean dispenseItem(String userItem){

        for(Product item : products.keySet()){
            //System.out.println(item.getName());
            if(item.getName().equals(userItem)){
                if(isAvailable(item)){
                    if(isSufficentFund(item)){
                        checkOut(item);
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
            else{

            }
        }
        return false;

    }
    public double returnChange(){
        double change = payment;
        payment = 0 ;
        return change;
    }
    public void checkOut(Product myItem){
        totalMoney+= payment;
        payment = 0 ;
        products.put(myItem, products.get(myItem)-1);
    }

    public String display(){
        //
        String menu= "";
        for(Product myItem : products.keySet()){

            String available ="";
            if(isAvailable(myItem)){
                available = "Available";
            }
            else{
                available = "Sold Out";
            }
            menu += " " + myItem.getName() + " " + available + " at $" +myItem.getPrice() + '\n';
        }
        return menu;
    }



}
