import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;

public class Main  extends Application {
    private int purchasePrice;
    private int termMonths;
    private int downPayment;
    private int creditScore;
    private double escrow;
    private double interestRate;
    Main_Layout stage;
    private AlertBox alertBox = new AlertBox();

    public static void main(String args[]){
        //create the UI
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = new Main_Layout();
        primaryStage = stage.createMainLayout();
        primaryStage.show();
        submit(stage.getSubmitButton(), stage);
    }

    public void convertValues(Main_Layout layout){
        try {
            setPurchasePrice((Integer.parseInt(layout.getTextPurchasePrice().getText().trim())));
            System.out.println("credit score "+getPurchasePrice());
            setTermMonths((Integer.parseInt(layout.getTextTermMonths().getText().trim())));
            System.out.println("term "+getTermMonths());
            setCreditScore((Integer.parseInt(layout.getTextCreditScore().getText().trim())));
            System.out.println("credit score"+getCreditScore());
            setDownPayment((Integer.parseInt(layout.getTextDownPayment().getText().trim())));
            System.out.println(getDownPayment());
        } catch (NumberFormatException e){
            alertBox.display("Incorrect Integer value entered", "Incorrect Integer value entered for either Purchase Price, Term, Credit Score, and or Down Payment. Please Check");
            e.printStackTrace();
        }
        try {
            setInterestRate((Double.parseDouble(layout.getTextInterestRate().getText().trim())));
            System.out.println(getInterestRate());
        } catch (NumberFormatException e){
            alertBox.display("Incorrect Decimal value entered", "Incorrect Decimal value entered for either Escrow and Interest Rate. Please Check");
            e.printStackTrace();
        }
        setEscrow((Double.parseDouble(layout.getTextEscrow().getText().trim())));
        System.out.println(getEscrow());
    }

    public void submit(Button button, Main_Layout layout){
        button.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                boolean isNotError;
                convertValues(layout);
                switch (layout.selectionLoanType()){
                    case 0:
                        ConventionalMortgage conventionalMortgage = new ConventionalMortgage(getInterestRate(),getPurchasePrice(),getTermMonths(),getDownPayment(),getCreditScore(),getEscrow());
                        conventionalMortgage.calculateAmortization();
                    case 1:
                        FHAMortgage fhaMortgage = new FHAMortgage(getInterestRate(),getPurchasePrice(),getTermMonths(),getDownPayment(),getCreditScore(),getEscrow());
                        fhaMortgage.calculateAmortization();
                    case 2:
                        VAMortgage vaMortgage = new VAMortgage(getInterestRate(),getPurchasePrice(),getTermMonths(),getDownPayment(),getCreditScore(),getEscrow());
                        vaMortgage.calculateAmortization();
                    case 3:
                        CarMortgage carMortgage = new CarMortgage(getInterestRate(),getPurchasePrice(),getTermMonths(),getDownPayment(),getCreditScore());
                        carMortgage.calculateAmortization();
                }
            }
        }));
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
