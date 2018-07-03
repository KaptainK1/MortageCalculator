import model.CarMortgage;
import model.ConventionalMortgage;
import model.Mortgage;

import java.util.Scanner;

public class Main {

    public static void main(String args[]){

        String userInput;
        String array[];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter in the format InterestRate, PurchasePrice, Term, DownPayment, CreditScore, Escrow");
        userInput=scanner.nextLine();
        array=userInput.split(",");
        // throw an exception when the array length doesn't equal 5
        if (array.length != 6) {
            throw new IllegalArgumentException("Not enough parameters or commas entered");
        }

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

//        ConventionalMortgage mortgage = new ConventionalMortgage(4.625d,172000,360,6880,700, 248.35);
        ConventionalMortgage mortgage = new ConventionalMortgage(Double.valueOf(array[0]), Integer.valueOf(array[1]), Integer.valueOf(array[2]),
                                                                    Integer.valueOf(array[3]), Integer.valueOf(array[4]), Double.valueOf(array[5]));
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
