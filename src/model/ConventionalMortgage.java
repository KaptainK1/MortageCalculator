package model;

public class ConventionalMortgage extends Mortgage{

    private static final int MONTHS=12;

    public ConventionalMortgage(double interestRate, int purchasePrice, int termMonths, int creditScore, int downPayment){
        super(interestRate,purchasePrice,termMonths,creditScore, downPayment);
    }
    //Method for calculating the total monthly cost of a loans p and i
    @Override
    public double calculatePI(){
        double monthlyPayment;
        double topEquation;
        double bottomEquation;
        //convert interest rate into a percentage
        double r = (getInterestRate()/MONTHS)/100;
        //Equation is M= P(r + 1)^n / ((1+r)^n - 1)
        //top equation represents (r+1)^n
        //bottom equation represents ((1+r)^n - 1)
        topEquation =((r)*(Math.pow(1+r,getTermMonths())));
        bottomEquation= ((Math.pow(1+r,getTermMonths())-1));
        monthlyPayment=getPurchasePrice()*(topEquation/bottomEquation);
        return monthlyPayment;
    }
    //method for calculating the Amortization for the loan
//for each month calculate the interest and principle paid, then the principle is added into the array
    @Override
    public void calculateAmortization() {
        double i;
        double monthlyPayments[] = new double[getTermMonths()];
        //set beginning principal to the down payment on the loan
        double p = getPurchasePrice() - getDownPayment();
        //convert interest rate into a percentage
        double r = (getInterestRate() / MONTHS) / 100;
        //run the calculatePI method to find the monthly payment
        double payment = calculatePI();
        //var to hold the total principal paid
        double totalP;
        //call method to add PMI

        for (int j = 0; j < monthlyPayments.length; j++) {
            //set the interest by multiplying the principal by the rate
            i = p * r;
            //set the principal equal to itself minus the payment minus the interest
            p = p - (payment - i);
            totalP = (getPurchasePrice() - p);
            monthlyPayments[j] = totalP;
        }
        addPMI();
        setMonthlyPayments(monthlyPayments);
        //run the print schedule method that prints out the array
        printSchedule();
    }
}