package model;

import Layouts.Main_Layout;
import javafx.application.Application;
import javafx.stage.Stage;

public class Tester extends Application {
    public static void main (String args[]){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

//        DecimalFormat format = new DecimalFormat("$0.00");
//        double test = 122147.381260433;
//        format.format(test);
//        String string = String.valueOf(test);

        Main_Layout box = new Main_Layout();
        primaryStage = box.createMainLayout("main_form_style.css");
        primaryStage.show();

    }
}
