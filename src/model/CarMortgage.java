package model;

public class CarMortgage extends Mortgage {

    public CarMortgage(double interestRate, int purchasePrice, int termMonths, int downPayment, int creditScore) {
        super(interestRate, purchasePrice, termMonths, downPayment, creditScore);
    }
}
