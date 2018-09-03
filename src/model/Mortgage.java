package model;

import Layouts.DisplayBox;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public abstract class Mortgage {

    private static final int MONTHS = 12;
    private double interestRate;
    private int purchasePrice;
    private int termMonths;
    private int downPayment;
    private int creditScore;
    private BigDecimal monthlyPayments[];
    private DisplayBox displayBox = new DisplayBox();

    public Mortgage(double interestRate, int purchasePrice, int termMonths, int downPayment, int creditScore){
        if(downPayment >= purchasePrice){
            throw new IllegalArgumentException("Congrats you don't need a loan with that down payment!");
        }
        if (interestRate > 100 || interestRate < 0.01){
            throw new IllegalArgumentException("Interest rate can't be greater than 100% or less than .1%");
        }
        if (termMonths > 360 || termMonths < 48){
            throw new IllegalArgumentException("Term months can't be greater than 30 years (360) or less than 4 years (48)");
        }
        if (purchasePrice < 10000){
            throw new IllegalArgumentException("Purchase price must be greater than $10,000");
        }
        if (creditScore > 900 || creditScore < 300){
            throw new IllegalArgumentException("Credit Score must be greater than 300 and less than 900");
        }
        this.downPayment=downPayment;
        this.interestRate=interestRate;
        this.termMonths=termMonths;
        this.purchasePrice=purchasePrice;
        this.creditScore=creditScore;
        monthlyPayments = new BigDecimal[getTermMonths()];
    }
    //Method for calculating the total monthly cost of a loans p and i
    public BigDecimal calculatePI(){
        BigDecimal monthlyPayment;
        double topEquation;
        double bottomEquation;
        double totalEquation;
        //convert interest rate into a percentage
        double r = (getInterestRate()/MONTHS)/100;
        //Equation is M= P(r + 1)^n / ((1+r)^n - 1)
        //top equation represents (r+1)^n
        //bottom equation represents ((1+r)^n - 1)
        topEquation =((r)*(Math.pow(1+r,getTermMonths())));
        bottomEquation= ((Math.pow(1+r,getTermMonths())-1));
        totalEquation=topEquation/bottomEquation;
        monthlyPayment=BigDecimal.valueOf(getPurchasePrice()).multiply(BigDecimal.valueOf(totalEquation));

        return monthlyPayment;
    }
    //method for calculating the Amortization for the loan
    //for each month calculate the interest and principle paid, then the principle is added into the array
    public void calculateAmortization(){
        BigDecimal interest;
        BigDecimal principal=new BigDecimal(getPurchasePrice());
        BigDecimal totalPrincipal=new BigDecimal(0);
        //convert interest rate into a percentage
        double r = (getInterestRate() / MONTHS) / 100;
        //run the calculatePI method to find the monthly payment
        BigDecimal payment = (calculatePI());

        for (int j = 0; j < getMonthlyPayments().length; j++) {
            //set the interest by multiplying the principal by the rate
            interest = principal.multiply(new BigDecimal(r));
            interest=interest.setScale(2,BigDecimal.ROUND_DOWN);
            //set the principal equal to itself minus the payment minus the interest
            principal=(principal.subtract(payment.subtract(interest)));
            totalPrincipal=(totalPrincipal.add(payment.subtract(interest)));

            principal=principal.setScale(2,BigDecimal.ROUND_DOWN);
            totalPrincipal=totalPrincipal.setScale(2,BigDecimal.ROUND_DOWN);

            setSpecificMonth(j,(totalPrincipal));
        }
        addPMI();
        setMonthlyPayments(getMonthlyPayments());
        //run the print schedule method that prints out the array
        printSchedule();
    }

    //need to understand how refinancing works
    public void refinance(){

    }
    //method for finding when the PMI will end if applicable
    public int returnPMIMonths(){
        double totalPaidAmount = getDownPayment();
        int PMIMonth;
        double PMI = getPurchasePrice()*.20d;

        for (int i = 0; i < getTermMonths(); i++) {
            totalPaidAmount=totalPaidAmount+monthlyPayments[i].doubleValue();
            if (totalPaidAmount>=PMI){
                PMIMonth=i+1;
                return PMIMonth;
            }
        }
        return 0;
    }

    //method to add pmi to each month
    //calls method return pmi which will determine the amount of months that will have pmi
    public void addPMI(){
        int months = returnPMIMonths();
        for (int i = 0; i < months; i++) {
            monthlyPayments[i].add(calculatePMI().add(monthlyPayments[i]));
        }
    }

    public BigDecimal calculatePMI(){
        BigDecimal pmiAmount;
        double loanAmount= getPurchasePrice()-getDownPayment();
        double lmv = loanAmount / getPurchasePrice();
        double pmiPercent = 0.00;

        //if credit score is greater than or equal to 760
        if (getCreditScore() >= 760){
            if (lmv >= .97){
                pmiPercent=(.55);
            } else if(lmv >= .95){
                pmiPercent=(.41);
            } else if(lmv >= .90){
                pmiPercent=(.30);
            } else {
                pmiPercent=(.19);
            }
        }

        //if credit score is less than 760 and greater than or equal to 740
        if (getCreditScore() < 760 && getCreditScore() >=740 ){
            if (lmv >= .97){
                pmiPercent=(.75);
            } else if(lmv >= .95){
                pmiPercent=(.59);
            } else if(lmv >= .90){
                pmiPercent=(.41);
            } else {
                pmiPercent=(.20);
            }
        }


        //if credit score is less than 740 and greater than or equal to 720
        if (getCreditScore() < 740 && getCreditScore() >=720 ){
            if (lmv >= .97){
                pmiPercent=(.95);
            } else if(lmv >= .95){
                pmiPercent=(.73);
            } else if(lmv >= .90){
                pmiPercent=(.50);
            } else {
                pmiPercent=(.23);
            }
        }

        //if credit score is less than 720 and greater than or equal to 700
        if (getCreditScore() < 720 && getCreditScore() >=700 ){
            if (lmv >= .97){
                pmiPercent=(1.15);
            } else if(lmv >= .95){
                pmiPercent=(.87);
            } else if(lmv >= .90){
                pmiPercent=(.60);
            } else {
                pmiPercent=(.27);
            }
        }

        //if credit score is less than 700 and greater than or equal to 680
        if (getCreditScore() < 700 && getCreditScore() >=680 ){
            if (lmv >= .97){
                pmiPercent=(1.40);
            } else if(lmv >= .95){
                pmiPercent=(1.08);
            } else if(lmv >= .90){
                pmiPercent=(.73);
            } else {
                pmiPercent=(.32);
            }
        }

        //if credit score is less than 680 and greater than or equal to 660
        if (getCreditScore() < 680 && getCreditScore() >=660 ){
            if (lmv >= .97){
                pmiPercent=(1.90);
            } else if(lmv >= .95){
                pmiPercent=(1.42);
            } else if(lmv >= .90){
                pmiPercent=(1.00);
            } else {
                pmiPercent=(.41);
            }
        }

        //if credit score is less than 660 and greater than or equal to 660
        if (getCreditScore() < 660 && getCreditScore() >=640 ){
            if (lmv >= .97){
                pmiPercent=(2.05);
            } else if(lmv >= .95){
                pmiPercent=(1.5);
            } else if(lmv >= .90){
                pmiPercent=(1.05);
            } else {
                pmiPercent=(.43);
            }
        }

        //if credit score is less than 640
        if (getCreditScore() < 640){
            if (lmv >= .97){
                pmiPercent=(2.25);
            } else if(lmv >= .95){
                pmiPercent=(1.61);
            } else if(lmv >= .90){
                pmiPercent=(1.10);
            } else {
                pmiPercent=(.45);
            }
        }

        pmiPercent=pmiPercent/100;
//        setPmi((pmiPercent*getPurchasePrice()/12));
        pmiAmount=BigDecimal.valueOf(pmiPercent).multiply(BigDecimal.valueOf(getPurchasePrice()).divide(BigDecimal.valueOf(12),2, BigDecimal.ROUND_FLOOR));
        pmiAmount=pmiAmount.setScale(2,BigDecimal.ROUND_FLOOR);
//        setPmi(pmiAmount.doubleValue());
        return pmiAmount;
    }

    public void yearPrinciple(int year){
        year = year*12;
        System.out.printf("%s %s %s $%.2f %s","At year",year,"you paid",monthlyPayments[year-1],"in principle");
    }

    public void printSchedule(){
        //variable to hold the monthly payment
        DecimalFormat format = new DecimalFormat("$0.00");
        BigDecimal monthlyPayment = calculatePI();
        BigDecimal interestPaid;
        BigDecimal principlePaid;
        String strMessage = "";
        //loop through the monthly payments array and print out the amount of principle paid and the amount of interest paid
        for (int i = 0; i < monthlyPayments.length; i++) {
            principlePaid=monthlyPayments[i];

            interestPaid=(monthlyPayment.multiply(BigDecimal.valueOf(i).add(BigDecimal.valueOf(1)))).subtract(principlePaid);
            //to calculate the interest paid, multiple the monthly payment by the number of iterations plus 1, then subtract that from the current array index
            System.out.printf("%s %d %s $%.2f\n %s $%.2f\n", "Total Principal paid for Month", i+1, "is:", principlePaid, "Total Interest paid is:", interestPaid);
            strMessage+=(" Total Principal paid for Month " + (i+1) + " is: " + format.format(monthlyPayments[i]) + " Total Interest paid is: " + format.format(interestPaid) + "\n");
        }
        displayBox.display("Amortization Schedule",strMessage,"model/main_style.css");
    }

//Setters and Getters
    public double getInterestRate() {
        return interestRate;
    }

    public void setSpecificMonth(int position, BigDecimal value){
        monthlyPayments[position]=value;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(int termMonths) {
        this.termMonths = termMonths;
    }

    public int getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(int downPayment) {
        this.downPayment = downPayment;
    }

    public BigDecimal[] getMonthlyPayments() {
        return monthlyPayments;
    }

    public void setMonthlyPayments(BigDecimal[] monthlyPayments) {
        this.monthlyPayments = monthlyPayments;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

}
