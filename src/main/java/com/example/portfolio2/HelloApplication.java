package com.example.portfolio2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.layout.*;

public class HelloApplication extends Application {

    private Model model = new Model();
    private Controller con = new Controller(model, this);

    private final BasicCreator basic = new BasicCreator();

    private final ModuleCreator subject1 = new ModuleCreator("1");
    private final ModuleCreator subject2 = new ModuleCreator("2");
    private final ElectiveCreator elective = new ElectiveCreator();

    private final CourseCreator basicCourses = new CourseCreator();
    private final CourseCreator subject1Courses = new CourseCreator();
    private final CourseCreator subject2Courses = new CourseCreator();
    private final CourseCreator elctiveCourses = new CourseCreator();

    /*
    void setArea(String s) {
        area.setText(s);
    }

    void clearField() {
        field.setText("");
    }*/

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

        GridPane root = new GridPane();
        root.add(basic.getBox(), 0, 0);
        root.add(subject1.getBox(), 1, 0);
        root.add(subject2.getBox(), 2, 0);
        root.add(elective.getBox(), 3, 0);
        root.add(basicCourses.getBox(), 0, 1);
        root.add(subject1Courses.getBox(), 1, 1);
        root.add(subject2Courses.getBox(), 2, 1);
        root.add(elctiveCourses.getBox(), 3, 1);


        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("JavaFX Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}





