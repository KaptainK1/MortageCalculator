import model.Mortgage;

public class Main {

    public static void main(String args[]){
        Mortgage mortgage = new Mortgage(4.625d,172000,360,6880,730);
        mortgage.calculateAmortization();
        mortgage.yearPrinciple(5);
    }
}
