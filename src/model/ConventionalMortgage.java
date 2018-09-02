package model;

import java.math.BigDecimal;

public class ConventionalMortgage extends Mortgage{

    private static final int MONTHS=12;
    private double escrow;
    public ConventionalMortgage(double interestRate, int purchasePrice, int termMonths, int downPayment, int creditScore, double escrow){
        super(interestRate,purchasePrice,termMonths,downPayment,creditScore);
        this.escrow=escrow;
    }
    //method for calculating the Amortization for the loan
    //for each month calculate the interest and principle paid, then the principle is added into the array
//    @Override
//    public void calculateAmortization() {
//        BigDecimal interest;
//        BigDecimal principal=new BigDecimal(getPurchasePrice()-getDownPayment());
//        BigDecimal totalPrincipal=new BigDecimal(0);
////        double i;
////        double monthlyPayments[] = new double[getTermMonths()];
//        //set beginning principal to the down payment on the loan
////        double p = getPurchasePrice() - getDownPayment();
//        //convert interest rate into a percentage
//        double r = (getInterestRate() / MONTHS) / 100;
//        //run the calculatePI method to find the monthly payment
//        BigDecimal payment = (calculatePI());
//        //var to hold the total principal paid
////        double totalP=0;
//
//        for (int j = 0; j < getMonthlyPayments().length; j++) {
//            //set the interest by multiplying the principal by the rate
////            i = p * r;
//            //set the principal equal to itself minus the payment minus the interest
////            p = p - (payment - interest.doubleValue());
////            totalP = totalP+(payment-i);
//
//            interest = principal.multiply(new BigDecimal(r));
//            interest=interest.setScale(2,BigDecimal.ROUND_FLOOR);
//
//            principal=(principal.subtract(payment.subtract(interest)));
//            totalPrincipal=(totalPrincipal.add(payment.subtract(interest)));
//
//            principal=principal.setScale(2,BigDecimal.ROUND_FLOOR);
//            totalPrincipal=totalPrincipal.setScale(2,BigDecimal.ROUND_FLOOR);
//
//            setSpecificMonth(j,(totalPrincipal));
//
//        }
//        addPMI();
//        setMonthlyPayments(getMonthlyPayments());
//        //run the print schedule method that prints out the array
//        printSchedule();
//    }

    public double getEscrow() {
        return escrow;
    }

    public void setEscrow(double escrow) {
        this.escrow = escrow;
    }
}