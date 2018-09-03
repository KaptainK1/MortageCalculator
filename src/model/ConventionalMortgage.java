package model;

public class ConventionalMortgage extends Mortgage{

    private double escrow;
    public ConventionalMortgage(double interestRate, int purchasePrice, int termMonths, int downPayment, int creditScore, double escrow){
        super(interestRate,purchasePrice,termMonths,downPayment,creditScore);
        this.escrow=escrow;
    }

    public double getEscrow() {
        return escrow;
    }

    public void setEscrow(double escrow) {
        this.escrow = escrow;
    }
}