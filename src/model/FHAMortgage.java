package model;

import java.math.BigDecimal;

public class FHAMortgage extends Mortgage{
    private double escrow;
    public FHAMortgage(double interestRate, int purchasePrice, int termMonths, int downPayment, int creditScore, double escrow){
        super(interestRate,purchasePrice,termMonths,downPayment,creditScore);
        this.escrow=escrow;
    }

//    @Override
//    public void calculateAmortization() {
//        double i;
//
//        BigDecimal interest;
//        BigDecimal principal=new BigDecimal(getPurchasePrice()-getDownPayment());
//        BigDecimal totalPrincipal=new BigDecimal(0);
////        double monthlyPayments[] = new double[getTermMonths()];
//        //set beginning principal to the down payment on the loan
////        double p = super.getPurchasePrice() - super.getDownPayment() + this.calculatePMI();
//        //convert interest rate into a percentage
//        double r = (super.getInterestRate() / 12) / 100;
//        //run the calculatePI method to find the monthly payment
//        BigDecimal payment = (super.calculatePI().add((calculateMI())));
//        //var to hold the total principal paid
//        double totalP;
//
//        for (int j = 0; j < getMonthlyPayments().length; j++) {
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
////        addPMI();
//        super.setMonthlyPayments(super.getMonthlyPayments());
//        //run the print schedule method that prints out the array
//        printSchedule();
//    }

    @Override
    public BigDecimal calculatePMI(){
        double insurancePremium = 0.0175d;
        return BigDecimal.valueOf(super.getPurchasePrice()).multiply(BigDecimal.valueOf(insurancePremium));
    }

    private BigDecimal calculateMI(){
        double mortgageInsurance = 0.0075d;
        return (super.calculatePI().multiply(BigDecimal.valueOf(mortgageInsurance)).divide(BigDecimal.valueOf(12),2,BigDecimal.ROUND_FLOOR));
    }

    public double getEscrow() {
        return escrow;
    }

    public void setEscrow(double escrow) {
        this.escrow = escrow;
    }
}
