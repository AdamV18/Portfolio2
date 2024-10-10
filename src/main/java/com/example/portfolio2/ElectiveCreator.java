
package com.example.portfolio2;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ElectiveCreator {

    public VBox box;

    ElectiveCreator(){

        VBox content = new VBox(10);

        Label tf = new Label("Elective");
        ComboBox<String> courses = new ComboBox<>();
        Button confirm = new Button("Add");

        content.getChildren().addAll(tf, courses, confirm);

        this.box = content;
    }

    public VBox getBox() {
        return box;
    }

}
