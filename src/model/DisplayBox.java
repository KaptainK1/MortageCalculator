package model;

import com.sun.javafx.css.Stylesheet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class DisplayBox {

    //stylesheet must be in the format DisplayBox.class.getResource("main_style.css").toExternalForm()
    public void display(String strTitle, String strMessage, String stylesheet){

        Stage window = new Stage();
        window.setTitle(strTitle);
        window.setMinWidth(600);
        window.setMinHeight(400);

        Button closeButton = new Button("OKAY");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
            }
        });

        BorderPane border = new BorderPane();
        VBox vbox = new VBox();
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setText(strMessage);
        vbox.getChildren().addAll(textArea);
        vbox.getStyleClass().add("vbox");

        border.setCenter(vbox);
        border.setBottom(closeButton);
        Scene scene = new Scene(border);
        scene.getStylesheets().add(stylesheet);
        window.setScene(scene);
        window.showAndWait();

    }
}
