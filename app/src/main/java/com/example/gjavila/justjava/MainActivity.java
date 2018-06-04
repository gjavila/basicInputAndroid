package com.example.gjavila.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity= 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        if(quantity==100){
            //error message for more than 100
            Toast.makeText(this,"you cannot order more than 100 cups",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity= quantity + 1;
        display(quantity);
    }
    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {

        if(quantity==1){
            //error message you for less than 1
            Toast.makeText(this,"you cannot order less than 1 cups",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity= quantity - 1;
        display(quantity);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameText=(EditText) findViewById(R.id.nameField);
        String customerName=nameText.getText().toString();
        CheckBox whipecream=(CheckBox) findViewById(R.id.whipped_cream);
        boolean hasWhipcream=whipecream.isChecked();
        CheckBox chocolate=(CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate=chocolate.isChecked();
        int price = calculatePrice(hasWhipcream,hasChocolate);
        String priceMessage=createOrderSummary(price, hasWhipcream, hasChocolate, customerName);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for:"+ customerName);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    /*
    creates a summary of the order with the number off cofffees and price

    * */
    public String createOrderSummary(int price, boolean haswhipcream, boolean haschocolate, String customerName){
        String priceMessage="Name: "+customerName;
        priceMessage= priceMessage+ "\nAdd whipp cream:" + haswhipcream;
        priceMessage= priceMessage+ "\nAdd whipp cream:" + haschocolate;
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
    public int calculatePrice(boolean haswhipecream, boolean haschocolate){
        int baseprice=5;
        // add 1 dolar to the base price
        if(haswhipecream== true){
            baseprice=baseprice+1;
        }
        //add 2 dollar to the ende price for the coffee
        if(haschocolate==true){
            baseprice=baseprice+2;
        }

    int price = quantity*baseprice;
    return price;
    }


}