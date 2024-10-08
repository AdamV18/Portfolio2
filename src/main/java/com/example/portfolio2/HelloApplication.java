package com.example.portfolio2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {




        ComboBox<String> c=new ComboBox<String> ();
        c.getItems().add("A");
        c.getItems().add("B");
        c.getItems().add("C");

        Button button1 = new Button("Click me!");
        Button button2 = new Button("and me");

        Label label1 = new Label("some text");
        Label label2 = new Label("and more text");

        button2.setDisable(true);
        label2.setText("changed text");



        TextField textfield =
                new TextField("Hello");
        TextArea textarea= new TextArea(
                "Hello\nover\nseveral lines");

        textfield.setText("Changed");
        String txt=textarea.getText();

        VBox box = new VBox(c, button1, button2, label1, label2, textfield, textarea);



        Scene scene = new Scene(box, 500, 500);
        stage.setTitle("My JavaFX program");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}


