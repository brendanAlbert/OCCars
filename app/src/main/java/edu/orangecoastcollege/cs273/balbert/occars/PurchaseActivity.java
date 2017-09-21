package edu.orangecoastcollege.cs273.balbert.occars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.NumberFormat;
import java.util.Locale;

public class PurchaseActivity extends AppCompatActivity {

    private static final NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());

    // Connections to VIEW
    private EditText mPriceEditText;
    private EditText mDownPaymentEditText;
    private RadioButton mThreeYearRadioButton;
    private RadioButton mFourYearRadioButton;
    private RadioButton mFiveYearRadioButton;
    private Button mLoanReportButton;


    // Connection to the MODEL
    private CarLoan mCarLoan = new CarLoan();

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
