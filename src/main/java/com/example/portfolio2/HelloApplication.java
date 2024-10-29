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

    public ComboBox<String> comboProgram = createCombobox();
    public ComboBox<String> comboSub1 = createCombobox();
    public ComboBox<String> comboSub2 = createCombobox();
    public ComboBox<String> comboBasicCourse = createCombobox();
    public ComboBox<String> comboElectiveCourse = createCombobox();



    public Button addBasicCourse = createButton("Add Course");
    public Button addElectiveCourse = createButton("Add Course");


    public TextArea textAreaBasicCourse = createTextArea();
    public TextArea textAreaSub1 = createTextArea();
    public TextArea textAreaSub2 = createTextArea();
    public TextArea textAreaElectiveCourse = createTextArea();

    public Label crBasic = createLabel("Basic Credits: ");
    public Label crSub1 = createLabel("SubMod 1 Credits: ");
    public Label crSub2 = createLabel("SubMod 2 Credits: ");
    public Label crElective = createLabel("Elective Credits: ");
    public Label crTotal = createLabel("Total Credits: ");

    @Override
    public void start(Stage stage) {

        Label program = createLabel("Program");
        Label subject1 = createLabel("Subject Module Programme");
        Label subject2 = createLabel("Subject Module Free Choice");
        Label basiccourse = createLabel("Choose Basic Courses");
        Label electivecourse = createLabel("Choose Elective Courses");

        //Fill Label, Boxes
        con.fillComboBox();

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

        root.add(comboProgram, 0, 1);
        root.add(comboSub1, 1, 1);
        root.add(comboSub2, 2, 1);



        root.add(basiccourse, 0, 3);
        root.add(electivecourse, 3, 3);

        root.add(comboBasicCourse, 0, 4);
        root.add(comboElectiveCourse, 3, 4);

        root.add(addBasicCourse, 0, 5);
        root.add(addElectiveCourse, 3, 5);

        root.add(textAreaBasicCourse, 0, 6);
        root.add(textAreaSub1, 1, 6);
        root.add(textAreaSub2, 2, 6);
        root.add(textAreaElectiveCourse, 3, 6);

        root.add(crBasic, 0, 7);
        root.add(crSub1, 1, 7);
        root.add(crSub2, 2, 7);
        root.add(crElective, 3, 7);
        root.add(crTotal, 0, 8);

        // Set scene size to a more reasonable size
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Choose your fighter !");
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
        textArea.setEditable(false);

        return textArea;
    }
}
