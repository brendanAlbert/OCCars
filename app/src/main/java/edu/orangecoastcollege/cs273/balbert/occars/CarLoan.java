package edu.orangecoastcollege.cs273.balbert.occars;

/**
 * CarLoan is a Class representing the Model for OC Cars.
 * The class has member variables representing the price of the car,
 * how much the down payment is, the number of years of the loan term,
 * and the state sales tax.
 *
 * The methods include getters for the price, term, tax, borrowed amount and total amount.
 * The setters set price, down payment amount, years of the loan term
 * There are methods to calculate tax, interest, and monthly payment amounts.
 *
 * Created by balbert on 9/14/2017.
 */

public class CarLoan {

    private static final double STATE_TAX = 0.08;
    private double mPrice;
    private double mDownPayment;
    private int mTerm;

    /**
     *
     * @return price.
     */
    public double getPrice() {
        return mPrice;
    }

    /**
     *
     * @param price sets the price received from user.
     */
    public void setPrice(double price) {
        mPrice = price;
    }

    /**
     *
     * @return the downPayment amount assuming it has been provided by the user.
     */
    public double getDownPayment() { return mDownPayment; }

    /**
     *
     * @param downPayment sets the downPayment to what the user provided.
     */
    public void setDownPayment(double downPayment) {
        mDownPayment = downPayment;
    }

    /**
     *
     * @return the number of years of the term.
     */
    public int getTerm() {
        return mTerm;
    }

    /**
     *
     * @param term sets the term to 3, 4 or 5 depending on which radio button the user chose.
     */
    public void setTerm(int term) {
        mTerm = term;
    }

    /**
     *
     * @return the calculated tax amount, price * state sales tax.
     */
    public double taxAmount()
    {
        return mPrice * STATE_TAX;
    }

    /**
     *
     * @return the total amount, price of the car plus added tax.
     */
    public double totalAmount()
    {
        return mPrice + taxAmount();
    }

    /**
     *
     * @return the sum of the car price and added tax, minus the down payment.
     */
    public double borrowedAmount()
    {
        return (mPrice - mDownPayment) + taxAmount();
    }

    /**
     *
     * @return computed interest amount which is a product of the borrowed amount and
     *  the interest rate which depends on whether the user chose a 3, 4 or 5 year term loan.
     *
     */
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

    /**
     *
     * @return monthly payment calculation: borrowed amount plus interest,
     * this sum is divided by the product of the number of years of the loan term and 12.
     */
    public double monthlyPayment()
    {
        return (borrowedAmount() + interestAmount()) / (mTerm * 12);
    }
}
