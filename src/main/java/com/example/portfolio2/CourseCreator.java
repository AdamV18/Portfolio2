package com.example.portfolio2;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class CourseCreator {

    public VBox box;

    CourseCreator() {
        VBox content = new VBox(10);

        TextArea coursesList= new TextArea();
        Label credits = new Label("Credits");

        content.getChildren().addAll(coursesList,credits);
        content.setAlignment(Pos.CENTER);

        this.box = content;
    }

    public VBox getBox() {
        return box;
    }
}
