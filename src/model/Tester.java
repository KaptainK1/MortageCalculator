package model;

import javafx.application.Application;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class Tester extends Application {
    public static void main (String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        DecimalFormat format = new DecimalFormat("$0.00");
        double test = 122147.381260433;
        format.format(test);
        String string = String.valueOf(test);

        DisplayBox box = new DisplayBox();

        box.display("Test", format.format(test),DisplayBox.class.getResource("main_style.css").toExternalForm());
    }
}
