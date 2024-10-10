package com.example.portfolio2;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ModuleCreator {

    public VBox box;

    public ModuleCreator(String number) {
        VBox content = new VBox(10);
        Label tf = new Label("Subject " + number);

        ComboBox<String> possibleSubjects = new ComboBox<>();
        Button confirm = new Button("Confirm");

        content.getChildren().addAll(tf, possibleSubjects, confirm);

        this.box = content;
    }

    public VBox getBox() {
        return box;
    }

}
