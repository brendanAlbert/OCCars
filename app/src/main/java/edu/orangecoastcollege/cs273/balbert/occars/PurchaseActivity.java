package edu.orangecoastcollege.cs273.balbert.occars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * PurchaseActivity can be thought of as the main Controller for OC Cars.
 * PurchaseActivity is a class which represents the entry point for the app.
 * The controller object instantiated from this class creates all
 * the buttons, views and images seen by the View first seen when you open the app,
 * activity_purchase.xml.
 *
 * In this Controller, we provide a way to format the currency amount entered by the user.
 *
 * We instantiate and create connections between the various TextViews and EditTexts in the View.
 * When the user enters information into the EditTexts or interacts with the RadioButton group,
 * this Controller takes that information and sends it to CarLoan.java to update the Model, and
 * the updated information is routed back to the View.
 */
public class PurchaseActivity extends AppCompatActivity {

    private static final NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());

    // Connections to VIEW
    private EditText mPriceEditText;
    private EditText mDownPaymentEditText;
    private RadioButton mThreeYearRadioButton;
    private RadioButton mFourYearRadioButton;
    private RadioButton mFiveYearRadioButton;

    // Connection to the MODEL
    private CarLoan mCarLoan = new CarLoan();


    /**
     * onCreate(Bundle) is where we wire up connections to
     * the EditTexts and RadioButtons in the View.
     *
     * @param savedInstanceState saves the state the app was in when the user
     *                           sent it to the background.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        // Use findViewById to connect controller to each view
        mPriceEditText = (EditText) findViewById(R.id.carPriceEditText);
        mDownPaymentEditText = (EditText) findViewById(R.id.downPaymentEditText);
        mThreeYearRadioButton = (RadioButton) findViewById(R.id.threeYearRadioButton);
        mFourYearRadioButton = (RadioButton) findViewById(R.id.fourYearRadioButton);
        mFiveYearRadioButton = (RadioButton) findViewById(R.id.fiveYearRadioButton);

    }

    private void collectCarLoanData()
    {
        mCarLoan.setPrice(Double.parseDouble(mPriceEditText.getText().toString()));
        mCarLoan.setDownPayment(Double.parseDouble(mDownPaymentEditText.getText().toString()));

        if(mThreeYearRadioButton.isChecked())
            mCarLoan.setTerm(3);
        else if(mFourYearRadioButton.isChecked())
            mCarLoan.setTerm(4);
        else if(mFiveYearRadioButton.isChecked())
            mCarLoan.setTerm(5);
    }

    /**
     * When the user taps the (Loan Report) button, the Model is updated (using a helper method)
     * with the user's data.  The Model returns new calculated data.  Then a report is generated
     * in the form of a long string with the user's unique information filled in.  This report
     * is sent as an Intent to the next Activity, LoanSummaryActivity.java.
     * @param v
     */
    protected void reportSummary(View v) {
        collectCarLoanData();

        String report;
        report  = getText(R.string.report_line1).toString() + currency.format(mCarLoan.monthlyPayment());
        report += getText(R.string.report_line2).toString() + currency.format(mCarLoan.getPrice());
        report += getText(R.string.report_line3).toString() + currency.format(mCarLoan.getDownPayment());
        report += getText(R.string.report_line4).toString() + currency.format(mCarLoan.taxAmount());
        report += getText(R.string.report_line5).toString() + currency.format(mCarLoan.totalAmount());
        report += getText(R.string.report_line6).toString() + currency.format(mCarLoan.borrowedAmount());
        report += getText(R.string.report_line7).toString() + currency.format(mCarLoan.interestAmount());
        report += getText(R.string.report_line8).toString() + " " + mCarLoan.getTerm() + " years.";

        report += getText(R.string.report_line9).toString();
        report += getText(R.string.report_line10).toString();
        report += getText(R.string.report_line11).toString();


        // Intents start new activities and can share data with them
        Intent launchLoanSummary = new Intent(this, LoanSummaryActivity.class);

        // Put data into the Intent for Loan Summary to receive
        launchLoanSummary.putExtra("loanReport", report);

        // Start the second activity
        startActivity(launchLoanSummary);
    }


}
