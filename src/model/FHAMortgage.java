package model;

public class FHAMortgage extends Mortgage{

    public FHAMortgage(double interestRate, int purchasePrice, int termMonths, int downPayment, int creditScore){
        super(interestRate,purchasePrice,termMonths,downPayment,creditScore);
    }

    @Override
    public double calculatePI() {
        return 0;
    }

    @Override
    public void calculateAmortization() {

    }
}
