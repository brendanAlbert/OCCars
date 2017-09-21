package edu.orangecoastcollege.cs273.balbert.occars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 *  LoanSummaryActivity.java is the Controller for the activity_loan_summary.xml View.
 *  This controller receives the report Intent from PurchaseActivity,
 *  and sets the TextView to the string from the provided report.
 *
 *  There is also a button to go back if the user wishes to change one of the inputs.
 */
public class LoanSummaryActivity extends AppCompatActivity {

    private TextView mDisplayLoanSummaryTextView;
    private Button mReturnToDataEntryButton;

    /**
     *
     * onCreate(Bundle)
     * @param savedInstanceState contains any information saved from the previous context,
     *                           such as when the user minimizes the app and leave it running
     *                           in the background.  The previous state including:
     *                           numbers provided and which activity was being looked at
     *                           will be there when the user returns.
     *
     *   We instantiate a TextView and set it with the information provided from the Intent
     *                           sent over by PurchaseActivity.java.
     *
     *   A button is provided for the users convenience to return to the previous Activity,
     *                           so they can change the numbers if they'd like.
     */
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
