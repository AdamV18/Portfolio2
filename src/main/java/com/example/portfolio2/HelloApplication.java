package com.example.portfolio2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private Model model = new Model();
    private Controller con = new Controller(model, this);

    @Override
    public void start(Stage stage) {

        Label program = createLabel("Program");
        Label subject1 = createLabel("Subject 1");
        Label subject2 = createLabel("Subject 2");
        Label elective = createLabel("Elective");
        Label course1 = createLabel("Choose Course");
        Label course2 = createLabel("Choose Course");

        ComboBox<String> comboProgram = createCombobox();
        ComboBox<String> comboSub1 = createCombobox();
        ComboBox<String> comboSub2 = createCombobox();
        ComboBox<String> comboElective = createCombobox();
        ComboBox<String> comboBasicCourse = createCombobox();

        Button buttonProgram = createButton("Select");
        Button buttonSub1 = createButton("Select");
        Button buttonSub2 = createButton("Select");
        Button addProgram = createButton("Add Course");
        Button addElective = createButton("Add Course");

        TextArea textAreaProgram = createTextArea();
        TextArea textAreaSub1 = createTextArea();
        TextArea textAreaSub2 = createTextArea();
        TextArea textAreaElective = createTextArea();

        Label crProgram = createLabel("Credits: ");
        Label crSub1 = createLabel("Credits: ");
        Label crSub2 = createLabel("Credits: ");
        Label crElective = createLabel("Credits: ");

        // Set up GridPane layout
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        // Add components to the GridPane
        root.add(program, 0, 0);
        root.add(subject1, 1, 0);
        root.add(subject2, 2, 0);
        root.add(elective, 3, 0);

        root.add(comboProgram, 0, 1);
        root.add(comboSub1, 1, 1);
        root.add(comboSub2, 2, 1);
        //root.add(comboElective, 3, 1);

        root.add(buttonProgram, 0, 2);
        root.add(buttonSub1, 1, 2);
        root.add(buttonSub2, 2, 2);
        //root.add(addElective, 3, 2);

        root.add(course1, 0, 3);
        root.add(course2, 3, 3);

        root.add(comboBasicCourse, 0, 4);
        root.add(comboElective, 3, 4);

        root.add(addProgram, 0, 5);
        root.add(addElective, 3, 5);

        root.add(textAreaProgram, 0, 6);
        root.add(textAreaSub1, 1, 6);
        root.add(textAreaSub2, 2, 6);
        root.add(textAreaElective, 3, 6);

        root.add(crProgram, 0, 7);
        root.add(crSub1, 1, 7);
        root.add(crSub2, 2, 7);
        root.add(crElective, 3, 7);

        // Set scene size to a more reasonable size
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("JavaFX Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Utility methods for creating components
    Label createLabel(String text) {
        Label label = new Label(text);
        label.setPrefSize(200, 20);
        label.setStyle("-fx-font-size: 14px;");
        return label;
    }

    Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(200, 20);
        return button;
    }

    ComboBox<String> createCombobox() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPrefSize(200, 20);
        return comboBox;
    }

    TextArea createTextArea() {
        TextArea textArea = new TextArea();
        textArea.setPrefSize(200, 300);
        return textArea;
    }
}
