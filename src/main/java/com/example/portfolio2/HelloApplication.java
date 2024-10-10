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
import org.controlsfx.control.spreadsheet.Grid;


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

        /*
        field.setOnAction(e -> con.enterText(field.getText()));

        HBox hbox = new HBox(10); // 10 Pixel Abstand zwischen den Buttons
        hbox.getChildren().addAll(delete, deleteAll);
        hbox.setAlignment(Pos.CENTER);
        delete.setOnAction(e -> con.deleteLastEntry());
        deleteAll.setOnAction(e -> con.deleteAllEntries());
        */


        VBox basic = Creator.createBasic();
        VBox subject1 = Creator.createModule("1");
        VBox subject2 = Creator.createModule("2");
        VBox elective = Creator.createElective();

        VBox basicCourses = Creator.createCourseOverview();
        VBox subject1Courses = Creator.createCourseOverview();
        VBox subject2Courses = Creator.createCourseOverview();
        VBox elctiveCourses = Creator.createCourseOverview();

        GridPane root = new GridPane();
        root.add(basic, 0, 0);
        root.add(subject1, 1, 0);
        root.add(subject2, 2, 0);
        root.add(elective, 3, 0);
        root.add(basicCourses, 0, 1);
        root.add(subject1Courses, 1, 1);
        root.add(subject2Courses, 2, 1);
        root.add(elctiveCourses, 3, 1);


        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("JavaFX Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*Button createButton  (String s){
        Button b = new Button(s);
        b.setPrefSize(200,20);
        b.setStyle("-fx-font:18 Arial;");
        return b;
    }*/


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

class Creator {
    static VBox createModule(String number){
        VBox content = new VBox(10);
        Label tf = new Label("Subject " + number);

        ComboBox<String> possibleSubjects = new ComboBox<>();
        possibleSubjects.getItems().addAll("A","B","C");
        Button confirm = new Button("Confirm");

        content.getChildren().addAll(tf,possibleSubjects, confirm);
        //content.setAlignment(Pos.CENTER);

        return content;

    }

    static VBox createBasic(){
        VBox content = new VBox(10);

        Label tf = new Label("Program");

        ComboBox<String> programs = new ComboBox<>();
        programs.getItems().addAll("A","B","C");
        Button confirm = new Button("Select");

        Label choiceCourses = new Label("Choose Courses:");

        ComboBox<String> courses = new ComboBox<>();
        courses.getItems().addAll("A","B","C");
        Button add = new Button("Add Course");

        content.getChildren().addAll(tf,programs,confirm,choiceCourses,courses,add);

        return content;

    }

    static VBox createElective(){
        VBox content = new VBox(10);

        Label tf = new Label("Elective");

        ComboBox<String> courses = new ComboBox<>();
        courses.getItems().addAll("A","B","C");

        Button confirm = new Button("Add");

        content.getChildren().addAll(tf,courses,confirm);

        return content;

    }


    static VBox createCourseOverview(){
        VBox content = new VBox(10);

        TextArea coursesList= new TextArea();
        Label credits = new Label("Credits");

        content.getChildren().addAll(coursesList,credits);
        content.setAlignment(Pos.CENTER);

        return content;

    }


}


