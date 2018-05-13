package com.example.gjavila.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int quantity= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        quantity= quantity + 1;
        display(quantity);
    }
    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        quantity= quantity - 1;
        display(quantity);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage=createOrderSummary(price);
        displayMessage(priceMessage);


    }

    /*
    creates a summary of the order with the number off cofffees and price

    * */
    public String createOrderSummary(int price){
        String priceMessage="Name: Gera Avila";
        priceMessage= priceMessage+ "\nQuantity:" + quantity;
        priceMessage= priceMessage + "\nTotal $" + price;
        priceMessage= priceMessage+ "\nThank you!!!";
        return priceMessage;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /*
    * this method will calculate the price
    * and return the price
    * */
    public int calculatePrice(){
    int price = quantity*5;
    return price;
    }

    /**
     * This method displays the given text on the screen.
     */

    private void displayMessage(String message) {
        TextView orederSumary = (TextView) findViewById(R.id.Order_Summary_Text_View);
        orederSumary.setText(message);
    }
}