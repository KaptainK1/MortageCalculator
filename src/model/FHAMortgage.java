package model;

public class FHAMortgage extends Mortgage{
    private double escrow;
    public FHAMortgage(double interestRate, int purchasePrice, int termMonths, int downPayment, int creditScore, double escrow){
        super(interestRate,purchasePrice,termMonths,downPayment,creditScore);
        this.escrow=escrow;
    }

    @Override
    public void calculateAmortization() {
        double i;
//        double monthlyPayments[] = new double[getTermMonths()];
        //set beginning principal to the down payment on the loan
        double p = super.getPurchasePrice() - super.getDownPayment() + this.calculatePMI();
        //convert interest rate into a percentage
        double r = (super.getInterestRate() / 12) / 100;
        //run the calculatePI method to find the monthly payment
        double payment = (super.calculatePI() + calculateMI());
        //var to hold the total principal paid
        double totalP;

        for (int j = 0; j < super.getMonthlyPayments().length; j++) {
            //set the interest by multiplying the principal by the rate
            i = p * r;
            //set the principal equal to itself minus the payment minus the interest
            p = p - (payment - i);
            totalP = (super.getPurchasePrice() - p);
            super.setSpecificMonth(j,totalP);
        }
//        addPMI();
        super.setMonthlyPayments(super.getMonthlyPayments());
        //run the print schedule method that prints out the array
        printSchedule();
    }

    @Override
    public double calculatePMI(){
        double insurancePremium = 0.0175d;
        return super.getPurchasePrice() * insurancePremium;
    }

    private double calculateMI(){
        double mortgageInsurance = 0.0075d;
        return (super.calculatePI()*mortgageInsurance)/12;
    }

    public double getEscrow() {
        return escrow;
    }

    public void setEscrow(double escrow) {
        this.escrow = escrow;
    }
}
