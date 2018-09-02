package model;

import java.math.BigDecimal;

public class Tester{
    public static void main (String args[]){

        ConventionalMortgage mortaage = new ConventionalMortgage(4.5,172000,360,6880,750,200);
        mortaage.calculateAmortization();

    }

}
