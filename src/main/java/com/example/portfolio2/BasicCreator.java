package com.example.portfolio2;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class BasicCreator {

    public VBox box;

    public BasicCreator() {
        VBox content = new VBox(10);

        Label tf = new Label("Program");
        ComboBox<String> programs = new ComboBox<>();
        Button confirm = new Button("Select");
        Label choiceCourses = new Label("Choose Courses:");
        ComboBox<String> courses = new ComboBox<>();
        Button add = new Button("Add Course");

        content.getChildren().addAll(tf, programs, confirm, choiceCourses, courses, add);

        this.box = content;
    }

    public VBox getBox() {
        return box;
    }


}
