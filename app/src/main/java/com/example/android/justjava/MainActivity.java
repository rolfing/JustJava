package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

import static com.example.android.justjava.R.string.cream;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1 ;
        displayQuantity (quantity) ;
    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1 ;
        displayQuantity (quantity) ;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox= (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        Log.v("MainActivity", "has whipped cream:" + hasWhippedCream);


        int price= calculatePrice() ;
        String priceMessage = createOrderSummary(price,hasWhippedCream);
        displayMessage(priceMessage);



    }


    /**
     *Create summary of the order
     * @param price of the order
     * @param addWhippedCream whether or not client want whipped cream topping
     * @return text summary
     */
    private String createOrderSummary(int price, boolean addWhippedCream){
    String priceMessage = "Name: Agelita Syphax";
    priceMessage+="Add whipped cream? "+addWhippedCream;
    priceMessage+="\nQuantity:"+ quantity;
    priceMessage+="\nTotal; $"+ price ;
    priceMessage+="\nThank You!";
    return priceMessage;
}

    /**
     * Calculates the price of the order.
     *@ return total price
     */
    private int calculatePrice() {
        return quantity*5 ;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView OrderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        OrderSummaryTextView.setText(message);
    }
}