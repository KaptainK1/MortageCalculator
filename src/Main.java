import model.ConventionalMortgage;

public class Main {

    public static void main(String args[]){
        ConventionalMortgage mortgage = new ConventionalMortgage(4.625d,172000,360,730,6880);
        mortgage.calculateAmortization();
        mortgage.yearPrinciple(5);
    }
}
