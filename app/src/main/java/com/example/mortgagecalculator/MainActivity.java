package com.example.mortgagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // declare views
    Spinner sp;
    TextView totalAmount;
    TextView rate;
    TextView years;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the objects of views
        sp = findViewById(R.id.types);
        totalAmount = findViewById(R.id.txtMAmount);
        rate = findViewById(R.id.txtRate);
        years = findViewById(R.id.txtYears);
    }

    // function to calculate the payments
    public double calculate(double principal, double intrest, double length)
    {
        // formula to calculate monthly payment
        intrest = intrest/100/12;
        length = length*12;
        double payment = (principal*intrest)/(1-Math.pow(1+intrest,-length));

        // round to two decimals
        payment = (double)Math.round(payment * 100) / 100;

        return payment;
    }

    // method to start the new activity and display the results
    public void displayPayment(View view)
    {
        // get the selected values from widgets
        String type = sp.getSelectedItem().toString();
        String pAmount = totalAmount.getText().toString();
        String iRate = rate.getText().toString();
        String term = years.getText().toString();

        if(pAmount.matches("") || pAmount.matches("0"))
        {
            totalAmount.setError("Mortgage Amount should not be Empty or Zero");
            return;
        }
        if(iRate.matches("") || iRate.matches("0"))
        {
            rate.setError("Interest Rate should not be Empty or Zero");
            return;
        }
        if(term.matches("") || term.matches("0"))
        {
            years.setError("No of years should not be Empty or Zero");
            return;
        }

        double principal = Double.parseDouble(pAmount);
        double intrest = Double.parseDouble(iRate);
        double length = Double.parseDouble(term);



        // call the calculate method and gets the payment back
        double payment = calculate(principal,intrest,length);

        Intent intent =new Intent(this, Payment.class);
        intent.putExtra("payment",payment);
        intent.putExtra("iRate",intrest);
        intent.putExtra("term", (int)length);
        intent.putExtra("type",type);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        totalAmount.setText("");
        rate.setText("");
        years.setText("");
    }
}