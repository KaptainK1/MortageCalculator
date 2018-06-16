package model;

public class FHAMortgage extends Mortgage{

    public FHAMortgage(double interestRate, int purchasePrice, int termMonths, int creditScore, int downPayment){
        super(interestRate,purchasePrice,termMonths,creditScore, downPayment);
    }

    @Override
    public double calculatePI() {
        return 0;
    }

    @Override
    public void calculateAmortization() {

    }
}
