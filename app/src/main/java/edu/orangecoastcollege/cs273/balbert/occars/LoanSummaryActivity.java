package edu.orangecoastcollege.cs273.balbert.occars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoanSummaryActivity extends AppCompatActivity {

    private TextView mDisplayLoanSummaryTextView;
    private Button mReturnToDataEntryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_summary);

        mDisplayLoanSummaryTextView = (TextView) findViewById(R.id.displayLoanSummaryTextView);

        // Receive the Intent (from PurchaseActivity)
        Intent intentFromPurchase = getIntent();

        // Populate the text view with data
        String report = intentFromPurchase.getStringExtra("loanReport");

        // Fill your TextView with data from the report
        mDisplayLoanSummaryTextView.setText(report);

        // Create connection to button for returning to previous activity, PurchaseActivity
        mReturnToDataEntryButton = (Button) findViewById(R.id.returnToDataEntry);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };

        mReturnToDataEntryButton.setOnClickListener(listener);
    }
}
