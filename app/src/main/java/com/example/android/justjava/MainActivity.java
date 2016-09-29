package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.duration;
import static android.R.attr.value;
import static com.example.android.justjava.R.string.cream;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    int quantity =0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity== 100){
          //display message error as a toast in short period of time
            Toast.makeText(getApplicationContext(), "You can not order more than 100 cups of coffee!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity < 1){
            Toast.makeText(this, "You can not order less than 1 cup  of coffee!", Toast.LENGTH_SHORT).show();
            return;

        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText)findViewById(R.id.name_text);
        String name = nameField.getText().toString();


        //Figure out if client want whipped cream
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if client want chocolate
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name,price, hasWhippedCream, hasChocolate);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        }


    /**
     * Create summary of the order
     * @param name of client
     * @param price of the order
     * @param addWhippedCream whether or not client want whipped cream topping
     * @param addChocolate    whether or not client want chocolate
     * @return text summary
     */
    private String createOrderSummary(String name,int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd Chocolate? " + addChocolate;
        priceMessage += "\nQuantity:" + quantity;
        priceMessage += "\nTotal; $" + price;
        priceMessage += "\nThank You!";
        return priceMessage;
    }

    /**
     * Calculates the price of the order.
     *
     * @ return total price
     */
    private int calculatePrice(boolean addWhippedCream,boolean addChocolate) {
        // Price for 1 cup of coffee
        int basePrice = 5;

        //Add $1 if client want whipped cream
        if (addWhippedCream){
            basePrice= basePrice+1;
        }
        // Add $2 if client want chocolate
        if(addChocolate){
            basePrice = basePrice +2;
        }
        //Calculate the total order price by multiplying by quantity
        return quantity * basePrice;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


}