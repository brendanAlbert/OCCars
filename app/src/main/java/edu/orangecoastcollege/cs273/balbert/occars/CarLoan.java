package edu.orangecoastcollege.cs273.balbert.occars;

/**
 * Created by balbert on 9/14/2017.
 */

public class CarLoan {

    private static final double STATE_TAX = 0.08;
    private double mPrice;
    private double mDownPayment;
    private int mTerm;

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public double getDownPayment() {
        return mDownPayment;
    }

    public void setDownPayment(double downPayment) {
        mDownPayment = downPayment;
    }

    public int getTerm() {
        return mTerm;
    }

    public void setTerm(int term) {
        mTerm = term;
    }

    public double taxAmount()
    {
        return mPrice * STATE_TAX;
    }

    public double totalAmount()
    {
        return mPrice + taxAmount();
    }

    public double borrowedAmount()
    {
        return (mPrice - mDownPayment) + taxAmount();
    }

    public double interestAmount()
    {
        /*
            3 year APR = 4.62%;
            4 year APR = 4.19%;
            5 year APR = 4.16%;
         */
        double interestRate;
        switch(mTerm)
        {
            case 3:
                interestRate = 0.0462;
                break;
            case 4:
                interestRate = 0.0419;
                break;
            case 5:
                interestRate = 0.0416;
                break;
            default:
                // this should never be used
                interestRate = 0.0;
                break;
        }
        return borrowedAmount() * interestRate;
    }

    public double monthlyPayment()
    {
        return (borrowedAmount() + interestAmount()) / (mTerm * 12);
    }
}
