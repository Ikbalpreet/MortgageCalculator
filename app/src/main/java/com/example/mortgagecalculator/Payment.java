package com.example.mortgagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // get the views
        TextView amount = findViewById(R.id.txtPayment);
        TextView rate = findViewById(R.id.txtIRate);
        TextView term = findViewById(R.id.txtTerm);

        // recieve the intent here created in previous activity
        Intent intent = getIntent();
        double pAmount = intent.getExtras().getDouble("payment");
        double iRate = intent.getExtras().getDouble("iRate");
        int lTerm = intent.getExtras().getInt("term");
        String type = intent.getExtras().getString("type");

        amount.setText("Your "+type+" Payment: "+pAmount);
        rate.setText("Interest Rate: "+iRate+"%");
        term.setText("Loan Period: "+lTerm+" Years");

    }

    public void goBack(View view)
    {
        finish();
    }
}