package com.example.portfolio2;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;

public class Controller {
    private Model model;
    private HelloApplication view;

    Controller(Model model, HelloApplication view) {
        this.model = model;
        this.view = view;
    }

    public void fillComboBox() {
        view.comboProgram.getItems().addAll(model.baseProgram());
        view.comboSub1.getItems().addAll(model.subjectModule());
        view.comboSub2.getItems().addAll(model.subjectModule());
        view.comboElectiveCourse.getItems().addAll(model.electiveCourse());

        view.comboProgram.setOnAction(event -> {
            String selectedBase = view.comboProgram.getValue();
            resetSelections();
            updateComboBasicCourse(selectedBase);
        });

        view.addBasicCourse.setOnAction(event -> {
            String selectedCourse = view.comboBasicCourse.getValue();
            if (selectedCourse != null) {
                addSelectedCourseToTextAreaProgram(selectedCourse); // Methode um Kurs in das TextArea zu schreiben
                view.comboBasicCourse.getItems().remove(selectedCourse); // Entfernt den ausgewählten Kurs
            }
        });

        view.comboSub1.setOnAction(event -> {
            String selectedSubject = view.comboSub1.getValue();
            updateTextAreaSub1(selectedSubject);
        });

        view.comboSub2.setOnAction(event -> {
            String selectedSubject = view.comboSub2.getValue();
            updateTextAreaSub2(selectedSubject);
        });

        // Handle selection of elective courses
        view.comboElectiveCourse.setOnAction(event -> {
            String selectedElective = view.comboElectiveCourse.getValue();
            updateTextAreaElective(selectedElective);
        });

        view.addElectiveCourse.setOnAction(event -> {
            String selectedElectiveCourse = view.comboElectiveCourse.getValue();
            if (selectedElectiveCourse != null) {
                addSelectedCourseToTextAreaElective(selectedElectiveCourse); // Add the selected elective course to the text area
                view.comboElectiveCourse.getItems().remove(selectedElectiveCourse); // Remove from combobox after selection
            }
        });
    }

    private void updateComboBasicCourse(String base) {
        view.comboBasicCourse.getItems().clear();
        List<String> baseCourses = model.baseCourse(base);
        if (baseCourses != null) {
            view.comboBasicCourse.getItems().addAll(baseCourses);
        }
    }

    private void addSelectedCourseToTextAreaProgram(String course) {
        view.textAreaBasicCourse.appendText(course + "\n"); // Fügt den ausgewählten Kurs in das TextArea ein
    }




    // Add the update for elective courses
    private void updateTextAreaElective(String elective) {
        view.textAreaElectiveCourse.clear();
        if (elective != null) {
            view.textAreaElectiveCourse.appendText(elective + "\n");
        }
    }

    // Add selected elective course to text area
    private void addSelectedCourseToTextAreaElective(String course) {
        view.textAreaElectiveCourse.appendText(course + "\n");
    }



    private void resetSelections() {
        // Setze alle Auswahlen zurück
        view.textAreaBasicCourse.clear();
        view.comboBasicCourse.getItems().clear();
        view.textAreaSub1.clear();
        view.textAreaSub2.clear();
        view.textAreaElectiveCourse.clear();
        view.comboSub1.getSelectionModel().clearSelection();
        view.comboSub2.getSelectionModel().clearSelection();
        view.comboElectiveCourse.getSelectionModel().clearSelection();
    }

    private void updateTextAreaSub1(String subject) {
        view.textAreaSub1.clear();
        List<String> subjectCourses = model.subjectCourse(subject);
        if (subjectCourses != null) {
            for (String course : subjectCourses) {
                view.textAreaSub1.appendText(course + "\n");
            }
        }
    }

    private void updateTextAreaSub2(String subject) {
        view.textAreaSub2.clear();
        List<String> subjectCourses = model.subjectCourse(subject);
        if (subjectCourses != null) {
            for (String course : subjectCourses) {
                view.textAreaSub2.appendText(course + "\n");
            }
        }
    }


}


