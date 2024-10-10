package com.example.portfolio2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class HelloApplication extends Application {

    private ModelOld model = new ModelOld();
    private Controller con = new Controller(model, this);
    private TextField field = new TextField();
    private TextArea area = new TextArea();
    private Button delete = new Button("Delete Last Entry");
    private Button deleteAll = new Button("Delete All Entries");


    void setArea(String s) {
        area.setText(s);
    }

    void clearField() {
        field.setText("");
    }

    @Override
    public void start(Stage stage) throws IOException {
        field.setOnAction(e -> con.enterText(field.getText()));

        HBox hbox = new HBox(10); // 10 Pixel Abstand zwischen den Buttons
        hbox.getChildren().addAll(delete, deleteAll);
        hbox.setAlignment(Pos.CENTER);
        delete.setOnAction(e -> con.deleteLastEntry());
        deleteAll.setOnAction(e -> con.deleteAllEntries());

        VBox root = new VBox(field, area, hbox);
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("JavaFX Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    Button createButton  (String s){
        Button b = new Button(s);
        b.setPrefSize(200,20);
        b.setStyle("-fx-font:18 Arial;");
        return b;
    }


}


class ModelOld {

    ArrayList<String> list = new ArrayList<String>();
    void add(String s){
        list.add(s);
    }

    ArrayList<String> get(){
        return list;
    }
}


