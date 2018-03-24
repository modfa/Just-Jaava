package modf.justjaava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        /** Get the name of the customer
         *
         */
        EditText nameOfCustomer = findViewById(R.id.name_text);
        String customerName = nameOfCustomer.getText().toString();

        /** Check the status of the whipped cream checkbox
         *
        */
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        /** Check the status of the Chocolate checkbox
         *
         */
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        Boolean hasChocolate= chocolateCheckBox.isChecked();
        /** Calling the method to calculate the price
         *
         */
        int totalPrice = calculatePrice(hasWhippedCream,hasChocolate);
        /** Calling the method createOrderSummary and passing the following parameters
         * @param price,hasWhippedCream,hasChocolate,customerName
         */
       String priceMessage = createOrderSummary(totalPrice,hasWhippedCream,hasChocolate,customerName);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java App Order For: " +customerName);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
    /**
     * Calculates the price of the order.
     *
     *
     */
    private int calculatePrice(boolean whippedCream,Boolean chocolate) {
        int basePrice =5;
        if(whippedCream){
            basePrice =(basePrice+1);
        }
        if(chocolate){
            basePrice = (basePrice+2);
        }
       ;
       return basePrice*quantity;
    }

    /**
     * Create summary of the order.
     *
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate is whether or not the user wants chocolate topping
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary(int price,boolean addWhippedCream, boolean addChocolate, String name){
        String priceMessage = "Name: "+name+"\nAdd Whipped Cream? : " + addWhippedCream +"\nAdd Chocolate? : " + addChocolate +" \nQuantity: "+ quantity +" \nTotal: $"+ price +"\n"+getString(R.string.thank_you);
        return priceMessage;
    }


    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if(quantity>=100){
            Toast.makeText(this,"You can't have more than hundred cup of coffes",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity +1;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity==1){
            Toast.makeText(this,"You can't have less than one cup of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;

        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffee) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffee);
    }
    private void testUpload(){
        int i = 2+1;
    }

}
