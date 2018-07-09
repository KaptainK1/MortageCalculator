import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.*;
import javafx.*;

import javax.xml.soap.Text;
import java.util.Scanner;

public class Main  extends Application {
    private int purchasePrice;
    private int termMonths;
    private int downPayment;
    private int creditScore;
    private double escrow;
    private double interestRate;

    public static void main(String args[]){
        //create the UI
        launch(args);

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

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Mortgage Calculator");
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(300);

        Button submitButton = new Button();
        submitButton.setText("Submit");
        //create all text fields
        TextField textPurchasePrice = new TextField("Enter Purchase Price: ");
        TextField textTermMonths = new TextField("Enter Term Months: ");
        TextField textDownPayment = new TextField("Enter Down Payment: ");
        TextField textCreditScore = new TextField("Enter Credit Score: ");
        TextField textEscrow = new TextField("Enter Escrow: ");
        TextField textInterestRate = new TextField("Enter Interest Rate: ");

        //add the method of click events for all text fields
        clickEvents(textCreditScore);
        clickEvents(textDownPayment);
        clickEvents(textEscrow);
        clickEvents(textInterestRate);
        clickEvents(textPurchasePrice);
        clickEvents(textTermMonths);


        //set to false until all boxes are filled
//        button.setVisible(false);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(submitButton,textTermMonths,textPurchasePrice,textInterestRate,textEscrow,textDownPayment,textCreditScore);

        Scene scene1 = new Scene(layout, 300,300);
        primaryStage.setScene(scene1);
        primaryStage.sizeToScene();
        primaryStage.show();





        //enter code from main

    }

    public void clickEvents(TextField field){
        //Creating the mouse event handler
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                field.clear();
            }
        };
        field.addEventFilter(MouseEvent.MOUSE_CLICKED,eventHandler);
    }

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

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public double getEscrow() {
        return escrow;
    }

    public void setEscrow(double escrow) {
        this.escrow = escrow;
    }
}
