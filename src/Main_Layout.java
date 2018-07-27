import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

public class Main_Layout {
    private Stage window = new Stage();
    private Scene mainScene;
    private Button submitButton;
    private TextField textPurchasePrice;
    private TextField textTermMonths;
    private TextField textDownPayment;
    private TextField textEscrow;
    private TextField textInterestRate;
    private TextField textCreditScore;
    private AlertBox alertBox = new AlertBox();
    private ListView<String> list = new ListView<String>();
    private int loanSelection;

    public Stage createMainLayout(){
//        Stage window = new Stage();
        //create the button
        submitButton = new Button();
        submitButton.setText("Submit");
        //run the submit function when the button is clicked
//        submit(submitButton);

        //create labels
        Label labelPurchasePrice = new Label("Enter Purchase Price: ");
        Label labelTermMonths = new Label("Enter Term Months: ");
        Label labelDownPayment = new Label("Enter Down Payment: ");
        Label labelCreditScore = new Label("Enter Credit Score: ");
        Label labelEscrow = new Label("Enter Escrow: ");
        Label labelInterestRate = new Label("Enter Interest Rate: ");
        Label labelLoanType = new Label("Select the Loan Type");

        //create list for loan objects
        ObservableList<String> items =FXCollections.observableArrayList (
                "Conventional", "FHA", "VA", "Car");
        list.setItems(items);
        list.setOrientation(Orientation.HORIZONTAL);

        //create all text fields
        textPurchasePrice = new TextField();
        textTermMonths = new TextField();
        textDownPayment = new TextField();
        textCreditScore = new TextField();
        textEscrow = new TextField();
        textInterestRate = new TextField();

        textPurchasePrice.setText("172000");
        textTermMonths.setText("360");
        textDownPayment.setText("6880");
        textCreditScore.setText("750");
        textEscrow.setText("200");
        textInterestRate.setText("4.5");

        //add the method of click events for all text fields
        clickEvents(textCreditScore);
        clickEvents(textDownPayment);
        clickEvents(textEscrow);
        clickEvents(textInterestRate);
        clickEvents(textPurchasePrice);
        clickEvents(textTermMonths);

        //create the layout using vbox
        VBox layout = new VBox(10);
        //add elements to the layout
        layout.getChildren().addAll(labelPurchasePrice,textPurchasePrice,labelDownPayment,textDownPayment,labelTermMonths,textTermMonths,labelInterestRate,textInterestRate,
                labelEscrow,textEscrow, labelCreditScore,textCreditScore,labelLoanType,list,submitButton);
        //create the scene, then add the scene to the window and show it
        mainScene = new Scene(layout, 400,500);
        window.setScene(mainScene);
        window.sizeToScene();

        return window;
    }


    //method to clear the field
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

    public int selectionLoanType(){
        setLoanSelection(list.getSelectionModel().getSelectedIndex());
        System.out.println(getLoanSelection());
        return getLoanSelection();
    }

    public int getLoanSelection(){
        return loanSelection;
    }

    public void setLoanSelection(int loanSelection){
        this.loanSelection=loanSelection;
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(Button submitButton) {
        this.submitButton = submitButton;
    }

    public TextField getTextPurchasePrice() {
        return textPurchasePrice;
    }

    public void setTextPurchasePrice(TextField textPurchasePrice) {
        this.textPurchasePrice = textPurchasePrice;
    }

    public TextField getTextTermMonths() {
        return textTermMonths;
    }

    public void setTextTermMonths(TextField textTermMonths) {
        this.textTermMonths = textTermMonths;
    }

    public TextField getTextDownPayment() {
        return textDownPayment;
    }

    public void setTextDownPayment(TextField textDownPayment) {
        this.textDownPayment = textDownPayment;
    }

    public TextField getTextEscrow() {
        return textEscrow;
    }

    public void setTextEscrow(TextField textEscrow) {
        this.textEscrow = textEscrow;
    }

    public TextField getTextInterestRate() {
        return textInterestRate;
    }

    public void setTextInterestRate(TextField textInterestRate) {
        this.textInterestRate = textInterestRate;
    }

    public TextField getTextCreditScore() {
        return textCreditScore;
    }

    public void setTextCreditScore(TextField textCreditScore) {
        this.textCreditScore = textCreditScore;
    }

    public AlertBox getAlertBox() {
        return alertBox;
    }

    public void setAlertBox(AlertBox alertBox) {
        this.alertBox = alertBox;
    }

    public ListView<String> getList() {
        return list;
    }

    public void setList(ListView<String> list) {
        this.list = list;
    }


}
