import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main  extends Application {
    private int purchasePrice;
    private int termMonths;
    private int downPayment;
    private int creditScore;
    private double escrow;
    private double interestRate;
//    public Logger log;
//
//    Stage stage;
//    Scene mainScene;
//    Button submitButton;
//    TextField textPurchasePrice;
//    TextField textTermMonths;
//    TextField textDownPayment;
//    TextField textEscrow;
//    TextField textInterestRate;
//    TextField textCreditScore;
    //ListView<String> list = new ListView<String>();
        private AlertBox alertBox = new AlertBox();


    public static void main(String args[]){
        //create the UI
        launch(args);
//        String userInput;
//        String array[];
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter in the format InterestRate, PurchasePrice, Term, DownPayment, CreditScore, Escrow");
//        userInput=scanner.nextLine();
//        array=userInput.split(",");
//        // throw an exception when the array length doesn't equal 5
//        if (array.length != 6) {
//            throw new IllegalArgumentException("Not enough parameters or commas entered");
//        }
//
//        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[i]);
//        }
//
////        ConventionalMortgage mortgage = new ConventionalMortgage(4.625d,172000,360,6880,700, 248.35);
//        ConventionalMortgage mortgage = new ConventionalMortgage(Double.valueOf(array[0]), Integer.valueOf(array[1]), Integer.valueOf(array[2]),
//                                                                    Integer.valueOf(array[3]), Integer.valueOf(array[4]), Double.valueOf(array[5]));
//        mortgage.calculateAmortization();
//        mortgage.yearPrinciple(5);
//        CarMortgage carMortgage = new CarMortgage(1.99,12000, 60,2000, 700);
//
//        Mortgage mortgages[] = new Mortgage[2];
//        mortgages[0]=mortgage;
//        mortgages[1]=carMortgage;
//        System.out.println();
//        System.out.println(Mortgage.totalMonthlyPayments(mortgages));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main_Layout stage = new Main_Layout();
        primaryStage = stage.createMainLayout();
//        window=primaryStage;
//        window.setTitle("Mortgage Calculator");
//        window.setMinHeight(300);
//        window.setMinWidth(300);
//
//        //create the button
//        submitButton = new Button();
//        submitButton.setText("Submit");
//        //run the submit function when the button is clicked
//        submit(submitButton);
//
//        //create labels
//        Label labelPurchasePrice = new Label("Enter Purchase Price: ");
//        Label labelTermMonths = new Label("Enter Term Months: ");
//        Label labelDownPayment = new Label("Enter Down Payment: ");
//        Label labelCreditScore = new Label("Enter Credit Score: ");
//        Label labelEscrow = new Label("Enter Escrow: ");
//        Label labelInterestRate = new Label("Enter Interest Rate: ");
//        Label labelLoanType = new Label("Select the Loan Type");
//
//        //create list for loan objects
//        ObservableList<String> items =FXCollections.observableArrayList (
//                "Conventional", "FHA", "VA", "Car");
//        list.setItems(items);
//        list.setOrientation(Orientation.HORIZONTAL);
//
//        //create all text fields
//        textPurchasePrice = new TextField();
//        textTermMonths = new TextField();
//        textDownPayment = new TextField();
//        textCreditScore = new TextField();
//        textEscrow = new TextField();
//        textInterestRate = new TextField();
//
//        //add the method of click events for all text fields
//        clickEvents(textCreditScore);
//        clickEvents(textDownPayment);
//        clickEvents(textEscrow);
//        clickEvents(textInterestRate);
//        clickEvents(textPurchasePrice);
//        clickEvents(textTermMonths);
//        //set to false until all boxes are filled
////        button.setVisible(false);
//
//        //create the layout using vbox
//        VBox layout = new VBox(10);
//        //add elements to the layout
//        layout.getChildren().addAll(labelPurchasePrice,textPurchasePrice,labelDownPayment,textDownPayment,labelTermMonths,textTermMonths,labelInterestRate,textInterestRate,
//                                        labelEscrow,textEscrow, labelCreditScore,textCreditScore,labelLoanType,list,submitButton);
//        //create the scene, then add the scene to the window and show it
//        mainScene = new Scene(layout, 400,500);
//        window.setScene(mainScene);
//        window.sizeToScene();
        submit(stage.getSubmitButton(),stage.selectionLoanType(), stage);
        primaryStage.show();
    }

    //method to clear the field
//    public void clickEvents(TextField field){
//        //Creating the mouse event handler
//        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent e) {
//                field.clear();
//            }
//        };
//        field.addEventFilter(MouseEvent.MOUSE_CLICKED,eventHandler);
//    }

    public boolean convertValues(Main_Layout layout){
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
            return false;
        }
        try {
            setInterestRate((Double.parseDouble(layout.getTextInterestRate().getText().trim())));
            System.out.println(getInterestRate());
        } catch (NumberFormatException e){
            alertBox.display("Incorrect Decimal value entered", "Incorrect Decimal value entered for either Escrow and Interest Rate. Please Check");
            e.printStackTrace();
            return false;
        }
        setEscrow((Double.parseDouble(layout.getTextEscrow().getText().trim())));
        System.out.println(getEscrow());
        return true;
    }

//    public int selectionLoanType(){
//        int selection;
//        selection = list.getSelectionModel().getSelectedIndex();
//        return selection;
//    }

    public void submit(Button button, int selection, Main_Layout layout){
        button.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                boolean isNotError;
                isNotError=convertValues(layout);
                switch (selection){
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
