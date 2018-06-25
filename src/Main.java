import model.CarMortgage;
import model.ConventionalMortgage;
import model.Mortgage;

public class Main {

    public static void main(String args[]){
        ConventionalMortgage mortgage = new ConventionalMortgage(4.625d,172000,360,6880,700);
        mortgage.calculateAmortization();
        mortgage.yearPrinciple(5);
        CarMortgage carMortgage = new CarMortgage(1.99,12000, 60,2000, 700, .05);

        Mortgage mortgages[] = new Mortgage[2];
        mortgages[0]=mortgage;
        mortgages[1]=carMortgage;
        System.out.println();
        System.out.println(Mortgage.totalMonthlyPayments(mortgages));
    }
}
