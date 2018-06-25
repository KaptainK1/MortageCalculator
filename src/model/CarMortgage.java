package model;

public class CarMortgage extends Mortgage{

    private static final int MONTHS = 12;
    private double salesTax;

    public CarMortgage(double interestRate, int purchasePrice, int termMonths, int downPayment, int creditScore, double salesTax){
        super(interestRate,purchasePrice,termMonths,downPayment,creditScore);
        this.salesTax=salesTax;
    }

    @Override
    public void calculateAmortization() {
        double i;
//        double monthlyPayments[] = new double[getTermMonths()];
        //set beginning principal to the down payment on the loan
        double p = getPurchasePrice() - getDownPayment();
        //convert interest rate into a percentage
        double r = (getInterestRate() / MONTHS) / 100;
        //run the calculatePI method to find the monthly payment
        double payment = (calculatePI()+getSalesTax());
        //var to hold the total principal paid
        double totalP;

        for (int j = 0; j < getMonthlyPayments().length; j++) {
            //set the interest by multiplying the principal by the rate
            i = p * r;
            //set the principal equal to itself minus the payment minus the interest
            p = (p - (payment - i));
            totalP = (getPurchasePrice() - p);
            setSpecificMonth(j,totalP);
        }
        setMonthlyPayments(getMonthlyPayments());
        //run the print schedule method that prints out the array
        printSchedule();
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }
}
