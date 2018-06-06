package model;

public class Mortgage {

    private static final int MONTHS=12;

    private double interestRate;
    private int purchasePrice;
    private int termMonths;
    private int downPayment;
    private int creditScore;
    private double monthlyPayments[];
    private double pmi;

    public Mortgage(double interestRate, int purchasePrice, int termMonths, int creditScore){
        this.downPayment=0;
        this.interestRate=interestRate;
        this.termMonths=termMonths;
        this.purchasePrice=purchasePrice;
        this.creditScore=creditScore;
    }

    public Mortgage(double interestRate, int purchasePrice, int termMonths, int downPayment, int creditScore){
        this.downPayment=downPayment;
        this.interestRate=interestRate;
        this.termMonths=termMonths;
        this.purchasePrice=purchasePrice;
        this.creditScore=creditScore;
    }
//Method for calculating the total monthly cost of a loans p and i
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
    public void calculateAmortization() {
        double i;
        //set beginning principal to the down payment on the loan
        double p = getPurchasePrice() - getDownPayment();
        //convert interest rate into a percentage
        double r = (getInterestRate() / MONTHS) / 100;
        //run the calculatePI method to find the monthly payment
        double payment = calculatePI();
        //var to hold the total principal paid
        double totalP;
        //array to hold the principal paid for each month
        monthlyPayments = new double[termMonths];
        //call method to add PMI

            for (int j = 0; j < monthlyPayments.length; j++) {
                //set the interest by multiplying the principal by the rate
                i = p * r;
                //set the principal equal to itself minus the payment minus the interest
                p = p - (payment - i);
                totalP = (getPurchasePrice() - p);
                monthlyPayments[j] = totalP;
            }
            //add the pmi amount to each month that pmi is needed
            //run the print schedule method that prints out the array
            addPMI();
            printSchedule();
        }

    //method for finding when the PMI will end if applicable
    private int returnPMIMonths(){
        double totalPaidAmount = getDownPayment();
        int PMIMonth;
        double PMI = getPurchasePrice()*.20d;

        for (int i = 0; i < getTermMonths(); i++) {
            totalPaidAmount=totalPaidAmount+monthlyPayments[i];
            if (totalPaidAmount>=PMI){
                PMIMonth=i+1;
                return PMIMonth;
            }
        }
        return 0;
    }

    //method to add pmi to each month
    //calls method return pmi which will determine the amount of months that will have pmi
    private void addPMI(){
        int months = returnPMIMonths();
        for (int i = 0; i < months; i++) {
            monthlyPayments[i]+=calculatePMI();
        }
    }

    public double calculatePMI(){
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
        setPmi((pmiPercent*getPurchasePrice()/MONTHS));
        return getPmi();
    }

    public void yearPrinciple(int year){
        year = year*12;
        System.out.printf("%s %s %s $%.2f %s","At year",year,"you paid",monthlyPayments[year-1],"in principle");
    }

    public void printSchedule(){
        //variable to hold the monthly payment
        double monthlyPayment = calculatePI();
        double interestPaid;
        double principlePaid;
        //loop through the monthly payments array and print out the amount of principle paid and the amount of interest paid
        for (int i = 0; i < monthlyPayments.length; i++) {
            principlePaid=monthlyPayments[i] - getDownPayment();
            interestPaid=(monthlyPayment*(i+1))-(principlePaid);
            //to calculate the interest paid, multiple the monthly payment by the number of iterations plus 1, then subtract that from the current array index
            System.out.printf("%s %d %s $%.2f\n %s $%.2f\n", "Total Principal paid for Month", i+1, "is:", monthlyPayments[i], "Total Interest paid is:", interestPaid);
        }
    }

//Setters and Getters
    public double getInterestRate() {
        return interestRate;
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

    public double[] getMonthlyPayments() {
        return monthlyPayments;
    }

    public void setMonthlyPayments(double[] monthlyPayments) {
        this.monthlyPayments = monthlyPayments;
    }

    public double getPmi() {
        return pmi;
    }

    public void setPmi(double pmi) {
        this.pmi = pmi;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
}
