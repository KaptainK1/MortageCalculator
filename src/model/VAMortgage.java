package model;

public class VAMortgage extends Mortgage{

    private double escrow;
    public VAMortgage(double interestRate, int purchasePrice, int termMonths, int downPayment, int creditScore, double escrow){
        super(interestRate,purchasePrice,termMonths,downPayment,creditScore);
        this.escrow=escrow;
    }

    @Override
    public double calculatePI() {
        return 0;
    }

    @Override
    public void calculateAmortization() {

    }

    public double getEscrow() {
        return escrow;
    }

    public void setEscrow(double escrow) {
        this.escrow = escrow;
    }
}
